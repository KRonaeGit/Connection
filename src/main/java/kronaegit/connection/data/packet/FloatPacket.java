package kronaegit.connection.data.packet;

import kronaegit.connection.ReadableStream;
import kronaegit.connection.WritableStream;
import kronaegit.connection.data.WritablePacket;
import kronaegit.connection.util.ByteParse;

import java.io.IOException;

public class FloatPacket implements WritablePacket {
    protected final float value;
    public FloatPacket(float value) {
        this.value = value;
    }
    @Override
    public void write(WritableStream stream) throws IOException {
        stream.write(ByteParse.toBytes(value));
    }
    public float getValue() {
        return value;
    }
    public static FloatPacket read(ReadableStream stream) throws IOException {
        float data = ByteParse.toFloat(stream.read(4));
        return new FloatPacket(data);
    }
    @Override
    public String toString() {
        return "FloatPacket" + "(" + value + ")";
    }
}
