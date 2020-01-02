package src.mua.Operations;

import src.mua.Configs.Config;
import src.mua.Exception.ParseError;
import src.mua.NameSpace.Space;
import src.mua.Values.BOOL;
import src.mua.Values.VALUE;

public class IsEmpty extends Operation {
    public IsEmpty() {
        isVoid = false;
        arg_num = 1;
    }
    public void exec(Space space) throws ParseError {
        VALUE tmp = args.get(0);
        if (tmp.type == Config.WORD_TYPE)
            ret = new BOOL(tmp.tostr().isEmpty());
        else if (tmp.type == Config.LIST_TYPE)
            ret = new BOOL(tmp.getVal().isEmpty());
        else
            throw new ParseError("** Cannot check whether empty");
    }
}
