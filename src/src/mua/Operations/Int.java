package src.mua.Operations;

import src.mua.Exception.ParseError;
import src.mua.NameSpace.Space;
import src.mua.Values.NUM;
import src.mua.Values.VALUE;

public class Int extends Operation {
    public Int() {
        isVoid = false;
        arg_num = 1;
    }

    @Override
    public void exec(Space space) throws ParseError {
        VALUE x = args.get(0);
        int num = (int)Math.floor(x.tonum());
        ret = new NUM(num);
    }
}
