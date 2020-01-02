package src.mua.Operations;

import src.mua.Values.VALUE;
import src.mua.Exception.ParseError;
import java.util.ArrayList;
import src.mua.NameSpace.Space;

public class Operation {
    protected ArrayList<VALUE> args;
    public boolean isVoid;
    public VALUE ret;
    protected int arg_num;

    public Operation() {
        ret = null;
        args = new ArrayList<VALUE>();
    }

    void clear() {
        ret = null;
        args.clear();
    }

    public boolean ready() {
        return args.size() == arg_num;
    }

    public void push_back(VALUE arg) {
        args.add(arg);
    }

    public void exec(Space space) throws ParseError {
        throw new ParseError();
    }

    public void debug() {
        System.out.println("arg num = " + arg_num);
    }
}