package tads.eaj.ufrn.tetris.models

class Block_S_Invert(x: Int, y: Int) : Block(x, y) {
    override var piece1 = Piece(x,y)
    override var piece2 = Piece(x-1,y)
    override var piece3 = Piece(x-1,y-1)
    override var piece4 = Piece(x,y+1)
}