package com.adhdriver.work.ui.adapter.forlistview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.constant.ConstHz;
import com.adhdriver.work.constant.ConstSign;
import com.adhdriver.work.constant.ConstTag;
import com.adhdriver.work.entity.driver.orders.AdditionService;
import com.adhdriver.work.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/19.
 * 类描述
 * 版本
 */

public class ExtraServiceAdapter extends BaseAdapter {


    private List<AdditionService> list;

    private Context context;

    private LayoutInflater inflater;


    public ExtraServiceAdapter(Context context) {
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
        if (list != null && list.size() > 0) {

            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (list != null && list.size() > 0) {

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
        AdditionService addition_service = list.get(position);

        ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_extra_service, null);
            vh.tv_service_name = (TextView) convertView.findViewById(R.id.tv_service_name);
            vh.tv_service_induction = (TextView) convertView.findViewById(R.id.tv_service_induction);

            convertView.setTag(vh);

        } else {
            vh = (ViewHolder) convertView.getTag();
        }


//        vh.tv_service_name.setText(addition_service.getRemark());
//
//
//        vh.tv_service_induction.setText(getTheExtraServiceByServiceType(addition_service));


        setDataToUi(addition_service, vh.tv_service_name, vh.tv_service_induction);



        return convertView;
    }


    private void setDataToUi(AdditionService addition_service, TextView tv_serviceName, TextView tv_service_induction) {


        String serviceType = addition_service.getType();
        String remark = addition_service.getRemark();
        String price = addition_service.getPrice();


        /**
         * 线上
         */


        if (serviceType.equals(ConstTag.AdditionServiceTag.INSURED)) {
            /**
             * 保价
             */
            tv_serviceName.setText(remark);
            tv_service_induction.setText( ConstHz.RMB_UNIT+price );
            tv_service_induction.setTextColor(Color.parseColor("#fd620e"));
            tv_service_induction.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD)); //代码设置字体加粗
        } else if (serviceType.equals(ConstTag.AdditionServiceTag.COD)) {
            /**
             * 代收货款
             */
            tv_serviceName.setText(remark);
            tv_service_induction.setText( ConstHz.RMB_UNIT+price );
            tv_service_induction.setTextColor(Color.parseColor("#fd620e"));
            tv_service_induction.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD)); //代码设置字体加粗

        } else if (serviceType.equals(ConstTag.AdditionServiceTag.HANDLING)) {
            /**
             * 装卸货物
             */
            tv_serviceName.setText(remark);
            tv_service_induction.setText(this.context.getString(R.string.need_bargain));

            tv_service_induction.setTextColor(Color.parseColor("#929292"));


        } else if (serviceType.equals(ConstTag.AdditionServiceTag.RECEIPT)) {
            /**
             * 回单
             */
            if (remark.contains(ConstSign.COLON)) {
                /**
                 * 传真回执
                 */
                String[] arry = StringUtil.getStringArry(remark, ConstSign.COLON);

                tv_serviceName.setText(arry[0]);

                tv_service_induction.setText(this.context.getString(R.string.need_bargain)+ context.getString(R.string.line_feed)+ this.context.getString(R.string.fax_no)+arry[1] );
            } else {
                /**
                 * 司机带回
                 */
                tv_serviceName.setText(remark);
                tv_service_induction.setText(this.context.getString(R.string.need_bargain));
            }

            tv_service_induction.setTextColor(Color.parseColor("#929292"));

        }



    }


    class ViewHolder {
        TextView tv_service_name;//额外服务名称
        TextView tv_service_induction;//额外服务说明
    }


}
