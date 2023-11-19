package models

data class Player (var playerName: String,
                   var gamesWon: Int,
                   var gamesPlayed: ArrayList<Game>?)