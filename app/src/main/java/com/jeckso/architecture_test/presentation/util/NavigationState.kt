package com.jeckso.architecture_test.presentation.util

import android.app.Activity
import android.os.Bundle
import androidx.core.os.bundleOf

sealed class NavigationState

data class BackState(
    val code: Int = Activity.RESULT_CANCELED,
    val arguments: Map<String, Any?> = mapOf()
) : NavigationState()

data class RequestPermissionsState(
    val permissions: List<String>
) : NavigationState()

abstract class NextScreenState : NavigationState()

data class LinkNavigation(
    val url: String,
    val openInsideApp: Boolean = false
): NextScreenState()

fun BackState.toBundle(): Bundle {
    return bundleOf(*arguments.map { it.key to it.value }.toTypedArray())
}
