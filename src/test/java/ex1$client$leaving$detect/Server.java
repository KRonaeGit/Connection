package ex1$client$leaving$detect;

import kronaegit.connection.server.Connectable;
import kronaegit.connection.util.IpUtil;
import kronaegit.connection.util.Port;

import java.io.IOException;

public class Server {
    public static void main(String[] args) throws IOException {
        new Connectable(new Port(1234)).onConnect(connector -> {
            String addr = IpUtil.toString(connector.getOpponentAddress());
            System.out.printf("[%s] New connection connected.\n", addr);

            long before = System.currentTimeMillis();
            while(connector.isConnected()) {
                if(before + 1000 > System.currentTimeMillis())
                    continue;
                before = System.currentTimeMillis();

                System.out.printf("[%s] Still connected\n", addr);
            }
            System.out.printf("[%s] Connection left.\n", addr);
        });
    }
}
