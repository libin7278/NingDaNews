package com.wudian.doudou.ningdanews.pager.tabpager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.wudian.doudou.ningdanews.R;
import com.wudian.doudou.ningdanews.base.BasePager;
import com.wudian.doudou.ningdanews.domain.NewDetailsBean;
import com.wudian.doudou.ningdanews.utils.CacheUtils;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by doudou on 16/1/26.
 */
public class TabDetailPager extends BasePager {
    public static final String READ_ARRAY_ID = "read_array_id";
    /**
     * 页签详情网络请求链接
     */
    private String url;

    @ViewInject(R.id.viewpager_tabdetail_pager)
    private ViewPager viewpager_tabdetail_pager;

    @ViewInject(R.id.tv_tabdetail_pager)
    private TextView tv_tabdetail_pager;

    @ViewInject(R.id.ll_poing_goup)
    private LinearLayout ll_poing_goup;

    @ViewInject(R.id.listview_tabdetail_pager)
    private ListView listview_tabdetail_pager;

    @ViewInject(R.id.swipe_container)
    private SwipeRefreshLayout swipe_container;

    private ImageOptions imageOptions;

    //listview适配器
    private  NewsListAdapter newsListAdapter;

    /**
     * 红点上一次高亮显示的位置
     */
    private int preSelectPosition = 0;

    /**
     * 新闻列表对应的数据
     */
    private List<NewDetailsBean.DataEntity> news;

    private InternetHandler internetHandler;

    public TabDetailPager(Activity activity, String url) {
        super(activity);

        this.url = url;

        imageOptions = new ImageOptions.Builder()
                .setSize(DensityUtil.dip2px(240), DensityUtil.dip2px(240))
                .setRadius(DensityUtil.dip2px(5))
                // 如果ImageView的大小不是定义为wrap_content, 不要crop.
                .setCrop(false)
                // 加载中或错误图片的ScaleType
                //.setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setLoadingDrawableId(R.drawable.home_scroll_default)
                .setFailureDrawableId(R.drawable.home_scroll_default)
                .build();

    }

    @Override
    protected View getView() {

        View view = View.inflate(mActivity, R.layout.tabdetail_pager, null);
        x.view().inject(this, view);

        View topView = View.inflate(mActivity, R.layout.tabledetail_imager_pager, null);
        x.view().inject(this, topView);

        listview_tabdetail_pager.addHeaderView(topView);

        swipe_container.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        swipe_container.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe_container.setRefreshing(true);
                Log.d("Swipe", "Refreshing Number");
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipe_container.setRefreshing(false);
                        getDataFromNet();
                        newsListAdapter.notifyDataSetChanged();
                        //listview_tabdetail_pager.deferNotifyDataSetChanged();
                        Toast.makeText(mActivity, "亲...刷新成功 >.< 喽!!!", Toast.LENGTH_SHORT).show();
                    }
                }, 3000);
            }
        });

        //点击某一天监听item
        listview_tabdetail_pager.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int realPosition = position-1;
                NewDetailsBean.DataEntity newsItem = news.get(realPosition);
                Log.d("TAG","newsItem.getID()"+newsItem.getID()+"newsItem.getPost_title()"+newsItem.getPost_title());
                String saveIds = CacheUtils.getString(mActivity, READ_ARRAY_ID);
                //首先读取ID是否被保存,如果没被保存,才去保存,并发送消息;
                if(!saveIds.contains(newsItem.getID()+"")){
                    //保存
                    String values = saveIds+newsItem.getID()+",";
                    CacheUtils.putString(mActivity,READ_ARRAY_ID,values);

                    newsListAdapter.notifyDataSetChanged();//getCount() --> getView()
                }
                Intent intent = new Intent(mActivity, NewsWebActivity.class);
                intent.putExtra("newsUrl",newsItem.getPost_link());
                intent.putExtra("newsTitle",newsItem.getPost_title());
                mActivity.startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        Log.e("TabDetailPager", "initData 被调用了");
        Log.e("TabDetailPager", url);

        String json = CacheUtils.getString(mActivity, url);
        if (!TextUtils.isEmpty(json)) {
            processData(json);
        }
        //联网请求数据：xUtils和Volley
        getDataFromNet();
    }

    private void getDataFromNet() {
        RequestQueue queue = Volley.newRequestQueue(mActivity);
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String json) {
                System.out.println("-----------联网请求成功==" + json);
                CacheUtils.putString(mActivity, url, json);
                processData(json);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(mActivity,"联网请求失败",Toast.LENGTH_SHORT).show();
                System.out.println("-----联网请求失败==" + volleyError);
            }
        }) {
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                try {
                    String jsonData = new String(response.data, "UTF-8");
                    return Response.success(jsonData, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return super.parseNetworkResponse(response);
            }
        };

        queue.add(request);
    }

    private void processData(String json) {
        Gson gson = new Gson();
        NewDetailsBean detailPagerBean = gson.fromJson(json, NewDetailsBean.class);
        //System.out.println("-----------"+detailPagerBean.getData().getNews().get(0).getTitle());

        //新闻的数据
        news = detailPagerBean.getData();

        viewpager_tabdetail_pager.setAdapter(new TopnewseAdapter());

        //把所有点移除
        ll_poing_goup.removeAllViews();
        //设置红点
        for (int i = 0; i < 3; i++) {

            ImageView point = new ImageView(mActivity);
            //设置背景
            point.setBackgroundResource(R.drawable.point_tabdetailpager_selector);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DensityUtil.dip2px(5), DensityUtil.dip2px(5));
            if (i != 0) {
                params.leftMargin = 5;
            }
            if (i == 0) {
                point.setEnabled(true);
            } else {
                point.setEnabled(false);
            }
            point.setLayoutParams(params);

            //把点击添加到线性布局里面去
            ll_poing_goup.addView(point);
        }

        viewpager_tabdetail_pager.setOnPageChangeListener(new TopnewseOnPageChangeListener());

        //默认显示第0个点高亮显示
        ll_poing_goup.getChildAt(preSelectPosition).setEnabled(true);
        tv_tabdetail_pager.setText(news.get(preSelectPosition).getPost_title());

        //设置listview适配器
        //1.得到数据
        news = detailPagerBean.getData();
        //2.设置适配器,写item布局
        newsListAdapter = new NewsListAdapter();
        listview_tabdetail_pager.setAdapter(newsListAdapter);

        if(internetHandler == null ){
            internetHandler =new InternetHandler();
        }else{
            //移除消息和任务
            internetHandler.removeCallbacksAndMessages(null);
        }

        //统一发消息和任务
        internetHandler.postDelayed(new MyRunnable(),3000);
    }

    class InternetHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            int item = (viewpager_tabdetail_pager.getCurrentItem()+1)%3;
            viewpager_tabdetail_pager.setCurrentItem(item);

            internetHandler.postDelayed(new MyRunnable(),3000);
        }
    }

    class MyRunnable implements Runnable{

        @Override
        public void run() {
            internetHandler.sendEmptyMessage(0);
        }
    }

    class NewsListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return news.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = View.inflate(mActivity, R.layout.table_detail_item, null);
                viewHolder = new ViewHolder();
                viewHolder.iv_icon_table_detail = (ImageView) convertView.findViewById(R.id.iv_icon_table_detail);
                viewHolder.tv_title_tabdetal = (TextView) convertView.findViewById(R.id.tv_title_tabdetal);
                viewHolder.tv_time_tabdetal = (TextView) convertView.findViewById(R.id.tv_time_tabdetal);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            NewDetailsBean.DataEntity newsItem = news.get(position);

            viewHolder.tv_title_tabdetal.setText(newsItem.getPost_title());
            viewHolder.tv_time_tabdetal.setText(newsItem.getPost_date());

            String saveIds = CacheUtils.getString(mActivity, READ_ARRAY_ID);
            if(saveIds.contains(newsItem.getID()+"")){
                //灰色
                viewHolder.tv_title_tabdetal.setTextColor(Color.GRAY);
            }else{
                //黑色
                viewHolder.tv_title_tabdetal.setTextColor(Color.BLACK);
            }

            x.image().bind(viewHolder.iv_icon_table_detail, newsItem.getItem_small_pic(), imageOptions);
            return convertView;
        }
    }

    static class ViewHolder {
        ImageView iv_icon_table_detail;
        TextView tv_title_tabdetal;
        TextView tv_time_tabdetal;
    }

    class TopnewseOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            //1.之前的点变成默认，setEnabled(false);
            ll_poing_goup.getChildAt(preSelectPosition).setEnabled(false);
            //2.当前点高亮显示
            ll_poing_goup.getChildAt(position).setEnabled(true);
            //3.文本动态更新-根据位置
            tv_tabdetail_pager.setText(news.get(position).getPost_title());

            //记录下一次作为被高亮过的点
            preSelectPosition = position;


        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    class TopnewseAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(mActivity);
            imageView.setImageResource(R.mipmap.home_scroll_default);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);//按照x轴和Y轴拉伸显示
            container.addView(imageView);

            //联网请求图片-xUtils
            x.image().bind(imageView, news.get(position).getItem_small_pic(), imageOptions);

            imageView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            if(internetHandler != null){
                                internetHandler.removeCallbacksAndMessages(null);
                            }
                            break;
                        case MotionEvent.ACTION_UP:
                            if(internetHandler != null){
                                internetHandler.postDelayed(new MyRunnable(),3000);
                            }
                            break;
                        case MotionEvent.ACTION_CANCEL://事件取消,down 抢占了up 所以要用cancel
                            if(internetHandler != null){
                                internetHandler.postDelayed(new MyRunnable(),3000);
                            }
                            break;
                    }
                    return true;
                }
            });

            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    }

}
