package controllers

import models.Game
import models.Player
import utils.Validator.readNextIntWithLimit



class GameplayController(gameAPI: GameAPI){
    private val game = Game(gameAPI.addId(), Array(6,{IntArray(7)}), "", 0.0, arrayOf("", ""))

    fun playGame(player1: Player, player2: Player): Game{
        game.opponents = arrayOf(player1.playerId, player2.playerId)
        player1.gamesPlayed?.add(game.gameId)
        player2.gamesPlayed?.add(game.gameId)
        gameRunning(player1, player2)
        return game
    }
    private fun gameRunning(player1: Player, player2: Player) {
        while (true) {
            println(placeToken(1))
            if(checkGame()) {player1.gamesWon++; game.winnerName = player1.playerName; break}
            println(placeToken(2))
            if(checkGame()) {player2.gamesWon++; game.winnerName = player2.playerName; break}
        }
    }
    private fun displayField(game: Game) {
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
    private fun checkLine(player: Int): Boolean {
        //Check horizontal
        for (row in 0 until 6) {
            for (col in 0 until 4) {
                if (game.gameField[row][col] == player &&
                    game.gameField[row][col + 1] == player &&
                    game.gameField[row][col + 2] == player &&
                    game.gameField[row][col + 3] == player
                ) {
                    return true
                }
            }
        }
        //Check vertical
        for (col in 0 until 7) {
            for (row in 0 until 3) {
                if (game.gameField[row][col] == player &&
                    game.gameField[row + 1][col] == player &&
                    game.gameField[row + 2][col] == player &&
                    game.gameField[row + 3][col] == player
                ) {
                    return true
                }
            }
        }
        //Check diagonals
        for (row in 0 until 3) {
            for (col in 0 until 4) {
                if (game.gameField[row][col] == player &&
                    game.gameField[row + 1][col + 1] == player &&
                    game.gameField[row + 2][col + 2] == player &&
                    game.gameField[row + 3][col + 3] == player
                ) {
                    return true
                }
            }
        }
        for (row in 3 until 6) {
            for (col in 0 until 4) {
                if (game.gameField[row][col] == player &&
                    game.gameField[row - 1][col + 1] == player &&
                    game.gameField[row - 2][col + 2] == player &&
                    game.gameField[row - 3][col + 3] == player
                ) {
                    return true
                }
            }
        }
        return false
    }
    private fun checkGame(): Boolean{
        if(checkLine(1)){
            println("Player 1 has won this game!")
            return true
        }
        else if(checkLine(2)){
            println("Player 2 has won this game!")
            return true
        }
        return false
    }
    private fun placeToken(playerNumber: Int){
        displayField(game)
        while(true){
            val columnSelected = readNextIntWithLimit("PLAYER $playerNumber: Enter a row (1-7)", 1, 7) - 1
            for(i in game.gameField.size - 1 downTo 0){
                if (game.gameField[i][columnSelected] == 0) {
                    game.gameField[i][columnSelected] = playerNumber
                    displayField(game)
                    return
                }
            }
            println("There is no space left in the column!")
        }
    }
}