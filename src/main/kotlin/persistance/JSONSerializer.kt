package persistence

import com.thoughtworks.xstream.XStream
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver
import models.Game
import models.Player
import java.io.File
import java.io.FileReader
import java.io.FileWriter

/**
 * A serializer implementation using JSON format for object serialization/deserialization.
 *
 * @property file The file where data will be stored.
 */
class JSONSerializer(private val file: File) : Serializer {

    /**
     * Reads and deserializes data from the specified file.
     *
     * @return The deserialized data.
     * @throws Exception If there is an error during the reading process.
     */
    @Throws(Exception::class)
    override fun read(): Any {
        val xStream = XStream(JettisonMappedXmlDriver())
        xStream.allowTypes(arrayOf(Game::class.java, Player::class.java))
        val inputStream = xStream.createObjectInputStream(FileReader(file))
        val obj = inputStream.readObject() as Any
        inputStream.close()
        return obj
    }

    /**
     * Serializes and writes the provided object to the specified file.
     *
     * @param obj The object to be serialized and written.
     * @throws Exception If there is an error during the writing process.
     */
    @Throws(Exception::class)
    override fun write(obj: Any?) {
        val xStream = XStream(JettisonMappedXmlDriver())
        val outputStream = xStream.createObjectOutputStream(FileWriter(file))
        outputStream.writeObject(obj)
        outputStream.close()
    }
}
