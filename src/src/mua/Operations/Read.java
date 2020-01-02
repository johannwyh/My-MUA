package src.mua.Operations;

import src.mua.Values.*;
import src.mua.Exception.ParseError;
import src.mua.NameSpace.Space;
import src.mua.Configs.Config;
import src.mua.IO.IO;

public class Read extends Operation{

    public Read() {
        arg_num = 0;
        isVoid = false;
    }

    @Override
    public void exec(Space space) throws ParseError {
        String rd = IO.scan.next();
        ret = new WORD(rd);
    }
}
