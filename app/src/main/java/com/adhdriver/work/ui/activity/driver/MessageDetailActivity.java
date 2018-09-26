package com.adhdriver.work.ui.activity.driver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.constant.ConstIntent;
import com.adhdriver.work.constant.ConstSign;
import com.adhdriver.work.entity.driver.AppMessage;
import com.adhdriver.work.presenter.driver.PresenterDriverMessageDetail;
import com.adhdriver.work.ui.activity.BaseActivity;
import com.adhdriver.work.ui.iview.driver.IMessageDetailView;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * 消息详情
 */
public class MessageDetailActivity extends BaseActivity<IMessageDetailView, PresenterDriverMessageDetail> implements
        IMessageDetailView,
        View.OnClickListener {


    private PresenterDriverMessageDetail presenterDriverMessageDetail;


    /**
     * titile
     */
    private ImageView iv_common_back;
    private TextView tv_common_title;
    private ImageView iv_common_search;


    /**
     * 中间
     */

    private ImageView iv_msg_type;
    private TextView tv_msg_content;


    /**
     * 数据相关
     *
     * @param savedInstanceState
     */

    private AppMessage currentAppMessage;

    private boolean isIsJson(String string) {

        return string.contains(ConstSign.JSON_OBJ_START) && string.contains(ConstSign.JSON_OBJ_END);
    }

    private JSONObject getJsonObj(String s) {


        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(s);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;

    }

    private String getTextFromJson(String key,JSONObject jsonObject) {

        String s = "";
        if (null != jsonObject) {
            try {
                s = jsonObject.getString(key);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return s;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_detail);

        presenterDriverMessageDetail.doSetDataToUi(currentAppMessage);
    }

    @Override
    protected PresenterDriverMessageDetail creatPresenter() {
        if (null == presenterDriverMessageDetail) {
            presenterDriverMessageDetail = new PresenterDriverMessageDetail(this);
        }
        return presenterDriverMessageDetail;
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


        /**
         * 中间
         */

        iv_msg_type = findADHViewById(R.id.iv_msg_type);
        tv_msg_content = findADHViewById(R.id.tv_msg_content);


    }

    @Override
    protected void initListener() {

        iv_common_back.setOnClickListener(this);
    }

    @Override
    protected void processIntent() {

        Intent intent = this.getIntent();

        if (null != intent) {
            Bundle bundle = intent.getExtras();

            if (null != bundle) {

                currentAppMessage = (AppMessage) bundle.getSerializable(ConstIntent.BundleKEY.MSG_DETAIL);
            }


        }
    }


    @Override
    public void doSetActivityDataToUi(AppMessage appMessage) {


        tv_common_title.setText(appMessage.getTitle());
        tv_msg_content.setText(appMessage.getMessage());
    }

    @Override
    public void doSetCustomerServiceDataToUi(AppMessage appMessage) {

        tv_common_title.setText(appMessage.getTitle());
        tv_msg_content.setText(appMessage.getMessage());
    }

    @Override
    public void doSetFeedbackDataToUi(AppMessage appMessage) {


        tv_common_title.setText(appMessage.getTitle());
//        tv_msg_content.setText(appMessage.getMessage());

        String message = appMessage.getMessage();


        if(isIsJson(message)) {

           JSONObject jsonObject = getJsonObj(message);

           String content =  getTextFromJson("content",jsonObject);
            String reply_content =  getTextFromJson("reply_content",jsonObject);
            tv_msg_content.setText(content+ "\n"+reply_content);
        }else {

            tv_msg_content.setText(appMessage.getMessage());
        }


    }

    @Override
    public void doSetVehicleAccessDataToUi(AppMessage appMessage) {


        tv_common_title.setText(appMessage.getTitle());
        tv_msg_content.setText(appMessage.getMessage());
        //         iv_msg_type.setImageResource(R.drawable.img_msg_vehicle_check_access);
    }

    @Override
    public void doSetVehicleDenyDataToUi(AppMessage appMessage) {


        tv_common_title.setText(appMessage.getTitle());
        tv_msg_content.setText(appMessage.getMessage());
        //      iv_msg_type.setImageResource(R.drawable.img_msg_vehicle_check_deny);

    }

    @Override
    public void doSetWithDrawDataToUi(AppMessage appMessage) {


        tv_common_title.setText(appMessage.getTitle());
        tv_msg_content.setText(appMessage.getMessage());
        //      iv_msg_type.setImageResource(R.drawable.img_msg_withdraw);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_common_back:
                dofinishItself();
                break;
        }
    }
}
