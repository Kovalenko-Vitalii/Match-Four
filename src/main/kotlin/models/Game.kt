package models

import java.util.*

data class Game (var gameField: Array<IntArray> = Array(6, {IntArray(7)}),
                 var isFinished: Boolean = false, var winnerName: String, var time: Date){
    override fun toString(): String {
        return "WINNER: $winnerName TIME: $time"
    }
}

