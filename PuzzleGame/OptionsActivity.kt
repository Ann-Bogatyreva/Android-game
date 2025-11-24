//настройки avtorstvo i td i tp + poprobyiy knopky vosvrata zdelat//
package com.example.puzzlegame

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class OptionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_options)

        // knopka nazad v ActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // drygoii sposob obrabotki knopki "Nazad"
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
