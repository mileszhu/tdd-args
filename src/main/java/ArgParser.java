import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ArgParser {
    public Map<String, Object> map = new HashMap();

    public ArgParser(String line) {
        String[] ss = line.split("\\s+");
        for (int i = 0; i < ss.length; i++) {
            map.put(ss[i], parseValue(ss, ss[i], i));
        }
    }

    public Object parseValue(String[] ss, String key, int index) {
        if (!key.startsWith("-") || (index + 1) >= ss.length) {
            return null;
        }
        return !ss[index + 1].startsWith("-") || StringUtils.isNumeric(ss[index + 1].substring(1)) ? ss[index + 1] : null;
    }

    public Object getValue(String key) {
        return map.get("-"+key);
    }
}

