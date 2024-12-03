package com.example.higherorlower

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.higherorlower.databinding.ActivityStartGameBinding


class StartGameActivity : AppCompatActivity() {

    lateinit var binding: ActivityStartGameBinding
    var score = 0
    var remainingCards = 52

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
            decreaseProgressBars()

            // show toast what card was hiding behind the back image?
//            Toast.makeText(this, "Hiding card was: ${rightCard.suit} ${rightCard.value}", Toast.LENGTH_LONG).show()
            showCustomToast(rightCard, DataManager.showCardImage(rightCard))

        }

//        Button-lower logic
        binding.btnLow.setOnClickListener {

            val leftCard = binding.imageLeftCard.tag as Card
            val rightCard = binding.imageRightCard.tag as Card

            if (leftCard.value < rightCard.value) {
                updateScore(true)
            } else {
                updateScore(false)
            }
            showTwoNewRandomCards()
            decreaseProgressBars()
            showCustomToast(rightCard, DataManager.showCardImage(rightCard))

        }

        binding.gameProgressbar.max = 52
        binding.gameProgressbar.progress = remainingCards

    }

    private fun decreaseProgressBars() {

        if (remainingCards > 0) {
            remainingCards -= 1
            binding.gameProgressbar.progress = remainingCards
        } else if (remainingCards == 0) {

//            new intent to end game (game over screen), send score to game over screen
            val intent = Intent(this, GameOverActivity::class.java)
            intent.putExtra("FINAL_SCORE", score)
            startActivity(intent)
            finish()
            println("game over")

        }

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

//    TODO check if you can change toast show position in landscape mode

    fun showCustomToast(card: Card, cardImageRes: Int) {

//        BINDING FOR CUSTOM TOAST?

//        inflate layout for custom xml
        val layout = layoutInflater.inflate(R.layout.custom_toast, null)

//        show layouts elements
        val cardImage = layout.findViewById<ImageView>(R.id.toast_card_image)
        val cardTextView = layout.findViewById<TextView>(R.id.toast_card_text)

        cardImage.setImageResource(cardImageRes)
        cardTextView.text = "Hiding card was: ${card.suit} ${card.value}"



        val toast = Toast(this)
        toast.duration = Toast.LENGTH_SHORT
        toast.setView(layout)

        toast.setGravity(Gravity.CENTER_HORIZONTAL or Gravity.CENTER_HORIZONTAL, 0, 0)
        toast.show()

    }





}