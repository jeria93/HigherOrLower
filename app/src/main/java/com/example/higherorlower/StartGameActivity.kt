package com.example.higherorlower

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.higherorlower.databinding.ActivityStartGameBinding
import com.google.android.material.snackbar.Snackbar


class StartGameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartGameBinding
    private lateinit var vm: CardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityStartGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        vm = ViewModelProvider(this)[CardViewModel::class.java]
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.game_main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        vm.score.observe(this, Observer { score ->
            binding.tvScore.text = "Score: $score"
        })
        vm.leftAndRightCardsIdRes.observe(this, Observer { (left,right) ->
            binding.imageLeftCard.setImageResource(left)
            binding.imageRightCard.setImageResource(R.drawable.back_card)
        })
        vm.remainingCards.observe(this, Observer { remaining ->
            binding.gameProgressbar.max = 52
            binding.gameProgressbar.progress = remaining

            if (remaining == 0) {
                gameOverActivity()
            }

        })
        vm.leftAndRightCards.observe(this, Observer { (left,right) ->
            binding.imageLeftCard.tag = left
            binding.imageRightCard.tag = right
        })
        binding.btnHigher.setOnClickListener {

            compareCards(true)

        }
        binding.btnLow.setOnClickListener {

            compareCards(false)

        }
        if (vm.leftAndRightCards.value == null) {
            vm.showTwoNewCards()
        }

    }

    private fun gameOverActivity() {
        val intent = Intent(this, GameOverActivity::class.java)
        intent.putExtra("FINAL_SCORE", vm.score.value)
        startActivity(intent)
        finish()
        println("game over")
    }

//    Move whole method to viewmodel?
    private fun compareCards(guess: Boolean) {

        val (leftCard, rightCard) = vm.leftAndRightCards.value ?: return

        val correctGuess = if (guess) {
            leftCard.value > rightCard.value
        } else {
            leftCard.value < rightCard.value
        }

        if (correctGuess) {
            vm.updateScore(true)
        } else {
            vm.updateScore(false)
        }

        vm.showTwoNewCards()
        vm.decreaseProgressBar()
        showCustomSnackBar(rightCard, DataManager.showCardImage(rightCard))

    }

    private fun showCustomSnackBar(card: Card, cardImageRes: Int) {

        val parentView = findViewById<View>(R.id.game_main)


        val snackBar = Snackbar.make(parentView, "", Snackbar.LENGTH_SHORT)


//        Works but complains and have no clue how to fix it
        val snackBarLayout = snackBar.view as Snackbar.SnackbarLayout

        val customLayout = layoutInflater.inflate(R.layout.custom_snackbar_layout, null)


        val cardImage = customLayout.findViewById<ImageView>(R.id.custom_snack_image)
        val cardTextView = customLayout.findViewById<TextView>(R.id.custom_snack_text)
        cardImage.setImageResource(cardImageRes)
        cardTextView.text = "Hiding card was: ${card.suit} ${card.value}"


        snackBarLayout.setPadding(0, 0, 0, 0)
        snackBarLayout.addView(customLayout)

        val position = snackBar.view.layoutParams as FrameLayout.LayoutParams
        position.gravity = Gravity.CENTER
        snackBar.view.layoutParams = position

        snackBar.show()

    }

}