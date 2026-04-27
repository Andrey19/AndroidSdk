package ru.effectivemobile.androidsdk.task3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import ru.effectivemobile.androidsdk.R

class Task3Fragment : Fragment() {

    private lateinit var rectangleView: FillableRectangleView
    private lateinit var tvClickCount: TextView
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_task3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rectangleView = view.findViewById(R.id.rectangleView)
        tvClickCount = view.findViewById(R.id.tvClickCount)
        progressBar = view.findViewById(R.id.progressBar)
        val btnReset = view.findViewById<Button>(R.id.btnReset)

        rectangleView.setOnFillUpdateListener { clickCount ->
            val percent = (clickCount * 10).coerceAtMost(100)
            tvClickCount.text = "📊 Кликов: $clickCount (залито $percent%)"
            progressBar.progress = percent

            if (percent >= 100) {
                tvClickCount.text = "🎉 100% достигнуто! Начинаем заново..."
            }
        }

        btnReset.setOnClickListener {
            rectangleView.reset()
            tvClickCount.text = "📊 Кликов: 0 (залито 0%)"
            progressBar.progress = 0
        }
    }
}