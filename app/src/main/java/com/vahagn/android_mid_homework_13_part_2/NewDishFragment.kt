package com.vahagn.android_mid_homework_13_part_2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.vahagn.android_mid_homework_13_part_2.databinding.FragmentNewDishBinding


class NewDishFragment : Fragment() {
    private lateinit var _binding: FragmentNewDishBinding

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewDishBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(), android.R.layout.simple_spinner_item, DishesFragment.restaurants.map { it.name }
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.restaurantsSpinner.adapter = adapter

        binding.cancelButton.setOnClickListener { findNavController().popBackStack() }
        binding.createButton.setOnClickListener { if(binding.nameEdit.text.isNotEmpty()) createContact()
        else Toast.makeText(requireContext(), "Name can't be empty!", Toast.LENGTH_SHORT).show() }
    }

    private fun createContact() {
        binding.apply {
            DishesFragment.dishes.add(
                Dish(nameEdit.text.toString(), DishesFragment.restaurants[restaurantsSpinner.selectedItemPosition],
                    ratingSpinner.selectedItem.toString().toInt(), descriptionEdit.text.toString())
            )
        }

        DishesFragment.dishAdded = true

        findNavController().popBackStack()

    }
}