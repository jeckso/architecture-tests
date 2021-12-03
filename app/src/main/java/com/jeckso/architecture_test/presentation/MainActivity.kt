package com.jeckso.architecture_test.presentation

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.fragment.app.commit
import com.jeckso.architecture_test.R
import com.jeckso.architecture_test.databinding.ActivityWithFragmentBinding
import com.jeckso.architecture_test.presentation.base.activity.BaseActivity
import com.jeckso.architecture_test.presentation.base.fragment.BaseFragment
import com.jeckso.architecture_test.presentation.list.ListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityWithFragmentBinding>() {

    override val viewModel: MainViewModel by viewModels()

    override fun inflateViewBinding(savedInstanceState: Bundle?): ActivityWithFragmentBinding {
        return ActivityWithFragmentBinding.inflate(LayoutInflater.from(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        replace(ListFragment.newInstance(), ListFragment.TAG, addToBackStack = false)
    }

    fun replace(
        fragment: BaseFragment<*, *>,
        tag: String? = null,
        enterAnimation: Int = R.anim.anim_move_to_top,
        exitAnimation: Int = R.anim.anim_move_to_bottom,
        addToBackStack: Boolean = true
    ) = supportFragmentManager.commit {
        setCustomAnimations(enterAnimation, exitAnimation, enterAnimation, exitAnimation)
        replace(R.id.fragment_container, fragment, tag)
        if (addToBackStack) {
            addToBackStack(tag)
        }
    }

}