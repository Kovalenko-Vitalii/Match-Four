import controllers.*
import models.Game
import models.Player
import persistence.JSONSerializer
import utils.Validator.readNextInt
import utils.Validator.readNextLine
import java.io.File
import java.util.*
import kotlin.collections.ArrayList


private val gameAPI = GameAPI(JSONSerializer(File("Match-four.json")))

fun main() {
    runMenu()
    mainMenu()
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
         > |   3) List all games                  |
         > |   4) Delete a game/player            |
         > |   5) Update a game/player            |
         > |   6) Search a game/player            |
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
            4 -> deleteOption()
            5 -> updateOption()
            6 -> searchOption()
            0 -> exitApp()
            else -> println("Invalid option entered: $option")
        }
    } while (true)
}

fun playGame() {
    val gameField = Array(6) { IntArray(7) }
    val isFinished = true
    val winnerName = readln()
    val date = Date()

    val newGame = Game(gameField, isFinished, winnerName, date)
    val isAdded = gameAPI.addGame(newGame)

    if (isAdded) {
        println("Added Successfully")
    } else {
        println("Add Failed")
    }
}

fun createPlayer() {
    val playerName = readNextLine("Enter Player name")

    val addPlayer = Player(playerName, 0, null)
}

fun listAllGames() {
    gameAPI.listAllGames()
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
        """.trimMargin())) {
            1 -> deleteGame()
            2 -> deletePlayer()
            0 -> break
            else -> println("Invalid option entered: $option")
        }
    } while(true)
}

fun deleteGame() {

}

fun deletePlayer() {

}

fun updateOption() {

}

fun searchOption() {

}

fun exitApp() {

}


