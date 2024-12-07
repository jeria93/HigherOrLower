package com.example.higherorlower

object DataManager {

    var score = 0
    var remainingCards = 52
    //maps out the name of the card as a string to the resource file added
    /*
    "card_heart_1" to R.drawable.card_heart_1 = (key) to (value)
    card_heart_1 = key
    R.drawable.card_heart_1 = value
    images are in resource file
     */

    val cardMap = mapOf(

//        Hearts(1-13)
        "card_heart_1" to R.drawable.card_heart_1,
        "card_heart_2" to R.drawable.card_heart_2,
        "card_heart_3" to R.drawable.card_heart_3,
        "card_heart_4" to R.drawable.card_heart_4,
        "card_heart_5" to R.drawable.card_heart_5,
        "card_heart_6" to R.drawable.card_heart_6,
        "card_heart_7" to R.drawable.card_heart_7,
        "card_heart_8" to R.drawable.card_heart_8,
        "card_heart_9" to R.drawable.card_heart_9,
        "card_heart_10" to R.drawable.card_heart_10,
        "card_heart_11" to R.drawable.card_heart_11,
        "card_heart_12" to R.drawable.card_heart_12,
        "card_heart_13" to R.drawable.card_heart_13,

//        Clubs(1-13)
        "card_club_1" to R.drawable.card_club_1,
        "card_club_2" to R.drawable.card_club_2,
        "card_club_3" to R.drawable.card_club_3,
        "card_club_4" to R.drawable.card_club_4,
        "card_club_5" to R.drawable.card_club_5,
        "card_club_6" to R.drawable.card_club_6,
        "card_club_7" to R.drawable.card_club_7,
        "card_club_8" to R.drawable.card_club_8,
        "card_club_9" to R.drawable.card_club_9,
        "card_club_10" to R.drawable.card_club_10,
        "card_club_11" to R.drawable.card_club_11,
        "card_club_12" to R.drawable.card_club_12,
        "card_club_13" to R.drawable.card_club_13,

//        Spades(1-13)
        "card_spade_1" to R.drawable.card_spade_1,
        "card_spade_2" to R.drawable.card_spade_2,
        "card_spade_3" to R.drawable.card_spade_3,
        "card_spade_4" to R.drawable.card_spade_4,
        "card_spade_5" to R.drawable.card_spade_5,
        "card_spade_6" to R.drawable.card_spade_6,
        "card_spade_7" to R.drawable.card_spade_7,
        "card_spade_8" to R.drawable.card_spade_8,
        "card_spade_9" to R.drawable.card_spade_9,
        "card_spade_10" to R.drawable.card_spade_10,
        "card_spade_11" to R.drawable.card_spade_11,
        "card_spade_12" to R.drawable.card_spade_12,
        "card_spade_13" to R.drawable.card_spade_13,

//        Diamonds(1-13)
        "card_diamond_1" to R.drawable.card_diamond_1,
        "card_diamond_2" to R.drawable.card_diamond_2,
        "card_diamond_3" to R.drawable.card_diamond_3,
        "card_diamond_4" to R.drawable.card_diamond_4,
        "card_diamond_5" to R.drawable.card_diamond_5,
        "card_diamond_6" to R.drawable.card_diamond_6,
        "card_diamond_7" to R.drawable.card_diamond_7,
        "card_diamond_8" to R.drawable.card_diamond_8,
        "card_diamond_9" to R.drawable.card_diamond_9,
        "card_diamond_10" to R.drawable.card_diamond_10,
        "card_diamond_11" to R.drawable.card_diamond_11,
        "card_diamond_12" to R.drawable.card_diamond_12,
        "card_diamond_13" to R.drawable.card_diamond_13
    )


    fun createDeck() : MutableList<Card> {

//    suits and values
        val suits: List<String> = listOf("club", "heart", "spade", "diamond")
        val values = 1..13

        val newDeck = mutableListOf<Card>()

        for (suit in suits) {
            for (value in values) {

                newDeck.add(Card(suit, value))
            }
        }

        newDeck.shuffle()

        return newDeck
    }

//    Shows card image
    fun showCardImage(card: Card) : Int {
        val resourceName = "card_${card.suit}_${card.value}"


//        if (image == null) {
//            println("Image not found")
//        } else {
//            return image
//        }
//


//                                       add a joker card instead,looks more nice
        return cardMap[resourceName] ?: R.drawable.cards_deck //if card image not found, set a default card
    }

//    override fun toString(): String {
//        return "DataManager(currentCard=$currentCard, cardMap=$cardMap)"
//    }

//    new functions shows cards + string help, even out spaces, cards must show in order 1-13, no duplicates
//    array has methods that shows things in order, numbers or letters

    fun showCardsInOrder() : MutableList<Card> {

        val suits: List<String> = mutableListOf("club", "heart", "spade", "diamond")
        val values = 1..13

        val newDeck = mutableListOf<Card>()

        for (value in values) {
            val suit = suits[(value - 1) % suits.size]
            newDeck.add(Card(suit, value))
        }

        return newDeck


    }


}