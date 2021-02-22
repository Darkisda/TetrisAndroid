package tads.eaj.ufrn.tetris

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import tads.eaj.ufrn.tetris.databinding.ConfigurationBinding

class Configuration : AppCompatActivity() {

    lateinit var binding: ConfigurationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.configuration)

        binding = DataBindingUtil.setContentView(this, R.layout.configuration)

        binding.apply {
            easyButton.setOnClickListener {

            }
        }
    }
}