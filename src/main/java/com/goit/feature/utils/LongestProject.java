package com.goit.feature.utils;

public class LongestProject {
    private Integer name;
    private Integer month_count;

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }

    public Integer getMonth_count() {
        return month_count;
    }

    public void setMonth_count(Integer month_count) {
        this.month_count = month_count;
    }

    @Override
    public String toString() {
        return "LongestProject{" +
                "name=" + name +
                ", month_count=" + month_count +
                '}';
    }
}
