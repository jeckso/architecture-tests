package com.jeckso.architecture_test.android.utils

import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.hideKeyboard() {
    val inputMethodManager = getSystemService(FragmentActivity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
}

fun Fragment.hideKeyboard() = requireActivity().hideKeyboard()