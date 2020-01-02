package src.mua.Operations;

import src.mua.Configs.Config;
import src.mua.Exception.ParseError;
import src.mua.NameSpace.Space;
import src.mua.Values.VALUE;
import src.mua.Values.WORD;

public class Word extends Operation {
    public Word() {
        isVoid = false;
        arg_num = 2;
    }

    @Override
    public void exec(Space space) throws ParseError {
        VALUE x = args.get(0), y = args.get(1);
        if (x.type != Config.WORD_TYPE)
            throw new ParseError("*** First Operand is not word");
        if (y.type != Config.WORD_TYPE && y.type != Config.NUM_TYPE && y.type != Config.BOOL_TYPE)
            throw new ParseError("*** Second Operand is not word/num/bool");
        ret = new WORD(x.tostr() + y.tostr());
    }
}
