package com.example.higherorlower

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecycleCardAdapter(val cardList: MutableList<Card>): RecyclerView.Adapter<RecycleCardAdapter.CardViewHolder>() {

    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val cardImage = itemView.findViewById<ImageView>(R.id.card_image)
        val cardValue = itemView.findViewById<TextView>(R.id.card_value)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        return CardViewHolder(view)
    }

    override fun getItemCount(): Int {
       return cardList.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {

        val cards = cardList[position]

        holder.cardImage.setImageResource(DataManager.showCardImage(cards))
        holder.cardValue.text = cards.value.toString()



    }


}