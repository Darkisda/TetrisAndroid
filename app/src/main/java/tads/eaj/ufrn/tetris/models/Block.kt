package tads.eaj.ufrn.tetris.models;

import tads.eaj.ufrn.tetris.models.interfaces.InterfaceMoves

abstract class Block(var x: Int, var y: Int) : InterfaceMoves {
    abstract var piece1: Piece
    abstract var piece2: Piece
    abstract var piece3: Piece
    abstract var piece4: Piece

    override fun moveDown() {
        piece1.moveDown()
        piece2.moveDown()
        piece3.moveDown()
        piece4.moveDown()
    }

    override fun moveLeft() {
        piece1.moveLeft()
        piece2.moveLeft()
        piece4.moveLeft()
        piece3.moveLeft()
    }

    override fun moveRight() {
        piece1.moveRight()
        piece2.moveLeft()
        piece4.moveRight()
        piece3.moveRight()
    }
}
