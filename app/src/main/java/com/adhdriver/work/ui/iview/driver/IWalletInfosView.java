package com.adhdriver.work.ui.iview.driver;

import com.adhdriver.work.entity.driver.orders.Order;
import com.adhdriver.work.entity.driver.wallet.FundsDetial;
import com.adhdriver.work.ui.iview.IBaseView;

import java.util.List;

/**
 * Created by Administrator on 2017/12/27.
 * 类描述  跨城fg界面
 * 版本
 */

public interface IWalletInfosView extends IBaseView {


    void onDataBackFail(int code, String errorMsg);

    void onDataBackFailInLoadMore(int code, String errorMsg);

    void dismissFreshLoading();

    void onDataBackSuccessForWalletInfos(List<FundsDetial> list);

    void onDataBackSuccessForWalletInfosInFresh(List<FundsDetial> list);

    void onDataBackSuccessForWalletInfosInLoadMore(List<FundsDetial> list);



}
