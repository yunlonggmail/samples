package com.yunlong.samples.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by shiyunlong on 2016/10/19.
 * 意图主参数
 */
public class YLIntentMain extends YLEntity implements Parcelable {
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
     * bundle参数
     */
    public Bundle bundle;

    public YLIntentMain() {

    }

    protected YLIntentMain(Parcel in) {
        activityIntent = in.readString();
        name = in.readString();
        desc = in.readString();
        bundle = in.readBundle();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(activityIntent);
        dest.writeString(name);
        dest.writeString(desc);
        dest.writeBundle(bundle);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<YLIntentMain> CREATOR = new Creator<YLIntentMain>() {
        @Override
        public YLIntentMain createFromParcel(Parcel in) {
            return new YLIntentMain(in);
        }

        @Override
        public YLIntentMain[] newArray(int size) {
            return new YLIntentMain[size];
        }
    };
}
