package kronaegit.connection.data.packet;

import kronaegit.connection.ReadableStream;
import kronaegit.connection.WritableStream;
import kronaegit.connection.data.WritablePacket;
import kronaegit.connection.util.ByteParse;

import java.io.IOException;

public class BytePacket implements WritablePacket {
    protected final byte value;
    public BytePacket(byte value) {
        this.value = value;
    }
    @Override
    public void write(WritableStream stream) throws IOException {
        stream.write(ByteParse.toBytes(value));
    }
    public byte getValue() {
        return value;
    }
    public static BytePacket read(ReadableStream stream) throws IOException {
        return new BytePacket(stream.read(1)[0]);
    }
    @Override
    public String toString() {
        return "BytePacket" + "(" + value + ")";
    }
}
