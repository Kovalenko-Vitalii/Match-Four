/**
 * Represents a game entity.
 * - Contains game-specific attributes such as ID, game field, winner name, duration, and opponents.
 * - Provides a string representation of a game instance.
 */
package models

data class Game(
    var gameId: String, // Unique ID for the game
    var gameField: Array<IntArray> = Array(6, { IntArray(7) }), // Represents the game field
    var winnerName: String, // Name of the game winner
    var time: Double, // Duration of the game in minutes
    var opponents: Array<String> = Array(2) { "" } // Array containing opponent IDs
) {
    /**
     * Provides a string representation of a game instance.
     * @return String A formatted string describing the game.
     */
    override fun toString(): String = "| GAME | id: $gameId | Winner name: $winnerName | Duration: $time minutes"
}
