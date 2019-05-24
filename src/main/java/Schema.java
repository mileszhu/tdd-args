import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Schema {
    private String command;
    private String type;
    public static Map<String, Function<Object, Object>> m = new HashMap();
    static {
        m.put("bool", value->{
            if (value == null) {
                return Boolean.FALSE;
            }
            return Boolean.valueOf((String)value);
        });
        m.put("int", value->{
            if (value == null) {
                return 0;
            }
            return Integer.valueOf((String) value);
        });
        m.put("string", value->{
            if (value == null) {
                return "";
            }
            return value;
        });
    }
    public Schema(String line) {
        String[] tmp = line.split(":");
        command = tmp[0];
        type = tmp[1];
    }


    public Object getValue(String s, Object command) {
        return m.get(type).apply(command);
    }

    public String getType() {
        return type;
    }

    public String getCommand() {
        return command;
    }
}
