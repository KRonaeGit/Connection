package kronaegit.connection;

import java.io.IOException;
import java.util.Arrays;

public class ReadingStream extends WritingStream implements ReadableStream {
    @Override
    public byte[] read(int capacity) throws IOException {
        if (buffer.length < capacity) {
            byte[] data = buffer;
            buffer = new byte[0];
            return data;
        }

        byte[] data = Arrays.copyOfRange(buffer, 0, capacity);

        byte[] remainingData = new byte[buffer.length - capacity];
        System.arraycopy(buffer, capacity, remainingData, 0, buffer.length - capacity);
        buffer = remainingData;

        return data;
    }
}
