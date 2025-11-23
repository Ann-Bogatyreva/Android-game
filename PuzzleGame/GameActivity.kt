\\изображение на уровнЯх + кнопки сложности\\
package com.example.puzzle

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {
    private lateinit var puzzleView: PuzzleView
    private var currentDifficulty = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val imageName = intent.getStringExtra("IMAGE_NAME") ?: "cube"
        val imageResId = resources.getIdentifier(imageName, "drawable", packageName)

        puzzleView = findViewById(R.id.puzzle_view)
        puzzleView.setImageResource(imageResId)
        puzzleView.setDifficulty(currentDifficulty)

        setupLevelTitle(imageName)
        setupButtons()
    }

    private fun setupLevelTitle(imageName: String) {
        val titles = mapOf(
            "cube" to "Собери кубик",
            "apple" to "Собери яблоко",
            "kot" to "Собери котиков",
            "ball" to "Собери мяч",
            "map" to "Собери карту",
            "sword" to "Собери меч",
            "flowers" to "Собери цветок в горшке",
            "car" to "Собери машину",
            "tower" to "Собери башню",
            "igolka" to "Собери иголку с ниткой"
        )

        findViewById<TextView>(R.id.level_title).text = titles[imageName]
    }

    private fun setupButtons() {
        findViewById<Button>(R.id.btn_restart).setOnClickListener {
            puzzleView.restartPuzzle()
        }

        findViewById<Button>(R.id.btn_3x3).setOnClickListener {
            currentDifficulty = 3
            puzzleView.setDifficulty(3)
        }

        findViewById<Button>(R.id.btn_5x5).setOnClickListener {
            currentDifficulty = 5
            puzzleView.setDifficulty(5)
        }

        findViewById<Button>(R.id.btn_7x7).setOnClickListener {
            currentDifficulty = 7
            puzzleView.setDifficulty(7)
        }

        findViewById<Button>(R.id.btn_10x10).setOnClickListener {
            currentDifficulty = 10
            puzzleView.setDifficulty(10)
        }
    }
}
