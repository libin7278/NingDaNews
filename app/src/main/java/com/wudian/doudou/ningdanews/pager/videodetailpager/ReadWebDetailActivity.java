package com.wudian.doudou.ningdanews.pager.videodetailpager;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.wudian.doudou.ningdanews.R;
import com.wudian.doudou.ningdanews.domain.ArticleInfoBean;

import java.util.HashMap;
import java.util.Map;

public class ReadWebDetailActivity extends Activity {

    private String url = "http://api2.pianke.me/article/info";

    private TextView tv_tittlebar_word;
    private WebView webview;
    private LinearLayout ll_pb_progressbar;
    private LinearLayout ll_pb_text;
    private LinearLayout ll_web_bottom_gushi;

    private ImageView iv_title_tuihou;
    private ImageView iv_title_love;
    private ImageView iv_title_more;
    private ImageView iv_title_pinlun;

    private ImageView iv_write_author;
    private TextView tv_write_author;
    private ImageButton tv_guanzhu;
    private TextView tv_write_content;

    private TextView web_bottom_gushi;

    private int positions;

    private String contentid;

    private WebSettings settings;

    private Gson gson;
    private ArticleInfoBean articleInfoBean;
    private String html;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_web_detail);

        initView();

        positions = getIntent().getIntExtra("positions", -1);
        String typetitle = getIntent().getStringExtra("typetitle");

        if (-1 < positions) {

            web_bottom_gushi.setText("查看来自" + "“" + typetitle + "”" + "的全部文章→");
        } else {
            ll_web_bottom_gushi.setVisibility(View.GONE);

        }

        contentid = getIntent().getStringExtra("contentids");

        initListener();

        initData();
    }

    private void initListener() {
        // 设置对应页面加载完成的监听
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                webview.setWebViewClient(new WebViewClient() {

                    @Override
                    public void onPageFinished(WebView view, String url) {
                        // TODO Auto-generated method stub
                        super.onPageFinished(view, url);
                        ll_pb_progressbar.setVisibility(View.GONE);
                    }
                });
            }
        }, 2000);

        iv_title_pinlun.setOnClickListener(mOnClickListener);
        iv_title_more.setOnClickListener(mOnClickListener);
        iv_title_love.setOnClickListener(mOnClickListener);
        iv_title_tuihou.setOnClickListener(mOnClickListener);
        web_bottom_gushi.setOnClickListener(mOnClickListener);

    }

    private void initView() {
        iv_title_more = (ImageView) findViewById(R.id.iv_title_more);
        iv_title_love = (ImageView) findViewById(R.id.iv_title_love);
        iv_title_tuihou = (ImageView) findViewById(R.id.iv_title_tuihou);
        iv_title_pinlun = (ImageView) findViewById(R.id.iv_title_pinlun);
        tv_tittlebar_word = (TextView) findViewById(R.id.tv_tittlebar_word);
        web_bottom_gushi = (TextView) findViewById(R.id.web_bottom_gushi);
        ll_pb_progressbar = (LinearLayout) findViewById(R.id.ll_pb_progressbar);
        ll_pb_text = (LinearLayout) findViewById(R.id.ll_pb_text);
        ll_web_bottom_gushi = (LinearLayout) findViewById(R.id.ll_web_bottom_gushi);

        iv_write_author = (ImageView) findViewById(R.id.iv_write_author);
        tv_guanzhu = (ImageButton) findViewById(R.id.tv_read_guanzhu);
        tv_write_content = (TextView) findViewById(R.id.tv_write_content);
        tv_write_author = (TextView) findViewById(R.id.tv_write_author);
        webview = (WebView) findViewById(R.id.webview);

    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.iv_title_tuihou:// 推出这个页面
                    finish();
                    break;
                case R.id.iv_title_pinlun:// 点击评论进入评论的页面
                    break;
                case R.id.iv_title_love:// 点击A调节大小,调节皮肤白天黑夜

                    break;

                case R.id.web_bottom_gushi:// 点击web底部的查看相对应的代码

                    break;
                case R.id.iv_title_more:
                    break;
                default:
                    break;
            }

        }
    };

    private void initData() {

        settings = webview.getSettings();
        // 设置WebView支持javaScript
        settings.setJavaScriptEnabled(true);
        // 设置支持显示缩放的按钮
        settings.setBuiltInZoomControls(true);
        // 设置支持双击变大变小
        settings.setUseWideViewPort(true);
        settings.setBuiltInZoomControls(true);

        getDataFromNet();
    }

    private void getDataFromNet() {

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.e("TAG", "写作详情联网请求成功==" + response);
                        processData(response.toString());
                        ll_pb_progressbar.setVisibility(View.GONE);

                    }

                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError message) {
                ll_pb_progressbar.setVisibility(View.GONE);
                ll_pb_text.setVisibility(View.VISIBLE);
                // TODO Auto-generated method stub
                Log.e("TAG", "写作详情联网请求失败==" + message);

            }

        })

        {
            @Override
            protected Response<String> parseNetworkResponse(
                    NetworkResponse response) {
                try {
                    String jsonString = new String(response.data, "UTF-8");
                    return Response.success(jsonString,
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (Exception je) {
                    return Response.error(new ParseError(je));
                }
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                HashMap<String, String> map = new HashMap<String, String>();

                map.put("contentid", contentid);
                map.put("client", "2");

                return map;
            }

        };
        requestQueue.add(request);

    }

    /**
     * 解析数据
     *
     * @param
     */
    protected void processData(String result) {
        gson = new Gson();
        articleInfoBean = gson.fromJson(result, ArticleInfoBean.class);
        html = articleInfoBean.getData().getHtml();

        Log.e("TAG", "mHtml======" + html);

        if (!TextUtils.isEmpty(html)) {

            webview.loadDataWithBaseURL("fake://not/needed", html, "text/html",
                    "utf-8", "");
        }
        String write_author = articleInfoBean.getData().getUserinfo()
                .getUname();
        tv_write_author.setText(write_author);

        String write_content = articleInfoBean.getData().getUserinfo()
                .getDesc();

        tv_write_content.setText(write_content);
    }
}
