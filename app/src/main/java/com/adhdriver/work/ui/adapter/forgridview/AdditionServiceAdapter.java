package com.adhdriver.work.ui.adapter.forgridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.constant.ConstLocalData;
import com.adhdriver.work.entity.driver.orders.AdditionService;
import com.adhdriver.work.entity.driver.pay.PayChannelInfo;
import com.adhdriver.work.utils.ImgUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/25.
 * 类描述  收款二维码的adapter
 * 版本
 */

public class AdditionServiceAdapter extends BaseAdapter {

    private Context context;

    private List<AdditionService> list;

    private LayoutInflater inflater;


    public AdditionServiceAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(this.context);
    }


    public List<AdditionService> getList() {
        return list;
    }

    public void setList(List<AdditionService> list) {
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
      AdditionService additionService = list.get(position);

        ViewHolder vh;

        if (convertView == null) {
            vh = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_addition_service, null);
            vh.iv_addition_service_icon = (ImageView) convertView.findViewById(R.id.iv_addition_service_icon);
            vh.tv_addition_service_name = (TextView) convertView.findViewById(R.id.tv_addition_service_name);
            convertView.setTag(vh);

        } else {
            vh = (ViewHolder) convertView.getTag();
        }



        doSetPic(vh.iv_addition_service_icon,additionService.getType());
        vh.tv_addition_service_name.setText(additionService.getRemark());


        return convertView;
    }

    public class ViewHolder {

        ImageView iv_addition_service_icon;
        TextView tv_addition_service_name;
    }


    private void doSetPic(ImageView imageView,String type) {

        switch (type) {

            case ConstLocalData.HANDLING:

                imageView.setImageResource(R.drawable.img_load_and_discharge_goods);
                break;
            case ConstLocalData.RECEIPT:

                imageView.setImageResource(R.drawable.img_icon_receipt);

                break;

            case ConstLocalData.COD:


                imageView.setImageResource(R.drawable.img_icon_cod);
                break;

            case ConstLocalData.INSURED:


                imageView.setImageResource(R.drawable.img_icon_insured);
                break;

        }
    }

}
