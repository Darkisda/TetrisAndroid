package tads.eaj.ufrn.tetris.models

import tads.eaj.ufrn.tetris.models.interfaces.InterfaceMoves

open class Piece(var x: Int, var y: Int) : InterfaceMoves {
    override fun moveDown() {
        x++
    }
    override fun moveRight() {
        y++
    }
    override fun moveLeft() {
        y--
    }
}