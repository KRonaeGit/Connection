package kronaegit.connection;

import kronaegit.connection.data.Packet;

import java.io.IOException;

public interface ReadableStream {
    byte[] read(int capacity) throws IOException;
}
