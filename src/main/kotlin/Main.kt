import controllers.GameAPI
import controllers.GameplayController
import controllers.PlayerAPI
import models.Player
import persistence.JSONSerializer
import utils.Validator.readNextInt
import utils.Validator.readNextLine
import java.io.File
import kotlin.system.exitProcess

private val gameAPI = GameAPI(JSONSerializer(File("Games.json")))
private val playerAPI = PlayerAPI(JSONSerializer(File("Players.json")))
private var gameplayController: GameplayController? = GameplayController(gameAPI)

fun main() {
    load()
    runMenu()
}

fun mainMenu(): Int =
    readNextInt(
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
         > |   5) Search a game/player            |
         > ----------------------------------------
         > |   0) Exit                            |
         > ----------------------------------------
         > ==>> """.trimMargin(">")
    )
fun runMenu() {
    while (true)
        when (val option = mainMenu()) {
            1 -> playGame()
            2 -> createPlayer()
            3 -> listAllGames()
            4 -> listAllPlayers()
            5 -> searchOption()
            0 -> exitApp()
            else -> println("Invalid option entered: $option")
        }
}

fun playGame() {
    println(playerAPI.listAllPlayers())
    if (playerAPI.numberOfPlayers() < 2) {
        println("There is no enough players in the system!")
        return
    }
    var player1: Player
    var player2: Player
    while (true) {
        player1 = selectPlayer()
        player2 = selectPlayer()
        if (player1.playerId != player2.playerId) {
            break
        } else {
            println("Selected players have identical IDs!")
        }
    }
    gameAPI.addGame(gameplayController!!.playGame(player1, player2))
}
fun createPlayer() {
    while (true) {
        val playerName = readNextLine("Enter Player name: ")
        if (playerAPI.addPlayer(Player(playerAPI.addId(), playerName, 0, null))) {
            println("Added Successfully!")
            break
        } else {
            println("Failed! Please try again.")
        }
    }
}

fun listAllGames() {
    while (true) {
        when (gameListOptions()) {
            1 -> println(gameAPI.listAllGames())
            2 -> println(gameAPI.listGamesByTime())
            0 -> break
            else -> println("Invalid option")
        }
        val selectedIndex = readNextInt(" Enter index of player to select or -1 to exit:")
        if (playerAPI.isValidIndexPlayers(selectedIndex)) {
            while (true)
                when (gameOptions()) {
                    1 -> gameAPI.deleteGame(selectedIndex)
                    2 -> gameplayController!!.displayField(gameAPI.getGameById(selectedIndex))
                    0 -> break
                    else -> println("Invalid option")
                }
        } else if (selectedIndex == -1) {
            break
        } else {
            println("Invalid index!")
        }
    }
}
fun listAllPlayers() {
    while (true) {
        when (playerListOptions()) {
            1 -> println(playerAPI.listAllPlayers())
            2 -> println(playerAPI.listPlayersByVictoriesAmount())
            3 -> println(playerAPI.listPlayersByGamesPlayed())
            0 -> break
            else -> println("Invalid option")
        }
        val selectedIndex = readNextInt(" Enter index of player to select or -1 to exit:")

        if (playerAPI.isValidIndexPlayers(selectedIndex)) {
            while (true)
                when (playerOptions()) {
                    1 -> playerAPI.deletePlayer(selectedIndex)
                    2 -> updatePlayer(selectedIndex)
                    0 -> break
                    else -> println("Invalid option")
                }
        } else if (selectedIndex == -1) {
            break
        } else {
            println("Invalid index!")
        }
    }
}

fun gameOptions(): Int = readNextInt(
    """
        > |-----------------|
        > |  Selected menu  |
        > |-----------------|
        > |  1 - delete     |
        > |  2 - view game  |
        > |  0 - exit       |
        > |-----------------|
        > ==>""".trimMargin(">")
)
fun playerOptions(): Int = readNextInt(
    """
        > |-----------------|
        > |  Selected menu  |
        > |-----------------|
        > |  1 - delete     |
        > |  2 - update     |
        > |  0 - exit       |
        > |-----------------|
        > ==>""".trimMargin(">")
)
fun playerListOptions(): Int = readNextInt(
    """
        > |--------------------------------------------|
        > |  Select list option                        |
        > |--------------------------------------------|
        > | 1 - list all players                       |
        > | 2 - list players by amount of victories    |
        > | 3 - list players by amount of games played |
        > | 0 - exit                                   |
        > |--------------------------------------------|
        > ==>
    """.trimMargin(">")
)
fun gameListOptions(): Int = readNextInt(
    """
        > |--------------------------------------------|
        > |  Select list option                        |
        > |--------------------------------------------|
        > | 1 - list all games                         |
        > | 2 - list games by lowest time              |
        > | 0 - exit                                   |
        > |--------------------------------------------|
        > ==>
    """.trimMargin(">")
)
fun searchListOption(): Int = readNextInt(
    """
        > |----------------------|
        > |   Search menu        |
        > | Type:                |
        > | -------------------- |
        > | 1 - to search game   |
        > | 2 - to search player |
        > | 0 - to exit menu     |
        > | ---------------------|
        > ==>""".trimMargin(">")
)
fun updateListPlayer(): Int = readNextInt(
    """ 
        >  |-----------------------------|
        >  | Player update menu          |
        >  |-----------------------------|
        >  | 1 - Nickname                |
        >  | 0 - Exit                    |
        >  |-----------------------------|
        ==>""".trimMargin(">")
)

fun searchOption() {
    while (true)
        when (searchListOption()) {
            1 -> searchGame()
            2 -> searchPlayer()
            0 -> break
            else -> println("Invalid option entered.")
        }
}

fun updatePlayer(indexToUpdate: Int) {
    while (true)
        if (playerAPI.isValidIndexPlayers(indexToUpdate)) {
            updatePlayerMenu(indexToUpdate)
            break
        } else {
            println("Sorry, could not update that!")
        }
}
fun updatePlayerMenu(indexToUpdate: Int) {
    while (true)
        when (updateListPlayer()) {
            1 -> playerAPI.updatePlayerNickname(indexToUpdate, readNextLine("Enter new player name: "))
            0 -> break
            else -> println("Invalid option entered.")
        }
}

fun searchPlayer() {
    val searchResults = playerAPI.searchPlayerByTitle(readNextLine("Enter the description to search by: "))
    if (searchResults.isEmpty()) {
        println("Nothing found!")
    } else {
        println(searchResults)
    }
}
fun searchGame() {
    val searchResults = gameAPI.searchGameByTitle(readNextLine("Enter the description to search by: "))
    if (searchResults.isEmpty()) {
        println("Nothing found")
    } else {
        println(searchResults)
    }
}

fun selectPlayer(): Player {
    while (true) {
        val index = readNextInt("Enter a player index:")
        if (playerAPI.isValidIndexPlayers(index)) {
            return playerAPI.findPlayer(index)!!
        } else {
            println("Sorry, invalid index")
        }
    }
}

fun save() =
    try {
        gameAPI.save()
        playerAPI.save()
    } catch (e: Exception) {
        System.err.println("Error writing to file: $e")
    }
fun load() =
    try {
        gameAPI.load()
        playerAPI.load()
    } catch (e: Exception) {
        System.err.println("Error loading from file: $e")
    }

fun exitApp() {
    save()
    println("Exiting...bye")
    exitProcess(0)
}
