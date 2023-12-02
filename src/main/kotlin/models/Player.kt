/**
 * Represents a player entity.
 * - Contains player-specific attributes such as ID, name, number of games won, and list of games played.
 * - Provides a string representation of a player instance.
 */
package models

data class Player(
    var playerId: String, // Unique ID for the player
    var playerName: String, // Name of the player
    var gamesWon: Int, // Number of games won by the player
    var gamesPlayed: ArrayList<String>? // List of game IDs played by the player
) {
    /**
     * Provides a string representation of a player instance.
     * @return String A formatted string describing the player.
     */
    override fun toString(): String = "| PLAYER | id: $playerId | Nickname: $playerName | Games won: $gamesWon"
}
