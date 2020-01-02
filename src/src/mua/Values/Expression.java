package src.mua.Values;

import src.mua.Configs.Config;
import src.mua.NameSpace.Space;

public class Expression extends VALUE {
    String expr;
    public Expression() {
        type = Config.EXPR_TYPE;
        expr = "";
    }

    public Expression(String val) {
        type = Config.EXPR_TYPE;
        expr = val;
    }

    @Override
    public VALUE copy() {
        Expression tmp = new Expression(expr);
        return tmp;
    }

    @Override
    public void print() {
        System.out.println(expr);
    }

    @Override
    public String tostr() { return expr; }

}
