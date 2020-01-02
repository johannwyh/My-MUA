package src.mua.Operations;

import src.mua.Values.VALUE;
import src.mua.Configs.Config;
import src.mua.Values.NUM;
import src.mua.Exception.ParseError;
import src.mua.NameSpace.Space;

public class Add extends Operation{

    public Add() {
        arg_num = 2;
        isVoid = false;
    }

    @Override
    public void exec(Space space) throws ParseError {
        VALUE x = args.get(0), y = args.get(1);
        if (x.type == Config.NUM_TYPE || y.type == Config.NUM_TYPE) {
            ret = new NUM(x.tonum() + y.tonum());
        } else {
            throw new ParseError();
        }
    }
}
