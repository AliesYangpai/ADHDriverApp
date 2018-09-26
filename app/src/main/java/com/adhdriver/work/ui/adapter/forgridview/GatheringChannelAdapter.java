package com.adhdriver.work.ui.adapter.forgridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.entity.driver.pay.PayChannelInfo;
import com.adhdriver.work.utils.ImgUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/25.
 * 类描述  收款二维码的adapter
 * 版本
 */

public class GatheringChannelAdapter extends BaseAdapter {

    private Context context;

    private List<PayChannelInfo> list;

    private LayoutInflater inflater;


    public GatheringChannelAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(this.context);
    }


    public List<PayChannelInfo> getList() {
        return list;
    }

    public void setList(List<PayChannelInfo> list) {
        if (null == list) {

            list = new ArrayList<>();

        }
        this.list = list;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if(list != null && list.size() > 0) {

            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if(list != null && list.size() > 0) {

            return list.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      PayChannelInfo payChannelInfo = list.get(position);

        ViewHolder vh;

        if (convertView == null) {
            vh = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_gathering_channel, null);
            vh.iv_pay_icon = (ImageView) convertView.findViewById(R.id.iv_pay_icon);
            vh.tv_pay_channel = (TextView) convertView.findViewById(R.id.tv_pay_channel);
            convertView.setTag(vh);

        } else {
            vh = (ViewHolder) convertView.getTag();
        }


        ImgUtil.getInstance().getImgFromNetByUrl(payChannelInfo.getLogo(),vh.iv_pay_icon, R.drawable.img_alipay);

        vh.tv_pay_channel.setText(payChannelInfo.getName());


        return convertView;
    }

    public class ViewHolder {

        ImageView iv_pay_icon;
        TextView tv_pay_channel;
    }

}
