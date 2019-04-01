package Operators;

import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OpWhile extends Operator {


    public OpWhile(String code) {
        super(code);
    }

    public OpWhile(ArrayList<String> lines) {
        super(lines);
    }

    @Override
    public void exec(Interpretater inte) {

        if (!this.isExtended()) {
            Pattern pattern = Pattern.compile("(.*) do (.*)");
            Matcher matcher = pattern.matcher(code);

            if (!matcher.find()) {
                System.err.println("Error: Wrong while syntax.");
            } else {

                String cond = ((Matcher) matcher).group(1);
                String toDo = ((Matcher) matcher).group(2);

                try {
                    while(true) {
                        Object res = Expression.eval(inte.getVars(), cond);
                        if (res instanceof Boolean && res.equals(true)) {
                            String parts[] = toDo.split(" ");
                            Operator op = OpFactory.createOperator(parts[0], toDo.substring(parts[0].length()));
                            op.exec(inte);
                        }
                        else break;
                    }


                } catch (ScriptException e) {
                    e.printStackTrace();
                }

            }
        }
        //      while i<4 do {
        //          print x
        //          var x=x+1
        //      }
        else {

            Pattern pattern = Pattern.compile("(.*) do (.*)");
            Matcher matcher = pattern.matcher(extendedCode.get(0));

            if (!matcher.find()) {
                System.err.println("Error: Wrong while syntax.");
            } else {
                String cond = matcher.group(1);
                String makingCond[] = cond.split(" ");
                cond = makingCond[2];
                extendedCode.remove(0);
                extendedCode.remove(extendedCode.size()-1);

                try {
                    while(true) {
                        Object res = Expression.eval(inte.getVars(), cond);
                        if (res instanceof Boolean && res.equals(true)) {
                            for(int i = 0; i < extendedCode.size(); ++i) {
                                String current = extendedCode.get(i).trim();
                                String parts[] = current.split(" ");
                                Operator op = OpFactory.createOperator(parts[0], current.substring(parts[0].length()));
                                op.exec(inte);
                            }
                        }
                        else break;
                    }
                } catch (ScriptException e) {
                    e.printStackTrace();
                }

            }
        }

    }
}
