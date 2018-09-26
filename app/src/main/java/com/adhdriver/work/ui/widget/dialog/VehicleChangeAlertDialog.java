package com.adhdriver.work.ui.widget.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.callback.VehicleChangeAlertDialogCallBack;
import com.adhdriver.work.entity.driver.vehicle.DriverVehicle;

/**
 * Created by Administrator on 2017/5/12.
 * 类描述   更换车辆的提示dialog
 * 版本
 */

public class VehicleChangeAlertDialog extends AlertDialog implements View.OnClickListener {


    private Context context;


    private TextView tv_vehicle_no;
    private TextView tv_cancel;
    private TextView tv_sure;



    private DriverVehicle driverVehicle;

    private VehicleChangeAlertDialogCallBack vehicleChangeAlertDialogCallBack;




    public void setVehicleChangeAlertDialogCallBack(VehicleChangeAlertDialogCallBack vehicleChangeAlertDialogCallBack) {
        this.vehicleChangeAlertDialogCallBack = vehicleChangeAlertDialogCallBack;
    }


    public void setDriverVehicle(DriverVehicle driverVehicle) {
        this.driverVehicle = driverVehicle;
    }

    public VehicleChangeAlertDialog(Context context) {
        super(context, R.style.vehicleChangeAlertDialog);
        this.context = context;

    }






    public VehicleChangeAlertDialog(Context context, AttributeSet attrs) {
        super(context, R.style.vehicleChangeAlertDialog);
        this.context = context;


    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_vehicle_change_alert);

        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);

        //初始化界面控件
        initView();

        //初始化界面控件的事件
        initListener();

        //初始化界面数据
        initData();
    }


    /**
     * 初始化界面的确定和取消监听器
     */
    private void initListener() {
        /**
         * 底部按钮
         */
        tv_cancel.setOnClickListener(this); //底部取消
        tv_sure.setOnClickListener(this);//底部确认

    }

    /**
     * 初始化界面控件的显示数据
     */
    private void initData() {


        if(null != driverVehicle) {

            tv_vehicle_no.setText(driverVehicle.getPlate_number());
        }


    }


    /**
     * 更新driverVehicle
     * @param driverVehicle
     */
    public void doUpdateData(DriverVehicle driverVehicle) {

        this.driverVehicle = driverVehicle;
        if(null != driverVehicle) {
            tv_vehicle_no.setText(driverVehicle.getPlate_number());
        }


    }



    /**
     * 初始化界面控件
     */
    private void initView() {


          tv_vehicle_no = (TextView) findViewById(R.id.tv_vehicle_no);
          tv_cancel = (TextView) findViewById(R.id.tv_cancel);
          tv_sure = (TextView) findViewById(R.id.tv_sure);

    }





    @Override
    public void onClick(View v) {

        this.dismiss();


        switch (v.getId()) {

            case R.id.tv_cancel:  //取消



                break;


            case R.id.tv_sure:    //确认

                if(null != vehicleChangeAlertDialogCallBack && null != driverVehicle) {
                    vehicleChangeAlertDialogCallBack.sureChangeToThisVehicleCallBack(driverVehicle.getDriver_vehicle_id());
                }

                break;


        }
    }




}
