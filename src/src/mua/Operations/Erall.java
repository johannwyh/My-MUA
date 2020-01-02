package src.mua.Operations;

import src.mua.NameSpace.Space;

public class Erall extends Operation {
    public Erall() {
        isVoid = true;
        arg_num = 0;
    }

    @Override
    public void exec(Space space) {
        space.clearLocal();
    }
}
