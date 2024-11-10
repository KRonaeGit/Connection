package kronaegit.connection.server;

import java.io.EOFException;
import java.io.IOException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ClientProcessor extends Thread {
    private final ServerConnection server;
    private final ServerSocketChannel socket;
    private boolean stop = false;

    public ClientProcessor(ServerConnection server, ServerSocketChannel socket) {
        this.server = server;
        this.socket = socket;
        start();
    }
    public void stopProcessor() {
        stop = true;
        interrupt();
    }
    @Override
    public void run() {
        try {
            while (server.isOpen() && !stop) {
                SocketChannel channel = socket.accept();
                if (channel == null)
                    continue;

                new Thread(() -> {
                    try {
                        ClientConnection client = new ClientConnection(channel);
                        server.addClientConnected(client);
                        try {
                            server.getListener().connect(client);
                        } catch(EOFException _) { }
                        server.callDisconnectEvent(client);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
