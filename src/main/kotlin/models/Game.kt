package models

data class Game(
    var gameId: String,
    var gameField: Array<IntArray> = Array(6, { IntArray(7) }),
    var winnerName: String,
    var time: Double,
    var opponents: Array<String> = Array(2) { "" }
) {
    override fun toString(): String = "| GAME | id: $gameId | Winner name: $winnerName | Duration: $time minutes"
}
