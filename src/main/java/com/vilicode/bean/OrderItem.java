package com.vilicode.bean;

public class OrderItem {
    private Integer oiid;
    private Double oiprice;
    private Integer oiamount;
    private Integer bid;
    private String oid;
    private Book book;
    private Order order;
    public Integer getOiid() {
        return oiid;
    }

    public void setOiid(Integer oiid) {
        this.oiid = oiid;
    }

    public Double getOiprice() {
        return oiprice;
    }

    public void setOiprice(Double oiprice) {
        this.oiprice = oiprice;
    }

    public Integer getOiamount() {
        return oiamount;
    }

    public void setOiamount(Integer oiamount) {
        this.oiamount = oiamount;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
