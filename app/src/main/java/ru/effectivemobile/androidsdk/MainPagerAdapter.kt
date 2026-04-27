package ru.effectivemobile.androidsdk

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.effectivemobile.androidsdk.task1.Task1Fragment
import ru.effectivemobile.androidsdk.task2.Task2Fragment
import ru.effectivemobile.androidsdk.task3.Task3Fragment
import ru.effectivemobile.androidsdk.task4.Task4Fragment

class MainPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> Task1Fragment()
            1 -> Task2Fragment()
            2 -> Task3Fragment()
            3 -> Task4Fragment()
            else -> Task1Fragment()
        }
    }
}