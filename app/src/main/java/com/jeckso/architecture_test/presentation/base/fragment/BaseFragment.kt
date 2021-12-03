package com.jeckso.architecture_test.presentation.base.fragment

import android.content.Context
import android.os.Bundle
import android.text.TextWatcher
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.viewbinding.ViewBinding
import com.google.android.material.textfield.TextInputEditText
import com.jeckso.architecture_test.android.utils.hideKeyboard
import com.jeckso.architecture_test.presentation.base.activity.BaseActivity
import com.jeckso.architecture_test.presentation.field.FieldHolder
import com.jeckso.architecture_test.presentation.field.MutableFieldHolder
import com.jeckso.architecture_test.presentation.util.*
import com.jeckso.reddit.presentation.base.vm.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class BaseFragment<VM : BaseViewModel, VB : ViewBinding> : Fragment() {

    companion object {
        private const val NO_ITEMS = 0
    }

    protected abstract val viewModel: VM
    protected lateinit var viewBinding: VB

    private var baseSubscriptionJobs: Job? = null

    protected val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            viewModel.navigateBack()
        }
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


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) = menu.clear()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        viewBinding = inflateViewBinding(inflater, container, savedInstanceState)
        return viewBinding.root
    }

    private var job: Job? = null

    override fun onDestroyView() {
        requireActivity().hideKeyboard()
        super.onDestroyView()
        job?.cancel()
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
        savedInstanceState: Bundle?,
    ): VB

    protected open fun handleBackNavigation(state: BackState) {
        val count = parentFragmentManager.backStackEntryCount
        if (count > NO_ITEMS) {
            parentFragmentManager.popBackStack()
        } else {
            requireActivity().finish()
        }
    }

    protected open fun handleCustomNavigation(state: NextScreenState) {
        (requireActivity() as? BaseActivity<*, *>)?.handleCustomNavigation(state)
    }

    override fun onResume() {
        super.onResume()
        onBackPressedCallback.isEnabled = true
    }

    override fun onPause() {
        onBackPressedCallback.isEnabled = false
        super.onPause()
    }

    protected fun <T> ViewModel.observe(
        fieldHolder: FieldHolder<T>,
        onSuccess: (T) -> Unit = {},
        onError: (Throwable?) -> Unit = {},
    ): Observer<Result<T>> {
        val observer: Observer<Result<T>> = Observer {
            when {
                it.isSuccess -> it.getOrNull()
                    ?.also(onSuccess)
                    ?.let { onError(null) }
                it.isFailure -> it.exceptionOrNull()?.let(onError)
            }
        }
        fieldHolder.state
            .asLiveData(viewModelScope.coroutineContext)
            .observe(viewLifecycleOwner, observer)
        return observer
    }



    protected fun subscribe(
        editText: TextInputEditText,
        fieldHolder: MutableFieldHolder<String>,
    ): TextWatcher {
        return editText.doAfterTextChanged {
            fieldHolder.setValue(it?.toString())
        }
    }

    private fun handleErrorMessage(failure: Failure) {
        when {
            failure.error != null -> handleError(failure.error)
            failure.message != null -> showErrorDialog(failure.message)
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        onBackPressedCallback.isEnabled = !hidden
        childFragmentManager.fragments.forEach { it.onHiddenChanged(hidden) }
    }

    protected fun <T> Flow<T>.asObserverJob(
        rootJob: Job? = null,
        state: Lifecycle.State = Lifecycle.State.STARTED,
        block: suspend (T) -> Unit
    ): Job {
        val block: suspend (CoroutineScope.() -> Unit) = {
            repeatOnLifecycle(state) {
                collect(block)
            }
        }
        return when (rootJob) {
            null -> lifecycleScope.launch(block = block)
            else -> lifecycleScope.launch(rootJob, block = block)
        }
    }

    private fun shouldShowProgress(isVisible: Boolean) {
        Timber.e("$this $isVisible")
        (requireActivity() as? BaseActivity<*, *>)?.shouldShowProgress(isVisible)
    }
}