package com.wudian.doudou.ningdanews.pager.myselfdetailpager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.wudian.doudou.ningdanews.R;

public class OptionRefectActivity extends Activity implements View.OnClickListener {
    private EditText ed_myself_ora_title;

    private EditText ed_myself_ora_content;

    private ImageView iv_myself_banck;

    private Button btn_muself_tijiao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_refect);
        ed_myself_ora_title = (EditText) findViewById(R.id.ed_myself_ora_title);
        ed_myself_ora_content = (EditText) findViewById(R.id.ed_myself_ora_content);
        iv_myself_banck = (ImageView) findViewById(R.id.iv_myself_banck);
        btn_muself_tijiao = (Button) findViewById(R.id.btn_muself_tijiao);

        iv_myself_banck.setOnClickListener(this);
        btn_muself_tijiao.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == iv_myself_banck) {
            finish();
        } else if (v == btn_muself_tijiao) {
            if (ed_myself_ora_title.getText().toString().equals("") && ed_myself_ora_content.getText().toString().equals("")) {
                Toast.makeText(this, "提交失败,内容不能为空", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "提交成功", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}
