package Operators;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class OpFactory {
    static Map<String, Class> ops = new HashMap<String, Class>();

    static{
        ops.put("print", OpPrint.class);
        ops.put("var", OpVar.class);
        ops.put("if", OpIf.class);
        ops.put("goto", OpGoTo.class);
        ops.put("while", OpWhile.class);
    }

    public static Operator createOperator(String opName, String subString) {
        Class opClass = ops.get(opName);

        try {
            return (Operator) opClass.getConstructor(String.class).newInstance(subString);
        }catch(InstantiationException e ) {
            throw new RuntimeException(e);
        }catch (IllegalAccessException e){
            throw new RuntimeException(e);
        }catch(InvocationTargetException e) {
            throw new RuntimeException(e);
        }catch(NoSuchMethodException e ) {
            throw new RuntimeException(e);
        }

    }

}
