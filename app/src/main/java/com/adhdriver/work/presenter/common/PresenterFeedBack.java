package com.adhdriver.work.presenter.common;

import android.text.TextUtils;

import com.adhdriver.work.R;
import com.adhdriver.work.constant.ConstError;
import com.adhdriver.work.dao.IBaseDao;
import com.adhdriver.work.dao.impl.IUserDaoImpl;
import com.adhdriver.work.entity.ErrorEntity;
import com.adhdriver.work.entity.User;
import com.adhdriver.work.http.ParseSerilizable;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.method.IFeedBack;
import com.adhdriver.work.method.impl.IFeedBackImpl;
import com.adhdriver.work.presenter.BasePresenter;
import com.adhdriver.work.ui.iview.IFeedBackView;
import com.adhdriver.work.utils.ToastUtil;

import org.feezu.liuli.timeselector.Utils.TextUtil;

/**
 * Created by Administrator on 2017/11/24.
 * 类描述
 * 版本
 */

public class PresenterFeedBack extends BasePresenter<IFeedBackView> {

    private IFeedBackView iFeedBackView;
    private IFeedBack iFeedBack;
    private IBaseDao<User> iUserDao;
    private ParseSerilizable parseSerilizable;


    public PresenterFeedBack(IFeedBackView iFeedBackView) {
        this.iFeedBackView = iFeedBackView;
        this.iFeedBack = new IFeedBackImpl();
        this.parseSerilizable = new ParseSerilizable();
        this.iUserDao = new IUserDaoImpl();
    }


    /**
     * 提交意见
     */
    public void doFeedBack(String url,
                           String source,
                           String content,
                           String phone,
                           String os_version,
                           String model,
                           String os_type) {

        if (TextUtils.isEmpty(content)) {


            iFeedBackView.doShowAlertWithOutMsg();

//            ToastUtil.showMsg(getApplicationContext(),this.getString(R.string.your_advice));

            return;
        }


        iFeedBack.doFeedBack(
                url,
                source,
                content,
                phone,
                os_version,
                model,
                os_type, new OnDataBackListener() {
                    @Override
                    public void onStart() {

                        iFeedBackView.showLoadingDialog();
                    }

                    @Override
                    public void onSuccess(String data) {

                        iFeedBackView.onDataBackSuccessToShowDialog();

                    }

                    @Override
                    public void onFail(int code, String data) {
                        ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                        if (null != errorEntity) {

                            iFeedBackView.onDataBackFail(code, errorEntity.getError_message());
                        } else {

                            iFeedBackView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                        }

                    }

                    @Override
                    public void onFinish() {

                        iFeedBackView.dismissLoadingDialog();
                    }
                });


    }


    public void doGetPhoneFromDb() {


       User user =  iUserDao.findFirst(User.class);

        if(null != user) {

           String phone =  user.getPhone();

            iFeedBackView.doGetUserPhoneFromDb(phone);

        }else {

            iFeedBackView.onDataBackFail(ConstError.DEFUAL_ERROR_LOCAL_SEL_CODE,ConstError.PARSE_ERROR_LOCAL_SEL_MSG);

        }

    }
}
