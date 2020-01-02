package src.mua.Operations;

import src.mua.Configs.Config;
import src.mua.Exception.RuntimeError;
import src.mua.Interpreter.Interpreter;
import src.mua.NameSpace.Space;
import src.mua.Values.LIST;
import src.mua.Values.VALUE;

import java.io.File;
import java.util.Scanner;

public class Load extends Operation {
    public Load() {
        isVoid = true;
        arg_num = 1;
    }

    @Override
    public void exec(Space space) throws RuntimeError {
        VALUE x = args.get(0);
        String filename = x.tostr();
        try {
            Scanner scan = new Scanner(new File(filename));
            StringBuffer code = new StringBuffer("[ ");
            while(scan.hasNextLine()) {
                String line = scan.nextLine();
                code.append(line + " ");
            }
            code.append("]");
            Interpreter I = new Interpreter(space, Config.REPEAT);
            I.executeList(LIST.getListFromStr(code.toString()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeError("*** Fail to load");
        }
    }
}
