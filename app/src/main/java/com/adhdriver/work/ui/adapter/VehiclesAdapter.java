package com.adhdriver.work.ui.adapter;

import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.constant.ConstSp;
import com.adhdriver.work.constant.ConstTag;
import com.adhdriver.work.entity.driver.vehicle.DriverVehicle;
import com.adhdriver.work.utils.SpUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import org.feezu.liuli.timeselector.Utils.TextUtil;

/**
 * Created by Administrator on 2017/11/20.
 * 类描述  车辆列表的adapter
 * 版本
 */

public class VehiclesAdapter extends BaseQuickAdapter<DriverVehicle, BaseViewHolder> {


    public VehiclesAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, DriverVehicle driverVehicle) {


        /**
         * 车辆审核状态
         */
        ImageView iv_vehicle_state = helper.getView(R.id.iv_vehicle_state);


        /**
         * 没有通过、审核中..
         */
        LinearLayout ll_vechcle_not_pass = helper.getView(R.id.ll_vechcle_not_pass);
        TextView tv_not_pass_info = helper.getView(R.id.tv_not_pass_info);
        /**
         * 审核成功
         */
        LinearLayout ll_vehicle_pass = helper.getView(R.id.ll_vehicle_pass);
        ImageView iv_current_logon_vehicle = helper.getView(R.id.iv_current_logon_vehicle);


        switch (driverVehicle.getVehicle_status()) {


            case ConstTag.CarPassStatusTag.PENDING:  //审核中
                ll_vehicle_pass.setVisibility(View.GONE);
                ll_vechcle_not_pass.setVisibility(View.VISIBLE);
                iv_vehicle_state.setImageResource(R.drawable.img_validating);
                tv_not_pass_info.setText(mContext.getString(R.string.validity_wait));

                break;

            case ConstTag.CarPassStatusTag.APPROVED:  //审核通过
                ll_vechcle_not_pass.setVisibility(View.GONE);
                ll_vehicle_pass.setVisibility(View.VISIBLE);
                iv_vehicle_state.setImageResource(R.drawable.img_validation_pass);

                break;

            case ConstTag.CarPassStatusTag.REJECTED:  //审核失败
                ll_vehicle_pass.setVisibility(View.GONE);
                ll_vechcle_not_pass.setVisibility(View.VISIBLE);
                iv_vehicle_state.setImageResource(R.drawable.img_validation_deny);
                tv_not_pass_info.setText("车辆信息审核不通过");
                break;

        }


        iv_current_logon_vehicle.setImageResource(getCurrentLognVehiclePic(driverVehicle.getDriver_vehicle_id()));

        helper.setText(R.id.tv_vehicle_name, driverVehicle.getVehicle_brand() + driverVehicle.getVehicle_type_name())
                .setText(R.id.tv_vehicle_no, driverVehicle.getPlate_number())
                .addOnClickListener(R.id.ll_vehicle_pass)
                .addOnClickListener(R.id.rl_top);


    }


    /**
     * 设置车辆状态
     *
     * @param
     */
    private int getCurrentLognVehiclePic(int driver_vehicle_id) {

        int drwableId = R.drawable.img_vehicle_normal;


        String stringValue = SpUtil.getInstance().getStringValue(ConstSp.SP_KEY_LOGON_DRIVER_VEHICLE_ID, ConstSp.SP_VALUE.DEFAULT_STRING);

        if (!TextUtil.isEmpty(stringValue)) {


            Integer integerLogon = Integer.valueOf(stringValue);
            if (integerLogon == driver_vehicle_id) {
                drwableId = R.drawable.img_vehicle_selected;
            }
        }

        return drwableId;
    }


}
