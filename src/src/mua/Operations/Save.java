package src.mua.Operations;

import src.mua.Exception.RuntimeError;
import src.mua.NameSpace.Space;
import src.mua.Values.VALUE;

import java.io.PrintStream;
import java.util.HashMap;

public class Save extends Operation {
    public Save() {
        isVoid = true;
        arg_num = 1;
    }

    @Override
    public void exec(Space space) throws RuntimeError {
        VALUE x = args.get(0);
        String filename = x.tostr();
        try {
            PrintStream stdout = System.out;
            System.setOut(new PrintStream(filename));
            HashMap<String, VALUE> map = space.getLocal();
            for (String key: map.keySet()) {
                System.out.println("make \"" + key + " " + map.get(key).tostr());
            }
            System.setOut(stdout);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeError("*** Fail to save");
        }
    }

}
