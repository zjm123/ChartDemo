package com.loveupj.chartdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class MPAndroidChartActivity extends AppCompatActivity {
    Button btnFresh;
    LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpandroid_chart);

        initView();
    }

    private void initView() {
        setTitle("MPAndroidChart");

        btnFresh = findViewById(R.id.btn_mp_linechart_fresh);
        lineChart = findViewById(R.id.lc_mp_linechart);
        btnFresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData();
            }
        });

        setChartStyle();
        setData();
    }

    private void setChartStyle() {
        //显示边界
        lineChart.setDrawBorders(true);
    }

    private void setData() {
        //设置数据
        List<Entry> entries = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            entries.add(new Entry(i, (float) (Math.random()) * 80));
        }
        //一个LineDataSet就是一条线
        LineDataSet lineDataSet = new LineDataSet(entries, "温度");
        LineData data = new LineData(lineDataSet);
        lineChart.clear();
        lineChart.setData(data);
    }

}
