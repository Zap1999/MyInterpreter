package Operators;

import VarTypes.DigitVar;
import VarTypes.StringVar;
import VarTypes.VarType;
import jdk.nashorn.internal.parser.TokenType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static java.lang.Character.isDigit;

public class ObjectContainer {

    private String name;
    private Map<String, VarType> vars = new HashMap<>();
    private ArrayList<ArrayList<String>> methods  = new ArrayList<ArrayList<String>>();

    public ObjectContainer(Interpretater inte, String name, ArrayList<String> vars, ArrayList<String> methods) {
        this.name = name;
        try {
            putVars(inte, vars);
            putMethods(methods);
        }catch (Exception e) {
            System.err.println("Incorrect object syntax.");
            e.printStackTrace();
        }
    }

    private void putVars(Interpretater inte, ArrayList<String> varArray) {

        HashMap<String, VarType> vars = new HashMap<>();

        for(String curStr:varArray) {

            curStr = curStr.trim();
            String tokens[] = curStr.split(" ");
            if(tokens[3].trim().matches("\"(.*)\"")) {
                vars.put(tokens[1], new StringVar(tokens[3]));
                inte.put(name + "." + tokens[1], new StringVar(tokens[3]));
            }
            else if(!isDigit(tokens[3].trim().charAt(0))) {
                VarType var = inte.getVars().get(tokens[3].trim());
                vars.put(tokens[1], var);
                inte.put(name + "." + tokens[1], var);
            }
            else {
                vars.put(tokens[1], new DigitVar(tokens[3]));
                inte.put(name + "." + tokens[1], new DigitVar(tokens[3]));
            }

        }

        this.vars = vars;

    }

    private void putMethods(ArrayList<String> mthds) throws Exception {

        ArrayList<String> method = new ArrayList<>();

        Iterator<String> iter = mthds.iterator();

        while(iter.hasNext()) {
            String str = iter.next();
            if(!(str.trim().isEmpty())) {

                if(str.contains(">>")) {
                    method.add(str);
                    this.methods.add(new ArrayList<String>(method));
                    method.clear();
                }
                else {

                    method.add(str);

                }

            }
        }
    }

    public String getName() {
        return this.name;
    }

    public boolean hasVar(String varName) {
        return this.vars.get(varName) != null;
    }

    public boolean hasMethod(String methodName) {
        for(ArrayList<String> array: this.methods)
            for (String methodHead: array)
                if (methodHead.contains(methodName))
                    return true;
        return false;
    }

    public VarType getVar(String name) {

        return vars.get(name);
    }

    public ArrayList<String> getMethod(String name) {

        for(ArrayList<String> array: this.methods)
            for (String methodHead: array)
                if (methodHead.contains(name))
                    return array;
        return new ArrayList<>();
    }

}