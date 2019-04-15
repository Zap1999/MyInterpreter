package Operators;

import java.util.ArrayList;

abstract public class Operator {
    String code;
    ArrayList<String> extendedCode;

    public Operator(String code) {
        this.code = code;
        extendedCode = null;
    }

    public Operator(ArrayList<String> lines) {
        this.extendedCode = lines;
        this.code = null;
    }

    abstract public void exec(Interpretater inte);

    public String toString () {
        return getClass().getName().toLowerCase() + " " + code;
    }

    public boolean isExtended() {
        if (extendedCode!= null) return true;
        else return false;
    }

}
