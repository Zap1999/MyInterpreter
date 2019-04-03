package Operators;

import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OpCreateObject extends Operator{

    public OpCreateObject(ArrayList<String> lines) {
        super(lines);
    }

    @Override
    public void exec(Interpretater inte) {

        Pattern pattern = Pattern.compile("createObject (.*)");
        Matcher matcher = pattern.matcher(extendedCode.get(0));

        if (!matcher.find()) {
            System.err.println("Error: Wrong object syntax.");
        } else {
            String objName = matcher.group(1);
            objName = objName.split(" ")[0];
            objName = objName.trim();
            extendedCode.remove(0);
            extendedCode.remove(extendedCode.size()-1);

            try {

                ArrayList<String> vars = new ArrayList<>();
                ArrayList<String> methods = new ArrayList<>();

                int pos = 0;

                while(true) {
                    if (pos < extendedCode.size()) {
                        String currStr = extendedCode.get(pos);

                        if (currStr.trim().isEmpty()) {
                            pos++;
                        } else {

                            if (currStr.contains("field")) {
                                vars.add(currStr);
                            }
                            else {
                                methods.add(currStr);
                            }
                            pos++;
                        }
                    }
                    else {
                        break;
                    }
                }
                inte.addObj(new ObjectContainer(objName, vars, methods));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

}
