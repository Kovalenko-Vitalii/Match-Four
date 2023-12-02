/**
 * Manages player-related functionalities and data.
 * - Handles player addition, deletion, and searching.
 * - Manages player data serialization.
 * - Provides various methods for retrieving player information.
 * - Allows loading and saving player data.
 */
package controllers

import models.Player
import persistance.Serializer

class PlayerAPI(serializerType: Serializer) {
    private var players = ArrayList<Player>() // Stores the list of players
    private var serializer = serializerType // Serializer for player data

    /**
     * Adds a player to the list of players.
     */
    fun addPlayer(player: Player) = players.add(player)

    /**
     * Deletes a player from the list of players based on the provided index.
     * @return Player? The deleted player or null if deletion fails.
     */
    fun deletePlayer(indexToDelete: Int): Player? =
        if (isValidListIndex(indexToDelete, players)) {
            players.removeAt(indexToDelete)
        } else {
            null
        }

    /**
     * Finds and returns a player based on the provided index.
     * @return Player? The found player or null if not found.
     */
    fun findPlayer(index: Int): Player? =
        if (isValidListIndex(index, players)) {
            players[index]
        } else {
            null
        }

    /**
     * Searches for players based on a provided title.
     * @return String A formatted string containing the search results.
     */
    fun searchPlayerByTitle(searchString: String): String =
        formatListString(players.filter { player -> player.playerName.contains(searchString, ignoreCase = true) })

    /**
     * Lists all players stored in the system.
     * @return String A formatted string containing all player records.
     */
    fun listAllPlayers(): String =
        if (players.isEmpty()) {
            "\u001B[31B No players recorded."
        } else {
            formatListString(players)
        }

    // Methods for listing players by different criteria

    /**
     * Lists players sorted by victories in descending order.
     * @return String A formatted string containing players sorted by victories.
     */
    fun listPlayersByVictoriesAmount(): String =
        if (players.isEmpty()) {
            "\u001B[31B No players recorded."
        } else {
            formatListString(players.sortedByDescending { it.gamesWon })
        }

    /**
     * Lists players sorted by the number of games played in descending order.
     * @return String A formatted string containing players sorted by games played.
     */
    fun listPlayersByGamesPlayed(): String =
        if (players.isEmpty()) {
            "\u001B[31B No players recorded."
        } else {
            formatListString(players.sortedByDescending { it.gamesPlayed?.size })
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
    fun isValidIndexPlayers(index: Int): Boolean = isValidListIndex(index, players)

    // Other functionalities for player management

    /**
     * @return Int number of players in the system.
     */
    fun numberOfPlayers(): Int = players.size

    /**
     * Generates a random ID for a player.
     * @return String A randomly generated ID for a player.
     */
    private fun generateID(): String = (0..99999).random().toString().padStart(5, '0')

    /**
     * Checks if a provided ID is unique among players.
     * @return Boolean Indicates if the ID is unique.
     */
    private fun checkId(newId: String): Boolean = !players.any { it.playerId.contains(newId) }

    /**
     * Generates and adds a unique ID to a player.
     * @return String A unique ID for a player.
     */
    fun addId(): String {
        while (true) {
            val newId = generateID()
            if (checkId(newId)) { return newId }
        }
    }

    // Methods for finding and updating player information

    /**
     * Finds a player based on their ID.
     * @return Player? The found player or null if not found.

     fun findPlayerById(playerId: String): Player? =
     players. find { it.playerId == playerId }
     */

    /**
     * Updates a player's nickname based on the provided index.
     */
    fun updatePlayerNickname(indexToUpdate: Int, newName: String) {
        findPlayer(indexToUpdate)!!.playerName = newName
    }

    // Methods for data loading and saving

    /**
     * Loads player data from the serializer.
     * @throws Exception If there's an error in the loading process.
     */
    @Throws(Exception::class)
    fun load() {
        players = serializer.read() as ArrayList<Player>
    }

    /**
     * Saves player data using the serializer.
     * @throws Exception If there's an error in the saving process.
     */
    @Throws(Exception::class)
    fun save() {
        serializer.write(players)
    }
}
