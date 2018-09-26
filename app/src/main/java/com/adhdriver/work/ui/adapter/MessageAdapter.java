package com.adhdriver.work.ui.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.constant.ConstSign;
import com.adhdriver.work.entity.driver.AppMessage;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2017/11/27.
 * 类描述   messagE的adapter适配器
 * 版本
 */

public class MessageAdapter extends BaseQuickAdapter<AppMessage, BaseViewHolder> {

    public MessageAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, AppMessage item) {


        helper.setText(R.id.tv_msg_type, item.getTitle())
                .setText(R.id.tv_msg_time, item.getCreate_time())
                .setText(R.id.tv_msg_detail, item.getMessage());


        TextView tv_msg_detail = helper.getView(R.id.tv_msg_detail);

        String msg = item.getMessage();
        if (isIsJson(msg)) {
            JSONObject jsonObject = getJsonObj(msg);
            tv_msg_detail.setText(getTextFromJson(jsonObject));

        }else {

            tv_msg_detail.setText(msg);

        }
    }


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

    private String getTextFromJson(JSONObject jsonObject) {

        String s = "";
        if (null != jsonObject) {
            try {
                s = jsonObject.getString("content");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return s;

    }
}
