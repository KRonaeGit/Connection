[Go back to Wiki](/wiki.md)
[Go back to util](/util/util.md)
<br>
ByteParse class is made for parse byte array to data type, or parse data type to byte array.

All methods in this class are:
```java
public static byte[] toBytes(String);
public static byte[] toBytes(String, Charset);
public static byte[] toBytes(short);
public static byte[] toBytes(int);
public static byte[] toBytes(float);
public static byte[] toBytes(long);
public static byte[] toBytes(double);

public static short toShort(byte[] bytes, int off);
public static short toShort(byte[] bytes);
public static int toInt(byte[] bytes, int off);
public static int toInt(byte[] bytes);
public static float toFloat(byte[] bytes, int off);
public static float toFloat(byte[] bytes);
public static long toLong(byte[] bytes, int off);
public static long toLong(byte[] bytes);
public static double toDouble(byte[] bytes, int off);
public static double toDouble(byte[] bytes);
public static String toString(byte[] bytes, int off);
public static String toString(byte[] bytes);
```
Each method converts the data type to byte[], or
Performs the task of converting byte[] to a data type.

These methods are useful when communicating or storing data.
