package models

data class Player (var playerName: String,
                   var gamesWon: Int,
                   var gamesPlayed: ArrayList<Game>?){
    override fun toString(): String {
        return "PLAYER: $playerName GAMES WON: $gamesWon GAMES PLAYED: $gamesPlayed"
    }
}