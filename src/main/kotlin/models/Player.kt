package models

data class Player (var playerId: String,
                   var playerName: String,
                   var gamesWon: Int,
                   var gamesPlayed: ArrayList<String>?){
    override fun toString(): String = "| PLAYER | id: $playerId | Nickname: $playerName | Games won: $gamesWon"
}