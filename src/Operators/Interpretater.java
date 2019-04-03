package Operators;

import VarTypes.VarType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Interpretater {

    private TreeMap<Integer, Operator> code =
            new TreeMap<Integer, Operator>();
    private Map<String, VarType> vars =
            new HashMap<String, VarType>();
    private Integer curLine;
    private ArrayList<ObjectContainer> objects =
            new ArrayList<>();

    public void next() {
        curLine = code.higherKey(curLine);
    }

    public void goTo(int line) {
        curLine = line;
    }

    public void run() {
        curLine = code.firstKey();
        while (true) {
            Operator operator = code.get(curLine);
            operator.exec(this);
            if(!(operator instanceof OpIf || operator instanceof OpGoTo))
                this.next();
            if (curLine == null) break;
        }
    }

    public void parse(ArrayList<String> lines) {
        try {
            String line = lines.get(0);
            String parts[] = line.split(" ");
            int lineNumber = Integer.parseInt(parts[0]);
            String opName = parts[1];
            Operator operator;
            if(opName == "while") {
                operator = new OpWhile(lines);
            }
            else {
                operator = new OpCreateObject(lines);
            }
            code.put(lineNumber, operator);
        } catch (RuntimeException e) {
            System.err.println("Wrong operation");
        }
    }

    public void parse(String line) {

        if (line.equalsIgnoreCase("RUN")) {
            this.run();
            return;
        }

        if (line.equalsIgnoreCase("LIST")) {
            for (int l : code.keySet()) {
                System.out.println(l + " " + code.get(l));
            }
            return;
        }

        try {
            String parts[] = line.split(" ");
            int lineNumber = Integer.parseInt(parts[0]);
            String opName = parts[1];
            Operator operator =
                    OpFactory.createOperator(opName,
                            line.substring(parts[0].length()
                                    + parts[1].length()
                                    + 2));
            code.put(lineNumber, operator);
        } catch (RuntimeException e) {
            System.err.println("Wrong operation");
        }

    }

    public Map<String, VarType> getVars() {
        return vars;
    }

    public void put(String var, VarType val) {vars.put(var, val);}

    public void addObj(ObjectContainer e) {
        this.objects.add(e);
    }

}
