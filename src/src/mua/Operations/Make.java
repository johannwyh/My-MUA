package src.mua.Operations;

import src.mua.Values.VALUE;
import src.mua.Values.BOOL;
import src.mua.Configs.Config;
import src.mua.Exception.ParseError;
import src.mua.NameSpace.Space;
import src.mua.Operations.OperationList;

public class Make extends Operation{

    public Make() {
        arg_num = 2;
        isVoid = true;
    }

    @Override
    public void exec(Space space) throws ParseError {
        VALUE x = args.get(0), y = args.get(1);
        //if (!isValid(x))
        //    throw new ParseError();
        space.setHere(x.tostr(), y);
    }

    boolean isValid(VALUE x) {
        if (x.type != Config.WORD_TYPE) return false;
        String name = x.tostr();
        if (name.length() == 0) return false;
        if (!Character.isLetter(name.charAt(0))) return false;
        for(int i = 1; i < name.length(); i++) {
            char c = name.charAt(i);
            if (!Character.isLetter(c) && !Character.isDigit(c) && c != '_')
                return false;
        }
        return true;
    }

    boolean isAlpha(char c) {
        return ((c >= 'a') && (c <= 'z')) || ((c >= 'A') && (c <= 'Z'));
    }
    boolean isDigit(char c) {
        return (c >= '0') && (c <= '9');
    }
}
