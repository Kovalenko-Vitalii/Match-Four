//[Match-Four](../../../index.md)/[persistence](../index.md)/[Serializer](index.md)

# Serializer

interface [Serializer](index.md)

Interface for serializing and deserializing objects.

#### Inheritors

| |
|---|
| [JSONSerializer](../-j-s-o-n-serializer/index.md) |
| [XMLSerializer](../-x-m-l-serializer/index.md) |

## Functions

| Name | Summary |
|---|---|
| [read](read.md) | [jvm]<br>abstract fun [read](read.md)(): [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?<br>Reads and deserializes an object from a storage medium. |
| [write](write.md) | [jvm]<br>abstract fun [write](write.md)(obj: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?)<br>Writes an object to a storage medium. |
