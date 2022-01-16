package ru.mvrlrd.pokeapi.ui.random

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import coil.api.load
import ru.mvrlrd.pokeapi.MyApplication
import ru.mvrlrd.pokeapi.databinding.FragmentRandomBinding

class RandomPokemonFragment : Fragment() {

    private lateinit var randomViewModel: RandomViewModel
    private var _binding: FragmentRandomBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        randomViewModel =
            (activity?.applicationContext as MyApplication).appComponent.injectRandomVM()
        _binding = FragmentRandomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.randomActionButton.setOnClickListener{
            randomViewModel.getRandomPokemon()
        }
        binding.addToFavoritesButton.setOnClickListener {
            randomViewModel.randomPokemon.value?.let{
                randomViewModel.savePokemon(it)
            }
        }

        randomViewModel.randomPokemon.observe(viewLifecycleOwner, Observer {
            binding.randomNameText.text = it.name
            binding.heightText.text = it.getHeight()
            binding.weightText.text = it.getWeight()
            binding.randomPokemonImage.load(it.url)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}