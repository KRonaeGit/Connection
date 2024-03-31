package ex1$client$leaving$detect;

import kronaegit.connection.client.Connector;
import kronaegit.connection.util.IpUtil;
import kronaegit.connection.util.Port;

import java.io.IOException;
import java.net.InetAddress;

public class Client {
    public static void main(String[] args) throws IOException {
        Connector connector = new Connector(InetAddress.getByName("127.0.0.1"), new Port(1234));
        String addr = IpUtil.toString(connector.getOpponentAddress());

        System.out.printf("[%s] Connected.\n", addr);

        long before = System.currentTimeMillis();
        while(connector.isConnected()) {
            if(before + 1000 > System.currentTimeMillis())
                continue;
            before = System.currentTimeMillis();

            System.out.printf("[%s] Still connected\n", addr);
            connector.close();
            System.out.printf("[%s] IM GONNA LEAVE\n", addr);
        }
        System.out.printf("[%s] Connection left.\n", addr);
    }
}
