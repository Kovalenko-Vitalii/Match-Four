//[Match-Four](../../../index.md)/[persistence](../index.md)/[XMLSerializer](index.md)

# XMLSerializer

[jvm]\
class [XMLSerializer](index.md)(file: [File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html)) : [Serializer](../-serializer/index.md)

A serializer implementation using XML format for object serialization/deserialization.

## Constructors

| | |
|---|---|
| [XMLSerializer](-x-m-l-serializer.md) | [jvm]<br>constructor(file: [File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html)) |

## Functions

| Name | Summary |
|---|---|
| [read](read.md) | [jvm]<br>open override fun [read](read.md)(): [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)<br>Reads and deserializes data from the specified file. |
| [write](write.md) | [jvm]<br>open override fun [write](write.md)(obj: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?)<br>Serializes and writes the provided object to the specified file. |
