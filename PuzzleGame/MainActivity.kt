//тут будет код//
package com.example.puzzle

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_play.setOnClickListener {
            startActivity(Intent(this, LevelsActivity::class.java))
        }

        btn_options.setOnClickListener {
            startActivity(Intent(this, OptionsActivity::class.java))
        }
    }
}
