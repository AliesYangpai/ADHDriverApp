package com.adhdriver.work.ui.activity.common;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.callback.AppUpdateDialogCallBack;
import com.adhdriver.work.constant.ConstParams;
import com.adhdriver.work.constant.ConstSign;
import com.adhdriver.work.entity.AppUpdate;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.presenter.driver.PresenterDriverAppInfo;
import com.adhdriver.work.ui.activity.BaseActivity;
import com.adhdriver.work.ui.iview.driver.IAppVersionView;
import com.adhdriver.work.utils.ToastUtil;
import com.adhdriver.work.utils.VersionUtil;
import com.adhdriver.work.ui.widget.dialog.AppUpdateDialog;

public class VersionActivity extends BaseActivity<IAppVersionView,PresenterDriverAppInfo> implements
        IAppVersionView,
        View.OnClickListener,
        AppUpdateDialogCallBack{



    private PresenterDriverAppInfo presenterDriverAppInfo;





    /**
     * titile
     */
    private ImageView iv_common_back;
    private TextView tv_common_title;
    private ImageView iv_common_search;


    /**
     * 中间
     */
    private TextView tv_version_code;
    private TextView tv_app_info;
    /**
     *
     * 底部按钮
     */
    private TextView tv_update_version;


    private AppUpdateDialog appUpdateDialog;

    @Override
    protected PresenterDriverAppInfo creatPresenter() {
        if(null == presenterDriverAppInfo) {

            presenterDriverAppInfo = new PresenterDriverAppInfo(this);
        }
        return presenterDriverAppInfo;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version);

        presenterDriverAppInfo.doSetLocalInfoToUi();
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

        tv_common_title.setText(this.getString(R.string.version_info));


        /**
         * 中间
         */
        tv_version_code = findADHViewById(R.id.tv_version_code);
        tv_app_info = findADHViewById(R.id.tv_app_info);

        /**
         *
         * 底部按钮
         */
        tv_update_version = findADHViewById(R.id.tv_update_version);


    }

    @Override
    protected void initListener() {

        /**
         * titile
         */
        iv_common_back.setOnClickListener(this);

        /**
         *
         * 底部按钮
         */
        tv_update_version.setOnClickListener(this);

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
    public void onDataBackSuccessForGetAppInfoIsNew() {

        ToastUtil.showMsg(getApplicationContext(),R.string.the_newst_version);

    }

    @Override
    public void onDataBackSuccessForGetAppInfoNotNew(AppUpdate appUpdateBack) {


        if (appUpdateDialog == null) {
            appUpdateDialog = new AppUpdateDialog(this, appUpdateBack);
            appUpdateDialog.setAppUpdateDialogCallBack(this);
            appUpdateDialog.setCancelable(false);


        }

        appUpdateDialog.show();

    }



    @Override
    public void doSetLocalInfoToUi() {

        tv_version_code.setText(VersionUtil.getVersionName());
        tv_app_info.setText(this.getString(R.string.adh_infor));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_common_back:

                dofinishItself();
                break;

            case R.id.tv_update_version:


                presenterDriverAppInfo.doGetAppInfo(HttpConst.URL.GET_APP_VERSION_INFO,
                        ConstParams.Config.OS_TYPE,
                        VersionUtil.getPhoneSystemVersion(),
                        VersionUtil.getPhoneBrand()+ ConstSign.UNDER_LINE+VersionUtil.getPhoneType(),
                        VersionUtil.getTheIMEI());
                break;

        }
    }


    /**
     * 跳转到Url去下载
     * @param appUpdate
     */
    @Override
    public void appUpdateDialogClickSure(AppUpdate appUpdate) {
        if(null != appUpdate) {
            String download_url = appUpdate.getDownload_url();
            Uri uri = Uri.parse(download_url);
            Intent it = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(it);
        }
    }
}
