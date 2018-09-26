package com.adhdriver.work.ui.widget.popwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.callback.PhotoPopwindowCallBack;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2017/4/5 0005.
 * 类描述   用于调用系统图片
 * 版本
 */
public class PhotoPopWindow extends PopupWindow implements OnClickListener {

    /**
     * 初始化配置
     */
    private Context context;

    private LayoutInflater inflater;


    private WeakReference<Context> weakReference;


    /**
     * 控件相关
     */

    private View view;

    private RelativeLayout rl_camera;//相机
    private RelativeLayout rl_gallary;//相册
    private TextView tv_photo_cancel; //取消


    private PhotoPopwindowCallBack photoPopwindowCallBack; //调用系统相册设置图片的回调函数



    public PhotoPopWindow(Context context) {
        super(context);

        weakReference = new WeakReference<Context>(context);

        this.inflater = LayoutInflater.from(weakReference.get());
        initViews();
        initListener();

    }


    public void setPhotoPopwindowCallBack(PhotoPopwindowCallBack photoPopwindowCallBack) {
        this.photoPopwindowCallBack = photoPopwindowCallBack;
    }

    private void initViews() {

        view = this.inflater.inflate(R.layout.popwindow_photo, null);

        rl_camera = (RelativeLayout) view.findViewById(R.id.rl_camera);//相机
        rl_gallary = (RelativeLayout) view.findViewById(R.id.rl_gallary);//相册
        tv_photo_cancel = (TextView) view.findViewById(R.id.tv_photo_cancel); //取消
        this.setContentView(view);

        ColorDrawable colorDrawable = new ColorDrawable(0x0000000);

        this.setBackgroundDrawable(colorDrawable);


        this.setWidth(WindowManager.LayoutParams.MATCH_PARENT);

        this.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

        this.setOutsideTouchable(true); //这一步要在showAsDropDown之前调用

        this.setFocusable(true);

        this.setAnimationStyle(R.style.popwindowPhotostyle);



    }

    private void initListener() {

        rl_camera.setOnClickListener(this);
        rl_gallary.setOnClickListener(this);
        tv_photo_cancel.setOnClickListener(this);

    }


    @Override
    public boolean isShowing() {


        boolean type = super.isShowing();

        Log.i("payPop", "isSHowIng:" + type);


        if(type) {


            backgroundAlpha((Activity)weakReference.get(),1f);

        }else {

            backgroundAlpha((Activity)weakReference.get(),0.5f);
        }




        return type;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.rl_camera:
                PhotoPopWindow.this.dismiss();

                getFromCamera();


                break;

            case R.id.rl_gallary:
                PhotoPopWindow.this.dismiss();

                getFromGallery();

                break;


            case R.id.tv_photo_cancel:

                PhotoPopWindow.this.dismiss();
                break;
        }
    }


    private void getFromCamera() {

        if(null != photoPopwindowCallBack) {

            photoPopwindowCallBack.callBackFromCamera();

        }

    }


    private void getFromGallery(){

        if(null != photoPopwindowCallBack) {

            photoPopwindowCallBack.callBackFromGallery();

        }

    }


    /**
     * 背景色变暗
     */

    public void backgroundAlpha(Activity context, float bgAlpha)
    {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }
}
