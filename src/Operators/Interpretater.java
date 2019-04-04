package Operators;

import VarTypes.VarType;

import java.util.*;

public class Interpretater {

    private TreeMap<Integer, Operator> code =
            new TreeMap<Integer, Operator>();
    private HashMap<String, VarType> vars =
            new HashMap<String, VarType>();
    private Integer curLine;
    private HashMap<String, ObjectContainer> objects =
            new HashMap<>();

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
            String line = lines.get(0).trim();
            String parts[] = line.split(" ");
            int lineNumber = Integer.parseInt(parts[0]);
            String opName = parts[1];
            Operator operator = null;
            if(opName.length() == 5) {
                operator = new OpWhile(lines);
            }
            else if (opName.length() == 12) {
                operator = new OpCreateObject(lines);
            }
            else {
                System.err.println("Wrong multiline syntax");
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

    public HashMap<String, VarType> getVars() {
        return vars;
    }

    public void put(String var, VarType val) {vars.put(var, val);}

    public void addObj(String name, ObjectContainer obj) {
        objects.put(name, obj);
    }

    public ObjectContainer getObj(String name) {
        return objects.get(name);
    }

    public void setVars(HashMap<String, VarType> vars) {
        Iterator it = vars.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            if(!(((String)pair.getKey()).contains("\\."))) {
                this.vars.put((String) pair.getKey(),(VarType) pair.getValue());
            }
        }
    }

}
