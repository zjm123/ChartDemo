package com.loveupj.chartdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tvHighCharts;
    TextView tvMPAndroidChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        tvHighCharts = findViewById(R.id.tv_main_highcharts);
        tvMPAndroidChart = findViewById(R.id.tv_main_mpandroidchart);

        tvHighCharts.setOnClickListener(new onViewClick());
        tvMPAndroidChart.setOnClickListener(new onViewClick());
    }


    class onViewClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()) {
                case R.id.tv_main_highcharts:
                    intent = new Intent(MainActivity.this, HighchartsActivity.class);
                    startActivity(intent);
                    break;
                case R.id.tv_main_mpandroidchart:
                    intent = new Intent(MainActivity.this, MPAndroidChartActivity.class);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        }
    }

}
