package com.adhdriver.work.ui.activity.common;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.callback.FeedBackSuccessDialogClickCallBack;
import com.adhdriver.work.constant.ConstParams;
import com.adhdriver.work.constant.ConstSign;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.presenter.common.PresenterFeedBack;
import com.adhdriver.work.ui.activity.BaseActivity;
import com.adhdriver.work.ui.iview.IFeedBackView;
import com.adhdriver.work.utils.ToastUtil;
import com.adhdriver.work.utils.VersionUtil;
import com.adhdriver.work.ui.widget.dialog.FeedBackSuccessDialog;

public class FeedBackActivity extends BaseActivity<IFeedBackView,PresenterFeedBack> implements
        IFeedBackView,
        OnClickListener,
        TextWatcher,
        FeedBackSuccessDialogClickCallBack {


    private PresenterFeedBack presenterFeedBack;



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

    private EditText et_feedback_content;
    private LinearLayout ll_feed_back;
    private TextView tv_entered;

    /**
     * 数据相关
     */

    private static final int MAX_COUNT = 200;
    private int editStart;
    private int editEnd;
    private String userPhone;


    /**
     * dialog相关
     * @param c
     * @return
     */

    private FeedBackSuccessDialog feedBackSuccessDialog;


    private long calculateLength(CharSequence c) {
        double len = 0;
        for (int i = 0; i < c.length(); i++) {
            int tmp = (int) c.charAt(i);
            if (tmp > 0 && tmp < 127) {
                len += 0.5;
            } else {
                len++;
            }
        }
        return Math.round(len);
    }


    private void setLeftCount() {
//        tv_entered.setText(String.valueOf((MAX_COUNT - getInputCount())));
        tv_entered.setText(String.valueOf(getInputCount()));
    }


    private long getInputCount() {
        return calculateLength(et_feedback_content.getText().toString());
    }

    @Override
    protected PresenterFeedBack creatPresenter() {
        if(null == presenterFeedBack) {
            presenterFeedBack = new PresenterFeedBack(this);
        }
        return presenterFeedBack;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

        presenterFeedBack.doGetPhoneFromDb();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(null != feedBackSuccessDialog && feedBackSuccessDialog.isShowing()) {
            feedBackSuccessDialog.dismiss();
            feedBackSuccessDialog = null;
        }
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

        tv_common_title.setText(this.getString(R.string.feed_back));


        /**
         * 中间
         * @param savedInstanceState
         */

        et_feedback_content = findADHViewById(R.id.et_feedback_content);
        tv_entered = findADHViewById(R.id.tv_entered);
        ll_feed_back = findADHViewById(R.id.ll_feed_back);


    }

    @Override
    protected void initListener() {

        /**
         * titile
         */
        iv_common_back.setOnClickListener(this);

        et_feedback_content.addTextChangedListener(this);

        ll_feed_back.setOnClickListener(this);

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
    public void onDataBackSuccessToShowDialog() {




        if (feedBackSuccessDialog == null) {

            feedBackSuccessDialog = new FeedBackSuccessDialog(this,this.getString(R.string.feed_thanks), this.getString(R.string.i_know));
            feedBackSuccessDialog.setFeedBackSuccessDialogClickCallBack(this);
            feedBackSuccessDialog.setCancelable(false);

        }
        feedBackSuccessDialog.show();




    }

    @Override
    public void doShowAlertWithOutMsg() {


        ToastUtil.showMsg(getApplicationContext(),this.getString(R.string.your_advice));
    }

    @Override
    public void doGetUserPhoneFromDb(String phone) {

        this.userPhone = phone;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_common_back:
                dofinishItself();
                break;

            case R.id.ll_feed_back:


               presenterFeedBack.doFeedBack(
                       HttpConst.URL.FEEDBACKS,
                       ConstParams.Config.SOURCE,
                       et_feedback_content.getText().toString(),
                       userPhone,
                       VersionUtil.getPhoneSystemVersion(),
                       VersionUtil.getPhoneBrand()+ ConstSign.UNDER_LINE+VersionUtil.getPhoneType(),
                       ConstParams.Config.OS_TYPE
                       );



                break;

        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {


        editStart = et_feedback_content.getSelectionStart();
        editEnd = et_feedback_content.getSelectionEnd();

        // 先去掉监听器，否则会出现栈溢出
        et_feedback_content.removeTextChangedListener(this);

        // 注意这里只能每次都对整个EditText的内容求长度，不能对删除的单个字符求长度
        // 因为是中英文混合，单个字符而言，calculateLength函数都会返回1
        while (calculateLength(s.toString()) > MAX_COUNT) { // 当输入字符个数超过限制的大小时，进行截断操作
            s.delete(editStart - 1, editEnd);
            editStart--;
            editEnd--;
        }
        // mEditText.setText(s);将这行代码注释掉就不会出现后面所说的输入法在数字界面自动跳转回主界面的问题了，多谢@ainiyidiandian的提醒
        et_feedback_content.setSelection(editStart);

        // 恢复监听器
        et_feedback_content.addTextChangedListener(this);

        setLeftCount();

    }

    @Override
    public void feedBackSuccessDialogClick() {
        dofinishItself();
    }
}
