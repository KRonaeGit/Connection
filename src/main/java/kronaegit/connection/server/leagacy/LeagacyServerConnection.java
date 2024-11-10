package kronaegit.connection.server.leagacy;

import kronaegit.connection.server.ClientConnection;
import kronaegit.connection.server.ServerEventListener;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

@Deprecated
@SuppressWarnings("Use ServerConnection instead. This class has no longer bug-fixes.")
public abstract class LeagacyServerConnection implements Closeable {
    private final ServerSocket server;
    private ServerEventListener listener = ServerEventListener.empty();
    private final List<ClientConnection> clients = new ArrayList<>();


    public LeagacyServerConnection(ServerSocket socket) throws IOException {
        this.server = socket;

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));
        adjustPenders();
    }
    public LeagacyServerConnection(int port) throws IOException {
        this(new ServerSocket(port));
    }
    protected void setListener(ServerEventListener listener) {
        this.listener = listener;
    }
    public ServerEventListener getListener() {
        return listener;
    }


    // Pender Management
    private int targetPenders = 1;
    private final List<Pender> penders = new ArrayList<>();

    protected void setTargetPenders(int count) {
        this.targetPenders = count;
        adjustPenders();
    }
    protected int getCurrentPenders() {
        return penders.size();
    }
    void adjustPenders() {
        int adjustAmount = targetPenders - penders.size();
        if(adjustAmount > 0) {
            addPender();
            return;
        }

        List<Pender> removing = new ArrayList<>();
        for (int i = 0; i < (-adjustAmount); i++) {
            Pender pender = penders.get(0);
            pender.interrupt();
            removing.add(pender);
            penders.remove(pender);
        }

//        int timeout = 1000;
//
//        long start = System.currentTimeMillis();
//        while(!removing.isEmpty()) {
//            List<Pender> newRemoving = new ArrayList<>();
//            for (Pender pender : removing) {
//                if(pender.isAlive()) {
//                    System.err.println(pender + " is queued to stop. But still alive!");
//                    newRemoving.add(pender);
//                }
//            }
//            removing = newRemoving;
//            if(System.currentTimeMillis() >= start + timeout) {
//                throw new RuntimeException(removing.size() + " Penders queued to stop. But still not stopped(timeout " + timeout + "): " + Arrays.toString(removing.toArray(new Pender[0])));
//            }
//        }
    }
    protected void addPender() {
        penders.add(Pender.by(server, this));
    }
    protected void addClientConnected(ClientConnection client) {
        clients.add(client);
    }
    protected void callDisconnectEvent(ClientConnection client) throws IOException {
        listener.disconnect(client);
        client.close();
        clients.remove(client);
    }


    protected List<ClientConnection> getClients() {
        return clients;
    }

    public void close() throws IOException {
        setTargetPenders(0);
        listener.close(clients);
        for (ClientConnection client : clients) client.close();
        server.close();
    }

    public boolean isOpen() {
        return server.isBound() && !server.isClosed();
    }
}
