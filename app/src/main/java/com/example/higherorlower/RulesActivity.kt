package com.example.higherorlower

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.higherorlower.databinding.ActivityRulesBinding

class RulesActivity : AppCompatActivity() {


    lateinit var binding: ActivityRulesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRulesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.game_main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.rvContainer.layoutManager = GridLayoutManager(this, 1)
        binding.rvContainer.adapter = RecycleCardAdapter(DataManager.showCardsInOrder())


    }
}


//TODO:
// 1, 2 textviews in card_item? = "= $(card.value).toString()" -> RecycleView