import kronaegit.connection.ReadingStream;

import java.io.IOException;
import java.util.Arrays;

public class ReadingStreamTest {
    public static void main(String[] args) throws IOException {
        ReadingStream stream = new ReadingStream();
        stream.write(new byte[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16});
        while(true) {
            System.out.println(Arrays.toString(stream.read(15)));
        }
    }
}
