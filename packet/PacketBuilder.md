[Go back to Wiki](/wiki.md)
[Go back to util](/util/util.md)
<br>
PacketBuilder is used to create Packet instances.

You can modify the `PacketBuilder` instance created through `new PacketBuilder()` with `builder-pattern` and then create a Packet instance by `build()`.
The `add` method is a method that adds data to the back,
The `put` method is a method that modifies data at a specified location.
The `put` method raises an error if the specified position is out of range.
The `allocate` method is used to allocate more space as much as `int capacity`.

Below is example code using PacketBuilder.
```java
Packet packet = new PacketBuilder()
  .add(1234) // Adds integer data
  .add(0.9876) // Adds float data
  .put(0, -128) // Set the first byte data to -128.
  .build(); // Build it to create Packet instance.
packet.write(...);
// Now you can use Packet instance 'packet'!
```
