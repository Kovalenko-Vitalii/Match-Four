package controllers

import persistance.Serializer
import models.*

class GameAPI(serializerType: Serializer) {
    private var games = ArrayList<Game>()
    private var players = ArrayList<Player>()
    private var serializer: Serializer = serializerType

    fun addGame(game: Game) = games.add(game)

    fun addPlayer(player: Player) = players.add(player)

    fun deleteGame(indexToDelete: Int): Game? =
        if(isValidListIndex(indexToDelete, games)) games.removeAt(indexToDelete)
        else null

    fun deletePlayer(indexToDelete: Int): Player? =
        if(isValidListIndex(indexToDelete, players)) players.removeAt(indexToDelete)
        else null

    fun listAllGames(): String =
        if(games.isEmpty()) "\u001B[31B No games recorded."
        else formatListString(games)

    fun listGamesByTime(): String =
        if(games.isEmpty()) "\u001B[31B No games recorded."
        else formatListString(games.sortedByDescending { it.time })

    fun listAllPlayers(): String =
        if(players.isEmpty()) "\u001B[31B No players recorded."
        else formatListString(players)

    fun listPlayersByVictoriesAmount(): String =
        if(players.isEmpty()) "\u001B[31B No players recorded."
        else formatListString(players.sortedByDescending { it.gamesWon })

    fun listPlayersByGamesPlayed(): String =
        if(players.isEmpty()) "\u001B[31B No players recorded."
        else formatListString(players.sortedByDescending { it.gamesPlayed?.size })


    private fun formatListString(contentToFormat: List<Any>): String =
        contentToFormat.joinToString (separator = "\n"){ record ->
            contentToFormat.indexOf(record).toString() + ": " + record.toString()
        }

    private fun isValidListIndex(index: Int, list: List<Any>): Boolean = index >= 0 && index < list.size

    private fun isValidListIndex(index: Int): Boolean = isValidListIndex(index, games)

    fun numberOfGames(): Int = games.size

    fun numberOfPlayers(): Int = players.size

    @Throws(Exception::class)
    fun load() {
        // games = serializer.read() as ArrayList<Game>
    }

    @Throws(Exception::class)
    fun save() {
        // serializer.write(games)
    }
}

