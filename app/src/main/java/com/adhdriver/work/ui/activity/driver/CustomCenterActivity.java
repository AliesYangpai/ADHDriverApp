package com.adhdriver.work.ui.activity.driver;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.constant.ConstLocalData;
import com.adhdriver.work.presenter.driver.PresenterDriverCustomCenter;
import com.adhdriver.work.ui.activity.BaseActivity;
import com.adhdriver.work.ui.iview.ICustomCenterView;
import com.adhdriver.work.utils.SimCardUtil;
import com.adhdriver.work.utils.ToastUtil;

public class CustomCenterActivity extends BaseActivity<ICustomCenterView, PresenterDriverCustomCenter> implements View.OnClickListener, ICustomCenterView {


    private PresenterDriverCustomCenter presenterDriverCustomCenter;


    /**
     * titile
     */
    private ImageView iv_common_back;
    private TextView tv_common_title;
    private ImageView iv_common_search;


    /**
     * 中间
     *
     * @param savedInstanceState
     */
    private RelativeLayout rl_call;
    private TextView tv_call_num;
    private RelativeLayout rl_wx;
    private RelativeLayout rl_sina;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_center);

        presenterDriverCustomCenter.doSetCustomerCenterData();
    }

    @Override
    protected PresenterDriverCustomCenter creatPresenter() {
        if (null == presenterDriverCustomCenter) {
            presenterDriverCustomCenter = new PresenterDriverCustomCenter(this);
        }
        return presenterDriverCustomCenter;
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

        tv_common_title.setText(this.getString(R.string.custom_service));


        /**
         * 中间
         * @param savedInstanceState
         */


        rl_call = findADHViewById(R.id.rl_call);
        tv_call_num = findADHViewById(R.id.tv_call_num);
        rl_wx = findADHViewById(R.id.rl_wx);
        rl_sina = findADHViewById(R.id.rl_sina);
    }

    @Override
    protected void initListener() {


        /**
         * titile
         */
        iv_common_back.setOnClickListener(this);


        /**
         * 中间
         * @param savedInstanceState
         */


        rl_call.setOnClickListener(this);
        rl_wx.setOnClickListener(this);
        rl_sina.setOnClickListener(this);


    }

    @Override
    protected void processIntent() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_common_back:
                dofinishItself();
                break;


            case R.id.rl_call:
                presenterDriverCustomCenter.doCallUs(tv_call_num.getText().toString().trim());
                break;


            case R.id.rl_wx:


//                presenterDriverCustomCenter.doOpenOfficialWeb(ConstLocalData.OFFICIAL_WEB);
                break;


            case R.id.rl_sina:
                presenterDriverCustomCenter.doOpenSina();
                break;

        }
    }

    @Override
    public void doSetCustomerCenterData() {


    }

    @Override
    public void doCallUs(String phone) {

        /**
         * 打电话需要获取系统权限，需要到AndroidManifest.xml里面配置权限
         * <uses-permission android:name="android.permission.CALL_PHONE"/>
         */


        if (TextUtils.isEmpty(phone)) {


            ToastUtil.showMsg(getApplicationContext(), R.string.have_not_get_contact);

            return;

        }

        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }


        if (!SimCardUtil.hasSimCard()) {


            ToastUtil.showMsg(getApplicationContext(), R.string.not_sim_card);
            return;
        }

        startActivity(intent);

    }

    @Override
    public void doOpenOfficialWeb(String url) {


        /**
         * 方法1
         */
        Uri uri = Uri.parse(url);
        Intent it = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(it);


    }

    @Override
    public void doOpenSinaClient(String url, String sinaId, String type) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url + sinaId));
        Intent chooseIntent = Intent.createChooser(intent, type);
        startActivity(chooseIntent);
    }

    @Override
    public void doOpenSinaWeb(String url, String sinaId, String type) {


        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url + sinaId));
        Intent chooseIntent = Intent.createChooser(intent, type);
        startActivity(chooseIntent);

    }
}
