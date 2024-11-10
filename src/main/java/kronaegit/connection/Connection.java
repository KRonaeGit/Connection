package kronaegit.connection;

import kronaegit.byterist.pipe.both.BothPipeAbs;
import kronaegit.connection.server.ServerEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;
import java.util.Objects;

public class Connection extends BothPipeAbs {
    protected final Socket socket;
    private ClientEventListener listener = ClientEventListener.empty();

    public Connection(Socket socket) throws IOException {
        super(socket.getInputStream(), socket.getOutputStream());
        this.socket = socket;
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));
    }
    public Connection(String addr, int port) throws IOException {
        this(new Socket(addr, port));
    }

    public Connection(SocketChannel channel) throws IOException {
        this(channel.socket());
    }

    protected void setListener(ClientEventListener listener) {
        this.listener = listener;
        new Thread(() -> {
            try {
                listener.connect(this);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    @Override
    public void close() throws IOException {
        listener.disconnect(this);
        listener.close(this);
        super.close();
        socket.close();
    }
    public final SocketAddress getLocalAddress() {
        return socket.getLocalSocketAddress();
    }
    public final SocketAddress getTargetAddress() {
        return socket.getRemoteSocketAddress();
    }
    public SocketAddress getClientAddress() {
        return getLocalAddress();
    }
    public SocketAddress getServerAddress() {
        return getTargetAddress();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Connection that = (Connection) o;
        return Objects.equals(socket, that.socket);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(socket);
    }

    public boolean isConnected() {
//        System.out.println(socket.isConnected() + " " + socket.isClosed + " " + socket.isInputShutdown() + " " + socket.isOutputShutdown());
        return socket.isConnected() && !(socket.isClosed() || socket.isInputShutdown() || socket.isOutputShutdown());
    }
}