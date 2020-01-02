package src.mua.Values;

import src.mua.Configs.*;
import src.mua.Values.VALUE;

public class NUM extends VALUE {
    public double val;

    public NUM(double v) {
        type = Config.NUM_TYPE;
        val = v;
    }

    public NUM(String v) {
        type = Config.NUM_TYPE;
        val = Double.valueOf(v);
    }

    @Override
    public VALUE copy() {
        NUM tmp = new NUM(val);
        return tmp;
    }

    @Override
    public void print() {
        System.out.println(val);
    }

    @Override
    public String tostr() {
        return Double.toString(val);
    }
    
    @Override
    public double tonum() {
        return val;
    }
}