package com.example.customviews.chart

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class ChartView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var contentHeight: Int = 0
    private var contentWidth: Int = 0
    private val defaultPadding = 32f
    private var xMin = 0.0
    private var xMax = 0.0
    private var yMin = 0.0
    private var yMax = 0.0
    private val contentRect = RectF(0f, 0f, 0f, 0f)

    private val dataSet = mutableListOf<ChartPoints>()

    private val chartPaint = Paint(ANTI_ALIAS_FLAG).apply {
        color = Color.RED
    }

    private val backgroundPain = Paint(ANTI_ALIAS_FLAG)

    private val axisPaint = Paint(ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
    }
    private val pointsPaint = Paint(ANTI_ALIAS_FLAG).apply {
        color = Color.BLUE
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onSizeChanged(width: Int, height: Int, oldw: Int, oldh: Int) {
        contentWidth = width - paddingLeft - paddingRight
        contentHeight = height - paddingTop - paddingBottom

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawLines(canvas)
        drawPoints(canvas)

    }

    private fun drawLines(canvas: Canvas?) {
        canvas?.drawLine(
            defaultPadding,
            defaultPadding,
            defaultPadding,
            (contentHeight.toFloat() - defaultPadding),
            axisPaint
        )

        canvas?.drawLine(
            defaultPadding,
            (contentHeight.toFloat() - defaultPadding),
            contentWidth.toFloat() - defaultPadding,
            (contentHeight.toFloat() - defaultPadding),
            axisPaint
        )
    }

    private fun drawPoints(canvas: Canvas?) {
        dataSet.forEachIndexed { index, currentChartPoints ->
            val realX = currentChartPoints.xVal.toRealX()
            val realY = currentChartPoints.yVal.toRealY()

            if (index < dataSet.size - 1) {
                val nextDataPoint = dataSet[index + 1]
                val startX = currentChartPoints.xVal.toRealX()
                val startY = currentChartPoints.yVal.toRealY()
                val endX = nextDataPoint.xVal.toRealX()
                val endY = nextDataPoint.yVal.toRealY()
                canvas?.drawLine(startX.toFloat(), startY.toFloat(), endX.toFloat(), endY.toFloat(), chartPaint)
            }

            canvas?.drawCircle(realX.toFloat(), realY.toFloat(), 6f, pointsPaint)
        }


    }

    private fun Double.toRealX() = toFloat() / xMax * (contentWidth - defaultPadding)
    private fun Double.toRealY() = toFloat() / yMax * (contentHeight - defaultPadding)

    fun setData(dataList: List<ChartPoints>) {
        xMin = dataList.minOfOrNull { it.xVal } ?: 0.0
        xMax = dataList.maxOfOrNull { it.xVal } ?: 0.0
        yMin = dataList.minOfOrNull { it.yVal } ?: 0.0
        yMax = dataList.maxOfOrNull { it.yVal } ?: 0.0
        dataSet.clear()
        dataSet.addAll(dataList)
        invalidate()
    }


}