package com.putya.qwotz.ui.savedQuotesFragment

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.putya.qwotz.R
import com.putya.qwotz.databinding.FragmentSavedQuoteBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SavedQuoteFragment : Fragment(R.layout.fragment_saved_quote) {

//    private val TAG = "savedQuote"

    private lateinit var adapter: SavedQuotesAdapter

    private var _binding: FragmentSavedQuoteBinding? = null
    private val binding get() = _binding!!


    private val viewModel by activityViewModels<SavedQuotesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentSavedQuoteBinding.bind(view)

        setUpRecyclerView()

        viewModel.getSavedQuotes().observe(viewLifecycleOwner, { listQuotesData ->
            if (listQuotesData.isEmpty()) {
                binding.noSavedQuoteText.isVisible = true
                adapter.differ.submitList(listQuotesData)
            } else {
                adapter.differ.submitList(listQuotesData)
            }
        })

    }

    private fun setUpRecyclerView() {
        adapter = SavedQuotesAdapter(
            onBookmarkClick = { quote ->
                viewModel.deleteQuote(quote)
            },
            onCopyClick = { quoteText ->
                viewModel.copyQuote(quoteText)
            }
        )

        binding.apply {
            savedRecyclerViewQuotes.setHasFixedSize(true)
            savedRecyclerViewQuotes.adapter = adapter
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}