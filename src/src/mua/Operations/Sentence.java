package src.mua.Operations;

import src.mua.Configs.Config;
import src.mua.NameSpace.Space;
import src.mua.Values.LIST;
import src.mua.Values.VALUE;

import java.util.ArrayList;

public class Sentence extends Operation {
    public Sentence() {
        isVoid = false;
        arg_num = 2;
    }

    @Override
    public void exec(Space space) {
        ArrayList<VALUE> tmp = new ArrayList<VALUE>();
        VALUE x = args.get(0).copy(), y = args.get(1).copy();
        if (x.type == Config.LIST_TYPE)
            tmp.addAll(x.getVal());
        else tmp.add(x);
        if (y.type == Config.LIST_TYPE)
            tmp.addAll(y.getVal());
        else tmp.add(y);
        ret = new LIST(tmp);
    }
}
