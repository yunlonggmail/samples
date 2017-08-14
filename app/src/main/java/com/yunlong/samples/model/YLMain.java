package com.yunlong.samples.model;


import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by shiyunlong on 2016/10/11.
 * 主页面参数
 */
public class YLMain extends YLEntity implements Parcelable {
    /**
     * Activity意图
     */
    public String activityIntent;
    /**
     * 名称
     */
    public String name;
    /**
     * 描述
     */
    public String desc;
    /**
     * 扩展信息
     */
    public Bundle extras;

    public YLMain() {
    }

    protected YLMain(Parcel in) {
        activityIntent = in.readString();
        name = in.readString();
        desc = in.readString();
        extras = in.readBundle();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(activityIntent);
        dest.writeString(name);
        dest.writeString(desc);
        dest.writeBundle(extras);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<YLMain> CREATOR = new Creator<YLMain>() {
        @Override
        public YLMain createFromParcel(Parcel in) {
            return new YLMain(in);
        }

        @Override
        public YLMain[] newArray(int size) {
            return new YLMain[size];
        }
    };
}
