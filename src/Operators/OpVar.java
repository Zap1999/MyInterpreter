package Operators;

import Operators.Operator;

import javax.script.ScriptException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OpVar extends Operator {

    public OpVar(String code) {
        super(code);
    }

    @Override
    public void exec(Interpretater inte) {
        String[] parts = code.split("=");
        if (parts[1].trim().matches("\".*\"") ) {
            try{
                String val =parts[1];
                Matcher m = Pattern.compile("(.*?)"/*"([\\\"'])(?:(?=(\\\\?))\\2.)*?\\1"*/).matcher(val);
                if (m.find()) {
                    val = m.group(1).replace("\"", "");
                    inte.put(parts[0].trim(), val);
                }else {
                    System.err.println("String syntax error");
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            try {
                Object val = Expression.eval(inte.getVars(), parts[1]);
                inte.put(parts[0].trim(), Double.parseDouble(val.toString()));
            } catch (ScriptException e) {
                e.printStackTrace();
            }
        }

        inte.next();

    }

}
