package src.mua.Operations;

import src.mua.Configs.Config;
import src.mua.NameSpace.Space;
import src.mua.Values.BOOL;

public class IsList extends Operation {
    public IsList() {
        isVoid = false;
        arg_num = 1;
    }

    public void exec(Space space) {
        ret = new BOOL(args.get(0).type == Config.LIST_TYPE);
    }
}
