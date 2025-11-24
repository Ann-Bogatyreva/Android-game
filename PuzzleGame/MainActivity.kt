//тут будет код, Dopystim kod//
package com.example.puzzle

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Naxodim knopki po ID
        val btnPlay = findViewById<Button>(R.id.btn_play)
        val btnOptions = findViewById<Button>(R.id.btn_options)

        btnPlay.setOnClickListener {
            startActivity(Intent(this, LevelsActivity::class.java))
        }

        btnOptions.setOnClickListener {
            startActivity(Intent(this, OptionsActivity::class.java))
        }
    }
}
