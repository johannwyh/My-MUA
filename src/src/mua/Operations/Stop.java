package src.mua.Operations;

import src.mua.NameSpace.Space;
public class Stop extends Operation {
    public Stop() {
        isVoid = true;
        arg_num = 0;
    }

    public void exec(Space space) {
        space.setStopped(true);
    }
}
