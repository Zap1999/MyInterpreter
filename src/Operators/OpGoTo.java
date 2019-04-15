package Operators;

public class OpGoTo extends Operator {
    public OpGoTo(String code) {
        super(code);
    }

    @Override
    public void exec(Interpretater inte) {
        inte.goTo(Integer.parseInt(code));
    }
}
