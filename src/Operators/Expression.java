package Operators;

import VarTypes.DigitVar;
import VarTypes.VarType;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Map;

public class Expression {

    public static Object eval(Map<String, VarType> vars, String code)
            throws ScriptException {
        for (String var : vars.keySet()) {
                code = code.replace(var, vars.get(var).toString());
        }

        if(code.matches("\".+?\"" )) {
            code = code.substring(1, code.length()-1);
            return code;
        }else{
            ScriptEngineManager mgr = new ScriptEngineManager();
            ScriptEngine engine = mgr.getEngineByName("JavaScript");

            for (String var : vars.keySet()) {
                if (vars.get(var).getType()=="Digit")
                    code = code.replace("--", "+");
                    code = code.replace("-"+var, DigitVar.invertSign(Double.parseDouble(vars.get(var).toString())));
            }
            code = code.replace("sqrt", "Math.sqrt");
            return engine.eval(code);
        }
    }

}
