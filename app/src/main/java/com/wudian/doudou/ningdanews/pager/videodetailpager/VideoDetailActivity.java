package com.wudian.doudou.ningdanews.pager.videodetailpager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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
import com.wudian.doudou.ningdanews.domain.Readtendetailpager;
import com.wudian.doudou.ningdanews.utils.CacheUtils;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class VideoDetailActivity extends Activity {
    private static final String url = "http://api2.pianke.me/read/latest";

    private ImageOptions imageOptions;

    public VideoDetailActivity() {
        imageOptions = new ImageOptions.Builder()
                .setSize(DensityUtil.dip2px(80), DensityUtil.dip2px(40))
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

    private SwipeRefreshLayout sw_refresh_read;

    private ListView lv_video_content;

    private TextView tv_video_title;

    private ImageView iv_video_title;

    //listview适配器
    private ReadListAdapter readListAdapter;

    //grilView数据
    Readtendetailpager.DataEntity.ListEntity listEntity;
    List<Readtendetailpager.DataEntity.ListEntity> list;

    //传过来的title
    private String ftitle;

    //传过来的position
    private int fposition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);

        fposition =getIntent().getIntExtra("position",0);
        ftitle = getIntent().getStringExtra("title");

        sw_refresh_read = (SwipeRefreshLayout) findViewById(R.id.sw_refresh_read);
        iv_video_title = (ImageView) findViewById(R.id.iv_video_title);
        tv_video_title = (TextView) findViewById(R.id.tv_video_title);
        lv_video_content = (ListView) findViewById(R.id.lv_video_content);


        sw_refresh_read.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        sw_refresh_read.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                sw_refresh_read.setRefreshing(true);
                Log.d("Swipe", "Refreshing Number");
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sw_refresh_read.setRefreshing(false);
                        getDataFromNet();
                      readListAdapter.notifyDataSetChanged();

                        Toast.makeText(getApplication(), "亲...刷新成功 >.< 喽!!!", Toast.LENGTH_SHORT).show();
                    }
                }, 3000);
            }
        });

        //点击退出
        iv_video_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv_video_title.setText(ftitle);

        // 取消GridView/ListView item被点击时的效果
        lv_video_content.setSelector(new ColorDrawable(Color.TRANSPARENT));

        //加载数据
        initData();
    }

    private void initData() {
        //联网请求数据：xUtils和Volley
        getDataFromNet();
    }

    private void getDataFromNet() {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String json) {
                System.out.println("-----------联网请求成功==" + json);
                CacheUtils.putString(getApplication(), url, json);
                processData(json);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplication(), "联网请求失败", Toast.LENGTH_SHORT).show();
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
        Readtendetailpager readtendetailpager = gson.fromJson(json, Readtendetailpager.class);

        listEntity = readtendetailpager.getData().getList().get(fposition);

        list = readtendetailpager.getData().getList();

        readListAdapter = new ReadListAdapter();

        lv_video_content.setAdapter(readListAdapter);

        lv_video_content.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplication(),ReadWebActivity.class);

                //传递内容id
                intent.putExtra("content",listEntity.getContentid());
                startActivity(intent);
            }
        });
    }

    class ReadListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = View.inflate(getApplication(), R.layout.video_detail_item, null);

                viewHolder.iv_read_title = (TextView) convertView.findViewById(R.id.iv_read_title);
                viewHolder.iv_read_item = (ImageView) convertView.findViewById(R.id.iv_read_item);
                viewHolder.tv_jianjie_read = (TextView) convertView.findViewById(R.id.tv_jianjie_read);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.tv_jianjie_read.setText(listEntity.getShortcontent());
            viewHolder.iv_read_title.setText(listEntity.getTitle());

            x.image().bind(viewHolder.iv_read_item, listEntity.getFirstimage(), imageOptions);
            return convertView;
        }
    }

    static class ViewHolder {
        //标题
        TextView iv_read_title;
        //图片
        ImageView iv_read_item;
        //简介
        TextView tv_jianjie_read;
    }
}
