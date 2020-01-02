package src.mua.Values;

import src.mua.Configs.Config;
import src.mua.Exception.ParseError;
import src.mua.Values.VALUE;

public class WORD extends VALUE {
    private String val;

    public WORD(String v) {
        type = Config.WORD_TYPE;
        val = v;
    }

    @Override
    public VALUE copy() {
        WORD tmp = new WORD(val);
        return tmp;
    }

    @Override
    public String tostr() {
        return val;
    }

    @Override
    public double tonum() {
        return Double.parseDouble(val);
    }

    @Override
    public boolean tobool() throws ParseError {
        if (val.equals("true")) return true;
        else if (val.equals("false")) return false;
        else throw new ParseError();
    }

    @Override
    public void print() {
        System.out.println(val);
    }
}