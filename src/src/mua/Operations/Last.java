package src.mua.Operations;

import src.mua.Configs.Config;
import src.mua.Exception.ParseError;
import src.mua.NameSpace.Space;
import src.mua.Values.VALUE;
import src.mua.Values.WORD;

public class Last extends Operation {
    public Last() {
        isVoid = false;
        arg_num = 1;
    }

    @Override
    public void exec(Space space) {
        VALUE x = args.get(0);
        if (x.type == Config.LIST_TYPE) {
            int sz = x.getVal().size();
            ret = x.getVal().get(sz - 1).copy();
        } else {
            int len = x.tostr().length();
            ret = new WORD(x.tostr().substring(len - 1));
        }
    }
}
