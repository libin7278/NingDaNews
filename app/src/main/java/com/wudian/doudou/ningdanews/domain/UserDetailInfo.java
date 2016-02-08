package com.wudian.doudou.ningdanews.domain;

/**
 * Created by doudou on 16/2/8.
 */
public class UserDetailInfo {
    /**
     *
     */
    private static final long serialVersionUID = 146465465464L;
    private int uid;
    private String uname;
    private String icon;
    private String desc;
    private boolean isfollow;
    public int getUid() {
        return uid;
    }
    public void setUid(int uid) {
        this.uid = uid;
    }
    public String getUname() {
        return uname;
    }
    public void setUname(String uname) {
        this.uname = uname;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public boolean isIsfollow() {
        return isfollow;
    }
    public void setIsfollow(boolean isfollow) {
        this.isfollow = isfollow;
    }

}
