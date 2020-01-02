package src.mua.Operations;

import src.mua.Values.VALUE;
import src.mua.Values.BOOL;
import src.mua.Exception.ParseError;
import src.mua.NameSpace.Space;
import src.mua.Configs.Config;

public class Not extends Operation{

    public Not() {
        arg_num = 1;
        isVoid = false;
    }

    @Override
    public void exec(Space space) throws ParseError {
        VALUE x = args.get(0);
        if (x.type == Config.BOOL_TYPE){
            boolean tmp = !x.tobool();
            ret = new BOOL(tmp);
        } else {
            throw new ParseError();
        }
    }
}
