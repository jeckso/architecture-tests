package com.jeckso.architecture_test.presentation.base.fragment

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.google.android.material.appbar.MaterialToolbar
import com.jeckso.reddit.presentation.base.vm.BaseViewModel
import timber.log.Timber

abstract class BaseToolbarFragment<VM : BaseViewModel, VB : ViewBinding> : BaseFragment<VM, VB>() {

    abstract val toolbar: MaterialToolbar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onResume() {
        super.onResume()
        setupActionBar()
    }

    private fun setupActionBar() {
        val activity = requireActivity() as? AppCompatActivity
        activity?.setSupportActionBar(toolbar)
        activity?.supportActionBar?.setupActionBar()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Timber.e("SELECTED $item")
        if (isHidden) {
            return false
        }
        return when (item.itemId) {
            android.R.id.home -> {
                viewModel.navigateBack()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        setHasOptionsMenu(!hidden)
        if (!hidden) {
            setupActionBar()
        }
    }

    protected open fun ActionBar.setupActionBar() {
        val displayHome = parentFragmentManager.backStackEntryCount > 0
        setDisplayHomeAsUpEnabled(displayHome)
        setDisplayShowHomeEnabled(true)
        setHomeButtonEnabled(true)
        setDisplayUseLogoEnabled(true)
        title = ""
    }
}