package test.v2;

import kronaegit.byterist.VarByterist;
import kronaegit.byterist.pipe.OutputPipe;
import kronaegit.connection.server.ClientConnection;
import kronaegit.connection.server.ServerConnection;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class MessageProcessor {
    private final ServerConnection server;
    public MessageProcessor(ServerConnection server) {
        this.server = server;
    }
    public void startMessageInputProcessor(InputStream stream) {
        new Thread(() -> {
            try {
                processInputs(stream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
    public void processInputs(InputStream stream) throws IOException {
        VarByterist buffer = new VarByterist();
        OutputPipe broadcast = server.broadcast();
        while(server.isOpen()) {
            while(stream.available() > 0) {
                int read = stream.read();
                if(read == 10) {
                    if(buffer.size() == 0) {
                        System.err.println("$ Disconnect signal broadcast.");

                        broadcast.writeInt(0);
                        broadcast.close();
                        continue;
                    }
                    byte[] data = buffer.extractAndClear();
                    broadcast.writeInt(data.length);
                    broadcast.write(data);
                    System.out.println("$ " + new String(data, StandardCharsets.UTF_8));
                    continue;
                }
                buffer.put(read);
            }
        }
    }
    public void processClient(ClientConnection client) throws IOException {
        while(client.isConnected()) {
            if(client.available() > 0) {
                int size = client.readInt();
                if(size == 0) {
                    System.out.println(client.getClientAddress() + "| Disconnect signal received.");
                    client.close();
                    continue;
                }
                String msg = new String(client.read(size), StandardCharsets.UTF_8).replace('\n',' ');
                System.out.println(client.getClientAddress() + ": " + msg);
            }
        }
    }
}
