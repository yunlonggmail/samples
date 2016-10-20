package com.yunlong.samples.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;


/**
 * Created by chencyu on 15/10/28.
 */
public class ToastUtils {

    private static Toast toast = null;

    public static Toast create(Context mContext) {
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
        toast = new Toast(mContext);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        return toast;
    }


    /**
     * @param context
     * @param str     字符串
     */
    public static void show(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * @param context
     * @param id      字符串在String文件的ID
     */
    public static void show(Context context, int id) {
        Toast.makeText(context, id, Toast.LENGTH_SHORT).show();
    }

}
