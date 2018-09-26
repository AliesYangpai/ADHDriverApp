package com.adhdriver.work.presenter.driver;

import com.adhdriver.work.constant.ConstError;
import com.adhdriver.work.entity.ErrorEntity;
import com.adhdriver.work.entity.driver.wallet.FundsDetial;
import com.adhdriver.work.http.ParseSerilizable;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.method.IWallet;
import com.adhdriver.work.method.impl.IWalletImpl;
import com.adhdriver.work.presenter.BasePresenter;
import com.adhdriver.work.ui.iview.driver.IWalletInfosView;
import com.adhdriver.work.verification.VertifyNotNull;

import java.util.List;

/**
 * Created by Administrator on 2018/1/10.
 * 类描述
 * 版本
 */

public class PresenterDriverWalletInfos extends BasePresenter<IWalletInfosView> {

    private IWalletInfosView iWalletInfosView;
    private IWallet iWallet;

    private ParseSerilizable parseSerilizable;
    private VertifyNotNull vertifyNotNull;

    public PresenterDriverWalletInfos(IWalletInfosView iWalletInfosView) {
        this.iWalletInfosView = iWalletInfosView;
        this.iWallet = new IWalletImpl();

        this.parseSerilizable = new ParseSerilizable();
        this.vertifyNotNull = new VertifyNotNull();
    }



    /**
     * 服务端获取跨城列表
     * @param url
     * @param size
     * @param index
     */
    public void doGetWalletInfos(String url,int size,int index) {


        this.iWallet.doGetWalletInfos(url, size, index, new OnDataBackListener() {
            @Override
            public void onStart() {
                iWalletInfosView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {


                List<FundsDetial> list = parseSerilizable.getParseToList(data,FundsDetial.class);


                if(vertifyNotNull.isNotNullListSize(list)) {
                    iWalletInfosView.onDataBackSuccessForWalletInfos(list);
                }


            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                if(vertifyNotNull.isNotNullObj(errorEntity)) {

                    iWalletInfosView.onDataBackFail(code,errorEntity.getError_message());
                }else  {

                    iWalletInfosView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);

                }


            }

            @Override
            public void onFinish() {
                iWalletInfosView.dismissLoadingDialog();
            }
        });




    }


    /**
     * 下拉差量更新
     * @param url
     * @param size
     * @param index
     */
    public void doGetWalletInfosInFresh(String url,int size,int index) {

        iWallet.doGetWalletInfosInFresh(url, size, index,  new OnDataBackListener() {
            @Override
            public void onStart() {



            }

            @Override
            public void onSuccess(String data) {


                List<FundsDetial> list = parseSerilizable.getParseToList(data,FundsDetial.class);
                iWalletInfosView.onDataBackSuccessForWalletInfosInFresh(list);

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                if(vertifyNotNull.isNotNullObj(errorEntity)) {

                    iWalletInfosView.onDataBackFail(code,errorEntity.getError_message());
                }else  {

                    iWalletInfosView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);

                }

            }

            @Override
            public void onFinish() {

                iWalletInfosView.dismissFreshLoading();
            }
        });

    };


    /**
     * 上拉加载更多
     * @param url
     * @param size
     * @param index
     */
    public void doGetWalletInfosInLoadMore(String url,int size,int index) {

        iWallet.doGetWalletInfosInLoadMore(url, size, index, new OnDataBackListener() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(String data) {

                List<FundsDetial> list = parseSerilizable.getParseToList(data,FundsDetial.class);
                iWalletInfosView.onDataBackSuccessForWalletInfosInLoadMore(list);

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                if(vertifyNotNull.isNotNullObj(errorEntity)) {

                    iWalletInfosView.onDataBackFailInLoadMore(code,errorEntity.getError_message());
                }else  {

                    iWalletInfosView.onDataBackFailInLoadMore(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);

                }


            }

            @Override
            public void onFinish() {


            }
        });

    }


}
