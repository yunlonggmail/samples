package com.yunlong.lib.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by shiyunlong on 16/4/12.
 * 使用Glide加载图片
 */
public enum GlideUtils {
    INSTANCE;

    private Context mContext = null;

    public static final String QI_NIU_IMAGE_2 = "?imageView2/%d";
    /**
     * 模式0
     * /0/w/<LongEdge>/h/<ShortEdge>
     * 限定缩略图的长边最多为<LongEdge>，短边最多为<ShortEdge>，进行等比缩放，不裁剪。
     * 如果只指定 w 参数则表示限定长边（短边自适应），只指定 h 参数则表示限定短边（长边自适应）。
     */
    public static final int QI_NIU_IMAGE_2_MODE_0 = 0;
    /**
     * 模式1
     * /1/w/<Width>/h/<Height>
     * 限定缩略图的宽最少为<Width>，高最少为<Height>，进行等比缩放，居中裁剪。
     * 转后的缩略图通常恰好是 <Width>x<Height> 的大小（有一个边缩放的时候会因为超出矩形框而被裁剪掉多余部分）。
     * 如果只指定 w 参数或只指定 h 参数，代表限定为长宽相等的正方图。
     */
    public static final int QI_NIU_IMAGE_2_MODE_1 = 1;
    /**
     * 模式2
     * /2/w/<Width>/h/<Height>
     * 限定缩略图的宽最多为<Width>，高最多为<Height>，进行等比缩放，不裁剪。
     * 如果只指定 w 参数则表示限定宽（长自适应），只指定 h 参数则表示限定长（宽自适应）。
     * 它和模式0类似，区别只是限定宽和高，不是限定长边和短边。
     * 从应用场景来说，模式0适合移动设备上做缩略图，模式2适合PC上做缩略图。
     */
    public static final int QI_NIU_IMAGE_2_MODE_2 = 2;
    /**
     * 模式3
     * /3/w/<Width>/h/<Height>
     * 限定缩略图的宽最少为<Width>，高最少为<Height>，进行等比缩放，不裁剪。
     * 如果只指定 w 参数或只指定 h 参数，代表长宽限定为同样的值。
     * 你可以理解为模式1是模式3的结果再做居中裁剪得到的。
     */
    public static final int QI_NIU_IMAGE_2_MODE_3 = 3;
    /**
     * 模式4
     * /4/w/<LongEdge>/h/<ShortEdge>
     * 限定缩略图的长边最少为<LongEdge>，短边最少为<ShortEdge>，进行等比缩放，不裁剪。
     * 如果只指定 w 参数或只指定 h 参数，表示长边短边限定为同样的值。
     * 这个模式很适合在手持设备做图片的全屏查看（把这里的长边短边分别设为手机屏幕的分辨率即可），
     * 生成的图片尺寸刚好充满整个屏幕（某一个边可能会超出屏幕）。
     */
    public static final int QI_NIU_IMAGE_2_MODE_4 = 4;
    /**
     * 模式5
     * /5/w/<LongEdge>/h/<ShortEdge>
     * 限定缩略图的长边最少为<LongEdge>，短边最少为<ShortEdge>，进行等比缩放，居中裁剪。
     * 如果只指定 w 参数或只指定 h 参数，表示长边短边限定为同样的值。
     * 同上模式4，但超出限定的矩形部分会被裁剪。
     */
    public static final int QI_NIU_IMAGE_2_MODE_5 = 5;
    /**
     * 七牛图片宽度
     */
    public static final String QI_NIU_IMAGE_2_WIDTH = "/w/%d";
    /**
     * 七牛图片高度
     */
    public static final String QI_NIU_IMAGE_2_HEIGHT = "/h/%d";
    /**
     * 七牛图片是否支持渐进显示
     */
    public static final String QI_NIU_IMAGE_2_INTERLACE = "/interlace/%d";
    /**
     * 不支持渐进显示
     */
    public static final int QI_NIU_IMAGE_2_INTERLACE_0 = 0;
    /**
     * 支持渐进显示
     */
    public static final int QI_NIU_IMAGE_2_INTERLACE_1 = 1;

    public void init(Context ctx) {
        mContext = ctx.getApplicationContext();
    }

    /**
     * 加载图片
     *
     * @param uri：图片的uri
     * @param imageView：图片控件
     */
    public void loadUri(String uri, ImageView imageView) {
        Glide.with(mContext).load(uri).into(imageView);
    }

//    /**
//     * 加载图片
//     *
//     * @param uri：图片的uri
//     */
//    public void loadUri(String uri, GlideDrawableImageViewTarget glideDrawableImageViewTarget) {
//        Glide.with(mContext).load(uri).diskCacheStrategy(DiskCacheStrategy.ALL).into(glideDrawableImageViewTarget);
//    }

    /**
     * 带有空图片加载
     *
     * @param uri:图片的uri
     * @param imageView：图片控件
     */
    public void loadUriWithDrawable(String uri, ImageView imageView, int emptyDrawable) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(emptyDrawable).error(emptyDrawable).diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(mContext).load(uri).apply(requestOptions).into(imageView);
    }

    /**
     * 加载图片
     * uri会处理成七牛的Uri
     *
     * @param uri：图片的uri
     * @param imageView：图片控件
     */
    public void load(String uri, ImageView imageView) {
        loadUri(convertedImageUri(uri), imageView);
    }

//    /**
//     * 加载图片
//     * uri会处理成七牛的Uri
//     *
//     * @param uri：图片的uri
//     */
//    public void load(String uri, GlideDrawableImageViewTarget glideDrawableImageViewTarget) {
//        loadUri(convertedImageUri(uri), glideDrawableImageViewTarget);
//    }

    /**
     * 带有空图片加载
     * uri会处理成七牛的Uri
     *
     * @param uri：uri
     * @param imageView：图片控件
     * @param emptyDrawable  默认图片resID
     */
    public void loadWithDrawable(String uri, ImageView imageView, int emptyDrawable) {
        loadUriWithDrawable(convertedImageUri(uri), imageView, emptyDrawable);
    }

    /**
     * 加载Drawable图片
     *
     * @param drawableID：空界面ID
     * @param imageView：图片控件
     */
    public void loadDrawableImage(int drawableID, ImageView imageView) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(mContext).load(drawableID).apply(requestOptions).into(imageView);
    }

    /**
     * 加载本地图片
     *
     * @param imagePath：图片路径
     * @param imageView：图片控件
     * @param emptyDrawable：空界面
     */
    public void loadNativeImage(String imagePath, ImageView imageView, int emptyDrawable) {
        if (emptyDrawable > 0)
            loadUriWithDrawable(convertImagePath(mContext, imagePath), imageView, emptyDrawable);
        else
            loadUri(convertImagePath(mContext, imagePath), imageView);
    }

//    /**
//     * 加载待处理的目标控件
//     *
//     * @param imagePath：图片路径
//     * @param glideDrawableImageViewTarget:目标控件
//     * @param emptyDrawable：空图片展示
//     */
//    public void loadNativeImageWithTarget(String imagePath, GlideDrawableImageViewTarget glideDrawableImageViewTarget, int emptyDrawable) {
//        RequestOptions requestOptions = new RequestOptions();
//        requestOptions.placeholder(emptyDrawable).error(emptyDrawable).diskCacheStrategy(DiskCacheStrategy.ALL);
//        Glide.with(mContext).load("file://" + imagePath).apply(requestOptions).into(glideDrawableImageViewTarget);
//    }

    /**
     * @param uri
     * @return
     */
    public static String convertedImageUri(String uri) {
        return UrlUtils.convertUrl(uri);
    }

    /**
     * @param url：图片路径
     * @param v：资源控件
     * @param mode：模式
     * @param width：图片宽度
     * @param height：图片高度
     * @param interlace：渐进显示
     */
    public void load(String url, ImageView v, int mode, int width, int height, int interlace) {
        if (url.contains(UrlUtils.QI_NIU_DEFAULT_URL)) {
            loadUri(convertedImageUri(url, mode, width, height, interlace), v);
        } else {
            load(url, v);
        }
    }

    /**
     * @param url：图片路径
     * @param v：资源控件
     * @param mode：模式
     * @param width：图片宽度
     * @param height：图片高度
     * @param interlace：渐进显示
     * @param emptyDrawable  默认图片resID
     */
    public void load(String url, ImageView v, int emptyDrawable, int mode, int width, int height, int interlace) {
        loadUriWithDrawable(convertedImageUri(url, mode, width, height, interlace), v, emptyDrawable);
    }

    /**
     * 对Uri进行处理
     *
     * @param uri：图片路径
     * @param mode：模式
     * @param width：图片宽度
     * @param height：图片高度
     * @param interlace：渐进显示
     * @return
     */
    public String convertedImageUri(String uri, int mode, int width, int height, int interlace) {
        if (mode < QI_NIU_IMAGE_2_MODE_0 || mode > QI_NIU_IMAGE_2_MODE_5) {
            mode = QI_NIU_IMAGE_2_MODE_0;
        }
        String formatUri = uri + String.format(QI_NIU_IMAGE_2, mode);
        String formatWidth = "";
        if (width >= 0) {
            formatWidth = String.format(QI_NIU_IMAGE_2_WIDTH, width);
        }
        String formatHeight = "";
        if (height >= 0) {
            formatHeight = String.format(QI_NIU_IMAGE_2_HEIGHT, height);
        }
        String formatInterlace = "";
        if (interlace == QI_NIU_IMAGE_2_INTERLACE_0 || interlace == QI_NIU_IMAGE_2_INTERLACE_1) {
            formatInterlace = String.format(QI_NIU_IMAGE_2_INTERLACE, interlace);
        }
        String formatUrl = formatUri + formatWidth + formatHeight + formatInterlace;
        return convertedImageUri(formatUrl);
    }

    /**
     * 转换本地Uri为file schema
     *
     * @param context
     * @param uriString
     * @return
     */
    private String convertImagePath(Context context, String uriString) {
        Uri uri = Uri.parse(uriString);
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = ContentResolver.SCHEME_FILE + "://" + uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uriString;
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }
}
