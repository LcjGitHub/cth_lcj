package com.chetuhui.lcj.chezhubao_x.utils;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.chetuhui.lcj.chezhubao_x.R;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * 网络图片的工具类
 *
 */
public class ShowImageUtils {

    /**
     * (1)
     * 显示图片Imageview
     *
     * @param context  上下文
     * @param url      图片链接
     * @param imgeview 组件
     */
    public static void showImageView(Context context, String url,
                                     ImageView imgeview) {
        RequestOptions options = new RequestOptions();
        options.placeholder(R.drawable.morentu); //设置加载未完成时的占位图
        options.error(R.drawable.morentu); //设置加载异常时的占位图

        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imgeview);

    }
    public static void showImageView(Context context,int map,
                                     ImageView imgeview) {

        Glide.with(context).load(map).into(imgeview); //加载Resources图片

    }

    /**
     * （2）
     * 获取到Bitmap---不设置错误图片，错误图片不显示
     *
     * @param context
     * @param imageView
     * @param url
     */

    public static void showImageViewGone(Context context,
                                         final ImageView imageView, String url) {
        Glide.with(context).load(url)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        //图片加载失败调用
                        imageView.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        //图片加载完成时调用
                        imageView.setVisibility(View.VISIBLE);


                        imageView.setImageDrawable(resource);


                        return false;
                    }
                })
                .into(imageView);

    }





    /**
     * （8）
     * 显示图片 圆角显示  ImageView
     *
     * @param context  上下文
     * @param url      图片链接
     * @param imgeview 组件
     */
    public static void showImageViewToCircle(Context context,
                                             String url, ImageView imgeview,int circle) {
        RequestOptions options = new RequestOptions();
        options.placeholder(R.drawable.morentu); //设置加载未完成时的占位图
        options.error(R.drawable.morentu); //设置加载异常时的占位图
        Glide.with(context).load(url)
                // 加载图片
                // 设置占位图
                .apply(options)
                .apply(bitmapTransform(new RoundedCornersTransformation(circle, 0, RoundedCornersTransformation.CornerType.ALL))) //第一个参数越大圆角越大

                .into(imgeview);

    }
    /**
     * （8）
     * 显示图片 圆形显示  ImageView
     *  @param context  上下文
     *
     * @param url      图片链接
     * @param imgeview 组件
     */
    public static void showImageViewToCrop(Context context,
                                           String url, ImageView imgeview) {
        RequestOptions options = new RequestOptions();
        options.placeholder(R.drawable.morentu); //设置加载未完成时的占位图
        options.error(R.drawable.morentu); //设置加载异常时的占位图
        Glide.with(context).load(url)
                // 加载图片
        //圆形图片

                .apply(options)
                .apply(bitmapTransform(new CropCircleTransformation()))
                .into(imgeview);

    }

}

