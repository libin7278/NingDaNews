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

    /**
     * 页签页面页数
     */
    private int pagerNumber;

    public void setPagerNumber(int pagerNumber) {
        this.pagerNumber = pagerNumber;
    }

    public int getPagerNumber() {
        return pagerNumber;
    }

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
        System.out.println("新闻菜单详情页面数据被初始化了...");

        newsPagers = new ArrayList<BasePager>();

        for (int i = 0; i < TABTITLE.length; i++) {

            TabDetailPager tabDetailPager = new TabDetailPager(mActivity, ConstantUtils.newsUrl[i]);

            newsPagers.add(tabDetailPager);
        }

        //设置适配器
        viewpager_news_menu_detail.setAdapter(new NewsMenuDetailPagerAdapter());

        //TabPagerIndicator和ViewPager关联
        tab_page_indicator.setViewPager(viewpager_news_menu_detail);

        //设置滑动的点击监听,得到当前position
        tab_page_indicator.setOnPageChangeListener(new TabViewOnPageChangeListener());

    }

    class TabViewOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
//            if (position==0) {
//                setPagerNumber(0);
//                CacheUtils.putInt(mActivity,"pagerNumber",0);
//                Log.w("TabDetailPager","0");
//            }else if(position==1){
//                setPagerNumber(1);
//                Log.w("TabDetailPager","1");
//                CacheUtils.putInt(mActivity,"pagerNumber",1);
//            }else if(position==2){
//                setPagerNumber(2);
//                Log.w("TabDetailPager","2");
//                CacheUtils.putInt(mActivity,"pagerNumber",2);
//            }else if(position==3){
//                setPagerNumber(3);
//                Log.w("TabDetailPager","3");
//                CacheUtils.putInt(mActivity,"pagerNumber",3);
//            }else if(position==4){
//                setPagerNumber(4);
//                Log.w("TabDetailPager","4");
//                CacheUtils.putInt(mActivity,"pagerNumber",4);
//            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
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
