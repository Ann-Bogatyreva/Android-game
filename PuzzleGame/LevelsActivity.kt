//уровни, переходы, кнопки//
package com.example.puzzle

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_levels.*

class LevelsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_levels)

        val levels = mapOf(
            R.id.level1 to "cube",
            R.id.level2 to "apple",
            R.id.level3 to "kot",
            R.id.level4 to "ball",
            R.id.level5 to "map",
            R.id.level6 to "sword",
            R.id.level7 to "flowers",
            R.id.level8 to "car",
            R.id.level9 to "tower",
            R.id.level10 to "igolka"
        )

        levels.forEach { (buttonId, imageName) ->
            findViewById<android.widget.Button>(buttonId)?.setOnClickListener {
                val intent = Intent(this, GameActivity::class.java)
                intent.putExtra("IMAGE_NAME", imageName)
                startActivity(intent)
            }
        }
    }
}
