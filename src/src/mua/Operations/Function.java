package src.mua.Operations;

import src.mua.Configs.Config;
import src.mua.Interpreter.Interpreter;
import src.mua.Values.*;
import src.mua.NameSpace.*;

import java.util.ArrayList;

public class Function extends Operation {
    public Space space;
    public ArrayList<VALUE> arg_name;
    public VALUE program;

    public Function(Space space, String name) {
        if (!space.existsSomewhere(name)) {
            System.out.println("** No Such Function ( " + name + " )");
            return;
        }
        //System.out.println(name);
        //System.out.println(space.getSomewhere("max").tostr());
        //System.out.println(space.getSomewhere(name).tostr());
        VALUE val = space.getSomewhere(name);
        if (val == null || val.type != Config.LIST_TYPE) {
            System.out.println("** Function should be described as a list");
            return;
        }
        ArrayList<VALUE> code = val.getVal();
        if (code.size() != 2 || code.get(0).type != Config.LIST_TYPE || code.get(1).type != Config.LIST_TYPE) {
            System.out.println("** Invalid Function Syntax");
            return;
        }
        arg_name = code.get(0).getVal();
        program = code.get(1);
        this.space = new Space(space);
        this.arg_num = arg_name.size();
    }

    public void exec(Space space) { run(); }

    public void run() {
        for(int i = 0; i < args.size(); i++)
            space.setHere(arg_name.get(i).tostr(), args.get(i));
        Interpreter interpreter = new Interpreter(space, Config.FUNCTION);
        interpreter.executeList(program);
        if (space.isStopped() && space.getRET() != null) {
            ret = space.getRET();
            isVoid = false;
        } else {
            ret = null;
            isVoid = true;
        }
    }
}
