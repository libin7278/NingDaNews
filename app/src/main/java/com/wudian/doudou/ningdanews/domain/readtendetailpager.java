package com.wudian.doudou.ningdanews.domain;

import java.util.List;

/**
 * Created by doudou on 16/2/6.
 */
public class Readtendetailpager {

    public ReadDetail data;
    public int result;

    public class ReadDetail {
        public ColumnsInfo columnsInfo;
        public List<ReadDetailList> list;
        public int total;
    }

    public class ColumnsInfo {
        public int typeid;
        public String typename;
    }

    public class ReadDetailList {
        public String content;
        public String coverimg;
        public String id;
        public String name;
        public String title;

    }
}
