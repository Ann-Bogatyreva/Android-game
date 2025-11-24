//изображение на уровнЯх + кнопки сложности\\
package com.example.puzzlegame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val puzzleView = findViewById<PuzzleView>(R.id.puzzle_view)
        val levelTitle = findViewById<TextView>(R.id.level_title)

        // Получаем имя изображения из Intent
        val imageName = intent.getStringExtra("IMAGE_NAME") ?: "cube"
        val imageResId = resources.getIdentifier(imageName, "drawable", packageName)

        // Устанавливаем изображение в PuzzleView
        puzzleView.setImageResource(imageResId)

        // Настройка кнопок сложности
        setupDifficultyButtons(puzzleView)
    }

    private fun setupDifficultyButtons(puzzleView: PuzzleView) {
        findViewById<Button>(R.id.btn_3x3).setOnClickListener {
            puzzleView.setDifficulty(3)
        }
        findViewById<Button>(R.id.btn_5x5).setOnClickListener {
            puzzleView.setDifficulty(5)
        }
        findViewById<Button>(R.id.btn_7x7).setOnClickListener {
            puzzleView.setDifficulty(7)
        }
        findViewById<Button>(R.id.btn_10x10).setOnClickListener {
            puzzleView.setDifficulty(10)
        }
        findViewById<Button>(R.id.btn_restart).setOnClickListener {
            puzzleView.restartPuzzle()
        }
    }
}
