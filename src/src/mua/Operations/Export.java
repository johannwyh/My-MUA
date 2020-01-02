package src.mua.Operations;

import src.mua.NameSpace.Space;
public class Export extends Operation {
    public Export() {
        isVoid = true;
        arg_num = 1;
    }
    public void exec(Space space) {
        String name = args.get(0).tostr();
        if (space.existsHere(name))
            space.setMain(name, space.getHere(name));
    }
}
