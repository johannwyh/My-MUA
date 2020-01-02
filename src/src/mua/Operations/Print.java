package src.mua.Operations;

import src.mua.Values.VALUE;
import src.mua.Values.BOOL;
import src.mua.Exception.ParseError;
import src.mua.NameSpace.Space;
import src.mua.Configs.Config;

public class Print extends Operation{

    public Print() {
        arg_num = 1;
        isVoid = true;
    }

    @Override
    public void exec(Space space) throws ParseError {
        VALUE x = args.get(0);
        if (x.type == Config.BOOL_TYPE) {
            System.out.println(x.tobool());
        } else if (x.type == Config.NUM_TYPE) {
            System.out.println(x.tonum());
        } else if (x.type == Config.WORD_TYPE) {
            System.out.println(x.tostr());
        } else if (x.type == Config.LIST_TYPE) {
            String outs = x.tostr();
            int len = outs.length();
            System.out.println(outs.substring(1, len - 1));
        }
    }
}
