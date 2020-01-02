package src.mua.Operations;

import src.mua.Operations.*;
import java.util.HashMap;
import src.mua.Exception.ParseError;

public class OperationList {
    public static HashMap<String, Operation> table = 
        new HashMap<String, Operation>() {{
            put("add", new Add());
            put("and", new And());
            put("div", new Div());
            put("eq", new Eq());
            put("erase", new Erase());
            put("gt", new Gt());
            put("isname", new IsName());
            put("lt", new Lt());
            put("make", new Make());
            put("mod", new Mod());
            put("mul", new Mul());
            put("not", new Not());
            put("or", new Or());
            put("print", new Print());
            put("read", new Read());
            put("sub", new Sub());
            put("thing", new Thing());
            put("export", new Export());
            put("if", new If());
            put("isbool", new IsBool());
            put("isempty", new IsEmpty());
            put("islist", new IsList());
            put("isnumber", new IsNumber());
            put("isword", new IsWord());
            put("output", new Output());
            put("readlist", new ReadList());
            put("repeat", new Repeat());
            put("stop", new Stop());
            put("word", new Word());
            put("sentence", new Sentence());
            put("list", new List());
            put("join", new Join());
            put("first", new First());
            put("last", new Last());
            put("butfirst", new Butfirst());
            put("butlast", new Butlast());
            put("random", new Randomize());
            put("int", new Int());
            put("sqrt", new Sqrt());
            put("run", new Run());
            put("save", new Save());
            put("load", new Load());
            put("erall", new Erall());
            put("poall", new Poall());
        }};
    
    public static Operation get(String name) throws ParseError {
        if (!table.containsKey(name)) {
            return null;
        } else {
            try {
                Operation ret = table.get(name).getClass().newInstance();
                ret.clear();
                return ret;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }
}