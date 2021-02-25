package tads.eaj.ufrn.tetris.models

interface IPiece {
    abstract fun moveDown()
    abstract fun moveRight()
    abstract fun moveLeft()
}

class Piece(var x: Int, var y: Int) : IPiece {

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