package com.adhdriver.work.presenter.driver;

import com.adhdriver.work.dao.IBaseDao;
import com.adhdriver.work.dao.impl.IUserDaoImpl;
import com.adhdriver.work.entity.User;
import com.adhdriver.work.presenter.BasePresenter;
import com.adhdriver.work.ui.iview.driver.IShareView;
import com.adhdriver.work.verification.VertifyNotNull;

/**
 * Created by Administrator on 2018/1/19.
 * 类描述
 * 版本
 */

public class PresenterDriverShare extends BasePresenter<IShareView> {

    private IShareView iShareView;
    private IBaseDao<User> iUserDao;
    private VertifyNotNull vertifyNotNull;

    public PresenterDriverShare(IShareView iShareView) {
        this.iShareView = iShareView;
        this.iUserDao = new IUserDaoImpl();
        this.vertifyNotNull = new VertifyNotNull();
    }


    public void doSetDataToUi(){


        User user  = iUserDao.findFirst(User.class);

        if(vertifyNotNull.isNotNullObj(user)) {

            iShareView.doSetDataToUi(user.getUnique_code());
        }


    }

    public void doShare() {

        iShareView.doShare();
    }
}
