import controllers.*
import models.Game
import models.Player
import persistence.JSONSerializer
import utils.Validator.readNextInt
import utils.Validator.readNextLine
import java.io.File
import java.util.*
import kotlin.system.exitProcess

private val gameAPI = GameAPI(JSONSerializer(File("Match-four.json")))

fun main() {
    load()
    runMenu()
}

fun mainMenu(): Int {
    return readNextInt(
        """ 
         > -------------------------------------------------------------------------------------------------------
         > 
         >  .___  ___.      ___   .___________.  ______  __    __      _______   ______    __    __  .______      
         >  |   \/   |     /   \  |           | /      ||  |  |  |    |   ____| /  __  \  |  |  |  | |   _  \     
         >  |  \  /  |    /  ^  \ `---|  |----`|  ,----'|  |__|  |    |  |__   |  |  |  | |  |  |  | |  |_)  |    
         >  |  |\/|  |   /  /_\  \    |  |     |  |     |   __   |    |   __|  |  |  |  | |  |  |  | |      /     
         >  |  |  |  |  /  _____  \   |  |     |  `----.|  |  |  |    |  |     |  `--'  | |  `--'  | |  |\  \----.
         >  |__|  |__| /__/     \__\  |__|      \______||__|  |__|    |__|      \______/   \______/  | _| `._____|
         >  
         > -------------------------------------------------------------------------------------------------------
         > | MENU:                                |
         > |                                      |
         > |   1) Play a game                     |
         > |   2) Create player                   |
         > |   3) List games                      |
         > |   4) List players                    |
         > |   5) Delete a game/player            |
         > |   6) Update a player                 |
         > |   7) Search a game/player            |
         > ----------------------------------------
         > |   0) Exit                            |
         > ----------------------------------------
         > ==>> """.trimMargin(">")
    )
}
fun runMenu() {
    do {
        when (val option = mainMenu()) {
            1 -> playGame()
            2 -> createPlayer()
            3 -> listAllGames()
            4 -> listAllPlayers()
            5 -> deleteOption()
            6 -> updatePlayer()
            7 -> searchOption()
            0 -> exitApp()
            else -> println("Invalid option entered: $option")
        }
    } while (true)
}

fun playGame() {
    val gameField = Array(6) { IntArray(7) }
    val isFinished = true
    val winnerName = readNextLine("Enter winner name: ")
    val date = Date()

    val newGame = Game(gameField, isFinished, winnerName, date)
    val isAdded = gameAPI.addGame(newGame)

    if (isAdded) {
        println("Added Successfully")
    } else {
        println("Failed! Please try again.")
    }
}
fun createPlayer() {
    while(true) {
        val playerName = readNextLine("Enter Player name: ")
        if (gameAPI.addPlayer(Player(playerName, 0, null))) {
            println("Added Successfully!")
            break
        } else {
            println("Failed! Please try again.")
        }
    }
}

fun listAllGames() {
    println(gameAPI.listAllGames())
}
fun listAllPlayers() {
    println(gameAPI.listAllPlayers())
}

fun deleteOption() {
    do{
        when(val option: Int = readNextInt("""
        |   Delete menu        |
        | Type:                |
        | -------------------- |
        | 1 - to delete game   |
        | 2 - to delete player |
        | 0 - to exit menu     |
        ==>""".trimMargin())) {
            1 -> deleteGame()
            2 -> deletePlayer()
            0 -> break
            else -> println("Invalid option entered: $option")
        }
    } while(true)
}
fun searchOption() {
    do{
        when(val option: Int = readNextInt("""
        |   Search menu        |
        | Type:                |
        | -------------------- |
        | 1 - to search game   |
        | 2 - to search player |
        | 0 - to exit menu     |
        ==>""".trimMargin())) {
            1 -> searchGame()
            2 -> searchPlayer()
            0 -> break
            else -> println("Invalid option entered: $option")
        }
    } while(true)
}

fun deleteGame() {
    listAllGames()
    if(gameAPI.numberOfGames() > 0)
        while (true)
            if (gameAPI.deleteGame(readNextInt("Enter index of game to delete: ")) != null) {
                println("Deleted successfully!")
                break
            } else {
                println("Sorry, could not delete that!")
            }
    else println("No game record found!")
}
fun deletePlayer() {
    listAllPlayers()
    if(gameAPI.numberOfPlayers() > 0)
        while (true)
            if (gameAPI.deletePlayer(readNextInt("Enter index of player to delete: ")) != null) {
                println("Deleted successfully!")
                break
            } else {
                println("Sorry, could not delete that!")
            }
    else println("No player record found!")
}
fun updatePlayer() {
    listAllPlayers()
    if(gameAPI.numberOfPlayers() > 0)
        while (true){
            val indexToUpdate = readNextInt("Enter index of player to update: ")
            if (gameAPI.isValidIndexPlayers(indexToUpdate)) {
                updatePlayerMenu(indexToUpdate)
                break
            } else {
                println("Sorry, could not update that!")
            }}
    else println("No player record found!")
}
fun updatePlayerMenu(indexToUpdate: Int){
    while(true)
        when(readNextInt(""" 
        >  | Enter player update option: |
        >  | 1 - Nickname                |
        >  | 0 - Exit                    |
        ==>""".trimMargin(">"))){
            1 -> gameAPI.updatePlayer(indexToUpdate, Player(readNextLine("Enter new player name: "), 0, null))
            0 -> break
            else -> println("Invalid option entered.")
        }
}
fun searchPlayer() {
    val searchTitle = readNextLine("Enter the description to search by: ")
    val searchResults = gameAPI.searchPlayerByTitle(searchTitle)
    if(searchResults.isEmpty())
        println("Nothing found")
    else
        println(searchResults)
}
fun searchGame() {
    val searchTitle = readNextLine("Enter the description to search by: ")
    val searchResults = gameAPI.searchGameByTitle(searchTitle)
    if(searchResults.isEmpty())
        println("Nothing found")
    else
        println(searchResults)
}

fun save(){
    try {
        gameAPI.save()
    } catch (e: Exception) {
        System.err.println("Error writing to file: $e")
    }
}
fun load(){
    try {
        gameAPI.load()
    } catch (e: Exception) {
        System.err.println("Error loading from file: $e")
    }
}

fun exitApp() {
    save()
    println("Exiting...bye")
    exitProcess(0)
}


