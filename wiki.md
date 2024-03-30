Star mark '*' means this is a professional(difficult) part.

### Examples
- Number Communication [Example1](/example/1/)
  - [Server](/example/1/Server.java)
  - [Client](/example/1/Client.java)

## Usage
### Client: [class Connector](/client/Connector.md)
- Write packets [Write](/client/write.md)
- Custom Connector [class Connection](/client/Connection.md)*
### Server: [class Connectable](/server/Connectable.md)
- Write packets [Write](/server/write.md)
- Client Connect Listener [class Connected](/server/Connected.md)*
### Packet: [class Packet](/packet/Packet.md)
- Create Packet easier [class PacketBuilder](/packet/PacketBuilder.md)
- Create custom packets [interface WritablePacket](/packet/WritablePacket.md)*

- Readable [interface ReadableStream](/packet/ReadableStream.md)*
- Make you can read [class ReadingStream](/packet/ReadingStream.md)*
- Writable [interface WritableStream](/packet/WritableStream.md)*
- Make you can write, So I know [class WritingStream](/packet/WritingStream.md)*
### Util [package: util](/util/util.md)
- Port, 0~65535 [class Port](/util/Port.md)
- Object to byte[], byte[] to Object [utility class ByteParse](/util/ByteParse.md)
- Big Endian Parser [utility class BigEndianParse](/util/BigEndianParse.md)*
