package kronaegit.connection.data;

import kronaegit.connection.WritableStream;

import java.io.IOException;
import java.util.Arrays;

public class Packet implements WritablePacket {
    private final byte[] bytes;
    public Packet(byte[] bytes) {
        this.bytes = bytes;
    }
    public byte[] getData() {
        return bytes;
    }
    @Override
    public void write(WritableStream stream) throws IOException {
        stream.write(bytes);
    }
    @Override
    public String toString() {
        return "Packet" + Arrays.toString(bytes);
    }
}
