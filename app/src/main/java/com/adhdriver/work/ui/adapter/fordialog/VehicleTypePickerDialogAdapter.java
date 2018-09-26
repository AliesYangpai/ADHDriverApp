package com.adhdriver.work.ui.adapter.fordialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.entity.driver.vehicle.VehicleType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/27.
 * 类描述
 * 版本
 */

public class VehicleTypePickerDialogAdapter extends BaseAdapter {


    private List<VehicleType> list;


    private Context context;

    private LayoutInflater inflater;

    public VehicleTypePickerDialogAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(this.context);

    }

    public VehicleTypePickerDialogAdapter(List<VehicleType> list, Context context) {
        this.list = list;
        this.inflater = LayoutInflater.from(this.context);
        this.context = context;
    }

    public List<VehicleType> getList() {
        return list;
    }

    public void setList(List<VehicleType> list) {

        if(null == list) {

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


        VehicleType vehicleType = list.get(position);
        ViewHolder vh = null;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_vehicle_type_dialog_picker, null);
            vh.tv_car_type_in_adapter = (TextView) convertView.findViewById(R.id.tv_car_type_in_adapter);
            convertView.setTag(vh);

        } else {
            vh = (ViewHolder) convertView.getTag();
        }

//        vh.tv_car_type_in_adapter.setText(vehicleType.getVehicle_type_name());


        vh.tv_car_type_in_adapter.setText(vehicleType.getVehicle_brand());




        return convertView;
    }


    public class ViewHolder {

        TextView tv_car_type_in_adapter;


    }
}
