package kronaegit.connection.data.packet;

import kronaegit.connection.ReadableStream;
import kronaegit.connection.WritableStream;
import kronaegit.connection.data.WritablePacket;
import kronaegit.connection.util.BigEndianParse;
import kronaegit.connection.util.ByteParse;

import java.io.IOException;

/**
 * Big Endian unsigned short packet.
 */
public class UnsignedShortPacket extends ShortPacket {
    public UnsignedShortPacket(int value) {
        super(BigEndianParse.toBigEndianShort(value));
    }
    public int getUnsignedValue() {
        return BigEndianParse.bigEndianShortTo(value);
    }
    @Override
    public String toString() {
        return "UnsignedShortPacket" + "(" + value + ")";
    }
}
