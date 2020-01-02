package src.mua.Operations;

import src.mua.Exception.RuntimeError;
import src.mua.Values.VALUE;
import src.mua.Values.BOOL;
import src.mua.Configs.Config;
import src.mua.Exception.ParseError;
import src.mua.NameSpace.Space;
import src.mua.Values.WORD;

public class Eq extends Operation{

    public Eq() {
        arg_num = 2;
        isVoid = false;
    }

    @Override
    public void exec(Space space) throws ParseError {
        VALUE x = args.get(0), y = args.get(1);
        try {
            if (x.type == Config.NUM_TYPE || y.type == Config.NUM_TYPE) {
                boolean tmp = x.tonum() == y.tonum();
                ret = new BOOL(tmp);
            } else if (x.type == Config.BOOL_TYPE || y.type == Config.BOOL_TYPE) {
                boolean tmp = x.tobool() == y.tobool();
                ret = new BOOL(tmp);
            } else if (x.type == Config.WORD_TYPE && y.type == Config.WORD_TYPE) {
                boolean tmp = x.tostr().equals(y.tostr());
                ret = new BOOL(tmp);
            } else if (x.type == Config.LIST_TYPE && y.type == Config.LIST_TYPE) {
                boolean tmp = x.getVal().equals(y.getVal());
                ret = new BOOL(tmp);
            } else {
                ret = new BOOL(false);
            }
        } catch (ParseError pe) {
            ret = new BOOL(false);
        } catch (RuntimeError re) {
            ret = new BOOL(false);
        }
    }

    @Override
    public void debug() {
        System.out.println("Func [Eq]");
    }
}
