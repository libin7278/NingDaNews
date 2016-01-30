package com.wudian.doudou.ningdanews.pager.tabpager;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.wudian.doudou.ningdanews.R;

public class NewsWebActivity extends AppCompatActivity {

    private CollapsingToolbarLayout toolbar_layout;

    //新闻url
    private String newsUrl;
    private String newsTitle;

    private WebSettings webSettings;

    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_web);

        webview = (WebView) findViewById(R.id.webview);

        toolbar_layout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_news_web_appbar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        newsUrl = getIntent().getStringExtra("newsUrl");
        Log.d("TAG","newsUrl------------------------"+newsUrl);
        newsTitle = getIntent().getStringExtra("newsTitle");

        webSettings();

        webview.loadUrl(newsUrl);

        toolbar_layout.setTitle(newsTitle);

    }

    private void webSettings() {
        webSettings = webview.getSettings();
        webSettings.setTextSize(WebSettings.TextSize.NORMAL);
        //设置支持JavaScript
        webSettings.setJavaScriptEnabled(true);
        //设置双击变大变小的支持
        webSettings.setUseWideViewPort(true);
        //设置支持按钮
        webSettings.setBuiltInZoomControls(true);
    }
}
