package com.adhdriver.work.ui.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.adhdriver.work.R;

/**
 * Created by Administrator on 2017/6/6.
 * 类描述
 * 版本
 */

public class AdhDialog extends Dialog {



    AnimationDrawable animationDrawable;

    public AdhDialog(@NonNull Context context) {
        super(context, R.style.loadingDialog);
        View view = View.inflate(context, R.layout.dialog_progress, null);
        ImageView iv_loading = (ImageView) view.findViewById(R.id.iv_loading);
        iv_loading.setImageResource(R.drawable.animation);
//        AnimationDrawable animationDrawable = (AnimationDrawable) iv_loading.getDrawable();

        animationDrawable = (AnimationDrawable) iv_loading.getDrawable();
        setCanceledOnTouchOutside(false);
        setContentView(view);

    }

    @Override
    public void show() {


        if(null!= animationDrawable) {

            animationDrawable.start();

        }


        super.show();


    }


    @Override
    public void dismiss() {


        if(null != animationDrawable && animationDrawable.isRunning()) {

            animationDrawable.stop();

        }
        super.dismiss();


    }
}
