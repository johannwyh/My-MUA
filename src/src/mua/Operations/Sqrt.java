package src.mua.Operations;

import src.mua.Exception.ParseError;
import src.mua.Exception.RuntimeError;
import src.mua.NameSpace.Space;
import src.mua.Values.NUM;
import src.mua.Values.VALUE;

public class Sqrt extends Operation {
    public Sqrt() {
        isVoid = false;
        arg_num = 1;
    }

    @Override
    public void exec(Space space) throws ParseError, RuntimeError {
        VALUE x = args.get(0);
        double v = x.tonum();
        if (v < 0) throw new RuntimeError("*** Operand should be greater than zero.");
        ret = new NUM(Math.sqrt(v));
    }
}
