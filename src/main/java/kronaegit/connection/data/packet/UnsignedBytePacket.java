package kronaegit.connection.data.packet;

import kronaegit.connection.util.BigEndianParse;

/**
 * Big Endian unsigned short packet.
 */
public class UnsignedBytePacket extends BytePacket {
    public UnsignedBytePacket(int value) {
        super(BigEndianParse.toBigEndianByte(value));
    }
    public int getUnsignedValue() {
        return BigEndianParse.bigEndianByteTo(value);
    }
    @Override
    public String toString() {
        return "UnsignedBytePacket" + "(" + value + ")";
    }
}
