package tads.eaj.ufrn.tetris

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import tads.eaj.ufrn.tetris.databinding.ConfigurationBinding
import tads.eaj.ufrn.tetris.utils.constants.Constants
import tads.eaj.ufrn.tetris.utils.constants.Difficulties


class Configuration : AppCompatActivity() {

    lateinit var binding: ConfigurationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.configuration)

        binding = DataBindingUtil.setContentView(this, R.layout.configuration)

        binding.apply {
            easyButton.setOnClickListener {
                finish()
            }

            mediumButton.setOnClickListener {
                finish()
            }

            hardButton.setOnClickListener {
                finish()
            }
        }
    }

    override fun onStop() {
        super.onStop()

        val settings = getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE)
        val editor = settings.edit()

        binding.apply {
            if(easyButton!!.isChecked) {
                editor.putString(Difficulties.DIFFICULTY, Difficulties.EASY).apply()
                editor.commit()
            }
            if (hardButton!!.isChecked) {
                editor.putString(Difficulties.DIFFICULTY, Difficulties.HARD).apply()
                editor.commit()
            }
            if(mediumButton!!.isChecked) {
                editor.putString(Difficulties.DIFFICULTY, Difficulties.MEDIUM).apply()
                editor.commit()
            }
            else {
                editor.remove(Difficulties.DIFFICULTY)
                editor.commit()
            }
        }
    }
}