package Operators;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Interpretater {

    private TreeMap<Integer, Operator> code =
            new TreeMap<Integer, Operator>();
    private Map<String, Double> vars =
            new HashMap<String, Double>();
    private Integer curLine;

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
            if (curLine == null) break;
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

    public Map<String, Double> getVars() {
        return vars;
    }


}
