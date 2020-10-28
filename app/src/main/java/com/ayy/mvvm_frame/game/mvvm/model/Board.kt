package com.ayy.mvvm_frame.game.mvvm.model

/**
 * model:数据和操作数据，不依赖视图
 */
class Board() {

    companion object {
        const val FINISHED = "finished"
        const val PLAYING = "playing"
    }

    //两个棋手：x、o
    lateinit var currentPlayer: Player
    var winner: Player? = null

    //记录当前游戏的状态：游戏中，结束
    private var state = PLAYING
    private var surplusCells = 9

    //9个棋格
    private var cells = Array(3) { arrayOfNulls<Player>(3) }


    init {
        reset()
    }

    /**
     * 重置棋盘
     */
    private fun reset() {
        //重置数据
        currentPlayer = Player.X
        winner = null
        state = PLAYING
    }

    /**
     * 切换棋手
     */
    fun switchPlayer() {
        currentPlayer = if (currentPlayer == Player.X) Player.O else Player.X
    }

    fun isFinished(): Boolean {
        return state == FINISHED
    }

    /**
     * 判断游戏结果
     */
    private fun handleGameResult(row: Int, column: Int) {
        //棋格满，无赢者，则平手
        if ((cells[row][0] == currentPlayer && cells[row][1] == currentPlayer && cells[row][2] == currentPlayer)
            || (cells[0][column] == currentPlayer && cells[1][column] == currentPlayer && cells[2][column] == currentPlayer)
            || (cells[0][0] == currentPlayer && cells[1][1] == currentPlayer && cells[2][2] == currentPlayer)
            || (cells[0][2] == currentPlayer && cells[1][1] == currentPlayer && cells[2][0] == currentPlayer)
        ) {
            state = FINISHED
            winner = currentPlayer
            return
        }
        if (surplusCells <= 0) {
            state = FINISHED
        }
    }

    /**
     * 判断棋格是否有效
     */
    private fun isValidCell(row: Int, column: Int): Boolean {
        if (cells[row][column] == null) {
            return true
        }
        return false
    }

    /**
     * 记录数据
     */
    fun mark(row: Int, column: Int): Boolean {
        if (state == FINISHED) {
            return false
        }
        if (!isValidCell(row, column)) {
            return false
        }
        cells[row][column] = currentPlayer
        surplusCells--
        handleGameResult(row, column)
        return true
    }
}