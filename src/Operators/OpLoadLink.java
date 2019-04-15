package Operators;

public class OpLoadLink extends Operator {

    public OpLoadLink(String code) {
        super(code);
    }

    @Override
    public void exec(Interpretater inte) {
        code = code.trim();
        code = code.replace("\"", "");
        inte.getDriver().navigate().to(code);
    }
}
