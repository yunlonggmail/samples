package com.yunlong.samples.design.main.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.yunlong.samples.model.YLEntity;

/**
 * Created by shiyunlong on 2017/7/24.
 * 单例模式模型
 */

public class YLDesignPatternModel extends YLEntity implements Parcelable {
    /**
     * 标题
     */
    public String title;
    /**
     * 描述
     */
    public String desc;
    /**
     * 资源路径
     */
    public String assertPath;
    /**
     * 类图路径
     */
    public String umlPath;

    public YLDesignPatternModel() {
    }

    private YLDesignPatternModel(Parcel in) {
        title = in.readString();
        desc = in.readString();
        assertPath = in.readString();
        umlPath = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(desc);
        dest.writeString(assertPath);
        dest.writeString(umlPath);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<YLDesignPatternModel> CREATOR = new Creator<YLDesignPatternModel>() {
        @Override
        public YLDesignPatternModel createFromParcel(Parcel in) {
            return new YLDesignPatternModel(in);
        }

        @Override
        public YLDesignPatternModel[] newArray(int size) {
            return new YLDesignPatternModel[size];
        }
    };
}
