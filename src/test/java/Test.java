import kronaegit.connection.WritingStream;
import kronaegit.connection.data.Packet;
import kronaegit.connection.data.PacketBuilder;
import kronaegit.connection.util.BigEndianParse;
import kronaegit.connection.util.ByteParse;

import java.io.IOException;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) throws IOException {
        WritingStream ws = new WritingStream();
        ws.write(new byte[]{2,5,38,32});
        System.out.println(ws);
        ws.write(new byte[]{-3,58,-63,39,38,-27});
        System.out.println(ws);
        ws.write(new byte[]{1,2,3,4,5});
        System.out.println(ws);

        int number = 32769;
        System.out.println(Arrays.toString(ByteParse.toBytes(BigEndianParse.toBigEndianShort(number))));

        ws = new WritingStream();

Packet packet = new PacketBuilder()
                    .add(1234) // Adds integer data
                    .add(0.9876) // Adds float data
                    .put(0, -128) // Set the first byte data to -128.
                    .build(); // Build it to create Packet instance.
// Now you can use Packet instance 'packet'!
    }
}
