package com.adhdriver.work.ui.adapter.fordialog;

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
 * 类描述
 * 版本
 */

public class RechargeMoneyChannelDialogAdapter extends BaseAdapter {

    private Context context;

    private List<PayChannelInfo> list;

    private LayoutInflater inflater;


    public RechargeMoneyChannelDialogAdapter(Context context) {
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
            convertView = inflater.inflate(R.layout.item_recharge_money_channel, null);
            vh.iv_pay_icon = (ImageView) convertView.findViewById(R.id.iv_pay_icon);
            vh.tv_center_text = (TextView) convertView.findViewById(R.id.tv_center_text);
            convertView.setTag(vh);

        } else {
            vh = (ViewHolder) convertView.getTag();
        }



        ImgUtil.getInstance().getImgFromNetByUrl(payChannelInfo.getLogo(),vh.iv_pay_icon, R.drawable.img_alipay);
        vh.tv_center_text.setText(payChannelInfo.getName()+context.getString(R.string.top_up_recharge));



        return convertView;
    }

    public class ViewHolder {

        ImageView iv_pay_icon;
        TextView tv_center_text;
    }

}
