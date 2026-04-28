package ru.effectivemobile.androidsdk.task1

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import ru.effectivemobile.androidsdk.R

class FirstFragment : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        val btnPrevious = view.findViewById<Button>(R.id.btnPrevious)
        val btnNext = view.findViewById<Button>(R.id.btnNext)

        tvTitle.text = "Фрагмент 1/3"

        btnPrevious.isEnabled = false
        btnPrevious.alpha = 0.5f

        btnNext.setOnClickListener {
            val router = (parentFragment as? Task1Fragment)?.getRouter()
            router?.navigateTo(SecondFragment())
        }
    }
}