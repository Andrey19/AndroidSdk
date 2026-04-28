package ru.effectivemobile.androidsdk.task1

import androidx.fragment.app.Fragment

interface Router {
    fun navigateTo(fragment: Fragment)
    fun navigateBack()
}