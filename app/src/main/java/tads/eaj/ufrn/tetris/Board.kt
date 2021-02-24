package tads.eaj.ufrn.tetris

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import tads.eaj.ufrn.tetris.databinding.BoardBinding
import tads.eaj.ufrn.tetris.models.Game
import tads.eaj.ufrn.tetris.utils.constants.Constants
import tads.eaj.ufrn.tetris.utils.constants.Difficulties

class Board : AppCompatActivity() {

    lateinit var binding: BoardBinding

    lateinit var game: Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.board)

        binding = DataBindingUtil.setContentView(this, R.layout.board)

        binding.apply {
            val params = intent.extras
            val boardParams = params!!.getString(Constants.BOARD)
            val difficultyParams = params!!.getString(Difficulties.DIFFICULTY)

            game = Game(difficultyParams!!)

            if (boardParams == Constants.NEW_GAME) {
                textView3.text = Constants.NEW_GAME
            }

            if (boardParams == Constants.CONTINUE_GAME) {
                textView3.text = Constants.CONTINUE_GAME
            }
        }
    }
}