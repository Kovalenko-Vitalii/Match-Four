/**
 * Manages the main functionalities of the game system.
 * - Initializes necessary APIs and controllers.
 * - Provides menus and options for gameplay and player management.
 */
import controllers.GameAPI
import controllers.GameplayController
import controllers.PlayerAPI
import models.Player
import mu.KotlinLogging
import persistance.JSONSerializer
import utilities.Validator.readNextInt
import utilities.Validator.readNextLine
import java.io.File
import kotlin.system.exitProcess

// APIs and controllers for game and player management
private val gameAPI = GameAPI(JSONSerializer(File("saves/", "Games.json")))
private val playerAPI = PlayerAPI(JSONSerializer(File("saves/", "Players.json")))

// private val gameAPI = GameAPI(XMLSerializer(File("saves/", "Games.xml")))
// private val playerAPI = PlayerAPI(XMLSerializer(File("saves/", "Players.xml")))

private var gameplayController: GameplayController? = GameplayController(gameAPI)

// Logger for debug
val logger = KotlinLogging.logger {}

/**
 * Entry point of the application.
 * - Loads necessary data.
 * - Runs the main menu.
 */
fun main() {
    logger.info { "Program executed" }
    load()
    runMenu()
}

/**
 * Displays the main menu options and returns the selected choice.
 * @return Int: User-selected option from the main menu.
 */
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

/**
 * Manages the loop for user interaction with the main menu options.
 * - Processes user selections and executes relevant functions accordingly.
 */
fun runMenu() {
    logger.info { "Running menu logic" }
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

/**
 * Manages the gameplay.
 * - Allows players to play a game if enough players exist in the system.
 */
fun playGame() {
    logger.info { "playGame fun started" }
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
    logger.info { "Game started" }
    gameAPI.addGame(gameplayController!!.playGame(player1, player2))
}

/**
 * Creates a new player and adds them to the system.
 */
fun createPlayer() {
    logger.info { "createdPlayer fun started" }
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

/**
 * Lists all games available in the system.
 */
fun listAllGames() {
    logger.info { "listAllGames fun started" }
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

/**
 * Lists all players registered in the system.
 */
fun listAllPlayers() {
    logger.info { "listAllPlayers fun started" }
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

/**
 * Displays the options available for managing games.
 * @return Int: User-selected option for game management.
 */
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

/**
 * Displays the options available for managing players.
 * @return Int: User-selected option for player management.
 */
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

/**
 * Displays the options for listing players based on different criteria.
 * @return Int: User-selected option for listing players.
 */
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

/**
 * Displays the options for listing games based on different criteria.
 * @return Int: User-selected option for listing games.
 */
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

/**
 * Displays the options for searching games or players.
 */
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

/**
 * Displays the options for updating player information.
 */
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

/**
 * Manages the search functionality for players.
 */
fun searchOption() {
    logger.info { "searchOption fun started" }
    while (true)
        when (searchListOption()) {
            1 -> searchGame()
            2 -> searchPlayer()
            0 -> break
            else -> println("Invalid option entered.")
        }
}

/**
 * Updates the information of a specific player based on the index.
 */
fun updatePlayer(indexToUpdate: Int) {
    logger.info { "updatePlayer fun started" }
    while (true)
        if (playerAPI.isValidIndexPlayers(indexToUpdate)) {
            updatePlayerMenu(indexToUpdate)
            break
        } else {
            println("Sorry, could not update that!")
        }
}

/**
 * Displays the menu for updating player information.
 */
fun updatePlayerMenu(indexToUpdate: Int) {
    logger.info { "updatePlayerMenu fun started" }
    while (true)
        when (updateListPlayer()) {
            1 -> playerAPI.updatePlayerNickname(indexToUpdate, readNextLine("Enter new player name: "))
            0 -> break
            else -> println("Invalid option entered.")
        }
}

/**
 * Searches for a specific player based on user input.
 */
fun searchPlayer() {
    logger.info { "updatePlayerMenu fun stared" }
    val searchResults = playerAPI.searchPlayerByTitle(readNextLine("Enter the description to search by: "))
    if (searchResults.isEmpty()) {
        println("Nothing found!")
    } else {
        println(searchResults)
    }
}

/**
 * Searches for a specific game based on user input.
 */
fun searchGame() {
    logger.info { "searchGame fun started" }
    val searchResults = gameAPI.searchGameByTitle(readNextLine("Enter the description to search by: "))
    if (searchResults.isEmpty()) {
        println("Nothing found")
    } else {
        println(searchResults)
    }
}

/**
 * Allows the user to select a player from the registered players.
 * @return Player: The selected player.
 */
fun selectPlayer(): Player {
    logger.info { "selectPlayer fun started" }
    while (true) {
        val index = readNextInt("Enter a player index:")
        if (playerAPI.isValidIndexPlayers(index)) {
            return playerAPI.findPlayer(index)!!
        } else {
            println("Sorry, invalid index")
        }
    }
}

/**
 * Saves game and player data to the respective files.
 */
fun save() =
    try {
        gameAPI.save()
        playerAPI.save()
    } catch (e: Exception) {
        System.err.println("Error writing to file: $e")
    }

/**
 * Loads game and player data from the respective files.
 */
fun load() =
    try {
        gameAPI.load()
        playerAPI.load()
    } catch (e: Exception) {
        System.err.println("Error loading from file: $e")
    }

/**
 * Handles the application shutdown process.
 * - Saves data and displays a goodbye message before exiting.
 */
fun exitApp() {
    logger.info { "exitApp fun started" }
    save()
    println("Exiting...bye")
    exitProcess(0)
}
