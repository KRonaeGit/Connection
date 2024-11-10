package kronaegit.connection;

import java.io.IOException;

public interface ClientEventListener extends ConnectionListener<Connection, Connection> {
    static ClientEventListener empty() {
        return new ClientEventListener() { };
    }
    @Deprecated
    @SuppressWarnings("Use disconnect(client) instead.")
    default void close(Connection con) throws IOException { }

    @Override
    default void connect(Connection con) throws IOException { ConnectionListener.super.connect(con); }

    @Override
    default void disconnect(Connection con) throws IOException { }
}
