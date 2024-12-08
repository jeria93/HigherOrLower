package com.example.higherorlower

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.higherorlower.databinding.ActivityStartGameBinding
import com.google.android.material.snackbar.Snackbar


class StartGameActivity : AppCompatActivity() {

//    val (count1, count2) = Pair(1,2) -> Not working, can be declared in methods, must be "independent",
//    would work with

//    val names = Pair("Nicholas", "Selma") // -> is independent, one variable with two values

//    two variables with two values -> is not going to work in class level, works in methods
//    val (name1, name2) = names


    lateinit var binding: ActivityStartGameBinding
    lateinit var vm: CardViewModel

//    var remainingCards = 52

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

//            val leftCard = binding.imageLeftCard.tag as Card
//            val rightCard = binding.imageRightCard.tag as Card
//
//            if (leftCard.value > rightCard.value) {
//                vm.updateScore(true)
//            } else {
//                vm.updateScore(false)
//            }
//            vm.showTwoNewCards()
////            decreaseProgressBars()
//            vm.decrease()
//            // show toast what card was hiding behind the back image?
////            Toast.makeText(this, "Hiding card was: ${rightCard.suit} ${rightCard.value}", Toast.LENGTH_LONG).show()
//            showCustomToast(rightCard, DataManager.showCardImage(rightCard))

        }

        binding.btnLow.setOnClickListener {

            compareCards(false)

//            val leftCard = binding.imageLeftCard.tag as Card
//            val rightCard = binding.imageRightCard.tag as Card
//
//            if (leftCard.value < rightCard.value) {
//                vm.updateScore(true)
//            } else {
//                vm.updateScore(false)
//            }
//            vm.showTwoNewCards()
////            decreaseProgressBars()
//            vm.decrease()
//            showCustomToast(rightCard, DataManager.showCardImage(rightCard))

        }

        if (vm.leftAndRightCards.value == null) {
            vm.showTwoNewCards()
        }

//        binding.gameProgressbar.max = 52
//        binding.gameProgressbar.progress = remainingCards

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
        vm.decrease()
        showCustomSnackBar(rightCard, DataManager.showCardImage(rightCard))

    }

    //TODO check if you can change toast show position in landscape mode, do snack bar instead,move to vm?

    fun showCustomSnackBar(card: Card, cardImageRes: Int) {

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

//    fun showCustomSnackBar(card: Card, cardImageRes: Int) {
//
//        val layout = layoutInflater.inflate(R.layout.custom_snackbar_layout, null)
//
//        val cardImage = layout.findViewById<ImageView>(R.id.custom_snack_image)
//        val cardTextView = layout.findViewById<TextView>(R.id.custom_snack_text)
//
//        cardImage.setImageResource(cardImageRes)
//        cardTextView.text = "Hiding card was: ${card.suit} ${card.value}"
//
//        val snackBar = Snackbar.make(layout, cardTextView.toString(), Snackbar.LENGTH_SHORT)
//        snackBar.setActionTextColor(Color.WHITE)
//        snackBar.show()
//    }


//    vm - done
//    private fun decreaseProgressBars() {
//
//        if (remainingCards > 0) {
//            remainingCards -= 1
//            binding.gameProgressbar.progress = remainingCards
//        } else if (remainingCards == 0) {
//
//
//            gameOverActivity()
//
//        }
//
//    }
// must be public

//    private fun showTwoNewRandomCards() {
//
//        val deck = DataManager.createDeck()
//
//        val leftCard = deck.random()
//        deck.remove(leftCard)
//
//        val rightCard = deck.random()
//
//        val leftCardIdRes = DataManager.showCardImage(leftCard)
//        binding.imageLeftCard.setImageResource(leftCardIdRes)
//        binding.imageLeftCard.tag = leftCard
//
//        val _rightCardIdRes = DataManager.showCardImage(rightCard)
//        binding.imageRightCard.setImageResource(R.drawable.back_card)
//        binding.imageRightCard.tag = rightCard
//
//        Log.e("!!!", "Left card suit= ${leftCard.suit}, value= ${leftCard.value}")
//        Log.e("!!!", "Right card suit= ${rightCard.suit}, value= ${rightCard.value}")
//
//    }
//
//    private fun showRandomImage() {
//        val deck = DataManager.createDeck()
//        val randomCard = deck.random()
//        val cardIdRes = DataManager.showCardImage(randomCard)
//        binding.imageLeftCard.setImageResource(cardIdRes)
//    }
//
//    private fun updateScore(isCorrect: Boolean) {
//
//        if (isCorrect) {
//            score += 1
//            binding.tvScore.text = "Score: $score"
//        }
//
//    }
}