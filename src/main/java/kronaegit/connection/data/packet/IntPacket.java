package kronaegit.connection.data.packet;

import kronaegit.connection.ReadableStream;
import kronaegit.connection.WritableStream;
import kronaegit.connection.data.WritablePacket;
import kronaegit.connection.util.ByteParse;

import java.io.IOException;

public class IntPacket implements WritablePacket {
    protected final int value;
    public IntPacket(int value) {
        this.value = value;
    }
    @Override
    public void write(WritableStream stream) throws IOException {
        stream.write(ByteParse.toBytes(value));
    }
    public int getValue() {
        return value;
    }
    public static IntPacket read(ReadableStream stream) throws IOException {
        int data = ByteParse.toInt(stream.read(4));
        return new IntPacket(data);
    }
    @Override
    public String toString() {
        return "IntPacket" + "(" + value + ")";
    }
}
