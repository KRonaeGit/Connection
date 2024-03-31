package kronaegit.connection.util;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ByteParse {
    public static byte[] toBytes(String str) {
        return str.getBytes(StandardCharsets.UTF_8);
    }
    public static byte[] toBytes(String str, Charset charset) {
        return str.getBytes(charset);
    }
    public static byte[] toBytes(short s) {
        return ByteBuffer.allocate(2).putShort(s).array();
    }
    public static byte[] toBytes(int i) {
        return ByteBuffer.allocate(4).putInt(i).array();
    }
    public static byte[] toBytes(float f) {
        return ByteBuffer.allocate(4).putFloat(f).array();
    }
    public static byte[] toBytes(long l) {
        return ByteBuffer.allocate(8).putLong(l).array();
    }
    public static byte[] toBytes(double d) {
        return ByteBuffer.allocate(8).putDouble(d).array();
    }

    public static short toShort(byte[] bytes, int off) {
        return ByteBuffer.wrap(bytes).getShort(off);
    }
    public static short toShort(byte[] bytes) {
        return toShort(bytes, 0);
    }
    public static int toInt(byte[] bytes, int off) {
        return ByteBuffer.wrap(bytes).getInt(off);
    }
    public static int toInt(byte[] bytes) {
        return toInt(bytes, 0);
    }
    public static float toFloat(byte[] bytes, int off) {
        return ByteBuffer.wrap(bytes).getFloat(off);
    }
    public static float toFloat(byte[] bytes) {
        return toFloat(bytes, 0);
    }
    public static long toLong(byte[] bytes, int off) {
        return ByteBuffer.wrap(bytes).getLong(off);
    }
    public static long toLong(byte[] bytes) {
        return toLong(bytes, 0);
    }
    public static double toDouble(byte[] bytes, int off) {
        return ByteBuffer.wrap(bytes).getDouble(off);
    }
    public static double toDouble(byte[] bytes) {
        return toDouble(bytes, 0);
    }
    public static String toString(byte[] bytes, Charset charset) {
        return new String(bytes, charset);
    }
    public static String toString(byte[] bytes) {
        return toString(bytes, StandardCharsets.UTF_8);
    }
}
