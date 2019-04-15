package Operators;

import javax.script.ScriptException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OpIf extends Operator {


    public OpIf(String code) {
        super(code);
    }

    @Override
    public void exec(Interpretater inte) {
        Pattern pattern = Pattern.compile("(.*) then goto (.*)");
        Matcher matcher = pattern.matcher(code);


        if(!matcher.find()) {
            System.err.println("Bad operation if");
        } else {
            String cond = ((Matcher) matcher).group(1);
            int line = Integer.parseInt(((Matcher) matcher).group(2));

            try {
                Object res = Expression.eval(inte.getVars(), cond);
                if (res instanceof Boolean && res.equals(true)) {
                    inte.goTo(line);
                }
            }catch(ScriptException e) {
                e.printStackTrace();
            }

        }
    }
}
