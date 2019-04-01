package Operators;

import javax.script.ScriptException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OpWhile extends Operator {

    public OpWhile(String code) {
        super(code);
    }

    @Override
    public void exec(Interpretater inte) {

        Pattern pattern = Pattern.compile("(.*) do (.*)");
        Matcher matcher = pattern.matcher(code);

        inte.next();

        if(!matcher.find()) {
            System.err.println("Bad operation if");
        } else {

            String cond = ((Matcher) matcher).group(1);
            String toDo = ((Matcher) matcher).group(2);

            try {
                Object res = Expression.eval(inte.getVars(), cond);
                Expression.eval(inte.getVars(), toDo);



            }catch(ScriptException e) {
                e.printStackTrace();
            }

        }

    }
}
