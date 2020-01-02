package src.mua.Operations;

import src.mua.Values.VALUE;
import src.mua.Values.BOOL;
import src.mua.Exception.ParseError;
import src.mua.NameSpace.Space;
import src.mua.Configs.Config;

public class Erase extends Operation{

    public Erase() {
        arg_num = 1;
        isVoid = true;
    }

    @Override
    public void exec(Space space) throws ParseError {
        String x = args.get(0).tostr();
        space.eraseSomewhere(x);
    }
}
