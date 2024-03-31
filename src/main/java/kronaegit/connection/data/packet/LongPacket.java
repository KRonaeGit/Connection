package kronaegit.connection.data.packet;

import kronaegit.connection.ReadableStream;
import kronaegit.connection.WritableStream;
import kronaegit.connection.data.WritablePacket;
import kronaegit.connection.util.ByteParse;

import java.io.IOException;

public class LongPacket implements WritablePacket {
    protected final long value;
    public LongPacket(long value) {
        this.value = value;
    }
    @Override
    public void write(WritableStream stream) throws IOException {
        stream.write(ByteParse.toBytes(value));
    }
    public long getValue() {
        return value;
    }
    public static LongPacket read(ReadableStream stream) throws IOException {
        long data = ByteParse.toLong(stream.read(8));
        return new LongPacket(data);
    }
    @Override
    public String toString() {
        return "LongPacket" + "(" + value + ")";
    }
}
