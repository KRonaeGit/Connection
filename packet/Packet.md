[Go back to Wiki](/wiki.md)
<br>
### Packet
- Create Packet easier [class PacketBuilder](/packet/PacketBuilder.md)
- Create custom packets [interface WritablePacket](/packet/WritablePacket.md)*

- Readable [interface ReadableStream](/packet/ReadableStream.md)*
- Make you can read [class ReadingStream](/packet/ReadingStream.md)*
- Writable [interface WritableStream](/packet/WritableStream.md)*
- Make you can write, So I know [class WritingStream](/packet/WritingStream.md)*

The Packet class was created to write the desired packet stored in byte[] format to a WritableStream (e.g. Connector).
Packet has a constructor called `public Packet(byte[] bytes)`.
To make Packet instances easier than byte[], use [class PacketBuilder](/packet/PacketBuilder.md) instead of the constructor.
Packet implements WritablePacket.
So you can `write(WritableStream)`.
