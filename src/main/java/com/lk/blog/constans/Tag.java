package com.lk.blog.constans;

public enum  Tag {
    SHARE("分享"), ASK("问答"), JOB("招聘");

    private String text;

    private Tag(String text) {
        this.text = text;
    }


    @Override
    public String toString() {
        return this.text;
    }
}
