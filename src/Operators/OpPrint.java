package Operators;

import Operators.Expression;
import Operators.Interpretater;
import Operators.Operator;

import javax.script.ScriptException;

public class OpPrint extends Operator {

    public OpPrint(String code) {
        super(code);
    }

    @Override
    public void exec(Interpretater inte) {

        Object res = null;

        inte.next();

        String trim = code.trim();
        /*if(trim.startsWith("\"")){
            String quotedStr = trim.substring(1, trim.length() -2);
            if(trim.endsWith(";")) {
                System.out.print(quotedStr);
            }else {
                System.out.println(quotedStr);
            }
            return;
        }*/

        try {
            res = Expression.eval(inte.getVars(), code);
            System.out.println(res);
        }catch(ScriptException e) {
            e.printStackTrace();
        }
    }

}
