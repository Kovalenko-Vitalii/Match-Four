package controllers

import models.*
import persistence.Serializer

class GameAPI(serializerType: Serializer) {
    private var games = ArrayList<Game>()
    private var serializer: Serializer = serializerType

    fun addGame(game: Game) = games.add(game)
    fun deleteGame(indexToDelete: Int): Game? =
        if(isValidListIndex(indexToDelete, games)) games.removeAt(indexToDelete)
        else null
    fun searchGameByTitle(searchString: String) : String =
        formatListString(games.filter {game -> game.winnerName.contains(searchString, ignoreCase = true)})

    fun listAllGames(): String =
        if(games.isEmpty()) "\u001B[31B No games recorded."
        else formatListString(games)
    fun listGamesByTime(): String =
        if(games.isEmpty()) "\u001B[31B No games recorded."
        else formatListString(games.sortedByDescending { it.time })

    private fun formatListString(contentToFormat: List<Any>): String =
        contentToFormat.joinToString (separator = "\n"){ record ->
            contentToFormat.indexOf(record).toString() + ": " + record.toString() }
    private fun isValidListIndex(index: Int, list: List<Any>): Boolean = index >= 0 && index < list.size
    fun isValidIndexGames(index: Int): Boolean = isValidListIndex(index, games)

    fun numberOfGames(): Int = games.size

    fun generateID(): String{
        return (0..99999).random().toString().padStart(5, '0')
    }

    fun chechkId(newId: String): Boolean{
        return !games.any { it.gameId.contains(newId)}
    }

    fun addId(): String{
        while(true) {
            val newId = generateID()
            if (chechkId(newId)){ return newId }
        }
    }

    @Throws(Exception::class)
    fun load() {
        games = serializer.read() as ArrayList<Game>

    }

    @Throws(Exception::class)
    fun save() {
        serializer.write(games)
    }
}

