package com.wudian.doudou.ningdanews;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.wudian.doudou.ningdanews.base.BasePager;
import com.wudian.doudou.ningdanews.pager.MyselfPager;
import com.wudian.doudou.ningdanews.pager.NewsPager;
import com.wudian.doudou.ningdanews.pager.TopicPager;
import com.wudian.doudou.ningdanews.pager.VideoPager;
import com.wudian.doudou.ningdanews.pager.drawerlayoutpager.JiaoWuPingTaiActivity;
import com.wudian.doudou.ningdanews.pager.drawerlayoutpager.JinPinKeChengActivity;
import com.wudian.doudou.ningdanews.pager.drawerlayoutpager.NingDaShouYeActivity;
import com.wudian.doudou.ningdanews.pager.drawerlayoutpager.WeiBoControlActivity;
import com.wudian.doudou.ningdanews.pager.drawerlayoutpager.WuDianXueYuanTaiActivity;

import java.util.ArrayList;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private RadioGroup mRadioGroup;

    private int mPosition;
    private boolean[] mInitFlag;
    private ArrayList<BasePager> mPagers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRadioGroup = (RadioGroup) findViewById(R.id.rg_content_fragment);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mPagers = new ArrayList<>();
        mPagers.add(new NewsPager(this));
        mPagers.add(new TopicPager(this));
        mPagers.add(new VideoPager(this));
        mPagers.add(new MyselfPager(this));
        mInitFlag = new boolean[mPagers.size()];
        mRadioGroup.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        mRadioGroup.check(R.id.rb_news); // 默认选中主页
    }


    /**
     * 设置 Fragment 到布局中
     */
    private void setFragment() {
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();

        // 以替换的形式添加
        transaction.replace(R.id.main_content, new Fragment() {
            @Nullable
            @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                return getBasePager().mRootView;
            }
        }).commit();
    }

    /**
     * 根据索引位置获取指定的 BasePager 页面
     */
    public BasePager getBasePager() {
        if (!mInitFlag[mPosition]) {
            mInitFlag[mPosition] = true;
            mPagers.get(mPosition).initData();
        }
        return mPagers.get(mPosition);
    }

    /**
     * 自定义 OnCheckedChangeListener
     * 点击按钮切换到相应的页面
     */
    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                default: // R.id.rb_news 新闻
                    mPosition = 0;
                    break;
                case R.id.rb_topic: // 话题
                    mPosition = 1;
                    break;
                case R.id.rb_video: // 视听
                    mPosition = 2;
                    break;
                case R.id.rb_myself: // 我
                    mPosition = 3;
                    break;
            }
            setFragment();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();
        if (id == R.id.nav_camera) {
            Intent intent = new Intent(this, NingDaShouYeActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(this, JiaoWuPingTaiActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(this, WeiBoControlActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent(this, WuDianXueYuanTaiActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_share) {
            showAppShare();
        } else if (id == R.id.nav_managlala) {
            Intent intent = new Intent(this, JinPinKeChengActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * shardSDK
     */
    private void showAppShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("分享智慧宁大,生活更便利");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("点击分享智慧宁大,让你的生多更精彩!智慧宁大,不一样的新闻客户端,有了它,从此高端大气上档次!!!!还等什么,心动不如行动!!!!");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImageUrl("http://fabu.ycen.com.cn/upload/2016/01/0.203667320405909.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);
    }

    private long waitTime = 2000;
    private long touchTime = 0;

    /**
     * 双击退出
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK){
            long currentTime = System.currentTimeMillis();
            if((currentTime-touchTime) >= waitTime) {
                Toast.makeText(this,"再次点击退出",Toast.LENGTH_SHORT).show();
                touchTime = currentTime;
            }else{
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
