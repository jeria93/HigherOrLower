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
// Create function that separates the image resource from its map,
// Create function that guesses if the card is either higher or lower
// Declare variables for tracking score, tracking deck, showingCard? (current card that you se/that you have in front of you tex. card-diamond-2)
// Create function that handles the images for tracking/comparing
// Create function that updates the UI/Game-play?, if guessing right = score +1
// Use binding for less code
// -----------------------------------------------------------------------------------
// Create function that finds respective card image with respective card value, for instance = card_club_1
// (combine resource material with function/code -> google it, its possible),
// needs to return a value for later comparison if card 1 > card 2, cards are set as in Int in ImageViews
// this way you COULD / be able to extract the images in resource file/package "drawable"
// needs to match exactly this the format card_heart_13
// When game activity starts -> first card that shows up, needs to be set with a value, else no point
// Use the mapof() in Card() to make function that searches for resource files(?)
// Before hand in - double check everything is good, change sizes, strings etc etc to resources in XML, make the design look as in concept,
// do debugging, try all possible cases for bugs/possible crashes.
// Make properties private the ones that can be it
