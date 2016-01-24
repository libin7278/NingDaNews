package com.wudian.doudou.ningdanews.pager;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.wudian.doudou.ningdanews.R;
import com.wudian.doudou.ningdanews.base.BasePager;


/**
 * Created by doudou on 16/1/13.
 */
public class NewsPager extends BasePager {
    public NewsPager(Activity activity) {
        super(activity);
    }

//    @ViewInject(R.id.viewpager_news_menu_detail)
//    private ViewPager viewpager_news_menu_detail;

//    @ViewInject(R.id.tab_page_indicator)
//    private TabPageIndicator tab_page_indicator;


    @Override
    protected View getView() {
       View view = View.inflate(mActivity, R.layout.ssssss,null);
        return view;
    }

    @Override
    public void initData() {
        Log.e("TAG","新闻页面的内容页被初始化了");
        Toast.makeText(mActivity,"新闻页面的内容页被初始化了", Toast.LENGTH_SHORT).show();
    }
}
