package com.vahagn.android_mid_homework_13_part_2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class DishesAdapter(private val clickListener: (Int) -> Unit, private val longClickListener: (Int) -> Boolean,
                    private val basket: (Int) -> Unit, private val fav: (Int) -> Unit
                    ) : RecyclerView.Adapter<DishesAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val root: View = view
        val ratingText: TextView = view.findViewById(R.id.ratingText)
        val nameText: TextView = view.findViewById(R.id.nameText)
        val restaurantText: TextView = view.findViewById(R.id.restaurantText)
        val basketButton: ImageButton = view.findViewById(R.id.basketButton)
        val favButton: ImageButton = view.findViewById(R.id.favButton)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.dishes_list_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.nameText.text = DishesFragment.dishes[position].name
        viewHolder.restaurantText.text = DishesFragment.dishes[position].restaurant.name
        viewHolder.ratingText.text = DishesFragment.dishes[position].rating.toString()
        viewHolder.favButton.setImageDrawable(
            ContextCompat.getDrawable(viewHolder.favButton.context,
            if(DishesFragment.favs.contains(DishesFragment.dishes[position])) R.drawable.ic_favorite_24
            else R.drawable.ic_favorite_border_24))

        viewHolder.root.setOnClickListener { clickListener(position) }
        viewHolder.root.setOnLongClickListener { longClickListener(position) }
        viewHolder.basketButton.setOnClickListener { basket(position) }
        viewHolder.favButton.setOnClickListener { fav(position) }
    }

    override fun getItemCount() = DishesFragment.dishes.size
}