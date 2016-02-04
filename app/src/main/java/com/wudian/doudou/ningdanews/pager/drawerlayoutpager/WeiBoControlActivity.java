package com.wudian.doudou.ningdanews.pager.drawerlayoutpager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.wudian.doudou.ningdanews.R;
import com.wudian.doudou.ningdanews.pager.drawerlayoutpager.weibodetailpager.YCFBAcyivity;

public class WeiBoControlActivity extends Activity implements AdapterView.OnItemClickListener {
    private GridView gv_weibo;

    private int[] icon = {R.drawable.ycfb, R.drawable.wzyc, R.drawable.ycrb, R.drawable.ycwb, R.drawable.yczww, R.drawable.payc, R.drawable.ycjy, R.drawable.ycsb, R.drawable.zfgjj, R.drawable.yyjy, R.drawable.yyyb, R.drawable.yyhb, R.drawable.yycg, R.drawable.ssdt, R.drawable.wwyc};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wei_bo_control);
        gv_weibo = (GridView) findViewById(R.id.gv_weibo);

        gv_weibo.setOnItemClickListener(this);

        gv_weibo.setAdapter(new Myadapter());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, YCFBAcyivity.class);
        switch (position) {
            case 0:
                intent.putExtra("weibo_url","http://weibo.com/yinchuanxcb");
                startActivity(intent);
                break;
            case 1:
                intent.putExtra("weibo_url","http://weibo.com/u/2239586647?topnav=1&wvr=6&topsug=1");
                startActivity(intent);
                break;
            case 2:
                intent.putExtra("weibo_url","http://weibo.com/u/3797463761?topnav=1&wvr=6&topsug=1");
                startActivity(intent);
                break;
            case 3:
                intent.putExtra("weibo_url","http://weibo.com/u/1785487003?topnav=1&wvr=6&topsug=1");
                startActivity(intent);
                break;
            case 4:
                intent.putExtra("weibo_url","http://weibo.com/ycxww0951");
                startActivity(intent);
                break;
            case 5:
                intent.putExtra("weibo_url", "http://weibo.com/payc");
                startActivity(intent);
                break;
            case 6:
                intent.putExtra("weibo_url", "http://weibo.com/u/2120105497");
                startActivity(intent);
                break;
            case 7:
                intent.putExtra("weibo_url","http://weibo.com/u/2232499222?topnav=1&wvr=6&topsug=1");
                startActivity(intent);
                break;
            case 8:
                intent.putExtra("weibo_url","http://weibo.com/u/2137732407?topnav=1&wvr=6&topsug=1");
                startActivity(intent);
                break;
            case 9:
                intent.putExtra("weibo_url","http://weibo.com/u/2184012800?topnav=1&wvr=6&topsug=1");
                startActivity(intent);
                break;
            case 10:
                intent.putExtra("weibo_url","http://weibo.com/u/2174751950?topnav=1&wvr=6&topsug=1");
                startActivity(intent);
                break;
            case 11:
                intent.putExtra("weibo_url","http://weibo.com/u/1958880922?topnav=1&wvr=6&topsug=1");
                startActivity(intent);
                break;
            case 12:
                intent.putExtra("weibo_url","http://weibo.com/u/1973872922?topnav=1&wvr=6&topsug=1");
                startActivity(intent);
                break;
            case 13:
                intent.putExtra("weibo_url","http://weibo.com/u/2118808961?topnav=1&wvr=6&topsug=1");
                startActivity(intent);
                break;
            case 14:
                intent.putExtra("weibo_url","http://weibo.com/u/1898782627?topnav=1&wvr=6&topsug=1");
                startActivity(intent);
                break;
        }
    }


    class Myadapter extends BaseAdapter {

        @Override
        public int getCount() {
            return icon.length;
        }

        @Override
        public Object getItem(int position) {
            return icon[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = View.inflate(getApplication(), R.layout.weibo_item, null);
                viewHolder = new ViewHolder();
                viewHolder.iv_weibo = (ImageView) convertView.findViewById(R.id.iv_weibo);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.iv_weibo.setImageResource(icon[position]);
            return convertView;
        }


    }

    static class ViewHolder {
        ImageView iv_weibo;
    }


}
