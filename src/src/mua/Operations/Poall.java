package src.mua.Operations;

import src.mua.NameSpace.Space;
import src.mua.Values.VALUE;

import java.util.HashMap;

public class Poall extends Operation {
    public Poall() {
        isVoid = true;
        arg_num = 0;
    }

    @Override
    public void exec(Space space) {
        HashMap<String, VALUE> map = space.getLocal();
        for(String key: map.keySet())
            System.out.println(key);
    }
}
