package src.mua.Values;

import src.mua.Exception.ParseError;
import src.mua.Configs.Config;

import java.util.ArrayList;

public class VALUE {
    public int type;

    public VALUE() {

    }

    public VALUE copy() {
        VALUE tmp = new VALUE();
        tmp.type = this.type;
        return tmp;
    }

    public String tostr() { 
        return "";
    }

    public boolean tobool() throws ParseError {
        throw new ParseError();
    }
    
    public double tonum() throws ParseError {
        throw new ParseError();
    }

    public ArrayList<VALUE> getVal() throws ParseError {
        throw new ParseError("*** Not a List");
    }

    public void listAdd(VALUE val) throws ParseError {
        throw new ParseError("*** Not a List");
    }
    public void print() {

    }

    public static VALUE plus(VALUE a, VALUE b) throws ParseError {
        if (a.type != b.type) throw new ParseError();
        if (a.type == Config.NUM_TYPE)
            return new NUM(a.tonum() + b.tonum());
        if (a.type == Config.WORD_TYPE)
            return new WORD(a.tostr() + b.tostr());
        throw new ParseError();   
    }

    public static VALUE sub(VALUE a, VALUE b) throws ParseError {
        if (a.type != b.type) throw new ParseError();
        if (a.type == Config.NUM_TYPE)
            return new NUM(a.tonum() - b.tonum());
        throw new ParseError();   
    }

    public static VALUE mul(VALUE a, VALUE b) throws ParseError {
        if (a.type != b.type) throw new ParseError();
        if (a.type == Config.NUM_TYPE)
            return new NUM(a.tonum() * b.tonum());
        throw new ParseError();   
    }

    public static VALUE div(VALUE a, VALUE b) throws ParseError {
        if (a.type != b.type) throw new ParseError();
        if (a.type == Config.NUM_TYPE)
            return new NUM(a.tonum() / b.tonum());
        throw new ParseError();   
    }

    public static VALUE mod(VALUE a, VALUE b) throws ParseError {
        if (a.type != b.type) throw new ParseError();
        if (a.type == Config.NUM_TYPE)
            return new NUM((int)a.tonum() % (int)b.tonum());
        throw new ParseError();   
    }
}
