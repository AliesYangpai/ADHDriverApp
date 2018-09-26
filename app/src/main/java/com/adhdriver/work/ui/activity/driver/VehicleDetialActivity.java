package com.adhdriver.work.ui.activity.driver;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.callback.VehicleDeletAlertDialogCallBack;
import com.adhdriver.work.constant.ConstHz;
import com.adhdriver.work.constant.ConstIntent;
import com.adhdriver.work.entity.driver.vehicle.DriverVehicle;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.presenter.driver.PresenterDriverVehicleDetail;
import com.adhdriver.work.ui.activity.BaseActivity;
import com.adhdriver.work.ui.iview.driver.IVehicleDetailView;
import com.adhdriver.work.utils.ImgUtil;
import com.adhdriver.work.utils.StringUtil;
import com.adhdriver.work.utils.ToastUtil;
import com.adhdriver.work.ui.widget.dialog.VehicleDelAlertDialog;

import org.feezu.liuli.timeselector.Utils.TextUtil;

public class VehicleDetialActivity extends BaseActivity<IVehicleDetailView, PresenterDriverVehicleDetail> implements
        IVehicleDetailView,
        OnClickListener,
        VehicleDeletAlertDialogCallBack {


    private PresenterDriverVehicleDetail presenterDriverVehicleDetail;


    /**
     * title
     */
    private ImageView iv_common_back;
    private TextView tv_common_title;
    private ImageView iv_common_search;


    /**
     * 中间
     *
     * @param savedInstanceState
     */
    private TextView tv_vehicle_no;
    private TextView tv_vehicle_brand_name;
    private LinearLayout ll_vehicle_type;
    private TextView tv_vehicle_type;
    private TextView tv_vehicle_max_capacity;
    private TextView tv_vehicle_max_volume;
    private TextView tv_vehicle_state;
    private ImageView iv_vehicle_property;


    /**
     * 下面 图片相关
     *
     * @param
     */



    private ImageView iv_upload_line1_left;
    private ImageView iv_upload_line1_right;
    private ImageView iv_upload_line2_left;
    private ImageView iv_upload_line2_right;
    private ImageView iv_upload_line3_right;

    private LinearLayout ll_edit_vehicle;

    private LinearLayout ll_bottom;

    /**
     * 数据相关
     *
     * @param savedInstanceState
     */

    private String currentDriverVehicleId;
    private DriverVehicle currentdriverVehicle;
    private boolean isEditSuccess;
    /**
     * dialog相关
     *
     * @param savedInstanceState
     */

    private VehicleDelAlertDialog vehicleDelAlertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_detial);
        presenterDriverVehicleDetail.doGetVehicleDetail(HttpConst.URL.DRIVER_VEHICLES + HttpConst.SEPARATOR + currentDriverVehicleId);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (null != data) {


            if (resultCode == ConstIntent.ResponseCode.VEHICLE_EDIT_SUCCESS_BACK) {


                isEditSuccess = true;

                Bundle extras = data.getExtras();

                String driverVehicleId = extras.getString(ConstIntent.BundleKEY.EDIT_VEHICLE_SUCCESS_BACK_ID, ConstIntent.BundleValue.DEFAULT_STRING);

                if (!TextUtil.isEmpty(driverVehicleId)) {

                    currentDriverVehicleId = driverVehicleId;

                    presenterDriverVehicleDetail.doGetVehicleDetail(HttpConst.URL.DRIVER_VEHICLES + HttpConst.SEPARATOR + currentDriverVehicleId);

                }

            }


        }
    }

    @Override
    protected void onDestroy() {

        if(null != vehicleDelAlertDialog && vehicleDelAlertDialog.isShowing()) {
            vehicleDelAlertDialog.dismiss();
            vehicleDelAlertDialog = null;
        }
        super.onDestroy();

    }

    @Override
    protected PresenterDriverVehicleDetail creatPresenter() {
        if (null == presenterDriverVehicleDetail) {
            presenterDriverVehicleDetail = new PresenterDriverVehicleDetail(this);
        }
        return presenterDriverVehicleDetail;
    }

    @Override
    protected void initViews() {


        /**
         * titile
         *
         */
        iv_common_back = findADHViewById(R.id.iv_common_back);
        tv_common_title = findADHViewById(R.id.tv_common_title);
        tv_common_title.setText(this.getString(R.string.my_vehicle_detial));


        /**
         * 下面 图片相关
         * @param
         */


        iv_upload_line1_left = findADHViewById(R.id.iv_upload_line1_left);
        iv_upload_line1_right = findADHViewById(R.id.iv_upload_line1_right);
        iv_upload_line2_left = findADHViewById(R.id.iv_upload_line2_left);
        iv_upload_line2_right = findADHViewById(R.id.iv_upload_line2_right);
        iv_upload_line3_right = findADHViewById(R.id.iv_upload_line3_right);

        ll_edit_vehicle = findADHViewById(R.id.ll_edit_vehicle);
        ll_edit_vehicle.setVisibility(View.VISIBLE);
        ll_bottom = findADHViewById(R.id.ll_bottom);
        /**
         * 中间
         * @param savedInstanceState
         */
        tv_vehicle_no = findADHViewById(R.id.tv_vehicle_no);
        tv_vehicle_brand_name = findADHViewById(R.id.tv_vehicle_brand_name);
        tv_vehicle_type = findADHViewById(R.id.tv_vehicle_type);
        ll_vehicle_type = findADHViewById(R.id.ll_vehicle_type);
        tv_vehicle_max_capacity = findADHViewById(R.id.tv_vehicle_max_capacity);
        tv_vehicle_max_volume = findADHViewById(R.id.tv_vehicle_max_volume);
        tv_vehicle_state = findADHViewById(R.id.tv_vehicle_state);
        iv_vehicle_property = findADHViewById(R.id.iv_vehicle_property);
    }

    @Override
    protected void initListener() {

        iv_common_back.setOnClickListener(this);
        ll_edit_vehicle.setOnClickListener(this);
        ll_bottom.setOnClickListener(this);

    }

    @Override
    protected void processIntent() {
        Intent intent = this.getIntent();
        if (null != intent) {
            Bundle extras = intent.getExtras();
            if (null != extras) {
                currentDriverVehicleId = extras.getString(ConstIntent.BundleKEY.SHOW_VEHICLE_DETIAL, ConstIntent.BundleValue.DEFAULT_STRING);
            }
        }
    }

    @Override
    public void showLoadingDialog() {
        showLoadDialog();
    }

    @Override
    public void dismissLoadingDialog() {
        dismissLoadDialog();
    }

    @Override
    public void onDataBackFail(int code, String errorMsg) {
        ToastUtil.showMsg(getApplicationContext(), errorMsg);
    }

    @Override
    public void onDataBackSuccessForHideVehicleCatogory() {
        ll_vehicle_type.setVisibility(View.GONE);
    }

    @Override
    public void onDatBackSuccessForVehicleVertifyPending() {

        tv_vehicle_state.setText(ConstHz.CARPASS_PENDING);
        tv_vehicle_state.setBackgroundResource(R.drawable.boder_vehicle_pending_bg);
        ll_edit_vehicle.setVisibility(View.GONE);


    }

    @Override
    public void onDatBackSuccessForVehicleVertifyApproved() {


        tv_vehicle_state.setText(ConstHz.CARPASS_APPROVED);
        tv_vehicle_state.setBackgroundResource(R.drawable.boder_vehicle_approved_bg);
        ll_edit_vehicle.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDatBackSuccessForVehicleVertifyRejected() {

        tv_vehicle_state.setText(ConstHz.CARPASS_REJECTED);
        tv_vehicle_state.setBackgroundResource(R.drawable.boder_vehicle_rejected_bg);
        ll_edit_vehicle.setVisibility(View.VISIBLE);

    }

    @Override
    public void onDataBackSuccessForShowVehicleCatogory(String catogory) {


        ll_vehicle_type.setVisibility(View.VISIBLE);
        tv_vehicle_type.setText(StringUtil.getVehicleTypeDescriptionHz(catogory));
    }

    @Override
    public void onDataBackSuccessForVehicleDetail(DriverVehicle driverVehicle) {


        this.currentdriverVehicle = driverVehicle;
        tv_vehicle_no.setText(currentdriverVehicle.getPlate_number());
        tv_vehicle_brand_name.setText(currentdriverVehicle.getVehicle_brand() + currentdriverVehicle.getVehicle_type_name());
        tv_vehicle_max_capacity.setText(currentdriverVehicle.getVehicle_dead_weight());
        tv_vehicle_max_volume.setText(currentdriverVehicle.getVehicle_stere());


        /**
         * 图片相关
         */


        String vehicle_license = currentdriverVehicle.getVehicle_license();//行驶证
        String vehicle_photo_front_path = currentdriverVehicle.getVehicle_photo_front_path();//车头照片
        String vehicle_photo_back_path = currentdriverVehicle.getVehicle_photo_back_path();//车尾照片
        String car_insurance_policy = currentdriverVehicle.getCar_insurance_policy();//车辆保单地址
        String business_insurance = currentdriverVehicle.getBusiness_insurance();//商业保险地址

//            ImgUtil.getInstance().getImgFromNetByUrl("", iv_upload_line1_left, R.drawable.img_upload_bg); //驾驶证

        ImgUtil.getInstance().getImgFromNetByUrl(vehicle_photo_front_path, iv_upload_line1_left, R.drawable.img_car_head);  //车头
        ImgUtil.getInstance().getImgFromNetByUrl(vehicle_photo_back_path, iv_upload_line1_right, R.drawable.img_car_back); //车尾
        ImgUtil.getInstance().getImgFromNetByUrl(car_insurance_policy, iv_upload_line2_left, R.drawable.img_traffic_insurance);  //交强保险单
        ImgUtil.getInstance().getImgFromNetByUrl(business_insurance, iv_upload_line2_right, R.drawable.img_bussiness_insurance);  //商险单
        ImgUtil.getInstance().getImgFromNetByUrl(vehicle_license, iv_upload_line3_right, R.drawable.img_jiashizheng); //行驶证

    }

    @Override
    public void onDataBackSuccessForDelete() {
        ToastUtil.showMsg(getApplicationContext(),R.string.del_vehicle_success);

        this.setResult(ConstIntent.ResponseCode.GO_TO_SHOW_VEHICLE_DETAIL_DEL);
        this.finish();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_common_back:
                dofinishItself();
                break;

            case R.id.ll_edit_vehicle:
                Bundle bundle = new Bundle();
                bundle.putSerializable(ConstIntent.BundleKEY.EDIT_VEHICLE, currentdriverVehicle);
                Intent intent = new Intent(this, VehicleEditActivity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent, ConstIntent.RequestCode.VEHICLE_DETAIL_TO_EDIT);

                break;


            case R.id.ll_bottom:


                if (null == vehicleDelAlertDialog) {
                    vehicleDelAlertDialog = new VehicleDelAlertDialog(this);
                    vehicleDelAlertDialog.setDriverVehicle(currentdriverVehicle);
                    vehicleDelAlertDialog.setVehicleDeletAlertDialogCallBack(this);
                }
                vehicleDelAlertDialog.show();
                break;
        }
    }


    @Override
    public void sureDeletThisVehicleCallBack(int driver_vehicle_id) {

        ToastUtil.showMsg(getApplicationContext(),"点击删除");

        presenterDriverVehicleDetail.doGetVehicleDetail(HttpConst.URL.DRIVER_VEHICLES + HttpConst.SEPARATOR + driver_vehicle_id);
    }
}
