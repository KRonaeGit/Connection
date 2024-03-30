[Go back to Wiki](/wiki.md)
[Go back to util](/util/util.md)
<br>
Port class is made for store Port number data.

Port class has two constructors.
```java
public Port(int port); // When 'int port' out of unsigned short range(0~65535), Throws IllegalArgumentException.
public Port(short rawPort); // 'rawPort' is Big Endian Signed Short(-32768~32767).
```
However, among these two constructors, the first one you will use is cosntructor.
`public Port(int port);`

If you want to save port 80, just use `new Port(80)`.
Also, if you want to save port 1234, you can use `new Port(1234)`.
You can create an instance of Port very easily.

If you want to get the port of Port , you can use the method `public int getPort()` to get the unsigned short port value in int format. This value is the port we often use.

> **Additional, Professional, Difficult**
If you want to get a signed short value in big endian, you can use `public short getRawPort()`.
If you need to use it to store or communicate data, you should use getRawPort() to save memory.
