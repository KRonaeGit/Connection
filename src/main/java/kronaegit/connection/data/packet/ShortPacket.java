package kronaegit.connection.data.packet;

import kronaegit.connection.ReadableStream;
import kronaegit.connection.WritableStream;
import kronaegit.connection.data.WritablePacket;
import kronaegit.connection.util.ByteParse;

import java.io.IOException;

public class ShortPacket implements WritablePacket {
    protected final short value;
    public ShortPacket(short value) {
        this.value = value;
    }
    @Override
    public void write(WritableStream stream) throws IOException {
        stream.write(ByteParse.toBytes(value));
    }
    public short getValue() {
        return value;
    }
    public static ShortPacket read(ReadableStream stream) throws IOException {
        short data = ByteParse.toShort(stream.read(2));
        return new ShortPacket(data);
    }
    @Override
    public String toString() {
        return "ShortPacket" + "(" + value + ")";
    }
}
