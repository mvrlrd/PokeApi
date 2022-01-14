package ru.mvrlrd.pokeapi.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ru.mvrlrd.pokeapi.databinding.FragmentRandomBinding

class RandomPokemonFragment : Fragment() {

    private lateinit var randomViewModel: RandomViewModel
    private var _binding: FragmentRandomBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

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

        val textView: TextView = binding.randomNameText
        randomViewModel.pokemonName.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}