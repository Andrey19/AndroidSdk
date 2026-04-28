package ru.effectivemobile.androidsdk.task1

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager


class FragmentRouter(
    private val fragmentManager: FragmentManager,
    private val containerId: Int
) : Router {

    override fun navigateTo(fragment: Fragment) {
        fragmentManager.beginTransaction()
            .replace(containerId, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun navigateBack() {
        if (fragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStack()
        }
    }
}