package com.loveupj.chartdemo;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.google.gson.Gson;

import java.util.Random;

public class HighchartsActivity extends AppCompatActivity {
    Button btnFresh;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highcharts);

        init();
    }

    private void init() {
        setTitle("Highcharts");
        btnFresh = findViewById(R.id.btn_highcharts_fresh);
        webView = findViewById(R.id.wv_highcharts_webView);

        btnFresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadUrl("javascript:refresh('" + getJsData() + "')");
            }
        });

        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 返回值是true的时候就用WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (!webView.getSettings().getLoadsImagesAutomatically()) {
                    webView.getSettings().setLoadsImagesAutomatically(true);
                }
            }
        });

        // 启用支持javascript
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setDomStorageEnabled(true);
        settings.setAppCacheMaxSize(8 * 1024 * 1024);
        settings.setAllowFileAccess(true);
        settings.setAppCacheEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            settings.setLoadsImagesAutomatically(true);
        } else {
            settings.setLoadsImagesAutomatically(false);
        }

        // 加载网页文件
        loadUrl("file:///android_asset/index.html");
    }

    private void loadUrl(final String url) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl(url);
            }
        });
    }

    private String getJsData() {
        JsData data = new JsData();
        data.categories = new String[]{"春", "夏", "秋", "冬"};
        data.series = new YData[2];
        Random random = new Random();

        for (int i = 0; i < data.series.length; i++) {
            data.series[i] = new YData();
            data.series[i].name = "Name" + (i + 1);
            int j = i;
            data.series[i].data = new int[]{100 * j + random.nextInt(100), 100 * j + random.nextInt(100), 100 * j + random.nextInt(100), 100 * j + random.nextInt(100)};
        }
        String json = new Gson().toJson(data);
        System.out.println("json = " + json);
        return json;
    }

    class JsData {
        String[] categories;
        YData[] series;
    }

    class YData {
        String name;
        int[] data;
    }

}
