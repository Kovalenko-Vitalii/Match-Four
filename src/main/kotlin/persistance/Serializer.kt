package persistence

/**
 * Interface for serializing and deserializing objects.
 */
interface Serializer {

    /**
     * Writes an object to a storage medium.
     *
     * @param obj The object to be serialized and written.
     * @throws Exception If there is an error during the writing process.
     */
    @Throws(Exception::class)
    fun write(obj: Any?)

    /**
     * Reads and deserializes an object from a storage medium.
     *
     * @return The deserialized object.
     * @throws Exception If there is an error during the reading process.
     */
    @Throws(Exception::class)
    fun read(): Any?
}