package src.mua.Operations;

import src.mua.NameSpace.Space;
import src.mua.Values.LIST;
import src.mua.Values.VALUE;

import java.util.ArrayList;

public class List extends Operation {
    public List() {
        isVoid = false;
        arg_num = 2;
    }

    @Override
    public void exec(Space space) {
        VALUE x = args.get(0), y = args.get(1);
        ArrayList<VALUE> tmp = new ArrayList<VALUE>();
        tmp.add(x.copy());
        tmp.add(y.copy());
        ret = new LIST(tmp);
    }
}
