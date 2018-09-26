package com.adhdriver.work.ui.activity.driver;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.callback.auth.QQAuthorityCallBack;
import com.adhdriver.work.constant.ConstEvent;
import com.adhdriver.work.constant.ConstLocalData;
import com.adhdriver.work.constant.ConstTag;
import com.adhdriver.work.entity.EventEntity;
import com.adhdriver.work.entity.driver.thirdauth.AuthState;
import com.adhdriver.work.entity.driver.thirdauth.QQAuthBack;
import com.adhdriver.work.entity.driver.thirdauth.ValidAuthInApp;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.listener.QQAuthorizationBackListener;
import com.adhdriver.work.presenter.driver.PresenterDriverThirdAuth;
import com.adhdriver.work.ui.activity.BaseActivity;
import com.adhdriver.work.ui.iview.driver.IThirdAuthView;
import com.adhdriver.work.utils.ToastUtil;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.tauth.Tencent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.UUID;


/**
 * 授权界面
 */
public class ThirdAuthActivity extends BaseActivity<IThirdAuthView,PresenterDriverThirdAuth> implements
        IThirdAuthView,
        View.OnClickListener,
        QQAuthorityCallBack {


    private PresenterDriverThirdAuth presenterDriverThirdAuth;




    /**
     * titile
     */
    private ImageView iv_common_back;
    private TextView tv_common_title;
    private ImageView iv_common_search;


    /**
     * 上面
     *
     * @param savedInstanceState
     */
    private RelativeLayout rl_wx_bind;//微信绑定
    private RelativeLayout rl_qq_bind;//qq绑定


    private TextView tv_is_wx_bind;
    private TextView tv_is_qq_bind;


    /**
     * 授权相关
     * @param savedInstanceState
     */


    /**
     * 授权登陆相关
     *
     * @param savedInstanceState
     */
    private QQAuthorizationBackListener qqAuthorizationBackListener;
    private Tencent mTencent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_auth);

        EventBus.getDefault().register(this);

        mTencent = Tencent.createInstance(ConstLocalData.APPID_QQ, this.getApplicationContext());

        presenterDriverThirdAuth.doGetBinds(HttpConst.URL.GET_AUTH_LIST);
    }

    @Override
    protected PresenterDriverThirdAuth creatPresenter() {
        if(null == presenterDriverThirdAuth) {
            presenterDriverThirdAuth = new PresenterDriverThirdAuth(this);
        }
        return presenterDriverThirdAuth;
    }

    @Override
    protected void initViews() {

        /**
         * title
         */
        iv_common_back = findADHViewById(R.id.iv_common_back);
        tv_common_title = findADHViewById(R.id.tv_common_title);
        iv_common_search = findADHViewById(R.id.iv_common_search);
        iv_common_search.setVisibility(View.GONE);

        tv_common_title.setText(this.getString(R.string.auth_bind));


        tv_is_wx_bind = findADHViewById(R.id.tv_is_wx_bind);
        tv_is_qq_bind = findADHViewById(R.id.tv_is_qq_bind);


        /**
         * 上面
         * @param savedInstanceState
         */
        rl_wx_bind = findADHViewById(R.id.rl_wx_bind);//微信绑定
        rl_qq_bind = findADHViewById(R.id.rl_qq_bind);//qq绑定

    }

    @Override
    protected void initListener() {


        /**
         * titile
         */
        iv_common_back.setOnClickListener(this);


        /**
         * 上面
         * @param savedInstanceState
         */
        rl_wx_bind.setOnClickListener(this); //微信绑定
        rl_qq_bind.setOnClickListener(this); //qq绑定

    }

    @Override
    protected void processIntent() {

    }

    @Override
    public void showLoadingDialog() {
        showLoadDialog();
    }

    @Override
    public void dismissLoadingDialog() {
        dismissLoadDialog();
    }

    @Override
    public void onDataBackFail(int code, String errorMsg) {

        ToastUtil.showMsg(getApplicationContext(),errorMsg);

    }

    @Override
    public void onDataBackSuccessForQQBind() {

          tv_is_wx_bind.setText(this.getString(R.string.has_third_bind));
          tv_is_qq_bind.setText(this.getString(R.string.not_third_bind));
    }

    @Override
    public void onDataBackSuccessForWxBind() {

        tv_is_wx_bind.setText(this.getString(R.string.not_third_bind));
        tv_is_qq_bind.setText(this.getString(R.string.has_third_bind));

    }

    @Override
    public void onDataBackSuccessForAllBind() {

        tv_is_wx_bind.setText(this.getString(R.string.has_third_bind));
        tv_is_qq_bind.setText(this.getString(R.string.has_third_bind));

    }

    @Override
    public void onDataBackSuccessForNoBind() {

        tv_is_wx_bind.setText(this.getString(R.string.not_third_bind));
        tv_is_qq_bind.setText(this.getString(R.string.not_third_bind));

    }

    @Override
    public void onDataBackSuccessForValidAuthQQ(ValidAuthInApp validAuthInApp) {

        presenterDriverThirdAuth.doBindForQQ(HttpConst.URL.BIND_AUTH ,validAuthInApp);

    }

    @Override
    public void onDataBackSuccessForValidAuthWx(ValidAuthInApp validAuthInApp) {

        presenterDriverThirdAuth.doBindForWx(HttpConst.URL.BIND_AUTH,validAuthInApp);

    }

    @Override
    public void onDataBackSuccessForBindAuthQQ(String back) {

        presenterDriverThirdAuth.doGetBinds(HttpConst.URL.GET_AUTH_LIST);

    }

    @Override
    public void onDataBackSuccessForBindAuthWx(String back) {

        presenterDriverThirdAuth.doGetBinds(HttpConst.URL.GET_AUTH_LIST);

    }

    @Override
    public void doShowQQHasBindAlert() {
        ToastUtil.showMsg(getApplicationContext(),R.string.has_qq_bind);
    }

    @Override
    public void doShowWxHasBindAlert() {
        ToastUtil.showMsg(getApplicationContext(),R.string.has_wx_bind);
    }

    @Override
    public void doGetQQAuthorization() {


        if (null == qqAuthorizationBackListener) {

            qqAuthorizationBackListener = new QQAuthorizationBackListener();

            qqAuthorizationBackListener.setQqAuthorityCallBack(this);

        }


        if (!mTencent.isSessionValid()) {

            mTencent.login(this, ConstLocalData.QQ_SCOPE, qqAuthorizationBackListener);

        }

    }

    @Override
    public void doGetWxAuthorization() {

        String uuid = UUID.randomUUID().toString();
        IWXAPI wxapi = WXAPIFactory.createWXAPI(this, ConstLocalData.APPID_WX, true);
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = uuid;
        wxapi.sendReq(req);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_common_back:
                dofinishItself();
                break;

            case R.id.rl_wx_bind:


                presenterDriverThirdAuth.doGetWxAuthorization();



                break;


            case R.id.rl_qq_bind:

                presenterDriverThirdAuth.doGetQQAuthorization();

                break;
        }
    }


    @Override
    public void qqAuthoritySuccessAndNext(QQAuthBack qqAuthBack) {

        presenterDriverThirdAuth.doValidAuthForQQ(HttpConst.URL.VALID_AUTH,qqAuthBack);

    }

    @Override
    public void qqAuthorityFail() {

    }

    @Override
    public void qqAuthorityCancel() {

    }


    /**
     * wx授权回调==============================================
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventInActivty(EventEntity eventEntity) {


        if (null != eventEntity) {


            String notifyMsg = eventEntity.getNotifyMsg();

            switch (eventEntity.getNotifyTag()) {


                case ConstEvent.WxAuthAbout.SUCCESS:



                    presenterDriverThirdAuth.doValidAuthForWx(HttpConst.URL.VALID_AUTH,notifyMsg,ConstTag.AuthTag.WX);



                    break;


            }

        }
    }
}
