package com.example.higherorlower

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class CardViewModel: ViewModel() {

    private var deck: MutableList<Card> = DataManager.createDeck()


//    Second improved structure
//Pairing two values
    private val _leftAndRightCards = MutableLiveData<Pair<Card, Card>>()
    val leftAndRightCards: LiveData<Pair<Card, Card>> get() = _leftAndRightCards

    private val _leftAndRightCardsIdRes = MutableLiveData<Pair<Int, Int>>()
    val leftAndRightCardsIdRes: LiveData<Pair<Int, Int>> get() = _leftAndRightCardsIdRes


//    First structure
    //    Left and right cards/ some can be removed BUT, can be usefully for future improvement
    private var _leftCard = MutableLiveData<Card>()
    val leftCard: LiveData<Card> get() = _leftCard

    private var _rightCard = MutableLiveData<Card>()
    val rightCard: LiveData<Card> get() = _rightCard

    private var _leftCardIdRes = MutableLiveData<Int>()
    val leftCardIdRes: LiveData<Int> get() = _leftCardIdRes

    private var _rightCardIdRes = MutableLiveData<Int>()
    val rightCardIdRes: LiveData<Int> get() = _rightCardIdRes


    //    Score and remaining cards
    private var _score = MutableLiveData<Int>(0)
    val score: LiveData<Int> get() = _score

//    change back to 52
    private var _remainingCards = MutableLiveData<Int>(52)
    val remainingCards: LiveData<Int> get() = _remainingCards



    //    Methods
    fun decreaseProgressBar() {
        val currentValue = _remainingCards.value ?: 0

        if (currentValue > 0) {
            _remainingCards.value = currentValue - 1
        }
    }

    fun updateScore(isCorrect: Boolean) {
        val currentValue = _score.value ?: 0
        if (isCorrect) {
            _score.value = currentValue + 1
        }
    }

    fun showTwoNewCards() {

        if (deck.size < 2) {
            return
        }

        val leftCard = deck.random()
        deck.remove(leftCard)

        val rightCard = deck.random()

        _leftCard.value = leftCard
        _rightCard.value = rightCard

        _leftAndRightCards.value = Pair(leftCard, rightCard)
        _leftAndRightCardsIdRes.value = Pair(
            DataManager.showCardImage(leftCard),
            DataManager.showCardImage(rightCard)
        )
        Log.e("!!!", "Left card suit= ${leftCard.suit}, value= ${leftCard.value}")
        Log.e("!!!", "Right card suit= ${rightCard.suit}, value= ${rightCard.value}")

    }

}