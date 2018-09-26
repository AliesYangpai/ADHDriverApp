package com.adhdriver.work.ui.adapter.fordialog;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.entity.driver.token.TokenInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/27.
 * 类描述
 * 版本
 */

public class TokenDialogPickerAdapter extends BaseAdapter {


    private List<TokenInfo> list;


    private Context context;

    private LayoutInflater inflater;

    private boolean accessOrDeny = false;


    public TokenDialogPickerAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(this.context);

    }

    public TokenDialogPickerAdapter(List<TokenInfo> list, Context context) {
        this.list = list;
        this.inflater = LayoutInflater.from(this.context);
        this.context = context;
    }


    public boolean isAccessOrDeny() {
        return accessOrDeny;
    }



    /**
     * 判断是否都是空闲车辆
     * @param list
     * @return
     */
    private boolean isAllVacant(List<TokenInfo> list) {

        boolean result = false;


        int  countTemp = 0;
        if(null != list && list.size() > 0) {


            for (TokenInfo tokenInfo:list) {

                int count = tokenInfo.getProcessing_order_counts();
                countTemp +=count;
            }

        }


        if(countTemp== 0) {

            result = true;
        }


        return result;


    }



    public List<TokenInfo> getList() {
        return list;
    }

    public void setList(List<TokenInfo> list) {

        if(null == list) {

            list = new ArrayList<>();


        }
        this.list = list;
        this.accessOrDeny = isAllVacant(this.list);

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


        TokenInfo tokenAbout = list.get(position);
        ViewHolder vh = null;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_picker_tokens, null);
            vh.tv_car_num_in_token_adapter = (TextView) convertView.findViewById(R.id.tv_car_num_in_token_adapter);
            vh.iv_is_business_car = (ImageView) convertView.findViewById(R.id.iv_is_business_car);

            convertView.setTag(vh);

        } else {
            vh = (ViewHolder) convertView.getTag();
        }




        vh.tv_car_num_in_token_adapter.setText(tokenAbout.getDescription());






        isBusinessVehicleOrNotUiChange(tokenAbout.is_joined_enterprise(),vh.iv_is_business_car);





        isOrderStillHas(tokenAbout.getProcessing_order_counts(),vh.tv_car_num_in_token_adapter);

        return convertView;
    }






    private void isOrderStillHas(int processing_order_counts,TextView tv_car_num_in_token_adapter) {



        if(accessOrDeny) {

            tv_car_num_in_token_adapter.setTextColor(Color.parseColor("#008fff"));

        }else {

            if(processing_order_counts != 0) {


                tv_car_num_in_token_adapter.setTextColor(Color.parseColor("#008fff"));

            }else {


                tv_car_num_in_token_adapter.setTextColor(Color.parseColor("#a9a9a9"));


            }

        }






    }

    /**
     * 判断是否是企业车辆，进行Ui显示变更
     */
    private void isBusinessVehicleOrNotUiChange(boolean isBusiness,ImageView imageView) {

        if(isBusiness) {
            imageView.setVisibility(View.VISIBLE);

        }else {

            imageView.setVisibility(View.GONE);

        }



    }


    public class ViewHolder {

        TextView tv_car_num_in_token_adapter;

        ImageView iv_is_business_car;
    }
}
