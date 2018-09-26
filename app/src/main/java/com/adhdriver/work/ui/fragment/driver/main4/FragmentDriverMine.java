package com.adhdriver.work.ui.fragment.driver.main4;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adhdriver.work.AiDaiHuoApplication;
import com.adhdriver.work.R;
import com.adhdriver.work.entity.EventEntity;
import com.adhdriver.work.entity.User;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.presenter.driver.PresenterDriverFgMine;
import com.adhdriver.work.ui.activity.driver.CustomCenterActivity;
import com.adhdriver.work.ui.activity.driver.MessageActivity;
import com.adhdriver.work.ui.activity.driver.SettingActivity;
import com.adhdriver.work.ui.activity.driver.UserProtocolActivity;
import com.adhdriver.work.ui.fragment.BaseFragment;
import com.adhdriver.work.ui.iview.driver.IFgMineDriverView;
import com.adhdriver.work.utils.ImgUtil;
import com.adhdriver.work.utils.ToastUtil;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDriverMine extends BaseFragment<IFgMineDriverView, PresenterDriverFgMine> implements
        IFgMineDriverView,
        View.OnClickListener,
        SwipeRefreshLayout.OnRefreshListener{
    public static final String TAG = FragmentDriverMine.class.getSimpleName();


    private PresenterDriverFgMine presenterDriverFgMine;


    /**
     * 上
     */

    private SwipeRefreshLayout srefreshl_mine;
    private ImageView iv_userHead;
    private TextView tv_userName;
    private TextView tv_userNumber;


    /**
     * 中间三项
     */

    private LinearLayout ll_my_wallet; //我的钱包
    private LinearLayout ll_my_discount_coupon; //优惠券
    private LinearLayout ll_my_integral;//积分


    /**
     * 中间
     *
     * @return
     */
    private RelativeLayout rl_invite_friend;//邀请好友
    private RelativeLayout rl_bidding_invite;//我参与的竞价

    private RelativeLayout rl_vehicle_mgr;//车辆管理

    private RelativeLayout rl_user_protocol;//用户协议
    private RelativeLayout rl_custom_service_center;//客服中心


    /**
     * 下部
     */

    private RelativeLayout rl_user_msg;//我的消息
    private RelativeLayout rl_setting;//收费标准


    @Override
    protected PresenterDriverFgMine creatPresenter() {


        if (null == presenterDriverFgMine) {
            presenterDriverFgMine = new PresenterDriverFgMine(this);
        }
        return presenterDriverFgMine;
    }

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_driver_mine;
    }

    @Override
    protected void getSendData(Bundle arguments) {


    }

    @Override
    protected void initView() {


        /**
         * 图片和文字
         */
        srefreshl_mine = findADHViewById(R.id.srefreshl_mine);
        srefreshl_mine.setColorSchemeColors(getSwipeRefreshColor());
        iv_userHead = findADHViewById(R.id.iv_userHead);
        tv_userName = findADHViewById(R.id.tv_userName);
        tv_userNumber = findADHViewById(R.id.tv_userNumber);

        /**
         * 中间三项
         *
         */

        ll_my_wallet = findADHViewById(R.id.ll_my_wallet); //我的钱包
        ll_my_discount_coupon = findADHViewById(R.id.ll_my_discount_coupon); //优惠券
        ll_my_integral = findADHViewById(R.id.ll_my_integral);//积分


        /**
         * 中间
         * @return
         */
        rl_invite_friend = findADHViewById(R.id.rl_invite_friend);//邀请好友
        rl_bidding_invite = findADHViewById(R.id.rl_bidding_invite);//我参与的竞价
        rl_vehicle_mgr = findADHViewById(R.id.rl_vehicle_mgr);//车辆管理
        rl_user_protocol = findADHViewById(R.id.rl_user_protocol);//用户协议
        rl_custom_service_center = findADHViewById(R.id.rl_custom_service_center);//客服中心

        /**
         * 下部
         */

        rl_user_msg = findADHViewById(R.id.rl_user_msg);//我的消息
        rl_setting = findADHViewById(R.id.rl_setting);//收费标准


    }

    @Override
    protected void initListener() {



        srefreshl_mine.setOnRefreshListener(this);
        /**
         * 中间三项
         *
         */

        ll_my_wallet.setOnClickListener(this); //我的钱包
        ll_my_discount_coupon.setOnClickListener(this); //优惠券
        ll_my_integral.setOnClickListener(this);//积分


        /**
         * 中间
         * @return
         */
        rl_invite_friend.setOnClickListener(this);//邀请好友
        rl_bidding_invite.setOnClickListener(this);//我参与的竞价
        rl_vehicle_mgr.setOnClickListener(this);//车辆管理
        rl_user_protocol.setOnClickListener(this);//用户协议
        rl_custom_service_center.setOnClickListener(this);//客服中心

        /**
         * 下部
         */
        rl_user_msg.setOnClickListener(this);//用户信息
        rl_setting.setOnClickListener(this);//收费标准

    }

    @Override
    protected void onLazyLoad() {

        presenterDriverFgMine.getUserInfoFromDb();

    }

    @Override
    protected void onEventBack(EventEntity eventEntity) {

    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {


            /**
             * 中间三项
             *
             */
            case R.id.ll_my_wallet: //我的钱包


                ToastUtil.showMsg(mActivity, "钱包 赶制中");

                break;


            case R.id.ll_my_discount_coupon:  //优惠券
                ToastUtil.showMsg(mActivity, "优惠券 赶制中");


                break;


            case R.id.ll_my_integral: //积分

                ToastUtil.showMsg(mActivity, "积分 赶制中");


                break;


            case R.id.rl_invite_friend: //邀请好友

                ToastUtil.showMsg(mActivity, "邀请好友 赶制中");

                break;


            case R.id.rl_vehicle_mgr://车辆管理

                ToastUtil.showMsg(mActivity, "车辆管理 赶制中");
                break;


            case R.id.rl_user_protocol: //用户协议

                openActivity(UserProtocolActivity.class,null);



                break;
            case R.id.rl_custom_service_center: //客服中心





                openActivity(CustomCenterActivity.class,null);

                break;

            /**
             * 下部
             */
            case R.id.rl_user_msg: //我的消息



                openActivity(MessageActivity.class,null);

                break;

            case R.id.rl_setting: //设置


                openActivity(SettingActivity.class,null);

                break;


            case R.id.rl_bidding_invite: //竞价结果

                ToastUtil.showMsg(mActivity, "竞价结果 赶制中");

                break;


            case R.id.iv_userHead:


                ToastUtil.showMsg(mActivity, "用户头像 赶制中");
                break;
        }
    }


    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void dismissLoadingDialog() {

    }

    @Override
    public void onDataBackFail(int code, String errorMsg) {


        srefreshl_mine.setRefreshing(false);

        ToastUtil.showMsg(AiDaiHuoApplication.getContext(),errorMsg);

    }

    @Override
    public void onDataBackSuccessForGetUserInfo(User user) {


        srefreshl_mine.setRefreshing(false);
        String avatar = user.getAvatar();
        String fullname = user.getFullname();
        ImgUtil.getInstance().getRadiusImgFromNetByUrl(avatar, iv_userHead, R.drawable.img_default_client_head, 120);
        tv_userName.setText(fullname);
        tv_userNumber.setText(getString(R.string.the_Id) + user.getPhone());
    }


    @Override
    public void onDataFromUserDb(User user) {

        String avatar = user.getAvatar();
        String fullname = user.getFullname();
        ImgUtil.getInstance().getRadiusImgFromNetByUrl(avatar, iv_userHead, R.drawable.img_default_client_head, 120);
        tv_userName.setText(fullname);
        tv_userNumber.setText(getString(R.string.the_Id) + user.getPhone());

    }

    @Override
    public void onRefresh() {
        presenterDriverFgMine.doGetUserInfoInFresh(HttpConst.URL.GET_USER_INFO);
    }
}
