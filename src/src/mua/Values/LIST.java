package src.mua.Values;

import java.util.ArrayList;

import src.mua.Configs.Config;
import src.mua.Values.VALUE;
import src.mua.Exception.ParseError;
import src.mua.Utils.Utils;

public class LIST extends VALUE {
    public ArrayList<VALUE> val;

    public LIST() {
        type = Config.LIST_TYPE;
        val = new ArrayList<VALUE>();
    }

    public LIST(String s) {
        type = Config.LIST_TYPE;
        System.out.println("RECURSION REQUIRED : Please use getListFromStr to generate a list from string.");
    }

    public LIST(ArrayList<VALUE> v) {
        type = Config.LIST_TYPE;
        val = v;
    }

    @Override
    public VALUE copy() {
        LIST tmp = new LIST();
        for(int i = 0; i < this.val.size(); i++) {
            tmp.val.add(this.val.get(i).copy());
        }
        return tmp;
    }

    @Override
    public String tostr() {
        StringBuffer s = new StringBuffer();
        s.append("[ ");
        for(int i = 0; i < val.size(); i++) {
            s.append(val.get(i).tostr()).append(" ");
        }
        s.append("]");

        String ret = s.toString();
        ret = ret.replace("[ ", "[");
        ret = ret.replace(" ]", "]");
        return ret;
    }

    @Override
    public ArrayList<VALUE> getVal() { return val; }

    @Override
    public void listAdd(VALUE v) {
        this.val.add(v);
    }

    public void print() {
        System.out.println(this.tostr());
    }

    public static int findMatchBracket(String s, int p) {
        if (s.charAt(p) != '[' && s.charAt(p) != '(' && s.charAt(p) != '{') {
            System.out.println("** Where is the bracket ??");
            return -1;
        }
        int cnt = 1;
        char left, right;
        left = s.charAt(p);
        if (left == '(') right = ')';
        else if (left == '[') right = ']';
        else right = '}';

        // Assuming that there is no comment line anymore
        for(int i = p + 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == left)
                cnt++;
            else if (c == right)
                cnt--;
            if (cnt == 0)
                return i;
        }
        System.out.println("** Bracket dost not match, Please check. ");
        return -1;
    }

    public static VALUE getListFromStr(String s) throws ParseError {
        int len = s.length();
        if (s.equals("") || s.charAt(0) != '[' || s.charAt(len-1) != ']')
            throw new ParseError("Not a List : " + s);
        ArrayList<VALUE> val = new ArrayList<>();
        for(int i = 1; i < len - 1; i++) {
            char c = s.charAt(i);
            if (Utils.is_blank(c)) continue;
            if (c == '(') {
                int j = findMatchBracket(s, i);
                if (j == -1) return null;
                val.add(new Expression(s.substring(i, j + 1)));
                i = j;
            } else if (c == '[') {
                int j = findMatchBracket(s, i);
                if (j == -1) return null;
                val.add(getListFromStr(s.substring(i, j + 1)));
                i = j;
            } else {
                int j = i;
                for(; (j < len - 1) && (!Utils.is_blank(s.charAt(j))); j++);
                val.add(new WORD(s.substring(i, j)));
                i = j;
            }
        }
        return new LIST(val);
    }
}