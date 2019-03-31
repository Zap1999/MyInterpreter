package Operators;

abstract public class Operator {
    String code;

    public Operator(String code) {
        this.code = code;
    }

    abstract public void exec(Interpretater inte);

    public String toString () {
        return getClass().getName().toLowerCase() + " " + code;
    }

}
