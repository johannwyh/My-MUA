package src.mua.Operations;

import src.mua.NameSpace.Space;

public class Output extends Operation {
    public Output() {
        isVoid = true;
        arg_num = 1;
    }
    public void exec(Space space) {
        space.setRET(args.get(0));
    }
}
