package com.example.customviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.customviews.chart.ChartPoints
import com.example.customviews.chart.ChartView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        findViewById<ChartView>(R.id.chart).setData(listOf(
//            ChartPoints(20.4, 30.4),
//            ChartPoints(35.4, 23.4),
//            ChartPoints(45.4, 43.4),
//            ChartPoints(78.4, 56.4),
//            ChartPoints(123.4, 232.4),
//            ChartPoints(77.4, 33.4),
//            ChartPoints(149.4, 170.4),
//        ).sortedBy { it.xVal })



    }
}