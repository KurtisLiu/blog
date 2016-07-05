package com.lk.blog.utils;

import java.util.Date;

public final class DateUtils {
    public static String getRangeToNow(Date date) {
        if (date == null) {
            return null;
        }
        Date now = new Date();
        double diff = (now.getTime() - date.getTime()) / 1000d;
        diff = Math.ceil(diff / 60);
        if (diff < 60) {
            return diff + "分钟前";
        }
        diff = Math.ceil(diff / 60);
        if (diff < 24) {
            return diff + "小时前";
        }
        diff = Math.ceil(diff / 24);
        if (diff < 30) {
            return diff + "天前";
        }
        diff = Math.ceil(diff / 30);
        if (diff < 365) {
            return diff + "个月前";
        }
        return diff / 365 + "年前";
    }
}
