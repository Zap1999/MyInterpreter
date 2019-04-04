package Operators;

import Operators.Operator;
import VarTypes.DigitVar;
import VarTypes.StringVar;
import VarTypes.VarType;

import javax.script.ScriptException;
import java.util.regex.Pattern;

public class OpVar extends Operator {

    public OpVar(String code) {
        super(code);
    }

    @Override
    public void exec(Interpretater inte) {
        String[] parts = code.split("=");
        String name = parts[0].trim();
        String value = parts[1].trim();
        if (value.matches("\".+?\"")) {
            inte.put(name, new StringVar(parts[1].trim()));
        }
        else if(value.matches(".*\\..*")) {
            String valSplit[] = value.split("\\.");
            String objName = valSplit[0];
            String objVar = valSplit[1];
            VarType var = inte.getObj(objName).getVar(objVar);
            if(var.getType() == "Digit") {
                try {
                    Object val = Expression.eval(inte.getVars(), var.getValue());
                    inte.put(name, new DigitVar(val.toString()));
                }catch (ScriptException e) {
                    System.err.println("Variable evaluation failed. (" + name + ")");
                    e.printStackTrace();
                }

            }
            else {
                inte.put(name, var);
            }
        }
        else{
            try {
                Object val = Expression.eval(inte.getVars(), parts[1]);
                inte.put(name, new DigitVar(val.toString()));
            } catch (ScriptException e) {
                System.err.println("Variable evaluation failed. (" + name + ")");
                e.printStackTrace();
            }
        }


    }

}
