package com.wudian.doudou.ningdanews.utils;

/**
 * Created by doudou on 16/1/14.
 */
public class ConstantUtils {

    /**
     * 新闻中心Url
     */
    //public static final String newsCenterUrl = "http://192.168.1.131:8080/zhbj/categories.json";
    //http://10.0.2.2:8080/zhbj/categories.json   这个IP不管地址怎么变都能访问本地数据
    //public static final String base_url = "http://10.0.2.2:8080/zhbj";
    //public static final String newsCenterUrlCommon =base_url + "/categories.json";

    //本土
    public static final String benTuUrl = "http://fabu.ycen.com.cn/api/posts.ashx?action=list&per=20&page=1&category=674";
    //县区
    public static final String xianQuUrl = "http://fabu.ycen.com.cn/api/posts.ashx?action=list&per=20&page=1&category=2124";
    //直播
    public static final String zhiBoUrl = "http://fabu.ycen.com.cn/api/posts.ashx?action=list&per=20&page=1&category=2114";
    //时尚
    public static final String shiShangUrl = "http://fabu.ycen.com.cn/api/posts.ashx?action=list&per=20&page=1&category=720";
    //视频
    public static final String shiPingUrl = "http://fabu.ycen.com.cn/api/posts.ashx?action=list&per=20&page=1&category=2118";
    //时事
    public static final String shiSHIUrl = "http://fabu.ycen.com.cn/api/posts.ashx?action=list&per=20&page=1&category=675";
    //图集
    public static final String tuJIUrl = "http://fabu.ycen.com.cn/api/posts.ashx?action=list&per=20&page=1&category=677";

    public static final String[] newsUrl={benTuUrl,xianQuUrl,zhiBoUrl,shiShangUrl,shiPingUrl,shiSHIUrl,tuJIUrl};
}
