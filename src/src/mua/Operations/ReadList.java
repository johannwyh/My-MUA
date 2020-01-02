package src.mua.Operations;

import src.mua.IO.IO;
import src.mua.NameSpace.Space;
import src.mua.Values.LIST;
import src.mua.Values.VALUE;
import src.mua.Values.WORD;

import java.util.ArrayList;

public class ReadList extends Operation {
    public ReadList() {
        isVoid = false;
        arg_num = 0;
    }

    public void exec(Space space) {
        String input = IO.scan.nextLine();
        int comment_pos = input.indexOf("//");
        if (comment_pos != -1)
            input = input.substring(0, comment_pos);
        String[] data = input.split("\\s+");
        ArrayList<VALUE> retList = new ArrayList<>();
        for(int i = 0; i < data.length; i++)
            retList.add(new WORD(data[i]));
        ret = new LIST(retList);
    }
}
