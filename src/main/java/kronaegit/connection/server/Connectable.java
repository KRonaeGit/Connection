package kronaegit.connection.server;

import kronaegit.connection.client.Connector;
import kronaegit.connection.util.Port;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Connectable {
    private final ServerSocket socket;
    private Connected listener = c -> System.out.println("Connectable: Use 'onConnect(Connected)' method to detect the client!");
    public Connectable(Port port) throws IOException {
        this(port, 1);
    }
    public Connectable(Port port, int sametimeConnecting) throws IOException {
        socket = new ServerSocket(port.getPort());
        for (int i = 0; i < sametimeConnecting; i++)
            acceptor();
    }

    private void acceptor() {
        Thread thread = new Thread(() -> {
            Socket clientSocket;
            try {
                clientSocket = socket.accept();
            } catch (IOException e) {
                System.err.println("An error occurred while 'ServerSocket#accept()': " + e.getMessage());
                throw new RuntimeException(e);
            }
            acceptor();
            Connector connection = null;
            try {
                connection = new Connector(clientSocket);
            } catch (IOException e) {
                System.err.println("An error occurred while creating Connector instance with Socket instance: while getting streams: " + e.getMessage());
                throw new RuntimeException(e);
            }
            try {
                listener.onConnect(connection);
            } catch (IOException e) {
                System.err.println("An error occurred while handling connect listener: " + e.getMessage());
                throw new RuntimeException(e);
            }
        });
        thread.start();
    }

    public ServerSocket getRawSocket() {
        return socket;
    }
    public Connectable
    onConnect(Connected listener) {
        this.listener = listener;
        return this;
    }
    @Override
    public String toString() {
        return "Connectable" + "(" + socket.toString() + ")";
    }
}
