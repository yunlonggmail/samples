package com.yunlong.lib.utils;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import com.yunlong.base.R;


/**
 * Created by shiyunlong on 2016/10/11.
 * 对话框工具类
 */
public class DialogFactory {


    public interface OnWhichListener {
        /**
         * 选择取消
         */
        int CHOOSE_CANCEL = 0;
        /**
         * 选择确认
         */
        int CHOOSE_SUBMIT = 1;

        void onConfirmClick(int which);
    }

    /**
     * 弹出普通对话框
     *
     * @param context：上下文
     * @param title：标题
     * @param info：内容
     * @param textCancel：取消按钮
     * @param textSubmit：确认按钮
     * @param flag：标识，是否点击空白区域取消
     * @param listen：选择监听
     */
    public static void showCommDialog(Context context, String title, CharSequence info, String textCancel, String textSubmit, boolean flag, final OnWhichListener listen) {
        LayoutInflater factory = LayoutInflater.from(context);
        final View view = factory.inflate(R.layout.dialog_view_ordinary, null);
        final Dialog builder = new Dialog(context, R.style.Translucent_NoTitle_dialog);
        builder.show();

        builder.setContentView(view);
        builder.setCanceledOnTouchOutside(flag);
        builder.setCancelable(flag);

        TextView btnCancel = (TextView) view.findViewById(R.id.btn_cancel);
        TextView btnSubmit = (TextView) view.findViewById(R.id.btn_true);

        if (!TextUtils.isEmpty(textCancel)) {
            btnCancel.setText(textCancel);
            btnCancel.setVisibility(View.VISIBLE);
        } else {
            btnCancel.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(textSubmit)) {
            btnSubmit.setText(textSubmit);
            btnSubmit.setVisibility(View.VISIBLE);
        } else {
            btnSubmit.setVisibility(View.GONE);
        }
        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        if (TextUtils.isEmpty(title)) {
            tv_title.setVisibility(View.GONE);
        } else {
            tv_title.setText(title);
            tv_title.setVisibility(View.VISIBLE);
        }
        ((TextView) view.findViewById(R.id.tv_info)).setText(info);

        // 点击取消按钮
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listen.onConfirmClick(OnWhichListener.CHOOSE_CANCEL);
                builder.dismiss();
            }
        });
        // 点击确定按钮
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listen.onConfirmClick(OnWhichListener.CHOOSE_SUBMIT);
                builder.dismiss();
            }
        });
        WindowManager.LayoutParams lp = builder.getWindow().getAttributes();
        lp.width = DisplayUtils.getScreenWidth() - DisplayUtils.dp2px(50);
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        builder.getWindow().setAttributes(lp);
    }

}
