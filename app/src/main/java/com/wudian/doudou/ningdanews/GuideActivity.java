package com.wudian.doudou.ningdanews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.wudian.doudou.ningdanews.utils.CacheUtils;
import com.wudian.doudou.ningdanews.utils.DensityUtils;

import java.util.ArrayList;

public class GuideActivity extends Activity {
    private static final String TAG = GuideActivity.class.getSimpleName();

    private ViewPager viewPager_guide;
    private Button btn_guide_tiyan;
    private LinearLayout ll_guild_point_group;
    private ArrayList<ImageView> imageViews; //图片集合

    private ImageView guild_red_point; //动态小圆点

    /**
     * 两点间距
     */
    private int margLeft;

    private int widthpx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        viewPager_guide = (ViewPager) findViewById(R.id.viewPager_guide);
        btn_guide_tiyan = (Button) findViewById(R.id.btn_guide_tiyan);
        ll_guild_point_group = (LinearLayout) findViewById(R.id.ll_guild_point_group);

        guild_red_point = (ImageView) findViewById(R.id.guild_red_point);

        imageViews = new ArrayList<ImageView>();

        widthpx = DensityUtils.dip2px(this,15);
        Log.e(TAG,"widthpx========" +widthpx);

        //资源id-->ImageView-->ArrayList<imageView>
        int[] ics = {R.mipmap.splash_yindao_one,R.mipmap.splash_yingdao_two,R.mipmap.splash_bg_yindao_three,R.mipmap.splash_yindao_four};

        for(int i = 0;i<ics.length;i++){
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(ics[i]);


            //添加到集合中
            imageViews.add(imageView);

            //添加默认点
            ImageView point_guild_normal = new ImageView(this);
            point_guild_normal.setBackgroundResource(R.drawable.point_guild_normal);

            LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(widthpx,widthpx);
            if(i != 0){
                parms.leftMargin = widthpx;
            }
            point_guild_normal.setLayoutParams(parms);

            ll_guild_point_group.addView(point_guild_normal);
        }

        /**
         * ViewPager设置适配器
         * 1.在布局文件定义
         * 2.实例化ViewPager
         * 3.准备数据
         * 4.设置适配器 PagerAdapter 至少有四个方法
         */
        viewPager_guide.setAdapter(new GuildAdapter());

        //在控件显示的过程中onMesure()-->onLayout()--> onDraw(); 测量时候不一定有值,但测量过后onLayout就一定有值;
        //添加全局监听
        guild_red_point.getViewTreeObserver().addOnGlobalLayoutListener(new MyOnGlobalLayoutListener());

        //监听ViewPager的状态
        viewPager_guide.setOnPageChangeListener(new MyOnPageChangeListener());

        //设置按钮的点击事件
        btn_guide_tiyan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //保存软件参数
                CacheUtils.putBoolean(GuideActivity.this,SplashActivity.IS_ENTER_MAIN_ACTIVITY,true);
                Intent intent = new Intent(GuideActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener{
        /**
         *
         * @param position 滑动页面的位置
         * @param positionOffset 滑动页面的百分比
         * @param positionOffsetPixels 滑动的像素
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            Log.e(TAG,"position=="+position+",positionOffset=="+positionOffset+",positionOffsetPixels=="+positionOffsetPixels);

            float marginPoint = (position + positionOffset)*margLeft;

            RelativeLayout.LayoutParams parms = new RelativeLayout.LayoutParams(widthpx,widthpx);
            parms.leftMargin = (int) marginPoint;
            guild_red_point.setLayoutParams(parms);
        }

        @Override
        public void onPageSelected(int position) {
            Log.e(TAG,"onPageSelected --position=="+position);
            if(position == imageViews.size()-1){
                btn_guide_tiyan.setVisibility(View.VISIBLE);
            }else{
                btn_guide_tiyan.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
    class MyOnGlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener{

        @Override
        public void onGlobalLayout() {
            guild_red_point.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            Log.e(TAG,"onGlobalLayout----");
            //间距的计算 = 第1个点距离左边距离- 第0个点距离左边的距离
            margLeft = ll_guild_point_group.getChildAt(1).getLeft()-ll_guild_point_group.getChildAt(0).getLeft();
        }
    }

    class GuildAdapter extends PagerAdapter {
        /**
         * 返回集合总条数
         * @return
         */
        @Override
        public int getCount() {
            return imageViews.size();
        }

        /**
         * 实例化具体的某个页面
         * @param container ViewPager
         * @param position 页面的下标
         * @return
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = imageViews.get(position);
            container.addView(imageView);
            //return position;
            return imageView;
        }

        /**
         *
         * @param view 当前页面的视图View
         * @param object 就是上面instantiateItem方法的返回值
         * @return
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            //return view ==imageViews.get(Integer.valueOf(object.toString()));
            return view==object;
        }



        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    }
}
