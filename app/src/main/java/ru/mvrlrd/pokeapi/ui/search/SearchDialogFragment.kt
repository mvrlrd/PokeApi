package ru.mvrlrd.pokeapi.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_search_dialog.*
import kotlinx.android.synthetic.main.fragment_search_dialog.view.*
import ru.mvrlrd.pokeapi.R

class SearchDialogFragment: BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_search_dialog, container, true)

        root.btn_start_searching.setOnClickListener {
            sendQueryText()
        }

        root.searchingByIngredients_EditText.setOnEditorActionListener { _, actionId, _ ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    sendQueryText()
                    searchingByIngredients_EditText.text?.clear()
                    dismiss()
                    true
                }
                else -> false
            }
        }

        return root
    }

    private fun sendQueryText() {
        val query = searchingByIngredients_EditText.text.toString()
        if (query.isNotEmpty()) {
            setFragmentResult("requestKey", bundleOf("queryKey" to query))
        }
    }
}