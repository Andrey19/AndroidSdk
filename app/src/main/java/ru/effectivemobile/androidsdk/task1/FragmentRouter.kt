package ru.effectivemobile.androidsdk.task1

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class FragmentRouter(
    private val fragmentManager: FragmentManager,
    private val containerId: Int
) : Router {

    private var currentIndex = 0
    private val fragments = listOf(
        FirstFragment(),
        SecondFragment(),
        ThirdFragment()
    )

    init {
        fragmentManager.beginTransaction()
            .add(containerId, fragments[currentIndex])
            .commit()
    }

    override fun navigateToNext() {
        if (currentIndex < fragments.size - 1) {
            currentIndex++
            fragmentManager.beginTransaction()
                .replace(containerId, fragments[currentIndex])
                .addToBackStack(null)
                .commit()
        }
    }

    override fun navigateToPrevious() {
        if (currentIndex > 0 && fragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStack()
            currentIndex--
        }
    }
}