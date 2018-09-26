package com.adhdriver.work.ui.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adhdriver.work.entity.EventEntity;
import com.adhdriver.work.presenter.BasePresenter;
import com.adhdriver.work.ui.widget.dialog.AdhDialog;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.WeakReference;


/**
 * 基类fragment的封装
 * 1.获取mActivty
 * 2.onCreateView方法的封装
 * 3.部分变量的写入
 * 4.部分方法封装
 */
public abstract class BaseFragment<V,T extends BasePresenter<V>> extends Fragment {





    /**
     * 获取所依附的Activty实例
     * @param activity
     */
    protected Activity mActivity;

    /**
     * 根view
     */
    protected View mRootView;


    /**
     * 是否对用户可见
     */
    protected boolean mIsVisible;

    /**
     * 是否加载完成
     * 当执行完oncreatview,View的初始化方法后方法后即为true
     */
    protected boolean mIsPrepare;



    private T presenter;


    private AdhDialog adhDialog;

    private WeakReference<Context> weakReference;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = getActivity();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        weakReference = new WeakReference<Context>(mActivity);
        presenter = creatPresenter();

        if(null != presenter) {

            presenter.attachView((V)this);
        }
//        presenter.attachView((V)this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mRootView = inflater.inflate(setLayoutResouceId(), container, false);

        getSendData(getArguments()); //传参

        initView();

        initListener();

        mIsPrepare = true;

        onLazyLoad();



        return mRootView;
    }



    protected abstract T creatPresenter();
    /**
     * 设置根布局的资源id
     */
    protected abstract int setLayoutResouceId();
    /**
     * 初始化数据
     * @param arguments 接收到从其他地方传递过来的参数
     */
    protected abstract void getSendData(Bundle arguments);

    /**
     * 初始化view
     */
    protected abstract void initView();


    /**
     * 初始化监听
     */
    protected abstract void initListener();


    /**
     * 懒加载，仅当用户可见切view初始化结束后才会执行
     * //懒加载的方法,在这个方法里面我们为Fragment的各个组件去添加数据
     */
    protected abstract void onLazyLoad();


    protected abstract void onEventBack(EventEntity eventEntity);


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusNotify(EventEntity eventEntity) {


        if(null != eventEntity) {


            onEventBack(eventEntity);
        }



    }



    /**
     * findViewById方法
     */
    @SuppressWarnings("unchecked")
    protected <T extends View> T findADHViewById(int id) {
        if (mRootView == null)
        {
            return null;
        }

        return (T) mRootView.findViewById(id);
    }



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        this.mIsVisible = isVisibleToUser;

        if (isVisibleToUser)
        {
            onVisibleToUser();
        }

        Log.i("baseFragment","调用setUserVisibleHint方法");
    }

    /**
     * 用户可见时执行的操作:界面完全可见时候，再去执行各种交互
     *
     *
     */
    protected void onVisibleToUser() {
        if (mIsPrepare && mIsVisible)
        {
            onLazyLoad();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        destroyLoadDialog();
        removePresenter();
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(this.getClass().getSimpleName());
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(this.getClass().getSimpleName());
    }

    private void removePresenter() {

        if(null != presenter) {
            presenter.detachView();
        }
    }

    /**
     * 显示加载Dialog
     */
    protected void showLoadDialog() {


        if(null != weakReference ) {
            if(null == adhDialog) {
                adhDialog = new AdhDialog(weakReference.get());
            }


            if(!mActivity.isFinishing()) {
                adhDialog.show();

            }

        }

    }


    protected void dismissLoadDialog() {

        if(null != weakReference) {


            if(null != adhDialog && adhDialog.isShowing()) {

                adhDialog.dismiss();
            }

        }

    }

    protected void destroyLoadDialog() {

        if(null != adhDialog) {

            if(adhDialog.isShowing()) {

                adhDialog.dismiss();
                adhDialog = null;

            }else {

                adhDialog = null;
            }
        }
    }



    /**
     * 打开activity的方法
     */
    protected void openActivity(Class<?> targetClass, Bundle bundle) {
        Intent intent = new Intent(mActivity, targetClass);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }



    public int[] getSwipeRefreshColor() {

        return new int[]{Color.parseColor("#26313d")};
    }
}
