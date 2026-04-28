package ru.effectivemobile.androidsdk.task1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import ru.effectivemobile.androidsdk.R

class SecondFragment : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        val btnPrevious = view.findViewById<Button>(R.id.btnPrevious)
        val btnNext = view.findViewById<Button>(R.id.btnNext)

        tvTitle.text = "Фрагмент 2/3"

        btnPrevious.setOnClickListener {
            (parentFragment as? Task1Fragment)?.getRouter()?.navigateBack()
        }

        btnNext.setOnClickListener {
            (parentFragment as? Task1Fragment)?.getRouter()?.navigateTo(ThirdFragment())
        }
    }
}