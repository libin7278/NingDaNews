package com.wudian.doudou.ningdanews.pager.myselfdetailpager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.wudian.doudou.ningdanews.R;

public class AboutUsActivity extends AppCompatActivity {
    private ImageView iv_title_tuihou_us;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        iv_title_tuihou_us = (ImageView) findViewById(R.id.iv_title_tuihou_us);

        iv_title_tuihou_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
