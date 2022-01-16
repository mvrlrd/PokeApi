package ru.mvrlrd.pokeapi.ui.favorites

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.mvrlrd.pokeapi.MyApplication
import ru.mvrlrd.pokeapi.databinding.FragmentFavoritesBinding
import ru.mvrlrd.pokeapi.domain.models.Pokemon

class FavoritesFragment : Fragment() {
    private lateinit var favoritesViewModel: FavoritesViewModel
    private var _binding: FragmentFavoritesBinding? = null

    private lateinit var favoritesAdapter : FavoritesAdapter
    private lateinit var recyclerView : RecyclerView

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favoritesViewModel =
            (activity?.applicationContext as MyApplication).appComponent.injectFavoritesVM()

        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        favoritesViewModel.favoritePokemons.observe(viewLifecycleOwner, Observer {
            favoritesAdapter.collection = it as MutableList<Pokemon>
        })

        favoritesViewModel.getAllFavoritePokemons()

        favoritesAdapter = FavoritesAdapter()

        recyclerView = binding.favoritesRecyclerview

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = favoritesAdapter
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}