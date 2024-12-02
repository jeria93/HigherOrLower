package com.example.higherorlower

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.higherorlower.databinding.ActivityStartGameBinding

class StartGameActivity : AppCompatActivity() {

    lateinit var binding: ActivityStartGameBinding
    var score = 0
    val remainingCards = 52

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

        showTwoNewRandomCards()

        binding.btnHigher.setOnClickListener {

            val leftCard = binding.imageLeftCard.tag as Card
            val rightCard = binding.imageRightCard.tag as Card

            if (leftCard.value > rightCard.value) {
                updateScore(true)
            } else {
                updateScore(false)
            }
            showTwoNewRandomCards()
        }

        binding.btnLow.setOnClickListener {

            val leftCard = binding.imageLeftCard.tag as Card
            val rightCard = binding.imageRightCard.tag as Card

            if (leftCard.value < rightCard.value) {
                updateScore(true)
            } else {
                updateScore(false)
            }
            showTwoNewRandomCards()
        }

        binding.gameProgressbar.max = 52
        binding.gameProgressbar.progress = remainingCards

    }

    private fun showTwoNewRandomCards() {

        val deck = DataManager.createDeck()

        val leftCard = deck.random()
        deck.remove(leftCard)

        val rightCard = deck.random()

        val leftCardIdRes = DataManager.showCardImage(leftCard)
        binding.imageLeftCard.setImageResource(leftCardIdRes)
        binding.imageLeftCard.tag = leftCard

        val rightCardIdRes = DataManager.showCardImage(rightCard)
        binding.imageRightCard.setImageResource(R.drawable.back_card)
        binding.imageRightCard.tag = rightCard

        Log.e("!!!", "Left card suit= ${leftCard.suit}, value= ${leftCard.value}")
        Log.e("!!!", "Right card suit= ${rightCard.suit}, value= ${rightCard.value}")


    }

    private fun showRandomImage() {
        val deck = DataManager.createDeck()
        val randomCard = deck.random()
        val cardIdRes = DataManager.showCardImage(randomCard)
        binding.imageLeftCard.setImageResource(cardIdRes)
    }

    private fun updateScore(isCorrect: Boolean) {

        if (isCorrect) {
            score += 1
            binding.tvScore.text = "Score: $score"
        }

    }


}