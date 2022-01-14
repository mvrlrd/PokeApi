package ru.mvrlrd.pokeapi.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import ru.mvrlrd.pokeapi.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {


    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root



        binding.searchActionButton.setOnClickListener {
            homeViewModel.getPokemon(binding.queryText.text.toString())
        }


        homeViewModel.pokemonName.observe(viewLifecycleOwner, Observer {
            binding.nameText.text = it.name
            binding.weightText.text ="weight: ${it.weight}"
            binding.heightText.text ="height: ${it.height}"
            binding.imageView.load(it.sprites.front_default)
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}