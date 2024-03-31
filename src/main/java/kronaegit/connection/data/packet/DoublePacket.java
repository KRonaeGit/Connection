package kronaegit.connection.data.packet;

import kronaegit.connection.ReadableStream;
import kronaegit.connection.WritableStream;
import kronaegit.connection.data.WritablePacket;
import kronaegit.connection.util.ByteParse;

import java.io.IOException;

public class DoublePacket implements WritablePacket {
    protected final double value;
    public DoublePacket(double value) {
        this.value = value;
    }
    @Override
    public void write(WritableStream stream) throws IOException {
        stream.write(ByteParse.toBytes(value));
    }
    public double getValue() {
        return value;
    }
    public static DoublePacket read(ReadableStream stream) throws IOException {
        double data = ByteParse.toDouble(stream.read(8));
        return new DoublePacket(data);
    }
    @Override
    public String toString() {
        return "DoublePacket" + "(" + value + ")";
    }
}
