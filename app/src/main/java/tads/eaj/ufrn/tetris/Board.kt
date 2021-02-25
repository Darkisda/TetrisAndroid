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
    lateinit var binding: BoardBinding

    lateinit var game: Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.board)

        binding = DataBindingUtil.setContentView(this, R.layout.board)

        var settings = getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE)

        binding.apply {
            val params = intent.extras
            val boardParams = params!!.getString(Constants.BOARD)

            var difficulty = settings.getString(Difficulties.DIFFICULTY, Difficulties.EASY)

            game = Game(difficulty!!)

        }
    }
}