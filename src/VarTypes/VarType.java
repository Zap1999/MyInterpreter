package VarTypes;

public abstract class VarType {
    private String value;

    public VarType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return value;
    }

    public abstract String getType();
}
