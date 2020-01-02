package src.mua.Values;

import src.mua.Configs.Config;
import src.mua.Values.VALUE;
import src.mua.Exception.ParseError;;

public class BOOL extends VALUE {
    boolean val;
    
    public BOOL(boolean v) {
        type = Config.BOOL_TYPE;
        val = v;
    }

    public BOOL(String v) throws ParseError {
        type = Config.BOOL_TYPE;
        if (v.equals("true")) val = true;
        else if (v.equals("false")) val = false;
        else throw new ParseError();
    } 

    @Override
    public VALUE copy() {
        BOOL tmp = new BOOL(val);
        return tmp;
    }

    @Override
    public String tostr() {
        if (val) return "true";
        else return "false";
    }

    @Override
    public boolean tobool() {
        return val;
    }

    @Override
    public void print() {
        if (val) System.out.println("true");
        else System.out.println("false");
    }
}
