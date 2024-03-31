package kronaegit.connection.util;

public class Port {
    private final short port;
    public Port(int port) {
        if(!(0 <= port && port <= 65535))
            throw new IllegalArgumentException(String.format("Port(%d) number out of range(0-65535)!", port));

        this.port = BigEndianParse.toBigEndianShort(port);
    }
    public Port(short unsignedShort) {
        this.port = unsignedShort;
    }
    public short getRawPort() {
        return port;
    }
    public int getPort() {
        return BigEndianParse.bigEndianShortTo(port);
    }
    @Override
    public String toString() {
        return "Connectable" + "(" + getPort() + ")";
    }
}
