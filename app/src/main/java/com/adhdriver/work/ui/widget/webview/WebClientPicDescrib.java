package com.adhdriver.work.ui.widget.webview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import com.adhdriver.work.ui.widget.dialog.AdhDialog;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2016/11/7 0007.
 * 类描述
 * 版本
 */
public class WebClientPicDescrib extends WebViewClient {


    private Context context;//内存优化前
    private WeakReference<Context> weakReference;//内存优化后




    private AdhDialog adhDialog;

    public WebClientPicDescrib(Context context) {
//        this.context = context;//内存优化前
        this.weakReference = new WeakReference<Context>(context);//内存优化后
        initDialog();


    }







    private void initDialog() {



        if(null != weakReference) {

            if (null == adhDialog) {
                adhDialog = new AdhDialog(weakReference.get());

            }

        }
    }


    private void showDialog() {



        if(null != weakReference) {

            if (null != adhDialog) {

                adhDialog.show();
            }

        }

    }



    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {// 网页页面开始加载的时候

        /**
         * 普通版Dialog
         */

        Log.i("wwwww","ssssssss");

        showDialog();




    }

    private void dismissDialog() {



        //普通版Dialog
        if(null != weakReference) {


            if (null != adhDialog && adhDialog.isShowing()) {



                adhDialog.dismiss();
                adhDialog = null;
            }


        }

    }


    @Override
    public void onPageFinished(WebView view, String url) {// 网页加载结束的时候

        //普通版Dialog
        dismissDialog();

    }






    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) { // 网页加载时的连接的网址
//        view.loadUrl(url);
//        return true;



        if(url.startsWith("http:") || url.startsWith("https:") ) {
            view.loadUrl(url);
            return false;
        }else{
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//            context.startActivity(intent);//内存优化前
            weakReference.get().startActivity(intent);//内存优化后
            return true;
        }
    }








}
