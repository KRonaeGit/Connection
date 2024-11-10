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
                    System.out.println("종료 신호 수신");
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
