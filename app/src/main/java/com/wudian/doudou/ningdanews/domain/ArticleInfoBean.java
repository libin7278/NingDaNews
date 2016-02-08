package com.wudian.doudou.ningdanews.domain;

/**
 * Created by doudou on 16/2/8.
 */
public class ArticleInfoBean {

    private static final long serialVersionUID = 1765875L;
    private int result;
    private ArticleInfoDataBean data;
    public int getResult() {
        return result;
    }
    public void setResult(int result) {
        this.result = result;
    }
    public ArticleInfoDataBean getData() {
        return data;
    }
    public void setData(ArticleInfoDataBean data) {
        this.data = data;
    }
}
