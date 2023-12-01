package controllers

import models.*
import persistence.Serializer

class PlayerAPI(serializerType: Serializer){
    private var players = ArrayList<Player>()
    private var serializer = serializerType

    fun addPlayer(player: Player) = players.add(player)
    fun deletePlayer(indexToDelete: Int): Player? =
        if(isValidListIndex(indexToDelete, players)) players.removeAt(indexToDelete)
        else null
    fun findPlayer(index: Int): Player? =
        if (isValidListIndex(index, players)) players[index]
        else null
    fun searchPlayerByTitle(searchString: String) : String =
        formatListString(players.filter {player -> player.playerName.contains(searchString, ignoreCase = true)})

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
    fun isValidIndexPlayers(index: Int): Boolean = isValidListIndex(index, players)
    fun numberOfPlayers(): Int = players.size

    fun generateID(): String = (0..99999).random().toString().padStart(5, '0')
    fun chechkId(newId: String): Boolean = !players.any { it.playerId.contains(newId)}

    fun addId(): String{
        while(true) {
            val newId = generateID()
            if (chechkId(newId)){ return newId }
        }
    }
    fun findPlayerById(playerId: String): Player? =
        players.find {it.playerId == playerId}

    fun updatePlayerNickname(indexToUpdate: Int, newName: String){
        findPlayer(indexToUpdate)!!.playerName = newName
    }
    @Throws(Exception::class)
    fun load() {
        players = serializer.read() as ArrayList<Player>
    }
    @Throws(Exception::class)
    fun save() {
        serializer.write(players)
    }
}