package kronaegit.connection;

import java.io.IOException;
import java.util.List;

public interface ConnectionListener<CON extends Connection, CONS> {
    default void connect(CON client) throws IOException {
        System.out.println("Empty connection listener detected connection: " + client);
    }
    default void disconnect(CON client) throws IOException { }
    default void close(CONS clients) throws IOException {}
}
