package com.adhdriver.work.utils;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2016/9/29 0029.
 * 类描述   统一的图片加载封装
 * 版本
 */
public class ImgUtil implements ImageLoadingListener {

    private ImageLoader imageLoader;

    private static volatile ImgUtil mInstance;



    private List<String> displayedImages;



    public static ImgUtil getInstance() {
        if(null == mInstance) {
            synchronized (ImgUtil.class){
                if(null == mInstance) {
                    mInstance = new ImgUtil();
                }
            }
        }
        return mInstance;
    }


    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    public ImgUtil() {

        imageLoader = ImageLoader.getInstance();
        displayedImages = Collections.synchronizedList(new LinkedList<String>());


    }


    /**
     * 获取正常图片
     */
    /**
     * 获取正常的图片
     * @param imgUrl     imgUrl网络图片的地址
     * @param imageView  图片所在控件
     * @param drawableId  加载图片过程中时，显示的图片
     * @return
     */
    public ImageLoader getImgFromNetByUrl(String imgUrl, ImageView imageView,
                                          int drawableId) {


        DisplayImageOptions options = new DisplayImageOptions.Builder()
//                .showImageOnLoading(drawableId) // 设置正在下载是显示的图片
                .showImageForEmptyUri(drawableId)// 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(drawableId)// 设置图片加载/解码过程中错误时候显示的图片
                .cacheInMemory(true)// 是否緩存都內存中
                .cacheOnDisk(true)// 是否緩存到sd卡上
                .considerExifParams(true) // 启用EXIF和JPEG图像格式
                .bitmapConfig(Bitmap.Config.ARGB_4444)
                .build();
        imageLoader.displayImage(imgUrl, imageView, options,this);
        return imageLoader;
    }






    /**
     * 根据drawable获取图片
     * @param id
     * @param imageView
     * @param drawableId
     * @return
     */
    public ImageLoader getImgFromDrawable(int id, ImageView imageView,
                                          int drawableId) {

        DisplayImageOptions options = new DisplayImageOptions.Builder()
//                .showImageOnLoading(drawableId) // 设置正在下载是显示的图片
//                .showImageForEmptyUri(drawableId)// 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(drawableId)// 设置图片加载/解码过程中错误时候显示的图片
                .cacheInMemory(true)// 是否緩存都內存中
                .cacheOnDisk(false)// 是否緩存到sd卡上
                .considerExifParams(true) // 启用EXIF和JPEG图像格式
                .bitmapConfig(Bitmap.Config.ARGB_4444)
                .build();
        imageLoader.displayImage("drawable://"+id, imageView, options,this);
        return imageLoader;

    }

    /**
     * 获取圆角图片
     * @param imgUrl     网络图片的地址
     * @param imageView  图片所在控件
     * @param drawableId 加载图片过程中时，显示的图片
     * @param radius    圆角的弧度
     */
    public void getRadiusImgFromNetByUrl(String imgUrl, ImageView imageView,
                                         int drawableId, int radius) {


        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(drawableId) // 设置正在下载是显示的图片
                .showImageForEmptyUri(drawableId)// 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(drawableId)// 设置图片加载/解码过程中错误时候显示的图片
                .cacheInMemory(true)// 是否緩存都內存中
                .cacheOnDisk(true)// 是否緩存到sd卡上
                .considerExifParams(true) // 启用EXIF和JPEG图像格式
                .displayer(new RoundedBitmapDisplayer(radius))
                .bitmapConfig(Bitmap.Config.ARGB_4444)
                .build();
        imageLoader.displayImage(imgUrl, imageView, options,this);

    }

    /**
     * 根据drawable获取图片
     * @param id
     * @param imageView
     * @param drawableId
     * @return
     */
    public ImageLoader getRadiusImgFromDrawable(int id, ImageView imageView,
                                          int drawableId,int radius) {

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(drawableId) // 设置正在下载是显示的图片
                .showImageForEmptyUri(drawableId)// 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(drawableId)// 设置图片加载/解码过程中错误时候显示的图片
                .cacheInMemory(false)// 是否緩存都內存中
                .cacheOnDisk(false)// 是否緩存到sd卡上
                .considerExifParams(true) // 启用EXIF和JPEG图像格式
                .displayer(new RoundedBitmapDisplayer(radius))
                .bitmapConfig(Bitmap.Config.ARGB_4444)
                .build();

        imageLoader.displayImage("drawable://"+id, imageView, options,this);
        return imageLoader;

    }




    /**
     * 获取圆角图片
     * @param imgUrl     网络图片的地址
     * @param imageView  图片所在控件
     * @param radius    圆角的弧度
     */
    public void getRadiusImgFromNetByUrl(String imgUrl, ImageView imageView,
                                          int radius) {


        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)// 是否緩存都內存中
                .cacheOnDisk(true)// 是否緩存到sd卡上
                .considerExifParams(true) // 启用EXIF和JPEG图像格式
                .displayer(new RoundedBitmapDisplayer(radius))
                .bitmapConfig(Bitmap.Config.ARGB_4444)
                .build();
        imageLoader.displayImage(imgUrl, imageView, options,this);



    }



    @Override
    public void onLoadingStarted(String imageUri, View view) {
        /**
         * showdialog
         */

    }

    @Override
    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {


        /**
         * dismissDialog
         */

    }

    @Override
    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {


        /**
         * dismissDialog
         */

        /**
         * 实现渐进动画
         */


        if (loadedImage != null) {
            ImageView imageView = (ImageView) view;
            boolean firstDisplay = !displayedImages.contains(imageUri);
            if (firstDisplay) {
                FadeInBitmapDisplayer.animate(imageView, 500);
                displayedImages.add(imageUri);
            }
        }


    }

    @Override
    public void onLoadingCancelled(String imageUri, View view) {


        /**
         * dismissDialog
         */
    }
}
