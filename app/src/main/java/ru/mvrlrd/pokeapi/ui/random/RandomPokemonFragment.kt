package ru.mvrlrd.pokeapi.ui.random

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import ru.mvrlrd.pokeapi.databinding.FragmentRandomBinding

class RandomPokemonFragment : Fragment() {

    private lateinit var randomViewModel: RandomViewModel
    private var _binding: FragmentRandomBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        randomViewModel =
            ViewModelProvider(this).get(RandomViewModel::class.java)

        _binding = FragmentRandomBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.randomActionButton.setOnClickListener{
            randomViewModel.getRandomPokemon()
        }


        randomViewModel.pokemonName.observe(viewLifecycleOwner, Observer {
            binding.randomNameText.text = it.name
            binding.heightText.text = "рост: ${it.height}"
            binding.weightText.text = "вес: ${it.weight}"
            binding.randomPokemonImage.load(it.sprites.front_default)
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}