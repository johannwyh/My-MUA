package src.mua.Operations;

import src.mua.Values.VALUE;
import src.mua.Values.BOOL;
import src.mua.Configs.Config;
import src.mua.Exception.ParseError;
import src.mua.NameSpace.Space;
import src.mua.Values.WORD;

public class Lt extends Operation{

    public Lt() {
        arg_num = 2;
        isVoid = false;
    }

    @Override
    public void exec(Space space) throws ParseError {
        VALUE x = args.get(0), y = args.get(1);
        if (x.type == Config.NUM_TYPE || y.type == Config.NUM_TYPE) {
            boolean tmp = x.tonum() < y.tonum();
            ret = new BOOL(tmp);
        } else if (x.type == Config.BOOL_TYPE || y.type == Config.BOOL_TYPE) {
            boolean tmp = !x.tobool() && y.tobool();
            ret = new BOOL(tmp);
        } else if (x.type == Config.WORD_TYPE && y.type == Config.WORD_TYPE){
            boolean tmp = x.tostr().compareTo(y.tostr()) < 0;
            ret = new BOOL(tmp);
        } else {
            throw new ParseError("** Cannot Compare");
        }
    }
}
