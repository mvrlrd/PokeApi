package ru.mvrlrd.pokeapi.ui.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import ru.mvrlrd.pokeapi.databinding.FragmentSearchBinding




class SearchFragment : Fragment() {
    private lateinit var searchViewModel: SearchViewModel
    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchViewModel =
            ViewModelProvider(this).get(SearchViewModel::class.java)

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.searchActionButton.setOnClickListener {
            searchViewModel.getPokemon(binding.queryText.text.toString())

        }

        searchViewModel.pokemonName.observe(viewLifecycleOwner, Observer {
            binding.nameText.text = it.name
            binding.pokemonWeightText.text ="вес: ${it.weight}"
            binding.pokemonHeightText.text ="рост: ${it.height}"
            binding.pokemonImage.load(it.sprites.front_default)
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }




}