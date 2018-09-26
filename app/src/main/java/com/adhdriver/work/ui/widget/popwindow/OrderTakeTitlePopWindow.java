package com.adhdriver.work.ui.widget.popwindow;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.callback.PopwindowOrderTakeTitleCallBack;
import com.adhdriver.work.utils.ImgUtil;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2018/1/3.
 * 类描述
 * 版本
 */

public class OrderTakeTitlePopWindow extends PopupWindow implements View.OnClickListener {


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


    private ImageView iv_common_back;
    private ImageView iv_client_head;
    private TextView tv_client_name;


    private String userName;
    private String avatar;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    private PopwindowOrderTakeTitleCallBack popwindowOrderTakeTitleCallBack;


    public void setPopwindowOrderTakeTitleCallBack(PopwindowOrderTakeTitleCallBack popwindowOrderTakeTitleCallBack) {
        this.popwindowOrderTakeTitleCallBack = popwindowOrderTakeTitleCallBack;
    }




    public OrderTakeTitlePopWindow(Context context, String userName, String avatar) {
        super(context);
        this.userName = userName;
        this.avatar = avatar;
        weakReference = new WeakReference<Context>(context);

        this.inflater = LayoutInflater.from(weakReference.get());
        initViews();
        initListener();
        initdata();
    }


    private void initdata() {
        tv_client_name.setText(userName);
        ImgUtil.getInstance().getRadiusImgFromNetByUrl(avatar,iv_client_head,R.drawable.img_default_client_head_round,120);
    }

    private void initViews() {

        view = this.inflater.inflate(R.layout.popwindow_order_take_title, null);


        iv_common_back = (ImageView) view.findViewById(R.id.iv_common_back);
        iv_client_head = (ImageView) view.findViewById(R.id.iv_client_head);
        tv_client_name = (TextView) view.findViewById(R.id.tv_client_name);

        this.setContentView(view);

        ColorDrawable colorDrawable = new ColorDrawable(0x0000000);

        this.setBackgroundDrawable(colorDrawable);

        this.setWidth(WindowManager.LayoutParams.MATCH_PARENT);

        this.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

        this.setOutsideTouchable(false); //这一步要在showAsDropDown之前调用

        this.setFocusable(false);

        this.setAnimationStyle(R.style.popwindowOrderTakeTitle);


    }


    private void initListener() {
        iv_common_back.setOnClickListener(this);
    }


    @Override
    public boolean isShowing() {


        boolean type = super.isShowing();

        Log.i("payPop", "isSHowIng:" + type);


        return type;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_common_back:



                if(null !=  popwindowOrderTakeTitleCallBack) {

                    popwindowOrderTakeTitleCallBack.onClickBack();
                }

                break;


        }
    }


}
