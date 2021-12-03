package com.jeckso.architecture_test.presentation.implementation

import com.jeckso.reddit.presentation.base.vm.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EmptyViewModel @Inject constructor() : BaseViewModel()