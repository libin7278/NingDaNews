package com.wudian.doudou.ningdanews.pager;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.wudian.doudou.ningdanews.domain.VideoDetailPagerBean;
import com.wudian.doudou.ningdanews.pager.videodetailpager.VideoDetailActivity;
import com.wudian.doudou.ningdanews.utils.CacheUtils;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 阅读
 * Created by doudou on 16/1/13.
 */
public class VideoPager extends BasePager {
    private static final String url = "http://api2.pianke.me/read/columns";

    @ViewInject(R.id.ll_pb_progressbar)
    private LinearLayout ll_pb_progressbar;

    @ViewInject(R.id.gv_reda_pager)
    private GridView gv_reda_pager;

    @ViewInject(R.id.viewpager_tabdetail_pager)
    private ViewPager viewpager_tabdetail_pager;

    private ImageOptions imageOptions;

    private InternetHandler internetHandler;

    //viewpager数据
    List<VideoDetailPagerBean.DataEntity.CarouselEntity> carousel;

    //gridview数据
    List<VideoDetailPagerBean.DataEntity.ListEntity> list;

    private VideoListAdapter videoListAdapter;

    public VideoPager(Activity activity) {
        super(activity);

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
        View view = View.inflate(mActivity, R.layout.read_detail_pager, null);
        x.view().inject(this, view);

        // 取消GridView/ListView item被点击时的效果
        gv_reda_pager.setSelector(new ColorDrawable(Color.TRANSPARENT));

        gv_reda_pager.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(mActivity, VideoDetailActivity.class);

                intent.putExtra("title",list.get(position).getName());
                intent.putExtra("position",position);

                mActivity.startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void initData() {
        super.initData();
        getDataFromNet();
    }

    private void getDataFromNet() {
        Log.e("VideoPager", "VideoPager 被调用了");

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
                Toast.makeText(mActivity, "联网请求失败", Toast.LENGTH_SHORT).show();
                System.out.println("-----联网请求失败==" + volleyError);
            }
        }) {
            //解决乱码问题
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
        VideoDetailPagerBean videoDetailPagerBean = gson.fromJson(json, VideoDetailPagerBean.class);

        carousel = videoDetailPagerBean.getData().getCarousel();
        list = videoDetailPagerBean.getData().getList();

        viewpager_tabdetail_pager.setAdapter(new VideoAdapter());

        videoListAdapter = new VideoListAdapter();
        gv_reda_pager.setAdapter(videoListAdapter);

        if(internetHandler == null ){
            internetHandler =new InternetHandler();
        }else{
            //移除消息和任务
            internetHandler.removeCallbacksAndMessages(null);
        }

        //统一发消息和任务
        internetHandler.postDelayed(new MyRunnable(),3000);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ll_pb_progressbar.setVisibility(View.INVISIBLE);
            }
        },1000);
    }

    class VideoAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return carousel.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(mActivity);
            imageView.setImageResource(R.mipmap.home_scroll_default);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);//按照x轴和Y轴拉伸显示
            container.addView(imageView);

            //联网请求图片-xUtils
            x.image().bind(imageView, carousel.get(position).getImg(), imageOptions);

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
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
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

    class VideoListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
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
                convertView = View.inflate(mActivity, R.layout.video_first_detail_item, null);
                viewHolder = new ViewHolder();
                viewHolder.iv_read_icon = (ImageView) convertView.findViewById(R.id.iv_read_icon);
                viewHolder.tv_read_name = (TextView) convertView.findViewById(R.id.tv_read_name);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.tv_read_name.setText(list.get(position).getName());

            x.image().bind(viewHolder.iv_read_icon, list.get(position).getCoverimg(), imageOptions);
            return convertView;
        }
    }

    static class ViewHolder {
        ImageView iv_read_icon;
        TextView tv_read_name;

    }

}
