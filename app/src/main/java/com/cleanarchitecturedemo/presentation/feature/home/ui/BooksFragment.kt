package com.cleanarchitecturedemo.presentation.feature.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.agridroid.baselib.callbacks.IProgressViewListener
import com.agridroid.baselib.extensions.processWithProgress
import com.cleanarchitecturedemo.databinding.FragmentBooksBinding
import com.cleanarchitecturedemo.presentation.feature.home.models.BookWithStatusViewData
import com.cleanarchitecturedemo.presentation.utils.LayoutUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BooksFragment : Fragment(), IProgressViewListener {

    private val binding: FragmentBooksBinding by viewBinding(CreateMethod.INFLATE)

    private lateinit var booksAdapter: BookAdapter

    private val booksViewModel: BooksViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        booksAdapter = BookAdapter(requireContext(), object : ActionClickListener {
            override fun bookmark(book: BookWithStatusViewData) {
                booksViewModel.bookmark(book)
            }

            override fun unBookmark(book: BookWithStatusViewData) {
                booksViewModel.unBookmark(book)
            }
        })

        booksViewModel.searchBooks("Robert C. Martin")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.apply {
            launch {

                // The block passed to repeatOnLifecycle is executed when the lifecycle
                // is at least STARTED and is cancelled when the lifecycle is STOPPED.
                // It automatically restarts the block when the lifecycle is STARTED again.

                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    booksViewModel.books.collectLatest {
                        it.processWithProgress(this@BooksFragment, {
                            booksAdapter.submitUpdate(it)
                        }) { messageViewType, errorMsg ->
                            Toast.makeText(
                                requireContext(),
                                errorMsg,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }

        }

        binding.rvBooks.apply {
            adapter = booksAdapter
        }
    }

    companion object {
        const val COLUMNS_COUNT = 2
    }

    override fun showProgressView() {
        binding.apply {
            LayoutUtils.crossFade(pbLoading, rvBooks)
        }
    }

    override fun hideProgressView() {
        binding.apply {
            LayoutUtils.crossFade(rvBooks, pbLoading)
        }
    }
}

