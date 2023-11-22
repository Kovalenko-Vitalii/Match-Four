package controllers

import models.*
import persistence.Serializer

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

    fun updatePlayer(indexToUpdate: Int, player: Player?): Boolean {
        val foundPlayer = findPlayer(indexToUpdate)

        if ((foundPlayer != null) && (player != null)) {
            foundPlayer.playerName = player.playerName
            return true
        }
        return false
    }


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
            contentToFormat.indexOf(record).toString() + ": " + record.toString() }

    private fun isValidListIndex(index: Int, list: List<Any>): Boolean = index >= 0 && index < list.size

    fun isValidIndexGames(index: Int): Boolean = isValidListIndex(index, games)
    fun isValidIndexPlayers(index: Int): Boolean = isValidListIndex(index, players)

    fun searchGameByTitle(searchString: String) : String =
        formatListString(games.filter {game -> game.winnerName.contains(searchString, ignoreCase = true)})
    fun searchPlayerByTitle(searchString: String) : String =
        formatListString(players.filter {player -> player.playerName.contains(searchString, ignoreCase = true)})

    fun numberOfGames(): Int = games.size

    fun numberOfPlayers(): Int = players.size

    fun findPlayer(index: Int): Player? =
        if (isValidListIndex(index, players)) players[index]
        else null

    @Throws(Exception::class)
    fun load() {
        players = serializer.read() as ArrayList<Player>
        games = serializer.read() as ArrayList<Game>

    }

    @Throws(Exception::class)
    fun save() {
        serializer.write(games)
        serializer.write(players)
    }
}

