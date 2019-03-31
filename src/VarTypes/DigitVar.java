package VarTypes;

public class DigitVar extends VarType{

    public DigitVar(String val) {
        super(val);
    }

    public String getType() {
        return "Digit";
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public static String invertSign(Double digit) {
        return Double.toString(digit*(-1));
    }

}
