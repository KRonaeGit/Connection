Star mark '*' means this is a professional(difficult) part.

### Examples
- Number Communication [Example1](example/1/)
  - [Server](example/1/Server.java)
  - [Client](example/1/Client.java)

## Usage
### Client: [class Connector](Connector.md)
- Write packets [Write](client/write.md)
- Custom Connector [class Connection](Connection.md)*
### Server: [class Connectable](Connectable.md)
- Write packets [Write](server/write.md)
- Client Connect Listener [class Connected](Connected.md)*
### Packet: [class Packet](Packet.md)
- Create Packet easier [class PacketBuilder](packet/PacketBuilder.md)
- Create your CUSTOM packet [CustomPacket](packet/custom.md)*
  - Important to create custom packets [interface WritablePacket](packet/WritablePacket.md)*

- Readable [interface ReadableStream](packet/ReadableStream.md)*
- Make you can read [class ReadingStream](packet/ReadingStream.md)*
- Writable [interface WritableStream](packet/WritableStream.md)*
- Make you can write, So I know [class WritingStream](packet/WritingStream.md)*
### Util [package: util](util.md)
- Port, 0~65535 [class Port](util/Port.md)
- Object to byte[], byte[] to Object[utility class ByteParse](util/ByteParse.md)
- Big Endian Parser [utility class BigEndianParse](util/bigendianparse.md)*
