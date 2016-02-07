package com.wudian.doudou.ningdanews.domain;

import java.util.List;

/**
 * Created by doudou on 16/2/5.
 */
public class VideoDetailPagerBean {

    /**
     * result : 1
     * data : {"list":[{"type":1,"name":"早安故事","enname":"Mor.","coverimg":"http://pkimg.image.alimmdn.com/upload/20160127/e095a71f5f47e11b3eed077738b8b471.JPG!300300"},{"type":27,"name":"晚安故事","enname":"Nig.","coverimg":"http://pkimg.image.alimmdn.com/upload/20160131/4c4f6434f289dbee283eafdecfbbeab2.JPG!300300"},{"type":10,"name":"读书","enname":"Book","coverimg":"http://pkimg.image.alimmdn.com/upload/20160131/70bcb81cc52bd9313a5e4d18335655bb.JPG!300300"},{"type":14,"name":"万相","enname":"World","coverimg":"http://pkimg.image.alimmdn.com/upload/20160129/e0020b8c660a596f9e9af4eb1214a35a.JPG!300300"},{"type":6,"name":"影视","enname":"Movie","coverimg":"http://pkimg.image.alimmdn.com/upload/20160127/4fa3edc024478078d1f0ea6152f2b03c.JPG!300300"},{"type":18,"name":"心理","enname":"Psychology","coverimg":"http://pkimg.image.alimmdn.com/upload/20160128/57f412c7a0555869214ecc8aee69d968.JPG!300300"},{"type":12,"name":"访谈","enname":"Interview","coverimg":"http://pkimg.image.alimmdn.com/upload/20160125/6ad02f773f2b0bc4439b9676c8b39b70.JPG!300300"},{"type":11,"name":"人物","enname":"People","coverimg":"http://pkimg.image.alimmdn.com/upload/20160131/39349b0e5b56c19c013c14bab32eb67e.JPG!300300"},{"type":7,"name":"旅行","enname":"Travel","coverimg":"http://pkimg.image.alimmdn.com/upload/20160128/0b774c33c7d430773d3ef0d5086a1ea7.JPG!300300"}],"carousel":[{"img":"http://pkicdn.image.alimmdn.com/timeline/tagimgs/61b3ed45974a9cad22318a5ec70ebf9b.jpg","url":"pianke://article/56af2dbb5e7743be218b4676"},{"img":"http://pkicdn.image.alimmdn.com/timeline/tagimgs/1c66856b83e95aab79a48741a1635644.jpg","url":"pianke://article/56a5f4f55e77430d0c8b45f8"},{"img":"http://pkicdn.image.alimmdn.com/timeline/tagimgs/94884c76824badf6600dbc8f1babb1a3.jpg","url":"pianke://article/5684d9545d7743143b8b465a"}]}
     */

    private int result;
    private DataEntity data;

    public void setResult(int result) {
        this.result = result;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public int getResult() {
        return result;
    }

    public DataEntity getData() {
        return data;
    }

    public static class DataEntity {
        /**
         * type : 1
         * name : 早安故事
         * enname : Mor.
         * coverimg : http://pkimg.image.alimmdn.com/upload/20160127/e095a71f5f47e11b3eed077738b8b471.JPG!300300
         */

        private List<ListEntity> list;
        /**
         * img : http://pkicdn.image.alimmdn.com/timeline/tagimgs/61b3ed45974a9cad22318a5ec70ebf9b.jpg
         * url : pianke://article/56af2dbb5e7743be218b4676
         */

        private List<CarouselEntity> carousel;

        public void setList(List<ListEntity> list) {
            this.list = list;
        }

        public void setCarousel(List<CarouselEntity> carousel) {
            this.carousel = carousel;
        }

        public List<ListEntity> getList() {
            return list;
        }

        public List<CarouselEntity> getCarousel() {
            return carousel;
        }

        public static class ListEntity {
            private int type;
            private String name;
            private String enname;
            private String coverimg;

            public void setType(int type) {
                this.type = type;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setEnname(String enname) {
                this.enname = enname;
            }

            public void setCoverimg(String coverimg) {
                this.coverimg = coverimg;
            }

            public int getType() {
                return type;
            }

            public String getName() {
                return name;
            }

            public String getEnname() {
                return enname;
            }

            public String getCoverimg() {
                return coverimg;
            }
        }

        public static class CarouselEntity {
            private String img;
            private String url;

            public void setImg(String img) {
                this.img = img;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getImg() {
                return img;
            }

            public String getUrl() {
                return url;
            }
        }
    }
}
