package com.adhdriver.work.ui.widget.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.callback.VehicleTypePickDialogClickCallBack;
import com.adhdriver.work.entity.driver.vehicle.VehicleType;
import com.adhdriver.work.ui.adapter.fordialog.VehicleTypePickerDialogAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/4/27.
 * 类描述 用于注册上传信息时选择车型
 * 版本
 */

public class VehicleTypePickerDialog extends AlertDialog implements AdapterView.OnItemClickListener {


    /**
     * 中间listView
     *
     * @param context
     */

    private ListView lv_car_type;

    private VehicleTypePickerDialogAdapter vehicleTypePickerDialogAdapter;

    private Context context;


    /**
     * 数据相关
     */
    private List<VehicleType> list;

    private VehicleTypePickDialogClickCallBack vehicleTypePickDialogClickCallBack;




    public void setVehicleTypePickDialogClickCallBack(VehicleTypePickDialogClickCallBack vehicleTypePickDialogClickCallBack) {
        this.vehicleTypePickDialogClickCallBack = vehicleTypePickDialogClickCallBack;
    }

    public List<VehicleType> getList() {
        return list;
    }

    public void setList(List<VehicleType> list) {
        this.list = list;
        if (null != vehicleTypePickerDialogAdapter) {

            vehicleTypePickerDialogAdapter.setList(this.list);
        }

    }

    public VehicleTypePickerDialog(Context context) {
        super(context, R.style.vehicleTypePickerDialog);
        this.context = context;
    }


    public VehicleTypePickerDialog(Context context,List<VehicleType> list) {
        super(context, R.style.vehicleTypePickerDialog);
        this.context = context;
        this.list = list;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_vehicle_type_picker);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);

        //初始化界面控件
        initView();
        //初始化界面数据
        initData();
        //初始化界面控件的事件
        initListener();


    }


    /**
     * 初始化界面控件
     */
    private void initView() {
        /**
         * 中间listView
         * @param context
         */

        lv_car_type = (ListView) findViewById(R.id.lv_car_type);

        vehicleTypePickerDialogAdapter = new VehicleTypePickerDialogAdapter(context);
        lv_car_type.setAdapter(vehicleTypePickerDialogAdapter);


    }


    /**
     * 初始化界面控件的显示数据
     */
    private void initData() {


        vehicleTypePickerDialogAdapter.setList(this.list);

    }


    /**
     * 初始化界面的确定和取消监听器
     */
    private void initListener() {

        lv_car_type.setOnItemClickListener(this);
    }


    /**
     * 点击事件
     */



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        this.dismiss();

        VehicleType vehicleType = vehicleTypePickerDialogAdapter.getList().get(position);

        if (null != vehicleTypePickDialogClickCallBack) {

            vehicleTypePickDialogClickCallBack.pickVehicleTypeClick(vehicleType);

        }


    }
}
