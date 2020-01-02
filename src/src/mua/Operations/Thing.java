package src.mua.Operations;

import src.mua.Values.VALUE;
import src.mua.Values.BOOL;
import src.mua.Exception.ParseError;
import src.mua.NameSpace.Space;
import src.mua.Configs.Config;

public class Thing extends Operation{

    public Thing() {
        arg_num = 1;
        isVoid = false;
    }

    @Override
    public void exec(Space space) throws ParseError {
        String x = args.get(0).tostr();
        if (space.existsSomewhere(x))
            ret = space.getSomewhere(x);
        else
            throw new ParseError("** Word Do not exist");
    }
}
