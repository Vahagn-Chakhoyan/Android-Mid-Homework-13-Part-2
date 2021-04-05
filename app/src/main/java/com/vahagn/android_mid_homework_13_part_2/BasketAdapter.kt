package com.vahagn.android_mid_homework_13_part_2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BasketAdapter: RecyclerView.Adapter<BasketAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ratingText: TextView = view.findViewById(R.id.ratingText)
        val nameText: TextView = view.findViewById(R.id.nameText)
        val restaurantText: TextView = view.findViewById(R.id.restaurantText)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.basket_list_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.nameText.text = DishesFragment.basket[position].name
        viewHolder.restaurantText.text = DishesFragment.basket[position].restaurant.name
        viewHolder.ratingText.text = DishesFragment.basket[position].rating.toString()
    }

    override fun getItemCount() = DishesFragment.basket.size
}