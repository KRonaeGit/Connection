package test.v1;

import kronaegit.byterist.VarByterist;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ExampleRawClient {
    private final String host;
    private final int port;
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;

    public ExampleRawClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() {
        try {
            socket = new Socket(host, port);
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());

            // 서버로부터 오는 메시지 수신 스레드
            new Thread(this::receiveMessages).start();

            // 사용자 입력을 읽어 서버로 전송
            handleUserInput();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            stop();
        }
    }

    private void handleUserInput() throws IOException {
        VarByterist byterist = new VarByterist();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            while (System.in.available() > 0) {
                int b = System.in.read();
                if (b == 10) { // Enter 키를 누르면 메시지를 전송
                    sendMessage(byterist.extract());
                    byterist.clear();
                } else if (b != -1) {
                    byterist.put(b);
                }
            }

            if (Thread.interrupted()) break;
        }
    }

    private void sendMessage(byte[] msgBytes) throws IOException {
        if (msgBytes.length == 0) { // 종료 신호
            out.writeInt(0);
        } else {
            out.writeInt(msgBytes.length); // 메시지 크기 전송
            out.write(msgBytes);           // 메시지 본문 전송
        }
        out.flush();
    }

    private void receiveMessages() {
        try {
            while (true) {
                if(in.available() == 0)
                    continue;
                int size = in.readInt();
                if (size == 0) {
                    System.out.println("서버 연결 종료 신호 수신.");
                    break;
                }

                byte[] msgBytes = new byte[size];
                in.readFully(msgBytes);
                String message = new String(msgBytes, StandardCharsets.UTF_8);
                System.out.println("서버로부터 수신된 메시지: " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stop() {
        try {
            if (socket != null) socket.close();
            if (in != null) in.close();
            if (out != null) out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExampleRawClient client = new ExampleRawClient("localhost", 1234);
        client.start();
    }
}
