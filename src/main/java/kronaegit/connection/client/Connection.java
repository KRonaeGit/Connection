package kronaegit.connection.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public abstract class Connection {
    private final Socket socket;
    protected final InputStream is;
    protected final OutputStream os;
    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        this.is = socket.getInputStream();
        this.os = socket.getOutputStream();
    }
    public Socket getSocket() {
        return socket;
    }

    @Override
    public String toString() {
        return "Connection" + "(" + socket.toString() + ")";
    }
}
