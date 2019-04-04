package Operators;

import VarTypes.DigitVar;
import VarTypes.StringVar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ObjectContainer {

    private String name;
    private Map<String, String> strVars = new HashMap<>();
    private Map<String, String> digVars = new HashMap<>();
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

        HashMap<String, String> strVars = new HashMap<>();
        HashMap<String, String> digVars = new HashMap<>();

        for(String curStr:varArray) {

            curStr = curStr.trim();
            String tokens[] = curStr.split(" ");
            if(tokens[2].trim().matches("\"(.*)\"")) {
                strVars.put(tokens[1], tokens[3]);
                inte.getVars().put(name + "." + tokens[1], new StringVar(tokens[3]));
            }
            else {
                digVars.put(tokens[1], tokens[3]);
                inte.getVars().put(name + "." + tokens[1], new DigitVar(tokens[3]));
            }

        }

        this.digVars = digVars;
        this.strVars = strVars;

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
        return (this.strVars.get(varName) != null ||
                this.digVars.get(varName) != null);
    }

    public boolean hasMethod(String methodName) {
        for(ArrayList<String> array: this.methods)
            for (String methodHead: array)
                if (methodHead.contains(methodName))
                    return true;
        return false;
    }

    public String getVar(String name) {

        if(strVars.get(name) != null)
            return strVars.get(name);
        else
            return digVars.get(name);
    }

    public ArrayList<String> getMethod(String name) {

        for(ArrayList<String> array: this.methods)
            for (String methodHead: array)
                if (methodHead.contains(name))
                    return array;
        return new ArrayList<>();
    }

}