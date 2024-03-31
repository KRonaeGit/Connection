package kronaegit.connection.util;

import java.net.InetAddress;

public class IpUtil {
    public static String toString(InetAddress address) {
        return String.format("%s(%s)", toString(address.getAddress()), address.getHostName());
    }

    private static String toString(byte[] address) {
        if(address.length != 4)
            throw new IllegalArgumentException("Length of parameter 'address' should be 4.");

        return toString(
            BigEndianParse.bigEndianByteTo(address[0]),
            BigEndianParse.bigEndianByteTo(address[1]),
            BigEndianParse.bigEndianByteTo(address[2]),
            BigEndianParse.bigEndianByteTo(address[3])
        );
    }

    private static String toString(int a, int b, int c, int d) {
        return String.format("%d.%d.%d.%d", a, b, c, d);
    }
}
