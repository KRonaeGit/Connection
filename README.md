# Connection
You can create a TCP/IP server or a client **easier**!

## How to use
Inssert code below into your `pom.xml` file. (maven)
```xml
<dependency>
  <groupId>io.github.kronaegit</groupId>
  <artifactId>connection</artifactId>
  <version>4.0.0</version>
</dependency>
```

## Example Use
CLEINT:
```java
import kronaegit.byterist.VarByterist;
import kronaegit.byterist.chunk.LengthEncodedString;
import kronaegit.connection.ClientEventListener;
import kronaegit.connection.Connection;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ExampleClient extends Connection implements ClientEventListener {
    public ExampleClient() throws IOException {
        super("127.0.0.1", 1234); // connect to 127.0.0.1:1234
        setListener(this); // <- 'this' is ClientEventListener
    }

    @Override // disconnect(Connection con) from ClientEventListener
    public void disconnect(Connection con) throws IOException {
        System.out.println(con.getServerAddress() + "> Disconnected");
        con.flush();
        con.write(0);  // Send disconnecting signal
    }

    @Override
    public void connect(Connection con) throws IOException {
        System.out.println(con.getServerAddress() + "> Connected");
        while (con.isConnected()) {
            if (con.available() > 0) {
                int size = con.readInt();
                if (size == 0) con.close();
                String msg = new String(con.read(size), StandardCharsets.UTF_8).replace('\n', ' ');
                System.out.println(con.getServerAddress() + ": " + msg);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        try (ExampleClient con = new ExampleClient()) {
            con.write(new LengthEncodedString("asdf"));

            VarByterist buffer = new VarByterist();
            while (con.isConnected()) {
                if (System.in.available() == 0)
                    continue;


                int b = System.in.read();
                if (b == 10) {
                    LengthEncodedString packet = new LengthEncodedString(buffer.extract());
                    con.write(packet);  // Send the message
                    buffer.clear();  // Clear buffer for next message
                } else {
                    buffer.put(b);  // Store input in buffer
                }
            }
        }
    }
}
```

SERVER:
```java
package test.v1;

import kronaegit.byterist.VarByterist;
import kronaegit.byterist.chunk.LengthEncodedString;
import kronaegit.connection.server.ClientConnection;
import kronaegit.connection.server.ServerConnection;
import kronaegit.connection.server.ServerEventListener;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ExampleServer extends ServerConnection implements ServerEventListener {
    public ExampleServer(int port) throws IOException {
        super(port);
        setListener(this);
    }

    @Override
    public void close(List<ClientConnection> clients) throws IOException {
        for (ClientConnection client : clients) {
            client.writeInt(0);
        }
        System.out.println("> Closed");
    }

    @Override
    public void connect(ClientConnection client) throws IOException {
        System.out.println(client.getClientAddress() + "> Connected");
        while(client.isConnected()) {
            if(client.available() > 0) {
                int size = client.readInt();
                if(size == 0) {
                  System.out.println("Disconnecting signal received");
                    client.close();
                }
                String msg = new String(client.read(size), StandardCharsets.UTF_8).replace('\n',' ');
                System.out.println(client.getClientAddress() + ": " + msg);
            }
        }
        System.out.println(client.getClientAddress() + "> Disconnected");
    }
    public static void main(String[] args) throws IOException {
        try(ExampleServer server = new ExampleServer(1234)) {
            VarByterist buffer = new VarByterist();
            while(server.isOpen()) {
                if(System.in.available() == 0)
                    continue;

                int b = System.in.read();
                if (b == 10) {
                    LengthEncodedString packet = new LengthEncodedString(buffer.extract());
                    for (ClientConnection client : server.getClients()) client.write(packet);

                    buffer.clear();
                    continue;
                }
                buffer.put(b);
            }
        }
    }
}
```
