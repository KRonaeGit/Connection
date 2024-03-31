package kronaegit.connection.client;

import kronaegit.connection.ReadableStream;
import kronaegit.connection.WritableStream;
import kronaegit.connection.util.Port;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Connector extends Connection implements WritableStream, ReadableStream {
    public Connector(InetAddress address, Port port) throws IOException {
        this(new Socket(address, port.getPort()));
    }
    public Connector(Socket socket) throws IOException {
        super(socket);
    }

    @Override
    public byte[] read(int capacity) throws IOException {
        return is.readNBytes(capacity);
    }

    @Override
    public void write(byte[] b) throws IOException {
        os.write(b);
    }
    public String toString() {
        return "Connector" + "(" + super.toString() + ")";
    }
}
