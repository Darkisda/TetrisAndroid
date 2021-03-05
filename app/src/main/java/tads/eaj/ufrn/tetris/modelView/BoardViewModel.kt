package tads.eaj.ufrn.tetris.modelView

import androidx.lifecycle.ViewModel

class BoardViewModel : ViewModel() {

    val LINE = 36
    val COL = 26

    var points = 0
    var board = Array(LINE) {
        Array(COL){0}
    }
}