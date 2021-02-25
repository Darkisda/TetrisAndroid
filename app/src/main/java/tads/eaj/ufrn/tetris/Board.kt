package tads.eaj.ufrn.tetris

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import tads.eaj.ufrn.tetris.databinding.BoardBinding
import tads.eaj.ufrn.tetris.models.Game
import tads.eaj.ufrn.tetris.models.Piece
import tads.eaj.ufrn.tetris.utils.constants.Constants
import tads.eaj.ufrn.tetris.utils.constants.Difficulties

class Board : AppCompatActivity() {

    val LINHA = 36
    val COLUNA = 26
    var running = true
    var speed:Long = 300

    lateinit var binding: BoardBinding

    lateinit var game: Game

    var board = Array(LINHA) {
        Array(COLUNA){0}
    }

    var pt = Piece(0,15)

    var boardView = Array(LINHA){
        arrayOfNulls<ImageView>(COLUNA)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.board)

        binding = DataBindingUtil.setContentView(this, R.layout.board)

        var settings = getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE)

        binding.apply {

            gridBoardLayout.rowCount = LINHA
            gridBoardLayout.columnCount = COLUNA

            val inflater = LayoutInflater.from(this@Board)

            for (i in 0 until LINHA) {
                for (j in 0 until COLUNA) {
                    boardView[i][j] = inflater.inflate(R.layout.inflate_image_view, gridBoardLayout, false) as ImageView
                    gridBoardLayout.addView( boardView[i][j])
                }
            }

            val params = intent.extras
            val boardParams = params!!.getString(Constants.BOARD)

            var difficulty = settings.getString(Difficulties.DIFFICULTY, Difficulties.EASY)

            game = Game(difficulty!!)
        }
        gameRun()
    }

    private fun gameRun(){
        Thread{
            while(running){
                Thread.sleep(speed)
                runOnUiThread{
                    //limpa tela
                    for (i in 0 until LINHA) {
                        for (j in 0 until COLUNA) {
                            boardView[i][j]!!.setImageResource(R.drawable.black)
                        }
                    }
                    //move peça atual
                    pt.moveDown()
                    //print peça
                    try {
                        boardView[pt.x][pt.y]!!.setImageResource(R.drawable.white)
                    }catch (e:ArrayIndexOutOfBoundsException ) {
                        //se a peça passou das bordas eu vou parar o jogo
                        running = false
                    }

                }
            }
        }.start()
    }
}