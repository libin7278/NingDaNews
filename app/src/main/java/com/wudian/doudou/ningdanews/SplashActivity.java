package com.wudian.doudou.ningdanews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.wudian.doudou.ningdanews.utils.CacheUtils;

public class SplashActivity extends Activity {
    public static final String IS_ENTER_MAIN_ACTIVITY = "isEnterMainActivity";

    private RelativeLayout rl_spalash_demo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        rl_spalash_demo = (RelativeLayout) findViewById(R.id.rl_spalash_demo);

        //设置三个动画：缩放动画，旋转动画，渐变动画
        ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        sa.setDuration(2000);//设置播放时长
        sa.setFillAfter(true);//设置停留在播放后的状态

        AlphaAnimation aa = new AlphaAnimation(0, 1);
        aa.setDuration(2000);//设置播放时长
        aa.setFillAfter(true);//设置停留在播放后的状态

        RotateAnimation ra = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        ra.setDuration(2000);//设置播放时长
        ra.setFillAfter(true);//设置停留在播放后的状态

        //让三个动画同时播放,添加动画没有先后顺序
        AnimationSet as = new AnimationSet(false);//false 表示与动画时间没有关系
        as.addAnimation(sa);
        as.addAnimation(ra);
        as.addAnimation(aa);

        rl_spalash_demo.startAnimation(as);

        as.setAnimationListener(new MyAnimationListener());

    }

    class MyAnimationListener implements Animation.AnimationListener {
        //开始时候调用
        @Override
        public void onAnimationStart(Animation animation) {

        }

        //结束时候调用
        @Override
        public void onAnimationEnd(Animation animation) {
            //判断一下是否进入过引导页面；
            //如果进入过了，并且点按钮了，就直接主页面，
            //否则去引导页面
            Intent intent;
            boolean isEnterMainActivity = CacheUtils.getBoolean(SplashActivity.this, IS_ENTER_MAIN_ACTIVITY);
            if (isEnterMainActivity) {
                intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {

                intent = new Intent(SplashActivity.this, GuideActivity.class);
                startActivity(intent);
                finish();
            }
        }

        //重复时候调用
        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}

