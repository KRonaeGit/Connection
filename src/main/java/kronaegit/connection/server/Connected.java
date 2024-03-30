package kronaegit.connection.server;

import kronaegit.connection.client.Connector;

import java.io.IOException;

public interface Connected {
    void onConnect(Connector connector) throws IOException;
}
