package tads.eaj.ufrn.tetris

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import tads.eaj.ufrn.tetris.databinding.ActivityMainBinding
import tads.eaj.ufrn.tetris.utils.constants.Constants

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.apply {
            newGameButton.setOnClickListener {
                var intent = Intent(this@MainActivity, Board::class.java)

                intent.putExtra(Constants.BOARD, Constants.NEW_GAME)

                startActivity(intent)
            }

            continueButton.visibility = View.INVISIBLE

            continueButton.setOnClickListener {
                var intent = Intent(this@MainActivity, Board::class.java)

                intent.putExtra(Constants.BOARD, Constants.CONTINUE_GAME)

                startActivity(intent)
            }

            configureButton.setOnClickListener {
                var intent = Intent(this@MainActivity, Configuration::class.java)

                startActivity(intent)
            }
        }
    }
}