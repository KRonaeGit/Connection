import kronaegit.connection.util.ByteParse;
import kronaegit.connection.util.Port;

import java.util.Arrays;

public class PortTest {
    public static void main(String[] args) {
        int number = 25565;
        Port port = new Port(number);
        System.out.println(number);
        System.out.println(port.getRawPort());
        System.out.println(Arrays.toString(ByteParse.toBytes(port.getRawPort())));
        System.out.println(port.getPort());
    }
}
