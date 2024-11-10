package kronaegit.connection.server;

import kronaegit.connection.Connection;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;

public class ClientConnection extends Connection {
    public ClientConnection(Socket client) throws IOException {
        super(client);
    }

    public ClientConnection(SocketChannel client) throws IOException {
        super(client);
    }

    public final SocketAddress getClientAddress() {
        return getTargetAddress();
    }
    public final SocketAddress getServerAddress() {
        return getLocalAddress();
    }
}
