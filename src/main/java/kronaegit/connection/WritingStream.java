package kronaegit.connection;

import kronaegit.connection.data.PacketBuilder;

import java.io.IOException;
import java.util.Arrays;

public class WritingStream implements WritableStream {
    public byte[] buffer = new byte[0];
    @Override
    public void write(byte[] b) throws IOException {
        buffer = new PacketBuilder().add(buffer).add(b).build().getData();
    }
    @Override
    public String toString() {
        return "WritingStream" + Arrays.toString(buffer);
    }
}
