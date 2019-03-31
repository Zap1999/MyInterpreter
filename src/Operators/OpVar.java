package Operators;

import Operators.Operator;

import javax.script.ScriptException;
import java.util.regex.Pattern;

public class OpVar extends Operator {

    public OpVar(String code) {
        super(code);
    }

    @Override
    public void exec(Interpretater inte) {
        String[] parts = code.split("=");
        if (parts[1].trim().matches("\".*\"") ) {

        }
        else{
            try {
                Object val = Expression.eval(inte.getVars(), parts[1]);
                inte.getVars().put(parts[0].trim(), Double.parseDouble(val.toString()));
            } catch (ScriptException e) {
                e.printStackTrace();
            }
        }

        inte.next();

    }

}
