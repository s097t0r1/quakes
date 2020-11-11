package com.example.quakeapplication.ui.quakes

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.example.quakeapplication.R
import kotlin.math.min

class BubbleTextView(context: Context, attrs: AttributeSet) : AppCompatTextView(context, attrs) {
    private var radius = 6
    private val paintStyle = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }
    private var textBounds = Rect()

    var bubbleColor: Int = Color.CYAN
        set(value) {
            paintStyle.color = value
            field = value
        }

    init {
        paint.textAlign = Paint.Align.CENTER

        if (isInEditMode) {
            text = "0"
        }

        context.theme.obtainStyledAttributes(attrs, R.styleable.BubbleTextView, 0, 0).apply {
            bubbleColor = getColor(R.styleable.BubbleTextView_bubbleColor, Color.CYAN)
            paintStyle.color = bubbleColor
        }

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        radius = min(w, h) / 2
        paint.getTextBounds(text.toString(), 0, text.length, textBounds)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawCircle(
            (width / 2).toFloat(),
            (height / 2).toFloat(),
            radius.toFloat(),
            paintStyle
        )
        canvas.drawText(
            text.toString(),
            (width / 2).toFloat(),
            (height / 2 + (textBounds.height() / 2)).toFloat(),
            paint
        )
    }

}