package controllers

import models.*
import persistence.Serializer
import kotlin.random.Random

class PlayerAPI(serializerType: Serializer){
    private var players = ArrayList<Player>()
    private var serializer: Serializer = serializerType

    fun addPlayer(player: Player) = players.add(player)
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

    fun generateID(): String{
        return (0..99999).random().toString().padStart(5, '0')
    }
    fun chechkId(newId: String): Boolean{
        return !players.any { it.playerId.contains(newId)}
    }
    fun addId(): String{
        while(true) {
            val newId = generateID()
            if (chechkId(newId)){ return newId }
        }
    }
    fun findPlayerById(playerId: String): Player? =
        players.find {it.playerId == playerId}

    fun selectPlayer(){

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