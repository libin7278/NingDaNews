package com.wudian.doudou.ningdanews.pager.drawerlayoutpager.weibodetailpager;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wudian.doudou.ningdanews.R;

public class YCFBAcyivity extends Activity {
    private WebView wv_ningdashouye;

    private WebSettings webSettings;

    private FloatingActionButton fab_nigndanews_web_appbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ycfb_activity);

        wv_ningdashouye = (WebView) findViewById(R.id.wv_ningdashouye);

        fab_nigndanews_web_appbar = (FloatingActionButton) findViewById(R.id.fab_nigndanews_web_appbar);

        webSettings();
        wv_ningdashouye.setWebViewClient(new WebViewClient(){

        });
        String weibo_url = getIntent().getStringExtra("weibo_url");
        wv_ningdashouye.loadUrl(weibo_url);

        fab_nigndanews_web_appbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void webSettings() {
        webSettings = wv_ningdashouye.getSettings();
        webSettings.setTextSize(WebSettings.TextSize.NORMAL);
        //设置支持JavaScript
        webSettings.setJavaScriptEnabled(true);
        //设置双击变大变小的支持
        webSettings.setUseWideViewPort(true);
        //设置支持按钮
        webSettings.setBuiltInZoomControls(true);
    }
}
