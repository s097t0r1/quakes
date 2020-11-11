package com.example.quakeapplication

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.quakeapplication.ui.statistics.MATERIAL_COLORS
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import timber.log.Timber

@BindingAdapter("onRefresh")
fun setRefreshListener(view: SwipeRefreshLayout, clickListener: () -> Unit) {
    clickListener.let {
        view.setOnRefreshListener {
            clickListener()
        }
    }
}

@BindingAdapter("isRefreshing")
fun setIsRefreshing(view: SwipeRefreshLayout, isDataLoading: Boolean?) {
    isDataLoading?.let {
        view.isRefreshing = isDataLoading
        //Timber.d(isDataLoading.toString())
    }
}

@BindingAdapter("dataSet")
fun setDataSet(view: BarChart, dataSet: Map<String, Int>?) {
    dataSet?.let {
        val entries: MutableList<BarEntry> = ArrayList<BarEntry>()

        for((key, value) in dataSet) {
            entries.add(BarEntry(key.toFloat(), value.toFloat()))
        }

        val barDataSet = BarDataSet(entries, "Magnitude Count")
        barDataSet.setColors(MATERIAL_COLORS, 255)

        val barData = BarData(barDataSet)

        view.data = barData
        view.setFitBars(true)
        view.invalidate()

//        val cartesian: Cartesian = AnyChart.column()
//
//        val data: MutableList<DataEntry> = ArrayList()
//
//        for((key, value) in dataSet) {
//            data.add(ValueDataEntry(key, value))
//        }
//
//        val column: Column = cartesian.column(data)
//
//        column.tooltip()
//            .titleFormat("{%X}")
//            .position(Position.CENTER_BOTTOM)
//            .anchor(Anchor.CENTER_BOTTOM)
//            .offsetX(0)
//            .offsetY(0)
//            .format("\${%Value}{groupsSeparator: }")
//
//
//        cartesian.animation(true);
//        cartesian.title("Top 10 Cosmetic Products by Revenue");
//
//        cartesian.yScale().minimum(0);
//
//        cartesian.yAxis(0).labels().format("\${%Value}{groupsSeparator: }");
//
//        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
//        cartesian.interactivity().hoverMode(HoverMode.BY_X);
//
//        cartesian.xAxis(0).title("Product");
//        cartesian.yAxis(0).title("Revenue");
//
//        view.setChart(cartesian);
    }

}