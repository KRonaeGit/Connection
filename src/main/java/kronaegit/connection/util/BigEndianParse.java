package kronaegit.connection.util;

public class BigEndianParse {
    // unsigned short(int) to big-endian signed short(short)
    public static short toBigEndianShort(int unsignedShort) {
        if(!(0 <= unsignedShort && unsignedShort <= 65535))
            throw new IllegalArgumentException(String.format("Unsigned Short(%d) out of range(0-65535)!", unsignedShort));

        return (short) (unsignedShort > Short.MAX_VALUE ? unsignedShort + Short.MIN_VALUE : unsignedShort);
    }

    // unsigned byte(int) to big-endian byte(byte)
    public static byte toBigEndianByte(int unsignedByte) {
        if(!(0 <= unsignedByte && unsignedByte <= 255))
            throw new IllegalArgumentException(String.format("Unsigned Byte(%d) out of range(0-255)!", unsignedByte));

        return (byte) (unsignedByte > Byte.MAX_VALUE ? unsignedByte + Byte.MIN_VALUE : unsignedByte);
    }

    // big-endian signed short(short) to unsigned short(int)
    public static int bigEndianShortTo(short bigEndianShort) {
        return bigEndianShort >= 0 ? bigEndianShort : bigEndianShort - Short.MIN_VALUE;
    }

    // big-endian byte(byte) to unsigned byte(int)
    public static int bigEndianByteTo(byte bigEndianByte) {
        return bigEndianByte >= 0 ? bigEndianByte : bigEndianByte - Byte.MIN_VALUE;
    }
}
