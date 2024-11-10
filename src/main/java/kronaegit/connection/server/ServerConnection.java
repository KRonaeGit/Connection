package kronaegit.connection.server;

import kronaegit.byterist.pipe.OutputPipe;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;


public abstract class ServerConnection implements Closeable {
    private final ServerSocketChannel serverSocket;
    private ClientProcessor processor;
    private ServerEventListener listener = ServerEventListener.empty();
    private final List<ClientConnection> clients = new ArrayList<>();
    private boolean closed = false;

    public ServerConnection(int port) throws IOException {
        this(ServerSocketChannel.open().bind(new InetSocketAddress(port)));
    }
    public ServerConnection(ServerSocketChannel serverSocket) throws IOException {
        this.serverSocket = serverSocket;
        if(!serverSocket.isOpen())
            throw new IllegalArgumentException();

        serverSocket.configureBlocking(false);  // Non-blocking mode

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));

        processor = new ClientProcessor(this, serverSocket);
    }

    protected void setListener(ServerEventListener listener) {
        this.listener = listener;
    }
    protected void addClientConnected(ClientConnection client) {
        clients.add(client);
    }
    protected void callDisconnectEvent(ClientConnection client) throws IOException {
        listener.disconnect(client);
        client.close();
        clients.remove(client);
    }


    public List<ClientConnection> getClients() {
        return clients;
    }

    public void close() throws IOException {
        closed = true;
        processor.stopProcessor();
        listener.close(clients);
        for (ClientConnection client : clients) client.close();
        clients.clear();
        serverSocket.close();
    }

    public boolean isOpen() {

        return serverSocket.isOpen() && !closed;
    }

    public ServerEventListener getListener() {
        return listener;
    }

    public OutputPipe broadcast() {
        return new OutputPipe() {
            @Override
            public void write(int b) throws IOException {
                for (ClientConnection client : clients) { client.write(b); }
            }
            @Override
            public void flush() throws IOException {
                for (ClientConnection client : clients) { client.flush(); }
            }
            @Override
            public void close() throws IOException {
                for (ClientConnection client : clients) { client.close(); }
                clients.clear();
            }
        };
    }
}
