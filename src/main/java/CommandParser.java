import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class CommandParser {
    public Map<String, Schema> map;
    public ArgParser argParser;
    public CommandParser(String s, String s1) {
        String[] ss = s.split(",");
        map = Arrays.asList(ss).stream().map(value-> new Schema(value)).collect(Collectors.toMap(Schema::getCommand, a->a));
        argParser = new ArgParser(s1);
    }

    public Object getArg(String l) {
        return map.get(l).getValue(l, argParser.getValue(l));
    }
}
