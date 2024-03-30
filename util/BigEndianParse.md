[Go back to Wiki](/wiki.md)
[Go back to util](/util/util.md)
<br>
This class made for parse big endian.

Here is a list of all methods:
```java
short toBigEndianShort(int); // param: unsigned short(0~65535), returns: big endian parsed signed short value(-32768~32767)
short toBigEndianByte(int); // param: unsigned byte(0~255), returns: big endian parsed signed byte value(-128~127)
int bigEndianShortTo(short); // param: big endian parsed signed short value(-32768~32767), returns: unsigned short
int bigEndianByteTo(byte); // param: big endian parsed signed byte value(-32768~32767), returns: unsigned byte
```
These methods are used in the Port class to store the port number as a big endian short.
It can also be used externally for Big Endian data parsing.
