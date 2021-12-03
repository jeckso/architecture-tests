package com.jeckso.architecture_test.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jeckso.architecture_test.android.adapter.OnItemClickListener
import com.jeckso.architecture_test.databinding.FragmentListBinding
import com.jeckso.architecture_test.presentation.base.fragment.BaseFragment
import com.jeckso.architecture_test.presentation.list.adapter.PostAdapter
import com.jeckso.architecture_test.presentation.list.adapter.PostVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment private constructor() : BaseFragment<ListViewModel, FragmentListBinding>(),
    OnItemClickListener<PostVM> {

    companion object {
        const val TAG = "ListFragment"

        fun arguments() = Bundle()

        fun newInstance(arguments: Bundle = Bundle()): ListFragment {
            val fragment = ListFragment()
            fragment.arguments = arguments
            return fragment
        }
    }

    override val viewModel: ListViewModel by viewModels()

    private val postAdapter = PostAdapter(this)


    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentListBinding {
        return FragmentListBinding.inflate(inflater, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.items.asObserverJob {
            postAdapter.submitData(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.setupRV()
    }

    override fun onItemClick(item: PostVM, position: Int) {

    }

    private fun FragmentListBinding.setupRV() {
        with(postsRV) {
            adapter = postAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }

}