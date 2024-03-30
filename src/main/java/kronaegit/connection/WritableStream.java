package kronaegit.connection;

import kronaegit.connection.data.WritablePacket;

import java.io.IOException;

public interface WritableStream {
    default void write(byte[] bytes, int off, int len) throws IOException {
        byte[] b = new byte[len];
        System.arraycopy(bytes, off, b, 0, len);
        write(b);
    }
    void write(byte[] b) throws IOException;
    default void writeByte(byte b) throws IOException {
        write(new byte[]{b}, 0, 1);
    }
    default void write(WritablePacket packet) throws IOException {
        packet.write(this);
    }
}
