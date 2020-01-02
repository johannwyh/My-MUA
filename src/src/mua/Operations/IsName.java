package src.mua.Operations;

import src.mua.Values.VALUE;
import src.mua.Values.BOOL;
import src.mua.Exception.ParseError;
import src.mua.NameSpace.Space;
import src.mua.Configs.Config;

public class IsName extends Operation{

    public IsName() {
        arg_num = 1;
        isVoid = false;
    }

    @Override
    public void exec(Space space) throws ParseError {
        VALUE x = args.get(0);
        if (x.type != Config.WORD_TYPE) {
            ret = new BOOL(false);
        } else {
            ret = new BOOL(space.existsSomewhere(x.tostr()));
        }
    }
}
