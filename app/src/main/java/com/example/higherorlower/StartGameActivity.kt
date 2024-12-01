package com.example.higherorlower

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.higherorlower.databinding.ActivityStartGameBinding

class StartGameActivity : AppCompatActivity() {

    lateinit var binding: ActivityStartGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityStartGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        showRandomCards()

        binding.btnHigher.setOnClickListener {

            showRandomImage()


        }

    }

    private fun showRandomCards() {

        val deck = DataManager.createDeck()

        val leftCard = deck.random()
        val rightCard = deck.random()

        val leftCardIdRes = DataManager.showCardImage(leftCard)
        binding.imageLeftCard.setImageResource(leftCardIdRes)
        binding.imageLeftCard.tag = leftCard

        val rightCardIdRes = DataManager.showCardImage(rightCard)
        binding.imageRightCard.setImageResource(rightCardIdRes)
        binding.imageRightCard.tag = rightCard

    }

    private fun showRandomImage() {
        val deck = DataManager.createDeck()
        val randomCard = deck.random()
        val cardIdRes = DataManager.showCardImage(randomCard)
        binding.imageLeftCard.setImageResource(cardIdRes)
    }


}