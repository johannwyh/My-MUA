package src.mua;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Iterator;

import src.mua.Configs.Config;
import src.mua.Exception.ParseError;
import src.mua.Exception.RuntimeError;
import src.mua.IO.IO;
import src.mua.Interpreter.Interpreter;
import src.mua.NameSpace.Space;
import src.mua.Values.*;
import src.mua.Operations.*;

public class Main {
    public static void main(String[] args) {
        //IO.scan = new Scanner(System.in);

        try {
            IO.scan = new Scanner(new File("./src/in"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Interpreter I = new Interpreter(new Space(), Config.FUNCTION);

        //while(IO.scan.hasNextLine()) {

        StringBuffer content = new StringBuffer("[ ");
        while(IO.scan.hasNextLine()) {
            String line = IO.scan.nextLine();
            if (line.equals("#"))
                break;
            int comment_pos = line.indexOf("//");
            if (comment_pos != -1)
                line = line.substring(0, comment_pos);
            content.append(line);
            content.append(" ");
        }
        content.append(" ]");
        try {
            I.executeList(LIST.getListFromStr(content.toString()));
        } catch (ParseError pe) {
            System.out.println(pe);
        } catch (RuntimeError re) {
            System.out.println(re);
        }

        //}
    }
}
