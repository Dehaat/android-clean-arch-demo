package com.cleanarchitecturedemo.presentation.feature.home.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cleanarchitecturedemo.databinding.ItemBookBinding
import com.cleanarchitecturedemo.presentation.feature.home.models.BookWithStatusViewData


class BookAdapter(
    private val context: Context,
    private val listener: ActionClickListener
) :
    RecyclerView.Adapter<BooksViewHolder>() {

    private val books: ArrayList<BookWithStatusViewData> = arrayListOf()

    override fun getItemCount() = books.size

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = BooksViewHolder(
        ItemBookBinding.inflate(
            LayoutInflater.from(context), parent,
            false
        )
    )

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        books[position].also { book ->
            holder.bind(book, listener)
        }
    }

    fun submitUpdate(update: List<BookWithStatusViewData>) {
        val callback = BooksDiffCallback(books, update)
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(callback)

        books.clear()
        books.addAll(update)
        diffResult.dispatchUpdatesTo(this)
    }

}