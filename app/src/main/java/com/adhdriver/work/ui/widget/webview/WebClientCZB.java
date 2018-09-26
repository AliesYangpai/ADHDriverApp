package com.adhdriver.work.ui.widget.webview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Administrator on 2016/11/7 0007.
 * 类描述  车主帮对接的webViewClient对接
 * 版本
 */
public class WebClientCZB extends WebViewClient {





    private Context  context;


    public WebClientCZB(Context  context) {

    this.context = context;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {// 网页页面开始加载的时候
        /**
         * 普通版Dialog
         */
        Log.i("WebClientCZB","onPageStarted:"+url);
    }




    @Override
    public void onPageFinished(WebView view, String url) {// 网页加载结束的时候
        Log.i("WebClientCZB","onPageStarted:"+url);

    }






    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) { // 网页加载时的连接的网址

        Log.i("WebClientCZB","shouldOverrideUrlLoading:"+url);



//        if (url.startsWith("weixin://wap/pay?")) {
//            Intent intent = new Intent();
//            intent.setAction(Intent.ACTION_VIEW);
//            intent.setData(Uri.parse(url));
//            context.startActivity(intent);//内存优化后
//            return true;
//        }else {
//            Map<String, String> extraHeaders = new HashMap<String, String>();
//            extraHeaders.put("Referer", "https://msdev.czb365.com");
//            view.loadUrl(url, extraHeaders);
//        }
//        return true;



        // 如下方案可在非微信内部WebView的H5页面中调出微信支付

        if (url.startsWith("weixin://wap/pay?")) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            context.startActivity(intent);
            return true;
        }

        return super.shouldOverrideUrlLoading(view, url);


    }

}
