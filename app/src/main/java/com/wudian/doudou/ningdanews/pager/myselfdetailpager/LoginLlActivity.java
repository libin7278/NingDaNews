package com.wudian.doudou.ningdanews.pager.myselfdetailpager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.wudian.doudou.ningdanews.R;

public class LoginLlActivity extends AppCompatActivity {
    private Button email_sign_in_button;
    private ImageView iv_title_tuihou_log;
    private Button email_sign_out_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_ll);
        email_sign_out_button = (Button) findViewById(R.id.email_sign_out_button);
        iv_title_tuihou_log = (ImageView) findViewById(R.id.iv_title_tuihou_log);
        email_sign_in_button = (Button) findViewById(R.id.email_sign_in_button);

        email_sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(),"登录失败,用户名/密码不正确",Toast.LENGTH_SHORT).show();
            }
        });

        iv_title_tuihou_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        email_sign_out_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(),"服务器请求失败...",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
