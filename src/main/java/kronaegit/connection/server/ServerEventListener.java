package kronaegit.connection.server;

import kronaegit.connection.ConnectionListener;

import java.util.List;

public interface ServerEventListener extends ConnectionListener<ClientConnection, List<ClientConnection>> {
    static ServerEventListener empty() {
        return new ServerEventListener() { };
    }
}
