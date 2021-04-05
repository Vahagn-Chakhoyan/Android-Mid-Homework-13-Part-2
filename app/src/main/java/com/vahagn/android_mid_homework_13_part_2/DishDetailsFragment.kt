package com.vahagn.androidmidhomework13

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vahagn.android_mid_homework_13_part_2.DishesFragment
import com.vahagn.android_mid_homework_13_part_2.DishesFragment.Companion.chosenDishIndex
import com.vahagn.android_mid_homework_13_part_2.databinding.FragmentDishDetailsBinding

class DishDetailsFragment : Fragment() {
    private var _binding: FragmentDishDetailsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDishDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nameText.text = DishesFragment.dishes[chosenDishIndex].name
        binding.restaurantText.text = DishesFragment.dishes[chosenDishIndex].restaurant.name
        binding.rateText.text = DishesFragment.dishes[chosenDishIndex].rating.toString()
        binding.descriptionText.text = DishesFragment.dishes[chosenDishIndex].description
    }
}