package src.mua.Operations;

import src.mua.Configs.Config;
import src.mua.NameSpace.Space;
import src.mua.Values.BOOL;

public class IsWord extends Operation {
    public IsWord() {
        isVoid = false;
        arg_num = 1;
    }

    public void exec(Space space) {
        ret = new BOOL(args.get(0).type == Config.WORD_TYPE);
    }
}
