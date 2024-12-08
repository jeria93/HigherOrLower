package com.example.higherorlower

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.higherorlower.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.game_main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.startGame.setOnClickListener {
            val intent = Intent(this, StartGameActivity::class.java)
            startActivity(intent)

        }

        binding.btnRules.setOnClickListener {
            val intent = Intent(this, RulesActivity::class.java)
            startActivity(intent)
        }
    }

}


//TODO
// if time -> refactor app, use fragments?
// Make custom app icon
// Change toast to snack bar
// implement landscape mode? - research
// -----------------------------------------------------------------------------------
// Before hand in - double check everything is good, change sizes, strings etc etc to resources in XML, make the design look as in concept,
// do debugging, try all possible cases for bugs/possible crashes.
// Make properties private, public etc etc the ones that can be it
// Remove unnecessary empty spaces
// If there is time, make everything super readable = var score: Int = 0, val suits: List<String> = listOf("clubs", "heart", "spade", "diamond") etc etc
