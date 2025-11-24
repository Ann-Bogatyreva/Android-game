//уровни, переходы, кнопки//
package com.example.puzzlegame

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.puzzlegame.databinding.ActivityLevelsBinding

class LevelsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLevelsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLevelsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Обработчики нажатий через binding
        binding.level1.setOnClickListener { startGameActivity("cube") }
        binding.level2.setOnClickListener { startGameActivity("apple") }
        binding.level3.setOnClickListener { startGameActivity("kot") }
        binding.level4.setOnClickListener { startGameActivity("ball") }
        binding.level5.setOnClickListener { startGameActivity("map") }
        binding.level6.setOnClickListener { startGameActivity("sword") }
        binding.level7.setOnClickListener { startGameActivity("flowers") }
        binding.level8.setOnClickListener { startGameActivity("car") }
        binding.level9.setOnClickListener { startGameActivity("tower") }
        binding.level10.setOnClickListener { startGameActivity("igolka") }
    }

    private fun startGameActivity(imageName: String) {
        val intent = Intent(this, GameActivity::class.java)
        intent.putExtra("IMAGE_NAME", imageName)
        startActivity(intent)
    }
}
