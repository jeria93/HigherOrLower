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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.startGame.setOnClickListener {
//            val deck = DataManager.createDeck()
//
//            showRandomImage(deck)

            val intent = Intent(this, StartGameActivity::class.java)
            startActivity(intent)

        }
    }

//    fun showRandomImage(deck: MutableList<Card>) {
//
//        val randomCard = deck.random()
//        val cardIdRes = DataManager.showCardImage(randomCard)
//        binding.imageView2.setImageResource(cardIdRes)
//    }
}



////Is not needed ?
//fun showRandomCardImage(deck: List<Card>) : Card{
//    return deck.random()
//}
//
////Finds image by (suit, value), returns it for later usage
//fun showCardImage(card: Card) : Int {
//    val resourceName = "card_${card.suit}_${card.value}"
//    return card.cardMap[resourceName] ?: R.drawable.cards_deck //if card image not found, set a default card
//}
//
////Creates new fresh deck
//fun createDeck(): MutableList<Card>  {
//
////    suits and values
//    val suits = listOf("clubs", "heart", "spade", "diamond")
//    val values = 1..13
//
////    Create new empty deck
//    val newDeck = mutableListOf<Card>()
//
////    Loop suits and values, add to the new deck
//    for (suit in suits) {
//        for (value in values) {
//            newDeck.add(Card(suit, value))
//        }
//
//    }
//
//    newDeck.shuffle()
//
//    return newDeck
//}

//TODO
// Compare deck/card with each-other for values, is card 1 bigger than 2 etc
// Create function that guesses if the card is either higher or lower
// Declare variables for tracking score, tracking deck, showingCard? (current card that you se/that you have in front of you tex. card-diamond-2)
// Create function that handles the images for tracking/comparing
// Create function that updates the UI/Game-play?, if guessing right = score +1
// -----------------------------------------------------------------------------------
// Before hand in - double check everything is good, change sizes, strings etc etc to resources in XML, make the design look as in concept,
// do debugging, try all possible cases for bugs/possible crashes.
// Make properties private, public etc etc the ones that can be it
// Remove unnecessary empty spaces
// If there is time, make everything super readable = var score: Int = 0, val suits: List<String> = listOf("clubs", "heart", "spade", "diamond") etc etc
// Add ViewModel? ONLY IF THERE IS TIME - do research
// Check all code for refactoring, more readable, less code, use Elvis operator if possible
// I only want to use the cards with values for comparison, not the other resources, double check the cardMap variable(other resources are showing up than the cards set in the card Map variable)
// Must change right card, when game initialized 2 random cards show, its a good starting point, but the right deck should be a "back card" and never change,
// random values (cards) should still continue.
