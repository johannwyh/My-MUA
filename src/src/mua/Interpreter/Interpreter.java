package src.mua.Interpreter;

import src.mua.Configs.Config;
import src.mua.NameSpace.Space;

import java.util.ArrayList;
import java.util.Stack;
import src.mua.Operations.Operation;
import src.mua.Operations.Function;
import src.mua.Operations.OperationList;
import src.mua.Values.*;
import src.mua.Exception.*;
import src.mua.Utils.Utils;

public class Interpreter {
    public Space space;
    public Stack<Operation> opts;
    public int code_type;

    public Interpreter() {
        space = new Space();
        opts = new Stack<Operation>();
        code_type = Config.FUNCTION;
    }

    public Interpreter(Space space, int _type) {
        this.space = space;
        opts = new Stack<Operation>();
        code_type = _type;
    }

    public void clear() {
        space.clear();
        opts.clear();
    }

    private void tryexec() {
        while(!opts.empty() && opts.peek().ready()) {
            Operation cur = opts.pop();
            try {
                cur.exec(space);
            } catch (ParseError pe) {
                System.out.println(pe.getMessage());
            } catch (RuntimeError re) {
                System.out.println(re.getMessage());
            }
            if (!cur.isVoid) {
                if (opts.empty()) {
                    if (code_type == Config.EXPRESSION)
                        space.RET = cur.ret;
                    break;
                }
                else opts.peek().push_back(cur.ret);
            }
        }
    }

    public void pushOpt(Operation opt) {
        opts.push(opt);
        tryexec();
    }

    public void pushArg(VALUE arg) {
        if (opts.empty()) return;
        opts.peek().push_back(arg);
        tryexec();
    }

    public boolean isEmpty() {
        return opts.isEmpty();
    }

    public int isOp(char c) {
        if (c == '+' || c == '-') return 1;
        else if (c == '*' || c == '/' || c == '%') return 2;
        else return 0;
    }

    public VALUE compute(char op, VALUE a, VALUE b) throws ParseError {
        if (op == '+')
            return VALUE.plus(a, b);
        else if (op == '-')
            return VALUE.sub(a, b);
        else if (op == '*')
            return VALUE.mul(a, b);
        else if (op == '/')
            return VALUE.div(a, b);
        else if (op == '%')
            return VALUE.mod(a, b);
        else throw new ParseError("Operator " + op + " not Found.");
    }

    public VALUE calcExpr(String expr) {
        String orig_expr = expr, new_expr = "";
        int len = orig_expr.length();
        int stk[] = new int[len + 1], lnk[] = new int[len + 1], top = 0;
        for (int i = 1; i < len - 1; i++) {
            if (orig_expr.charAt(i) == '(')
                stk[++top] = i;
            if (orig_expr.charAt(i) == ')') {
                lnk[i] = stk[top];
                lnk[stk[top]] = i;
                top--;
            }
        }
        for(int i = 1; i < len - 1; i++) {
            if (orig_expr.charAt(i) == '(') {
                int L = i, R = lnk[i];
                String sub_expr = orig_expr.substring(L, R + 1);
                VALUE ret = calcExpr(sub_expr);
                new_expr += " " + ret.tostr();
                i = R;
            } else {
                new_expr += orig_expr.charAt(i);
            }
        }

        len = new_expr.length();
        orig_expr = new_expr; new_expr = "";
        boolean afterOp = true;
        for(int i = 0; i < len; i++) {
            if (Utils.is_blank(orig_expr.charAt(i)) && afterOp)
                continue;
            new_expr += orig_expr.charAt(i);
            if (isOp(orig_expr.charAt(i)) > 0)
                afterOp = true;
            else if (!Utils.is_blank(orig_expr.charAt(i)))
                afterOp = false;
        }

        expr = new_expr;
        len = expr.length();
        boolean negOp[] = new boolean[len + 10];
        for(int i = 0; i < len; i++) negOp[i] = false;
        afterOp = true;
        for(int i = 0; i < len; i++) {
            if (Utils.is_blank(expr.charAt(i))) continue;
            if (isOp(expr.charAt(i)) > 0) {
                if (afterOp) negOp[i] = true;
                afterOp = true;
            } else {
                afterOp = false;
            }
        }

        int pms[] = new int[len + 10], n_pm = 0; // plus/minus s
        for(int i = 0; i < len; i++) {
            if (isOp(expr.charAt(i)) == 1 && !negOp[i])
                pms[++n_pm] = i;
        }

        VALUE ans = new NUM(0);
        pms[n_pm + 1] = len;

        for(int i = 0; i <= n_pm; i++) {
            int l1 = (i == 0 ? 0 : pms[i] + 1);
            int r1 = (i < n_pm ? pms[i + 1] - 1 : len - 1);
            char op1 = (i == 0 ? '+' : expr.charAt(pms[i]));

            VALUE tmp = new NUM(1);
            int mdms[] = new int[len + 10], n_mdm = 0; // mul/div/mod s
            for(int j = l1; j <= r1; j++) {
                if (isOp(expr.charAt(j)) == 2) mdms[++n_mdm] = j;
            }
            mdms[n_mdm + 1] = r1 + 1;
            for (int j = 0; j <= n_mdm; j++) {
                int l2 = (j == 0 ? l1 : mdms[j] + 1);
                int r2 = (j < n_mdm ? mdms[j + 1] - 1 : r1);
                char op2 = (j == 0 ? '*' : expr.charAt(mdms[j]));
                float sgn = 1;
                if (expr.charAt(l2) == '-') {
                    sgn = -1;
                    l2++;
                }

                VALUE operand = new NUM(0);
                try {
                    operand = new NUM(Float.parseFloat(expr.substring(l2, r2 + 1)));
                } catch (Exception e) {
                    String code = "[" + expr.substring(l2, r2 + 1) + "]";
                    Interpreter I = new Interpreter(space, Config.EXPRESSION);
                    I.executeList(LIST.getListFromStr(code));
                    operand = I.space.RET;
                }

                if (sgn == -1) operand = VALUE.mul(operand, new NUM(-1));
                try {
                    tmp = compute(op2, tmp, operand);
                } catch (ParseError pe) {
                    pe.printStackTrace();
                }
            }

            ans = compute(op1, ans, tmp);
        }

        return ans;
    }

    public void executeList(VALUE code_list) {
        space.setStopped(false);
        space.setRET(null);
        /*
        if (space.getHere("max") != null)
            System.out.println("this space: " + space.getHere("max").tostr());
        if (space.getSomewhere("max") != null)
            System.out.println("all space" + space.getSomewhere("max").tostr());
        */
        if (code_list.type != Config.LIST_TYPE) {
            System.out.println("** Fatal Error, code is not a list.");
            return;
        }

        ArrayList<VALUE> code = code_list.getVal();
        //System.out.println(code.size());
        for(int i = 0; i < code.size(); i++) {
            String tmp = code.get(i).tostr();
            // System.out.println("** " + tmp);
            char firstChar = tmp.charAt(0);
            if (Utils.is_blank(firstChar)) continue;
            if (code.get(i).type == Config.LIST_TYPE) {
                pushArg(code.get(i));
            } else if (code.get(i).type == Config.EXPR_TYPE) {
                String expr = code.get(i).tostr();
                VALUE ret = calcExpr(expr);
                pushArg(ret);
            } else if (firstChar == ':') {
                Operation opt = OperationList.get("thing");
                WORD arg = new WORD(tmp.substring(1));
                pushOpt(opt);
                pushArg(arg);
            } else if (firstChar == '"') {
                WORD arg = new WORD(tmp.substring(1));
                pushArg(arg);
            } else if (tmp.equals("true") || tmp.equals("false")) {
                BOOL arg = new BOOL(tmp);
                pushArg(arg);
            } else if (Character.isDigit(firstChar) || firstChar == '-') {
                NUM arg = new NUM(tmp);
                pushArg(arg);
            } else if (Character.isLetter(firstChar)) {
                Operation opt = OperationList.get(tmp);
                //System.out.println(opt == null);
                if (opt != null) {
                    pushOpt(opt);
                } else {
                    opt = new Function(space, tmp);
                    pushOpt(opt);
                }
            } else {
                System.out.println("** Character Error Happens, Code cannot be parsed.");
            }
            if (space.isStopped()) break;
        }
        /*
        if (space.getHere("max") != null)
            System.out.println("this space: " + space.getHere("max").tostr());
        if (space.getSomewhere("max") != null)
            System.out.println("all space" + space.getSomewhere("max").tostr());
         */
        if (code_type == Config.FUNCTION)
            space.setStopped(true);
    }

    public void debug() {
        space.debug();
    }
}