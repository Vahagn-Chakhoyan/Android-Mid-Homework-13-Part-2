package com.vahagn.android_mid_homework_13_part_2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.vahagn.android_mid_homework_13_part_2.databinding.FragmentDishesBinding
import kotlin.properties.Delegates

class DishesFragment : Fragment() {
    private var _binding: FragmentDishesBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDishesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.dishes.layoutManager = LinearLayoutManager(requireContext())
        binding.dishes.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        binding.dishes.adapter = DishesAdapter( {dishClicked(it)}, {deleteDishByIndex(it)}, {basket(it)}, {fav(it)})
        binding.cancelButton.setOnClickListener { binding.confirmDelete.visibility = View.GONE }
        binding.deleteButton.setOnClickListener { binding.confirmDelete.visibility = View.GONE; confirmDelete() }
        binding.createNewDish.setOnClickListener { findNavController().navigate(R.id.goToNewDish) }
    }

    private fun fav(position: Int) {
        if(favs.contains(dishes[position])) favs.remove(dishes[position])
        else favs.add(dishes[position])

        binding.dishes.adapter?.notifyItemChanged(position)
    }

    private fun basket(position: Int) {
        basket.add(dishes[position])
        Toast.makeText(requireContext(), "Added into basket!", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        if(dishAdded) binding.dishes.adapter?.notifyDataSetChanged()
        dishAdded = false
    }

    private fun dishClicked(index: Int) {
        chosenDishIndex = index

        findNavController().navigate(R.id.goToDetails)
    }

    private fun confirmDelete() {
        favs.removeAll { it == dishes[indexToDelete] }
        basket.removeAll { it == dishes[indexToDelete] }

        dishes.removeAt(indexToDelete)
        binding.dishes.adapter?.notifyDataSetChanged()
    }

    private fun deleteDishByIndex(index: Int): Boolean {
        indexToDelete = index
        binding.confirmDelete.visibility = View.VISIBLE

        return true
    }

    companion object {
        val restaurants = mutableListOf<Restaurant>(
            Restaurant("Norma’s", "Ever hear of a \$1,000 frittata? Norma’s creative breakfast concept comes with a hefty price tag, but with good reason: the frittata is made with one pound of lobster set on a bed of fried potatoes and finished with a pile of caviar. But don’t worry: you can also get a “cheap” version for \$100.", 5),
            Restaurant("Dinner in the Sky", "Bring your dining experience to new heights at Dinner in the Sky. This one-of-a-kind restaurant, suspended 160+ feet in the air, can accommodate 22 brave diners and three staffers.", 5),
            Restaurant("The Disaster Café", "Looking for an earth-shattering dining experience? The Disastre Cafe will deliver, with 7.8 richter scale earthquakes simulated during meals.", 5)
        )

        val dishes = mutableListOf<Dish>(
            Dish("Pizza", restaurants[0], 4, "San Marzano tomato sauce, fresh mozzarella fior di latte, fresh organic basil"),
            Dish("Pizza", restaurants[1], 5, "San Marzano tomato sauce, fresh mozzarella, grated parmesan and pepperoni from Il Mondo Vecchio"),
            Dish("Pizza", restaurants[2], 3, "White pizza with garlic infused olive oil, goat cheese, fresh mozzarella fior di latte prosciutto fired off then topped with arugula and shaved parmesan."),
            Dish("Pizza", restaurants[0], 5, "A traditional Italian seasoned roasted pork shoulder, seared in the oven, served on a fresh, hand-made flat-bread then topped with pesto crème fraiche and arugula "),
            Dish("Pizza", restaurants[1], 5, "A rich and creamy whole-wheat pasta dish filled layer by layer with refreshingly fresh onions and garlic, lathered in a succulent sauce and topped with imported, premium quality mozzarella."),
            Dish("Pizza", restaurants[2], 4, "some description"),
            Dish("Pizza", restaurants[0], 4, "some description"),
            Dish("Pizza", restaurants[1], 3, "some description"),
            Dish("Pizza", restaurants[2], 5, "some description"),
            Dish("Pizza", restaurants[0], 5, "some description"),
            Dish("Pizza", restaurants[2], 5, "San Marzano tomato sauce, fresh mozzarella fior di latte, fresh organic basil"),
        )

        val favs = mutableListOf<Dish>()
        val basket = mutableListOf<Dish>()

        var chosenDishIndex by Delegates.notNull<Int>()

        private var indexToDelete by Delegates.notNull<Int>()

        var dishAdded: Boolean = false
    }
}