package src.mua.Operations;

import src.mua.Configs.Config;
import src.mua.Exception.ParseError;
import src.mua.Interpreter.Interpreter;
import src.mua.NameSpace.Space;
import src.mua.Values.VALUE;

public class If extends Operation {
    public If() {
        isVoid = true;
        arg_num = 3;
    }
    public void exec(Space space) throws ParseError {
        VALUE cdt = args.get(0), p1 = args.get(1), p2 = args.get(2);
        if (cdt.type != Config.BOOL_TYPE)
            throw new ParseError("** if should be followed by a bool");
        Interpreter interpreter = new Interpreter(space, Config.REPEAT);
        if (cdt.tobool() == true)
            interpreter.executeList(p1);
        else
            interpreter.executeList(p2);
    }
}
