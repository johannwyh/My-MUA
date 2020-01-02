package src.mua.Operations;

import src.mua.Configs.Config;
import src.mua.Interpreter.Interpreter;
import src.mua.NameSpace.Space;
import src.mua.Values.VALUE;

public class Repeat extends Operation {
    public Repeat() {
        isVoid = true;
        arg_num = 2;
    }
    public void exec(Space space) {
        VALUE x = args.get(0), y = args.get(1);
        int N = (int)x.tonum();
        for(int i = 0; i < N; i++) {
            Interpreter interpreter = new Interpreter(space, Config.REPEAT);
            interpreter.executeList(y);
        }
    }
}
