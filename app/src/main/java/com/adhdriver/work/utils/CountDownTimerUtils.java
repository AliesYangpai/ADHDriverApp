package com.adhdriver.work.utils;

import android.app.Activity;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2017/4/19.
 * 类描述
 * 版本
 */

public class CountDownTimerUtils extends CountDownTimer {

    private TextView mTextView;


    private Activity activity;

    private WeakReference<Activity> weakReference;

    public CountDownTimerUtils(long millisInFuture, long countDownInterval, TextView mTextView, Activity activity) {
        super(millisInFuture, countDownInterval);
        this.mTextView = mTextView;


        weakReference = new WeakReference<Activity>(activity);
    }

    /**
     *
     * @param textView  需要变化的textView
     * @param millisInFuture   总时间
     * @param countDownInterval  秒数的变化量
     */
    public CountDownTimerUtils(TextView textView, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);

        this.mTextView = textView;
    }




    @Override
    public void onTick(long millisUntilFinished) {

        if(null != weakReference.get()) {


            if(weakReference.get().isFinishing()) {

                this.cancel();
                return;
            }

            mTextView.setClickable(false); //设置不可点击
            mTextView.setText(millisUntilFinished / 1000 + "s后点击重新发送");  //设置倒计时时间




            /**
             * 超链接 URLSpan
             * 文字背景颜色 BackgroundColorSpan
             * 文字颜色 ForegroundColorSpan
             * 字体大小 AbsoluteSizeSpan
             * 粗体、斜体 StyleSpan
             * 删除线 StrikethroughSpan
             * 下划线 UnderlineSpan
             * 图片 ImageSpan
             * http://blog.csdn.net/ah200614435/article/details/7914459
             */
            SpannableString spannableString = new SpannableString(mTextView.getText().toString());  //获取按钮上的文字
            ForegroundColorSpan span = new ForegroundColorSpan(Color.RED);
            /**
             * public void setSpan(Object what, int start, int end, int flags) {
             * 主要是start跟end，start是起始位置,无论中英文，都算一个。
             * 从0开始计算起。end是结束位置，所以处理的文字，包含开始位置，但不包含结束位置。
             */
            spannableString.setSpan(span, 0, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);//将倒计时的时间设置为红色
            mTextView.setText(spannableString);

            Log.i("asdasdasd","*****************");

        } else {

            this.cancel();
        }


    }

    @Override
    public void onFinish() {


        if(null != weakReference.get()) {

            mTextView.setText("重新获取验证码");
            mTextView.setClickable(true);//重新获得点击
        }

    }
}
