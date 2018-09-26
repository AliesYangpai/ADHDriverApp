package com.adhdriver.work.function;

import android.widget.ListView;

import com.adhdriver.work.constant.ConstLocalData;
import com.adhdriver.work.constant.ConstSign;
import com.adhdriver.work.constant.ConstTag;
import com.adhdriver.work.entity.OssConfig;
import com.adhdriver.work.entity.driver.pay.PayChannelInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/10.
 * 类描述  支付渠道方法
 * 版本
 */

public class FunctionPayChannel {


    public FunctionPayChannel() {
    }


    public List<PayChannelInfo> getReSetChannelInfos(List<PayChannelInfo> list, OssConfig ossConfig) {


        List<PayChannelInfo> payChannelInfos = new ArrayList<>();


        for (PayChannelInfo payChannelInfo:list) {


            if (payChannelInfo.getChannel_id().equals(ConstLocalData.ALIPAY)) {
                payChannelInfo.setLogo(ossConfig.getPost_url() + ossConfig.getObject_key() + payChannelInfo.getLogo());
                payChannelInfos.add(payChannelInfo);
            }
            if (payChannelInfo.getChannel_id().equals(ConstLocalData.WX)) {
                payChannelInfo.setLogo(ossConfig.getPost_url() + ossConfig.getObject_key() + payChannelInfo.getLogo());
                payChannelInfos.add(payChannelInfo);
            }

        }


        return payChannelInfos;

    }


}
