package test.v2;

import kronaegit.connection.server.ClientConnection;
import kronaegit.connection.server.ServerConnection;
import kronaegit.connection.server.ServerEventListener;

import java.io.IOException;
import java.util.List;

public class ExampleServer extends ServerConnection implements ServerEventListener {
    public static ExampleServer server = null;
    private static MessageProcessor processor = null;

    public ExampleServer(int port) throws IOException { super(port); setListener(this); }
    public static void main(String[] args) throws IOException {
        long startup = System.currentTimeMillis();

        int port = args.length > 0 ? Integer.parseInt(args[0]) : 1234;
        System.out.println("Server is opening...");

        server = new ExampleServer(port);
        (processor = new MessageProcessor(server)).startMessageInputProcessor(System.in);

        System.out.println("Server is running on port " + port + "(" + (startup = System.currentTimeMillis() - startup) + "ms)");
    }

    public void close(List<ClientConnection> clients) throws IOException {
        System.out.println("Server closing... (" + clients.size() + ")");

        for (ClientConnection client : clients)
            client.writeInt(0);

        System.out.println("Server closed.");
    }
    public void connect(ClientConnection client) throws IOException {
        System.out.println(client.getClientAddress() + "| Connected");
        processor.processClient(client);
        System.out.println(client.getClientAddress() + "| Disconnected");
    }
}
