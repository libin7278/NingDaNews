package com.wudian.doudou.ningdanews.domain;

/**
 * Created by doudou on 16/2/8.
 */
public class ArticleInfoDataBean {

    /**
     *
     */
    private static final long serialVersionUID = 167457L;
    private String contentid;
    private UserDetailInfo userinfo;
    private String songid;
    private boolean islike;
    private boolean isfav;
    private String html;
    /*private List<ImgUrl> imglist;
    private ShareInfo shareinfo;
    private TopicInfo topicInfo;
    private TingInfo tingInfo;*/
    private int typeid;
    private String typename;

    public String getContentid() {
        return contentid;
    }

    public void setContentid(String contentid) {
        this.contentid = contentid;
    }

    public UserDetailInfo getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(UserDetailInfo userinfo) {
        this.userinfo = userinfo;
    }

    public String getSongid() {
        return songid;
    }

    public void setSongid(String songid) {
        this.songid = songid;
    }

    public boolean isIslike() {
        return islike;
    }

    public void setIslike(boolean islike) {
        this.islike = islike;
    }

    public boolean isIsfav() {
        return isfav;
    }

    public void setIsfav(boolean isfav) {
        this.isfav = isfav;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

	/*public List<ImgUrl> getImglist() {
		return imglist;
	}

	public void setImglist(List<ImgUrl> imglist) {
		this.imglist = imglist;
	}

	public ShareInfo getShareinfo() {
		return shareinfo;
	}

	public void setShareinfo(ShareInfo shareinfo) {
		this.shareinfo = shareinfo;
	}

	public TopicInfo getTopicInfo() {
		return topicInfo;
	}

	public void setTopicInfo(TopicInfo topicInfo) {
		this.topicInfo = topicInfo;
	}

	public TingInfo getTingInfo() {
		return tingInfo;
	}

	public void setTingInfo(TingInfo tingInfo) {
		this.tingInfo = tingInfo;
	}*/

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }
}
