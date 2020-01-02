package src.mua.Operations;

import src.mua.Configs.Config;
import src.mua.Exception.ParseError;
import src.mua.NameSpace.Space;
import src.mua.Values.LIST;
import src.mua.Values.VALUE;

public class Join extends Operation {
    public Join() {
        isVoid = false;
        arg_num = 2;
    }
    @Override
    public void exec(Space space) throws ParseError {
        VALUE x = args.get(0), y = args.get(1);
        if (x.type != Config.LIST_TYPE)
            throw new ParseError("*** The first operand is not list");
        LIST tmp = new LIST(x.copy().getVal());
        tmp.listAdd(y);
        // System.out.println(x.tostr() + " join " + y.tostr() + " = " + tmp.tostr());
        ret = tmp;
    }
}
