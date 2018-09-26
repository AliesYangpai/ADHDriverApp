package com.adhdriver.work.ui.activity.driver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.constant.ConstLocalData;
import com.adhdriver.work.presenter.driver.PresenterDriverShare;
import com.adhdriver.work.ui.activity.BaseActivity;
import com.adhdriver.work.ui.iview.driver.IShareView;
import com.adhdriver.work.utils.ToastUtil;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class ShareActivity extends BaseActivity<IShareView,PresenterDriverShare> implements
        IShareView,
        OnClickListener,
        PlatformActionListener {



    private PresenterDriverShare presenterDriverShare;


    /**
     * title
     */

    private ImageView iv_common_back;
    private TextView tv_common_title;
    private ImageView iv_common_search;





    private LinearLayout ll_invite_code;
    private TextView tv_share_to_friend;
    private TextView tv_invite_code;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share2);
        presenterDriverShare.doSetDataToUi();


    }

    @Override
    protected PresenterDriverShare creatPresenter() {
        if(null == presenterDriverShare) {
            presenterDriverShare = new PresenterDriverShare(this);
        }
        return presenterDriverShare;
    }

    @Override
    protected void initViews() {

        /**
         * titile
         *
         */
        iv_common_back = findADHViewById(R.id.iv_common_back);
        tv_common_title = findADHViewById(R.id.tv_common_title);
        iv_common_search = findADHViewById(R.id.iv_common_search);

        tv_common_title.setText(this.getString(R.string.num_share));
        iv_common_search.setVisibility(View.GONE);

        ll_invite_code = findADHViewById(R.id.ll_invite_code);


        tv_share_to_friend = findADHViewById(R.id.tv_share_to_friend);
        tv_invite_code = findADHViewById(R.id.tv_invite_code);


    }

    @Override
    protected void initListener() {


        iv_common_back.setOnClickListener(this);
        tv_share_to_friend.setOnClickListener(this);

    }

    @Override
    protected void processIntent() {

    }

    @Override
    public void doShare() {

        OnekeyShare oks = new OnekeyShare();
//关闭sso授权
        oks.disableSSOWhenAuthorize();

// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle(getString(R.string.app_name));
// titleUrl是标题的网络链接，QQ和QQ空间等使用
//        oks.setTitleUrl(ConstBase.DOWNLOAD_PATH);

        oks.setTitleUrl(ConstLocalData.DOWNLOAD_PATH);
// text是分享文本，所有平台都需要这个字段
        oks.setText(this.getString(R.string.share_code)+tv_invite_code.getText().toString() +"\n"+this.getString(R.string.goto_downLoad));
// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath(ConstLocalData.SHARE_IMAGE_PATH);//确保SDcard下面存在此张图片
// url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(ConstLocalData.DOWNLOAD_PATH);



// comment是我对这条分享的评论，仅在人人网和QQ空间使用
//        oks.setComment("我是测试评论文本");



// site是分享此内容的网站名称，仅在QQ空间使用
//        oks.setSite(getString(R.string.app_name));
// siteUrl是分享此内容的网站地址，仅在QQ空间使用
//        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);


        oks.setCallback(this);

    }

    @Override
    public void doSetDataToUi(String code) {


        tv_invite_code.setText(code);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_common_back:
                dofinishItself();
                break;

            case R.id.tv_share_to_friend:

                presenterDriverShare.doShare();
                break;
        }
    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        ToastUtil.showMsg(getApplicationContext(),"分享成功");
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        ToastUtil.showMsg(getApplicationContext(),"分享失败");
    }

    @Override
    public void onCancel(Platform platform, int i) {
        ToastUtil.showMsg(getApplicationContext(),"已取消");
    }
}
