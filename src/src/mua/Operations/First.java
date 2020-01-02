package src.mua.Operations;

import src.mua.Configs.Config;
import src.mua.Exception.ParseError;
import src.mua.NameSpace.Space;
import src.mua.Values.VALUE;
import src.mua.Values.WORD;

public class First extends Operation {
    public First() {
        isVoid = false;
        arg_num = 1;
    }

    @Override
    public void exec(Space space) {
        VALUE x = args.get(0);
        if (x.type == Config.LIST_TYPE)
            ret = x.getVal().get(0).copy();
        else
            ret = new WORD(x.tostr().substring(0, 1));
    }
}
