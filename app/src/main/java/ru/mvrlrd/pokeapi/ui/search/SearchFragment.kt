package ru.mvrlrd.pokeapi.ui.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import ru.mvrlrd.pokeapi.databinding.FragmentSearchBinding



 const val REQUEST_KEY = "requestKey"
 const val QUERY_KEY = "queryKey"
class SearchFragment : Fragment() {
    private lateinit var searchViewModel: SearchViewModel
    private var _binding: FragmentSearchBinding? = null
    private val searchDialogFragment = SearchDialogFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener("requestKey")
        {
                key, bundle ->
            val query = bundle.getString("queryKey")
            searchViewModel.getPokemon(query!!)
        }
    }

    private val binding get() = _binding!!

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchViewModel =
            ViewModelProvider(this)[SearchViewModel::class.java]

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.searchActionButton.setOnClickListener {
            parentFragmentManager.let {
                searchDialogFragment.show(it, "SearchingDialogFragment")
            }
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