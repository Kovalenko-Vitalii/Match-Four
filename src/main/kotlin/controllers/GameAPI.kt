/**
 * Manages game-related functionalities and data.
 * - Handles game addition, deletion, and searching.
 * - Manages game data serialization.
 * - Provides various methods for retrieving game information.
 * - Allows loading and saving game data.
 */
package controllers

import models.Game
import persistance.Serializer

class GameAPI(serializerType: Serializer) {
    private var games = ArrayList<Game>() // Stores the list of games
    private var serializer: Serializer = serializerType // Serializer for game data

    /**
     * Adds a game to the list of games.
     */
    fun addGame(game: Game) = games.add(game)

    /**
     * Deletes a game from the list of games based on the provided index.
     * @return Game? The deleted game or null if deletion fails.
     */
    fun deleteGame(indexToDelete: Int): Game? =
        if (isValidListIndex(indexToDelete, games)) {
            games.removeAt(indexToDelete)
        } else {
            null
        }

    /**
     * Searches for games based on a provided title.
     * @return String A formatted string containing the search results.
     */
    fun searchGameByTitle(searchString: String): String =
        formatListString(games.filter { game -> game.winnerName.contains(searchString, ignoreCase = true) })

    /**
     * Lists all games stored in the system.
     * @return String A formatted string containing all game records.
     */
    fun listAllGames(): String =
        if (games.isEmpty()) {
            "\u001B[31B No games recorded."
        } else {
            formatListString(games)
        }

    /**
     * Lists games sorted by time.
     * @return String A formatted string containing games sorted by time.
     */
    fun listGamesByTime(): String =
        if (games.isEmpty()) {
            "\u001B[31B No games recorded."
        } else {
            formatListString(games.sortedByDescending { it.time })
        }

    // Private helper methods

    /**
     * Formats a list of records into a string.
     * @return String A formatted string with indexed records.
     */
    private fun formatListString(contentToFormat: List<Any>): String =
        contentToFormat.joinToString(separator = "\n") { record ->
            contentToFormat.indexOf(record).toString() + ": " + record.toString()
        }

    /**
     * Checks if the provided index is valid within the list bounds.
     * @return Boolean Indicates if the index is valid.
     */
    private fun isValidListIndex(index: Int, list: List<Any>): Boolean = index >= 0 && index < list.size

    /**
     * Checks if the index provided is valid for games.
     * @return Boolean Indicates if the index is valid for games.
     */
    private fun isValidIndexGames(index: Int): Boolean = isValidListIndex(index, games)

    /**
     * Retrieves the total number of games recorded.
     * @return Int The total number of games.
     */
    fun numberOfGames(): Int = games.size

    /**
     * Retrieves a game based on the provided index.
     * @return Game? The game or null if the index is invalid.
     */
    fun getGameById(index: Int): Game? {
        return if (isValidIndexGames(index)) {
            games[index]
        } else {
            null
        }
    }

    // ID generation and validation

    /**
     * Generates a random ID for a game.
     * @return String A randomly generated ID for a game.
     */
    private fun generateID(): String = (0..99999).random().toString().padStart(5, '0')

    /**
     * Checks if a provided ID is unique among games.
     * @return Boolean Indicates if the ID is unique.
     */
    private fun checkId(newId: String): Boolean = !games.any { it.gameId.contains(newId) }

    /**
     * Generates and adds a unique ID to a game.
     * @return String A unique ID for a game.
     */
    fun addId(): String {
        while (true) {
            val newId = generateID()
            if (checkId(newId)) { return newId }
        }
    }

    // Data loading and saving

    /**
     * Loads game data from the serializer.
     * @throws Exception If there's an error in the loading process.
     */
    @Throws(Exception::class)
    fun load() {
        games = serializer.read() as ArrayList<Game>
    }

    /**
     * Saves game data using the serializer.
     * @throws Exception If there's an error in the saving process.
     */
    @Throws(Exception::class)
    fun save() {
        serializer.write(games)
    }
}
