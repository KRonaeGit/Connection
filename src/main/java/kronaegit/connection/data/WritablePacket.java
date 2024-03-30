package kronaegit.connection.data;

import kronaegit.connection.WritableStream;

import java.io.IOException;

public interface WritablePacket {
    void write(WritableStream stream) throws IOException;
}
