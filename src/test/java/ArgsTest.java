import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class ArgsTest {
    @Test
    public void testBool() {
        Schema schema = new Schema("l:bool");
        assertEquals(schema.getValue("l","true"), Boolean.TRUE);
        assertEquals(schema.getValue("l","false"), Boolean.FALSE);
        assertEquals(schema.getValue("l",null), Boolean.FALSE);
        assertEquals(schema.getValue("l",""), Boolean.FALSE);
    }

    @Test
    public void testInt() {
        Schema schema = new Schema("l:int");
        assertEquals(schema.getValue("l",100), new Integer(100));
        assertEquals(schema.getValue("l",null), new Integer(0));
    }

    @Test
    public void testString() {
        Schema schema = new Schema("l:string");
        assertEquals(schema.getValue("l","100"), "100");
        assertEquals(schema.getValue("l",""), "");
        assertEquals(schema.getValue("l",null), "");
    }

    @Test
    public void testOneValue() {
        ArgParser argParser = new ArgParser("-b true");
        assertEquals(argParser.getValue("b"), "true");
    }

    @Test
    public void testNegative() {
        ArgParser argParser = new ArgParser("-l -1");
        assertEquals(argParser.getValue("l"), "-1");
    }

    @Test
    public void testMore()  {
        ArgParser argParser = new ArgParser("-b true -p 8080 -s string -m -n -1");
        assertEquals(argParser.getValue("b"), "true");
        assertEquals(argParser.getValue("p"), "8080");
        assertEquals(argParser.getValue("s"), "string");
        assertEquals(argParser.getValue("n"), "-1");
    }

    @Test
    public void test() {
        CommandParser commandLine = new CommandParser("l:bool,p:int,d:string","-l true -p 8080 -d /usr/bin");
        assertEquals(commandLine.getArg("l"),true);
        assertEquals(commandLine.getArg("p"),8080);
        assertEquals(commandLine.getArg("d"),"/usr/bin");
    }
}
