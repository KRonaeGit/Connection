package kronaegit.connection.server;

import kronaegit.connection.client.Connector;
import kronaegit.connection.util.IpUtil;
import kronaegit.connection.util.Port;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Connectable {
    private final ServerSocket socket;
    private final AcceptorThread acceptorThread;
    private Connected listener = c -> System.out.println("Connectable: Use 'onConnect(Connected)' method to detect the client!");
    private boolean closed = false;
    public Connectable(Port port) throws IOException {
        this(port, 1);
    }
    public Connectable(Port port, int sametimeConnecting) throws IOException {
        socket = new ServerSocket(port.getPort());
        acceptorThread = new AcceptorThread(socket);
        for (int i = 0; i < sametimeConnecting; i++)
            acceptor();
    }
    private boolean acceptor() {
        if(closed)
            return false;

        new Thread(acceptorThread).start();
        return true;
    }
    public ServerSocket getRawSocket() {
        return socket;
    }
    public Connectable onConnect(Connected listener) {
        this.listener = listener;
        return this;
    }
    @Override
    public String toString() {
        return "Connectable" + "(" + socket.toString() + ")";
    }

    private class AcceptorThread implements Runnable {
        private final ServerSocket socket;
        public AcceptorThread(ServerSocket socket) {
            this.socket = socket;
        }
        @Override
        public void run() {
            Socket clientSocket;
            try {
                clientSocket = socket.accept();
            } catch (IOException e) {
                System.err.println("An error occurred while 'ServerSocket#accept()': " + e.getMessage());
                e.printStackTrace();
                acceptor();
                return;
            }
            acceptor();
            Connector connection;
            try {
                connection = new Connector(clientSocket);
            } catch (IOException e) {
                System.err.println("An error occurred while creating Connector instance with Socket instance: while getting streams: " + e.getMessage());
                e.printStackTrace();
                acceptor();
                return;
            }
            try {
                listener.onConnect(connection);
            } catch (SocketException e) {
                if(e.getMessage().toLowerCase().contains("connection reset")) {
                    System.err.printf("Event Listener: Client '%s': Connection Reset: It is assumed that a forced shutdown occurred on the client side.\n", IpUtil.toString(connection.getOpponentAddress()));
                    e.printStackTrace();
                    acceptor();
                    return;
                }
                System.err.printf("Event Listener: Client '%s': An SocketException occurred inside the listener: %s\n", IpUtil.toString(connection.getOpponentAddress()), e.getMessage());
                e.printStackTrace();
                acceptor();
            } catch (IOException e) {
                System.err.printf("Event Listener: Client '%s': An IOException occurred inside the listener: %s\n", IpUtil.toString(connection.getOpponentAddress()), e.getMessage());
                e.printStackTrace();
                acceptor();
            }
            connection.close();
        }
    }
}
