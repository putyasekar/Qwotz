package com.putya.qwotz.ui.savedQuotesFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.putya.qwotz.R
import com.putya.qwotz.data.QuotesData
import com.putya.qwotz.databinding.ItemQuoteBinding

class SavedQuotesAdapter(
    val onBookmarkClick: (QuotesData) -> Unit,
    val onCopyClick : (String) -> Unit
) : RecyclerView.Adapter<SavedQuotesAdapter.SavedQuotesViewHolder>() {

    private val differCallBack = object : DiffUtil.ItemCallback<QuotesData>() {
        override fun areItemsTheSame(oldItem: QuotesData, newItem: QuotesData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: QuotesData, newItem: QuotesData): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedQuotesViewHolder {
        return SavedQuotesViewHolder(
            ItemQuoteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onBookmarkClick = { position ->
                val qData = differ.currentList[position]
                if (qData != null) {
                    onBookmarkClick(qData)
                }
            },
            onCopyClick = { position ->
                val qData = differ.currentList[position]
                if (qData != null) {
                    onCopyClick(qData.quoteText)
                }
            }
        )
    }

    override fun onBindViewHolder(holder: SavedQuotesViewHolder, position: Int) {
        val currentItemQuote = differ.currentList[position]

        if (currentItemQuote != null) {
            holder.bind(currentItemQuote)
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class SavedQuotesViewHolder(
        private val binding: ItemQuoteBinding,
        val onBookmarkClick: (Int) -> Unit,
        val onCopyClick: (Int) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(quote: QuotesData) {
            binding.apply {
                quoteText.text = quote.quoteText

                savedQuoteEmpty.setImageResource(
                    if (quote.isBookmarked == true) {
                        R.drawable.ic_favourite_quote_filled
                    } else {
                        R.drawable.ic_favourite_quote_empty
                    }
                )

                savedQuoteEmpty.setOnClickListener {
                    val position = bindingAdapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        onBookmarkClick(position)
                    }
                }

                copyText.setOnClickListener {
                    val position = bindingAdapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        onCopyClick(position)
                    }
                }
            }
        }
    }
}