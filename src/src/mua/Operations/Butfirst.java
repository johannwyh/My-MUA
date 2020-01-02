package src.mua.Operations;

import src.mua.Configs.Config;
import src.mua.NameSpace.Space;
import src.mua.Values.LIST;
import src.mua.Values.VALUE;
import src.mua.Values.WORD;

import java.util.ArrayList;

public class Butfirst extends Operation {
    public Butfirst() {
        isVoid = false;
        arg_num = 1;
    }

    @Override
    public void exec(Space space) {
        VALUE x = args.get(0);
        if (x.type == Config.LIST_TYPE) {
            ArrayList<VALUE> tmp = new ArrayList<VALUE>();
            for(int i = 1; i < x.getVal().size(); i++)
                tmp.add(x.getVal().get(i).copy());
            ret = new LIST(tmp);
        } else {
            ret = new WORD(x.tostr().substring(1));
        }
    }
}
