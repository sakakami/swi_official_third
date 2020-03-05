package com.switube.www.landmark2018test.util;

import android.graphics.Color;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class LineChartManager {
    private LineChart lineChart;

    public LineChartManager(LineChart lineChart, String chartType) {
        this.lineChart = lineChart;
        lineChart.getDescription().setText(chartType);
        XAxis xAxis = this.lineChart.getXAxis();
        xAxis.setEnabled(true);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(true);
        xAxis.setDrawLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.enableGridDashedLine(5f, 5f, 0f);
        xAxis.setAvoidFirstLastClipping(true);
        xAxis.setLabelRotationAngle(10f);
        YAxis yAxisL = this.lineChart.getAxisLeft();
        yAxisL.enableAxisLineDashedLine(10f, 10f, 0f);
        yAxisL.setDrawZeroLine(false);
        yAxisL.setAxisMinimum(0f);
        YAxis yAxisR = this.lineChart.getAxisRight();
        yAxisR.setAxisMinimum(0f);
        this.lineChart.animateX(8000);
    }

    public void setEntryData(ArrayList<Entry> entries) {
        if (lineChart.getData() != null && lineChart.getData().getDataSetCount() > 0) {
            lineChart.getData().clearValues();
            LineDataSet dataSet = (LineDataSet)lineChart.getData().getDataSetByIndex(0);
            dataSet.setValues(entries);
            lineChart.getData().notifyDataChanged();
            lineChart.notifyDataSetChanged();
        } else {
            LineDataSet dataSet = new LineDataSet(entries, "");
            dataSet.setColor(Color.BLUE);
            dataSet.setCircleColor(Color.BLUE);
            dataSet.setLineWidth(1.5f);
            dataSet.setCircleRadius(1f);
            final DecimalFormat decimalFormat = new DecimalFormat("###,###,##0");
            dataSet.setValueFormatter(new ValueFormatter() {
                @Override
                public String getFormattedValue(float value) {
                    return decimalFormat.format(value);
                }
            });
            //ArrayList<LineDataSet> dataSets = new ArrayList<>();
            //dataSets.add(dataSet);
            LineData lineData = new LineData();
            lineData.addDataSet(dataSet);
            lineChart.setData(lineData);
            lineChart.invalidate();
        }
    }
}
