package com.wudian.doudou.ningdanews.pager;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.viewpagerindicator.TabPageIndicator;
import com.wudian.doudou.ningdanews.R;
import com.wudian.doudou.ningdanews.base.BasePager;
import com.wudian.doudou.ningdanews.pager.tabpager.TabDetailPager;
import com.wudian.doudou.ningdanews.utils.CacheUtils;
import com.wudian.doudou.ningdanews.utils.ConstantUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;


/**
 * Created by doudou on 16/1/13.
 */
public class NewsPager extends BasePager {
    private static final String[] TABTITLE = new String[]{"本土", "县区","直播","爱尚","视频","时事", "图集"};

    public NewsPager(Activity activity) {
        super(activity);
    }

    @ViewInject(R.id.viewpager_news_menu_detail)
    private ViewPager viewpager_news_menu_detail;

    @ViewInject(R.id.tab_page_indicator)
    private TabPageIndicator tab_page_indicator;

    /**
     * 页签页面集合
     */
    private ArrayList<BasePager> newsPagers;



    @Override
    protected View getView() {
        View view = View.inflate(mActivity, R.layout.news_menu_detail_pager, null);
        x.view().inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        newsPagers = new ArrayList<BasePager>();

        for (int i = 0; i < TABTITLE.length; i++) {

            TabDetailPager tabDetailPager = new TabDetailPager(mActivity, ConstantUtils.newsUrl[i]);

            newsPagers.add(tabDetailPager);
        }

        //设置适配器
        viewpager_news_menu_detail.setAdapter(new NewsMenuDetailPagerAdapter());

        //TabPagerIndicator和ViewPager关联
        tab_page_indicator.setViewPager(viewpager_news_menu_detail);

    }

    class NewsMenuDetailPagerAdapter extends PagerAdapter {
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
//            return super.instantiateItem(container, position);
            CacheUtils.putInt(mActivity,"pagerNumber",position);
            BasePager basePager = newsPagers.get(position);
            View rootView = basePager.mRootView;
            container.addView(rootView);
            basePager.initData();

            return rootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            container.removeView((View) object);


        }

        @Override
        public CharSequence getPageTitle(int position) {

            return TABTITLE[position];

        }

        @Override
        public int getCount() {
            return TABTITLE.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
