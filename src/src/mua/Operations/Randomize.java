package src.mua.Operations;

import src.mua.Exception.ParseError;
import src.mua.NameSpace.Space;
import src.mua.Values.NUM;
import src.mua.Values.VALUE;

import java.util.Random;

public class Randomize extends Operation {
    public Randomize() {
        isVoid = false;
        arg_num = 1;
    }

    @Override
    public void exec(Space space) throws ParseError {
        VALUE x = args.get(0);
        int bound = (int)Math.ceil(x.tonum());
        if (bound <= 0)
            throw new ParseError("*** Upper Bound should be greater than zero");
        Random r = new Random();
        int num = r.nextInt(bound);
        ret = new NUM(num);
    }
}
