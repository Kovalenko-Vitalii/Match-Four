package models

import java.util.*

data class Game (var gameField: Array<IntArray> = Array(6, {IntArray(7)}),
                 var isFinished: Boolean = false, var winnerName: String, var time: Date)

