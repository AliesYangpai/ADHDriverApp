package com.adhdriver.work.ui.activity.driver;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.callback.h5.IRefuelNavigateCallBack;
import com.adhdriver.work.callback.h5.RefuelExitDialogCallBack;
import com.adhdriver.work.constant.ConstCZB;
import com.adhdriver.work.constant.ConstIntent;
import com.adhdriver.work.constant.ConstSign;
import com.adhdriver.work.entity.driver.refuel.RefuleCoordinate;
import com.adhdriver.work.presenter.driver.PresenterDriverRefuel;
import com.adhdriver.work.ui.activity.BaseActivity;
import com.adhdriver.work.ui.iview.driver.IRefuelView;
import com.adhdriver.work.utils.ToastUtil;
import com.adhdriver.work.ui.widget.dialog.ExitRefuelH5Dialog;
import com.adhdriver.work.ui.widget.webview.WebClientCZB;
import com.just.library.AgentWeb;
import com.just.library.ChromeClientCallbackManager;

public class RefuelActivity extends BaseActivity<IRefuelView, PresenterDriverRefuel> implements
        IRefuelView,
        ChromeClientCallbackManager.ReceivedTitleCallback,
        View.OnClickListener,
        IRefuelNavigateCallBack,
        RefuelExitDialogCallBack {


    private PresenterDriverRefuel presenterDriverRefuel;


    private AgentWeb mAgentWeb;
    private WebView mWebView;
    private LinearLayout mLinearLayout;


    /**
     * title
     */

    private ImageView iv_common_back;
    private TextView tv_common_title;
    private ImageView iv_common_search;

    /**
     * dialog相关
     *
     * @param savedInstanceState
     */

    private ExitRefuelH5Dialog exitRefuelH5Dialog;

    /**
     * 数据系相关
     * @param savedInstanceState
     */


    /**
     * 数据相关
     * @param savedInstanceState
     */

    private String currentPhone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refuel);


        presenterDriverRefuel.doInitH5Refuel(ConstCZB.URL, ConstCZB.PLAT_FORM_TYPE_VALUE, currentPhone);
    }

    @Override
    protected PresenterDriverRefuel creatPresenter() {
        if (null == presenterDriverRefuel) {
            presenterDriverRefuel = new PresenterDriverRefuel(this);
        }
        return presenterDriverRefuel;
    }

    @Override
    protected void initViews() {

        mLinearLayout = (LinearLayout) findViewById(R.id.ll_container);


        iv_common_back = findADHViewById(R.id.iv_common_back);
        tv_common_title = findADHViewById(R.id.tv_common_title);
        iv_common_search = findADHViewById(R.id.iv_common_search);
        iv_common_search.setVisibility(View.GONE);
        tv_common_title.setText(R.string.come_to_refuel);

    }

    @Override
    protected void initListener() {
        iv_common_back.setOnClickListener(this);
    }

    @Override
    protected void processIntent() {


        Intent intent = this.getIntent();
        if(null != intent) {

            Bundle bundle = intent.getExtras();

            if(null != bundle) {
                currentPhone =  bundle.getString(ConstIntent.BundleKEY.REFUEL_DRIVER_PHONE,ConstIntent.BundleValue.DEFAULT_STRING);

            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
            mWebView.goBack();// 返回前一个页面
            return true;
        }else {


            if (exitRefuelH5Dialog == null) {

                exitRefuelH5Dialog = new ExitRefuelH5Dialog(this);
                exitRefuelH5Dialog.setRefuelExitDialogCallBack(this);
                exitRefuelH5Dialog.setCancelable(false);
            }
            exitRefuelH5Dialog.show();

        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void dismissLoadingDialog() {

    }

    @Override
    public void doInitH5Refuel(String url, String platform, String phone) {


        //        String url = "https://msdev.czb365.com/?platformType"+"="+platformType+"&"+"platformCode="+currentPhone;  //测试Url

//        String url = "http://open.czb365.com/redirection/todo?platformType"+"="+platformType+"&"+"platformCode="+currentPhone;//正式Url


        mAgentWeb = AgentWeb.with(this)//
                .setAgentWebParent(mLinearLayout, new LinearLayout.LayoutParams(-1, -1))//
                .useDefaultIndicator()//
                .defaultProgressBarColor()
                .setReceivedTitleCallback(this)
                .setWebViewClient(new WebClientCZB(this))
                .setSecutityType(AgentWeb.SecurityType.default_check)
                .createAgentWeb()//
                .ready()
                .go(url + ConstSign.EQUAL_SIGN + ConstCZB.PLAT_FORM_TYPE_VALUE + ConstSign.AND + ConstCZB.PLAT_FORM_CODE_KEY + ConstSign.EQUAL_SIGN + phone);


        mAgentWeb.getJsInterfaceHolder().addJavaObject(ConstCZB.MATHOD_NAME, this);


        mWebView = mAgentWeb.getWebCreator().get();

    }

    @Override
    public void doGotoRefuelNavigate(String startLat, String startLng, String endLat, String endLng) {

        RefuleCoordinate refuleCoordinate = new RefuleCoordinate(startLat, startLng, endLat, endLng);
        Bundle bundle = new Bundle();
        bundle.putSerializable(ConstIntent.BundleKEY.REFUEL_TO_NAVIGATE, refuleCoordinate);
        openActivity(RefuelNavigateActivity.class, bundle);





        ToastUtil.showMsg(getApplicationContext(),"进入油站导航界面");
    }

    @Override
    public void doVertifyErrorForNullCurrentLat() {

        ToastUtil.showMsg(getApplicationContext(),R.string.null_for_your_lat);

    }

    @Override
    public void doVertifyErrorForNullCurrentLnt() {

        ToastUtil.showMsg(getApplicationContext(),R.string.null_for_your_lnt);
    }

    @Override
    public void doVertifyErrorForNullGasStationLat() {

        ToastUtil.showMsg(getApplicationContext(),R.string.null_for_gas_station_lat);

    }

    @Override
    public void doVertifyErrorForNullGasStationLnt() {

        ToastUtil.showMsg(getApplicationContext(),R.string.null_for_gas_station_lnt);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_common_back:


                if (exitRefuelH5Dialog == null) {

                    exitRefuelH5Dialog = new ExitRefuelH5Dialog(this);
                    exitRefuelH5Dialog.setRefuelExitDialogCallBack(this);
                    exitRefuelH5Dialog.setCancelable(false);
                }
                exitRefuelH5Dialog.show();

                break;
        }
    }


    /**
     * h5调用本地方法
     *
     * @param startLat
     * @param startLng
     * @param endLat
     * @param endLng
     */
    @JavascriptInterface
    @Override
    public void startNavigate(String startLat, String startLng, String endLat, String endLng) {
        presenterDriverRefuel.doGotoRefuelNavigate(startLat,startLng,endLat,endLng);
    }


    @JavascriptInterface
    @Override
    public void setExtraInfoHead(String key, String value) {

    }

    @Override
    public void onReceivedTitle(WebView view, String title) {

    }

    @Override
    public void onClickRefuelExitSure() {


        if (null != exitRefuelH5Dialog && exitRefuelH5Dialog.isShowing()) {

            exitRefuelH5Dialog.dismiss();
            RefuelActivity.this.finish();
        }
    }

    @Override
    public void onClickRefuelExitCancel() {
        if (null != exitRefuelH5Dialog && exitRefuelH5Dialog.isShowing()) {

            exitRefuelH5Dialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {

        mAgentWeb.getWebLifeCycle().onDestroy();
        if (null != exitRefuelH5Dialog && exitRefuelH5Dialog.isShowing()) {
            exitRefuelH5Dialog.dismiss();
            exitRefuelH5Dialog = null;

        }
        super.onDestroy();
    }
}
