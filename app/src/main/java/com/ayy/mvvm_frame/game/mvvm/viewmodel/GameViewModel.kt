package com.ayy.mvvm_frame.game.mvvm.viewmodel

import androidx.databinding.ObservableArrayMap
import androidx.databinding.ObservableField
import com.ayy.mvvm_frame.game.mvvm.model.Board

class GameViewModel {
    var cellData: ObservableArrayMap<String, String> = ObservableArrayMap()
    var model: Board = Board()
    var result: ObservableField<String> = ObservableField()

    fun reset() {
        cellData.clear()
        result.set("")
    }

    fun clickCell(row: Int, column: Int) {
        if (model.isFinished()) {
            return
        }
        if (model.mark(row, column)) {
            cellData.put("${row}${column}", model.currentPlayer.toString())
            if (model.isFinished()) {
                result.set(if (model.winner == null) "平手" else "${model.winner!!} 赢了")
            } else {
                model.switchPlayer()
            }
        }
    }
}