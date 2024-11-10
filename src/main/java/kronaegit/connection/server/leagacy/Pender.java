package kronaegit.connection.server.leagacy;

import kronaegit.connection.server.ClientConnection;

import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;

public abstract class Pender extends Thread {
    private static int lastId = 0;
    private final int id;

    public static Pender by(ServerSocket socket, LeagacyServerConnection server) {
        Pender pender =  new Pender() {
            @Override
            public void run() {
                try {
                    ClientConnection client = new ClientConnection(socket.accept());
                    server.addClientConnected(client);
                    try {
                        server.getListener().connect(client);
                    } catch(EOFException _) { }
                    server.callDisconnectEvent(client);
                    server.adjustPenders();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        pender.start();
        return pender;
    }
    public Pender() {
        this.id = ++lastId;
    }

    @Override
    public String toString() {
        return "Pender(" + id + "#" + threadId() + ")";
    }
    @Override
    public abstract void run();
}
