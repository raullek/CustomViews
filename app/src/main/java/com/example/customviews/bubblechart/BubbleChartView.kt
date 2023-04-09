package com.example.customviews.bubblechart

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class BubbleChartView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var contentHeight: Int = 0
    private var contentWidth: Int = 0


    private val chartPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.RED
    }


    override fun onSizeChanged(width: Int, height: Int, oldw: Int, oldh: Int) {
        contentWidth = width - paddingLeft - paddingRight
        contentHeight = height - paddingTop - paddingBottom
    }


    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        drawBubbles(canvas)
    }

    fun drawBubbles(canvas: Canvas?) {
        val xVal = contentWidth/2
        val yVal = contentHeight/2
        canvas?.drawCircle(xVal.toFloat(),yVal.toFloat(),100F,chartPaint)


    }


    fun getBubbleItems() = listOf(BubbleChartData("First", 0.54))


}