package src.mua.Operations;

import src.mua.Configs.Config;
import src.mua.Exception.ParseError;
import src.mua.Interpreter.Interpreter;
import src.mua.NameSpace.Space;
import src.mua.Values.VALUE;

public class Run extends Operation {
    public Run() {
        isVoid = true;
        arg_num = 1;
    }

    @Override
    public void exec(Space space) throws ParseError {
        VALUE x = args.get(0);
        if (x.type != Config.LIST_TYPE)
            throw new ParseError("*** Can only run a list.");
        Interpreter I = new Interpreter(space, Config.REPEAT);
        I.executeList(x);
    }
}
