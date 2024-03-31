package kronaegit.connection.client;

import kronaegit.connection.util.Port;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
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
    public boolean isConnected() {
        try {
            socket.getInputStream();
            socket.getOutputStream();
        } catch(IOException e) {
            return false;
        }
        return socket.isConnected() && (!socket.isClosed());
    }
    public Port getLocalPort() {
        return new Port(socket.getLocalPort());
    }
    public InetAddress getLocalAddress() {
        return socket.getLocalAddress();
    }
    public Port getOpponentPort() {
        return new Port(socket.getPort());
    }
    public InetAddress getOpponentAddress() {
        return socket.getInetAddress();
    }
    public boolean close() {
        try {
            socket.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "Connection" + "(" + socket.toString() + ")";
    }
}
