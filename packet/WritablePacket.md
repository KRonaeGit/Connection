[Go back to Wiki](/wiki.md)
[Go back to Packet](/packet/Packet.md)
<br>
If you implement WritablePacket, the class becomes a packet that can write data.
You must implement the method `void write(WritableStream stream) throws IOException`.

[class Packet](/packet/Packet.md) also implements WritablePacket.
So you can create custom packets by creating a class that implements WritablePacket.

Below is the code for IntPacket.
This will be helpful when creating custom packets.
```java
public class IntPacket implements WritablePacket {
     protected final int value;
     public IntPacket(int value) {
         this.value = value;
     }
     @Override
     public void write(WritableStream stream) throws IOException {
         stream.write(ByteParse.toBytes(value));
     }
     public int getValue() {
         return value;
     }
     public static IntPacket read(ReadableStream stream) throws IOException {
         int data = ByteParse.toInt(stream.read(4));
         return new IntPacket(data);
     }
     @Override
     public String toString() {
         return "IntPacket" + "(" + value + ")";
     }
}
```
The `write(WritableStream)` method implements code that parses `int value` into byte[] and writes it to WritableStream.
Additionally, the `read(ReadableStream)` method implements code to read an IntPacket from a ReadableStream.

These two features are essential when creating custom packets.
