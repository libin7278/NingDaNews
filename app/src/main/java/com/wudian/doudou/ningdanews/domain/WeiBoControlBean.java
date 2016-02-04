package com.wudian.doudou.ningdanews.domain;

import java.util.List;

/**
 * Created by doudou on 16/2/1.
 */
public class WeiBoControlBean {

    /**
     * error : 0
     * data : [{"id":"15","type":"0","name":"银川发布微博","icon":"http://fabu.ycen.com.cn/upload/2015/05/0.926924198831862.png","waptype":"0","sort":"15","reurl":"http://weibo.com/yinchuanxcb"},{"id":"16","type":"0","name":"问政银川","icon":"http://fabu.ycen.com.cn/upload/2015/05/0.898684804280607.png","waptype":"0","sort":"16","reurl":"http://weibo.com/u/2239586647?topnav=1&wvr=6&topsug=1"},{"id":"18","type":"0","name":"微博银川","icon":"http://fabu.ycen.com.cn/upload/2015/05/0.772473163796809.png","waptype":"0","sort":"17","reurl":"http://weibo.com/u/1898782627?topnav=1&wvr=6&topsug=1"},{"id":"21","type":"0","name":"银川日报微博","icon":"http://fabu.ycen.com.cn/upload/2015/05/0.775186009134718.png","waptype":"0","sort":"18","reurl":"http://weibo.com/u/3797463761?topnav=1&wvr=6&topsug=1"},{"id":"20","type":"0","name":"银川晚报微博","icon":"http://fabu.ycen.com.cn/upload/2015/05/0.234279680174906.png","waptype":"0","sort":"19","reurl":"http://weibo.com/u/1785487003?topnav=1&wvr=6&topsug=1"},{"id":"19","type":"0","name":"银川新闻网微博","icon":"http://fabu.ycen.com.cn/upload/2015/05/0.477391198499776.png","waptype":"0","sort":"20","reurl":"http://weibo.com/ycxww0951"},{"id":"17","type":"0","name":"平安银川","icon":"http://fabu.ycen.com.cn/upload/2015/05/0.920444706883489.png","waptype":"0","sort":"21","reurl":"http://weibo.com/payc"},{"id":"25","type":"0","name":"银川市民大厅","icon":"http://fabu.ycen.com.cn/upload/2015/05/0.953674808122998.png","waptype":"0","sort":"22","reurl":"http://weibo.com/u/2118808961?topnav=1&wvr=6&topsug=1"},{"id":"26","type":"0","name":"银川教育","icon":"http://fabu.ycen.com.cn/upload/2015/05/0.581567922877878.png","waptype":"0","sort":"23","reurl":"http://weibo.com/u/2120105497"},{"id":"27","type":"0","name":"银川社保","icon":"http://fabu.ycen.com.cn/upload/2015/05/0.914693832823398.png","waptype":"0","sort":"24","reurl":"http://weibo.com/u/2232499222?topnav=1&wvr=6&topsug=1"},{"id":"24","type":"0","name":"银川住房公积金","icon":"http://fabu.ycen.com.cn/upload/2015/05/0.342446500129274.png","waptype":"0","sort":"25","reurl":"http://weibo.com/u/2137732407?topnav=1&wvr=6&topsug=1"},{"id":"22","type":"0","name":"银川就业","icon":"http://fabu.ycen.com.cn/upload/2015/05/0.544263299808029.png","waptype":"0","sort":"26","reurl":"http://weibo.com/u/2184012800?topnav=1&wvr=6&topsug=1"},{"id":"28","type":"0","name":"银川城管","icon":"http://fabu.ycen.com.cn/upload/2015/05/0.252820872353772.png","waptype":"0","sort":"27","reurl":"http://weibo.com/u/1973872922?topnav=1&wvr=6&topsug=1"},{"id":"29","type":"0","name":"银川环保","icon":"http://fabu.ycen.com.cn/upload/2015/05/0.998195589984858.png","waptype":"0","sort":"28","reurl":"http://weibo.com/u/1958880922?topnav=1&wvr=6&topsug=1"},{"id":"23","type":"0","name":"银川医保","icon":"http://fabu.ycen.com.cn/upload/2015/05/0.981942316508825.png","waptype":"0","sort":"29","reurl":"http://weibo.com/u/2174751950?topnav=1&wvr=6&topsug=1"}]
     */

    private int error;
    /**
     * id : 15
     * type : 0
     * name : 银川发布微博
     * icon : http://fabu.ycen.com.cn/upload/2015/05/0.926924198831862.png
     * waptype : 0
     * sort : 15
     * reurl : http://weibo.com/yinchuanxcb
     */

    private List<DataEntity> data;

    public void setError(int error) {
        this.error = error;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public int getError() {
        return error;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private String id;
        private String type;
        private String name;
        private String icon;
        private String waptype;
        private String sort;
        private String reurl;

        public void setId(String id) {
            this.id = id;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public void setWaptype(String waptype) {
            this.waptype = waptype;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public void setReurl(String reurl) {
            this.reurl = reurl;
        }

        public String getId() {
            return id;
        }

        public String getType() {
            return type;
        }

        public String getName() {
            return name;
        }

        public String getIcon() {
            return icon;
        }

        public String getWaptype() {
            return waptype;
        }

        public String getSort() {
            return sort;
        }

        public String getReurl() {
            return reurl;
        }
    }
}
