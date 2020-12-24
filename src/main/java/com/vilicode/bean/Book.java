package com.vilicode.bean;

public class Book {
    private Integer bid;
    private String bisbn;
    private String bname;
    private String bauthor;
    private String bpublisher;
    private String bcover;
    private String bimage1;
    private String bimage2;
    private Double bprice;
    private Integer btid;
    private String btname;
    private Integer bstock;
    private String bmark;

    private boolean isScroll;
    private boolean isHot;
    private boolean isNew;

    public boolean getIsScroll() {
        return isScroll;
    }
    public void setScroll(boolean isScroll) {
        this.isScroll = isScroll;
    }
    public boolean getIsHot() {
        return isHot;
    }
    public void setHot(boolean isHot) {
        this.isHot = isHot;
    }
    public boolean getIsNew() {
        return isNew;
    }
    public void setNew(boolean isNew) {
        this.isNew = isNew;
    }

    public String getBtname() {
        return btname;
    }

    public void setBtname(String btname) {
        this.btname = btname;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getBisbn() {
        return bisbn;
    }

    public void setBisbn(String bisbn) {
        this.bisbn = bisbn;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getBauthor() {
        return bauthor;
    }

    public void setBauthor(String bauthor) {
        this.bauthor = bauthor;
    }

    public String getBpublisher() {
        return bpublisher;
    }

    public void setBpublisher(String bpublisher) {
        this.bpublisher = bpublisher;
    }

    public String getBcover() {
        return bcover;
    }

    public void setBcover(String bcover) {
        this.bcover = bcover;
    }

    public String getBimage1() {
        return bimage1;
    }

    public void setBimage1(String bimage1) {
        this.bimage1 = bimage1;
    }

    public String getBimage2() {
        return bimage2;
    }

    public void setBimage2(String bimage2) {
        this.bimage2 = bimage2;
    }

    public Double getBprice() {
        return bprice;
    }

    public void setBprice(Double bprice) {
        this.bprice = bprice;
    }

    public Integer getBtid() {
        return btid;
    }

    public void setBtid(Integer btid) {
        this.btid = btid;
    }

    public Integer getBstock() {
        return bstock;
    }

    public void setBstock(Integer bstock) {
        this.bstock = bstock;
    }

    public String getBmark() {
        return bmark;
    }

    public void setBmark(String bmark) {
        this.bmark = bmark;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bid=" + bid +
                ", bisbn='" + bisbn + '\'' +
                ", bname='" + bname + '\'' +
                ", bauthor='" + bauthor + '\'' +
                ", bpublisher='" + bpublisher + '\'' +
                ", bcover='" + bcover + '\'' +
                ", bimage1='" + bimage1 + '\'' +
                ", bimage2='" + bimage2 + '\'' +
                ", bprice=" + bprice +
                ", btid=" + btid +
                ", btname='" + btname + '\'' +
                ", bstock=" + bstock +
                ", bmark='" + bmark + '\'' +
                '}';
    }
}
