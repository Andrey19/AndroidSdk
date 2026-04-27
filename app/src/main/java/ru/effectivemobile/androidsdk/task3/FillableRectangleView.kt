package ru.effectivemobile.androidsdk.task3

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.random.Random

class FillableRectangleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var fillPercent = 0f
    private var currentFillColor = Color.rgb(70, 130, 200)
    private var clickCount = 0

    private val fillPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }

    private val borderPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 6f
        color = Color.BLACK
    }

    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        textSize = 40f
        textAlign = Paint.Align.CENTER
    }

    private var onFillUpdateListener: ((Int) -> Unit)? = null

    init {
        setOnClickListener {
            add10Percent()
        }
        isClickable = true
    }

    private fun add10Percent() {
        fillPercent = (fillPercent + 10).coerceAtMost(100f)
        clickCount++
        currentFillColor = Color.rgb(
            Random.nextInt(256),
            Random.nextInt(256),
            Random.nextInt(256)
        )
        invalidate()

        onFillUpdateListener?.invoke(clickCount)

        if (fillPercent >= 100f) {
            fillPercent = 0f
            clickCount = 0
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val width = width.toFloat()
        val height = height.toFloat()
        val fillHeight = height * (fillPercent / 100f)

        fillPaint.color = currentFillColor
        canvas.drawRect(0f, height - fillHeight, width, height, fillPaint)
        canvas.drawRect(0f, 0f, width, height, borderPaint)

        val percentText = "${fillPercent.toInt()}%"
        canvas.drawText(percentText, width / 2, height / 2, textPaint)
    }

    fun reset() {
        fillPercent = 0f
        clickCount = 0
        invalidate()
        onFillUpdateListener?.invoke(0)
    }

    fun setOnFillUpdateListener(listener: (Int) -> Unit) {
        onFillUpdateListener = listener
    }
}