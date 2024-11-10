package test.v1;

import kronaegit.byterist.VarByterist;
import kronaegit.byterist.chunk.LengthEncodedString;
import kronaegit.connection.ClientEventListener;
import kronaegit.connection.Connection;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ExampleClient extends Connection implements ClientEventListener {
    public ExampleClient() throws IOException {
        super("127.0.0.1", 1234);
        setListener(this);
    }

    @Override
    public void disconnect(Connection con) throws IOException {
        System.out.println(con.getServerAddress() + "> Disconnected");
        con.flush();
        con.write(0);  // Send termination signal
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
