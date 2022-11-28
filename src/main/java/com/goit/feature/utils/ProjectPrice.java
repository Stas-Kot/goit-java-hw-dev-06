package com.goit.feature.utils;

public class ProjectPrice {
    private Integer id;
    private Integer price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProjectPrice{" +
                "id=" + id +
                ", price=" + price +
                '}';
    }
}
