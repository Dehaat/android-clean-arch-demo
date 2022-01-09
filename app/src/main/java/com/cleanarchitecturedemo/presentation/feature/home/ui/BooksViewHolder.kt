package com.cleanarchitecturedemo.presentation.feature.home.ui

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cleanarchitecturedemo.R
import com.cleanarchitecturedemo.databinding.ItemBookBinding
import com.cleanarchitecturedemo.presentation.feature.home.models.BookWithStatusViewData
import com.cleanarchitecturedemo.presentation.feature.home.models.BookmarkStatusViewData

class BooksViewHolder(private val binding: ItemBookBinding) : RecyclerView.ViewHolder(binding.root) {

    private val context = binding.root.context
    fun bind(book: BookWithStatusViewData, listener: ActionClickListener) = binding.apply {
        book.imageUrl?.let { imageUrl ->
            Glide.with(context)
                .load(imageUrl)
                .into(ivBookCover)
            tvBookName.text = ""
            tvBookAuthors.text = ""
        } ?: kotlin.run {
            Glide.with(context)
                .load(R.drawable.ic_launcher_background)
                .into(ivBookCover)
            tvBookName.text = book.title
            tvBookAuthors.text = book.authors.joinToString()
        }

        ivUnbookmark.setOnClickListener {
            listener.unBookmark(book)
        }

        ivBookmark.setOnClickListener {
            listener.bookmark(book)
        }

        when (book.status) {
            BookmarkStatusViewData.BOOKMARKED -> {
                ivBookmark.visibility = View.GONE
                ivUnbookmark.visibility = View.VISIBLE
            }
            BookmarkStatusViewData.UNBOOKMARKED -> {
                ivBookmark.visibility = View.VISIBLE
                ivUnbookmark.visibility = View.GONE
            }
        }
    }

}

class BooksDiffCallback(
    private val oldBooks: List<BookWithStatusViewData>,
    private val newBooks: List<BookWithStatusViewData>
) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldBooks.size
    }

    override fun getNewListSize(): Int {
        return newBooks.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldBooks[oldItemPosition].id == newBooks[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldBooks[oldItemPosition].status == newBooks[newItemPosition].status
    }
}