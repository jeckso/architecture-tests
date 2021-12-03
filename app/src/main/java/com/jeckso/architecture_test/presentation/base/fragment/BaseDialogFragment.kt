package com.jeckso.architecture_test.presentation.base.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.withStarted
import androidx.viewbinding.ViewBinding
import com.jeckso.architecture_test.presentation.base.activity.BaseActivity
import com.jeckso.architecture_test.presentation.util.*
import com.jeckso.reddit.presentation.base.vm.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class BaseDialogFragment<VB : ViewBinding, VM : BaseViewModel> : DialogFragment() {

    protected abstract val viewModel: VM
    protected lateinit var viewBinding: ViewBinding

    private var baseSubscriptionJobs: Job? = null

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() = viewModel.navigateBack()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseSubscriptionJobs = lifecycleScope.launch {
            viewModel.navigationState
                .onEach { withStarted { onNavigationChanged(it) } }
                .launchIn(this)
            viewModel.errorState
                .onEach { withStarted { handleErrorMessage(it) } }
                .launchIn(this)
            viewModel.shouldShowProgress
                .onEach { withStarted { shouldShowProgress(it) } }
                .launchIn(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = inflateViewBinding(inflater, container, savedInstanceState)
        return viewBinding.root
    }

    override fun onDestroy() {
        baseSubscriptionJobs?.cancel()
        baseSubscriptionJobs = null
        super.onDestroy()
    }

    protected open fun onNavigationChanged(navigation: NavigationState) = when (navigation) {
        is BackState -> handleBackNavigation(navigation)
        is NextScreenState -> handleCustomNavigation(navigation)
        is RequestPermissionsState -> handlePermissionRequestState(navigation)
    }

    open fun handleError(error: Throwable) {
        (requireActivity() as? BaseActivity<*, *>)?.handleError(error)
    }

    open fun handlePermissionRequestState(requestPermissionsState: RequestPermissionsState) = Unit

    open fun showErrorDialog(message: String) {
        (requireActivity() as? BaseActivity<*, *>)?.showErrorDialog(message)
    }

    protected abstract fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): VB

    protected open fun handleBackNavigation(state: BackState) {
        dismiss()
    }

    protected open fun handleCustomNavigation(state: NextScreenState) {
        (requireActivity() as? BaseActivity<*, *>)?.handleCustomNavigation(state)
    }

    private fun handleErrorMessage(failure: Failure) {
        Timber.e("$this $failure")
        when {
            failure.error != null -> handleError(failure.error)
            failure.message != null -> showErrorDialog(failure.message)
        }
    }

    private fun shouldShowProgress(isVisible: Boolean) {
        Timber.e("$this $isVisible")
        (requireActivity() as? BaseActivity<*, *>)?.shouldShowProgress(isVisible)
    }
}