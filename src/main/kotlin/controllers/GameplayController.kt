package controllers

import models.Game
import models.Player
import utils.Validator.readNextIntWithLimit



class GameplayController(gameAPI: GameAPI){
    val game = Game(gameAPI.addId(), Array(6,{IntArray(7)}), "", 0.0, arrayOf("", ""))

    fun playGame(player1: Player, player2: Player){
        game.opponents = arrayOf(player1.playerId, player2.playerId)

        player1.gamesPlayed?.add(game.gameId)
        player2.gamesPlayed?.add(game.gameId)

        gameRunning(game, player1, player2)
    }
    fun gameRunning(game: Game, player1: Player, player2: Player) {
        while (true) {
            println(placeToken(1))
            println(placeToken(2))
        }
    }

    fun displayField() {
        println("1 2 3 4 5 6 7")
        for (row in game.gameField) {
            for (cell in row) {
                val symbol = when (cell) {
                    0 -> "_"
                    1 -> "\u001B[31mX\u001b[0m"
                    2 -> "\u001B[34mO\u001b[0m"
                    else -> "$"
                }
                print("$symbol ")
            }
            println()
        }
    }

    fun checkLine() {

    }

    fun placeToken(playerNumber: Int){
        displayField()
        while(true){
            val columnSelected = readNextIntWithLimit("PLAYER $playerNumber: Enter a row (1-7)", 1, 7) - 1
            for(i in game.gameField.size - 1 downTo 0){
                if (game.gameField[i][columnSelected] == 0) {
                    game.gameField[i][columnSelected] = playerNumber
                    checkLine()
                    return
                }
            }
            println("There is no space left in the column!")
        }
    }
}