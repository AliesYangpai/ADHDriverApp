package com.adhdriver.work.presenter.driver;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import com.adhdriver.work.AiDaiHuoApplication;
import com.adhdriver.work.constant.ConstLocalData;
import com.adhdriver.work.method.ICustomCenter;
import com.adhdriver.work.presenter.BasePresenter;
import com.adhdriver.work.ui.iview.ICustomCenterView;
import com.adhdriver.work.utils.CheckOtherAppClientUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/11/20.
 * 类描述
 * 版本
 */

public class PresenterDriverCustomCenter extends BasePresenter<ICustomCenterView> {


    private ICustomCenterView iCustomCenterView;

    private ICustomCenter iCustomCenter;


    public PresenterDriverCustomCenter(ICustomCenterView iCustomCenterView) {
        this.iCustomCenterView = iCustomCenterView;
    }


    public void doSetCustomerCenterData() {


        this.iCustomCenterView.doSetCustomerCenterData();
    }


    public void doCallUs(String phone) {

        this.iCustomCenterView.doCallUs(phone);

    }


    public void doOpenOfficialWeb(String url) {

        this.iCustomCenterView.doOpenOfficialWeb(url);
    }


    public void doOpenSina() {


        boolean result = CheckOtherAppClientUtil.hasSineClient();

        if (result) {

            iCustomCenterView.doOpenSinaClient(ConstLocalData.SINA_CLIENT_URL, ConstLocalData.SINA_ID_ADH,ConstLocalData.SINA_TYPE);
        } else {

            this.iCustomCenterView.doOpenSinaWeb(ConstLocalData.SINA_WEB_URL, ConstLocalData.SINA_ID_ADH,ConstLocalData.SINA_TYPE);

        }


    }


}
