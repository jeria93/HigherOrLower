package com.example.higherorlower


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

////    Dummy code for testing
class PairTest:ViewModel() {

    private var deck: MutableList<Card> = DataManager.createDeck()

    private var _leftCard = MutableLiveData<Card>()
    val leftCard: LiveData<Card> get() = _leftCard

    private var _rightCard = MutableLiveData<Card>()
    val rightCard: LiveData<Card> get() = _rightCard

    private var _leftCardIdRes = MutableLiveData<Int>()
    val leftCardIdRes: LiveData<Int> get() = _leftCardIdRes

    private var _rightCardIdRes = MutableLiveData<Int>()
    val rightCardIdRes: LiveData<Int> get() = _rightCardIdRes

    private val _leftAndRightCards = MutableLiveData<Pair<Card, Card>>()
    val leftAndRightCards: LiveData<Pair<Card, Card>> get() = _leftAndRightCards

    private val _leftAndRightCardsIdRes = MutableLiveData<Pair<Int, Int>>()
    val leftAndRightCardsIdRes: LiveData<Pair<Int, Int>> get() = _leftAndRightCardsIdRes

//    Dummy code for testing
    fun getUserCoordinates(): Pair<Double, Double> {
        val latitude = 59.3293
        val longitude = 18.0686
        return Pair(latitude, longitude)
    }

}