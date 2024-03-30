package kronaegit.connection.data;

import kronaegit.connection.util.ByteParse;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class PacketBuilder {
    private final ByteBuffer bb;
    public PacketBuilder(int capacity) {
        this.bb = ByteBuffer.allocate(capacity);
    }
    public PacketBuilder() {
        this(0);
    }
    public PacketBuilder allocate(int capacity) {
        byte[] data = getData();
        PacketBuilder out = new PacketBuilder(data.length + capacity);
        out.put(0, data);
        return out;
    }
    private int size() {
        return getData().length;
    }
    private byte[] getData() {
        return bb.array();
    }
    public Packet build() {
        return new Packet(getData());
    }
    public PacketBuilder put(int index, byte[] bytes) {
        bb.put(index, bytes);
        return this;
    }
    public PacketBuilder add(byte[] bytes) {
        int index = getData().length;
        PacketBuilder out = allocate(bytes.length);
        out.put(index, bytes);
        return out;
    }
    public PacketBuilder put(int index, byte b) {
        return put(index, new byte[]{b});
    }
    public PacketBuilder add(byte b) {
        PacketBuilder out = allocate(1);
        out.put(out.size() - 1, b);
        return out;
    }
    public PacketBuilder put(int index, short data) {
        return put(index, ByteParse.toBytes(data));
    }
    public PacketBuilder add(short data) {
        int index = getData().length;
        PacketBuilder out = allocate(2);
        out.put(index, data);
        return out;
    }
    public PacketBuilder put(int index, int data) {
        return put(index, ByteParse.toBytes(data));
    }
    public PacketBuilder add(int data) {
        int index = getData().length;
        PacketBuilder out = allocate(4);
        out.put(index, data);
        return out;
    }
    public PacketBuilder put(int index, float data) {
        return put(index, ByteParse.toBytes(data));
    }
    public PacketBuilder add(float data) {
        int index = getData().length;
        PacketBuilder out = allocate(4);
        out.put(index, data);
        return out;
    }
    public PacketBuilder put(int index, long data) {
        return put(index, ByteParse.toBytes(data));
    }
    public PacketBuilder add(long data) {
        int index = getData().length;
        PacketBuilder out = allocate(8);
        out.put(index, data);
        return out;
    }
    public PacketBuilder put(int index, double data) {
        return put(index, ByteParse.toBytes(data));
    }
    public PacketBuilder add(double data) {
        int index = getData().length;
        PacketBuilder out = allocate(8);
        out.put(index, data);
        return out;
    }
    @Override
    public String toString() {
        return "PacketBuilder" + Arrays.toString(getData());
    }
}