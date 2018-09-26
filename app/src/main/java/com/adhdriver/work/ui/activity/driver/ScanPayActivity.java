package com.adhdriver.work.ui.activity.driver;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.callback.doublebutton.DoubleDialogCallBack;
import com.adhdriver.work.callback.edit.DialogClickEditCallBack;
import com.adhdriver.work.constant.ConstConfig;
import com.adhdriver.work.constant.ConstError;
import com.adhdriver.work.constant.ConstIntent;
import com.adhdriver.work.constant.ConstLocalData;
import com.adhdriver.work.constant.ConstParams;
import com.adhdriver.work.constant.ConstTag;
import com.adhdriver.work.entity.ErrorEntity;
import com.adhdriver.work.entity.driver.orders.Fee;
import com.adhdriver.work.entity.driver.orders.FeeDetail;
import com.adhdriver.work.entity.driver.orders.Order;
import com.adhdriver.work.entity.driver.orders.OrderTagEntity;
import com.adhdriver.work.entity.driver.orders.To;
import com.adhdriver.work.entity.driver.pay.PayScanBack;
import com.adhdriver.work.http.CallServer;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.http.HttpSingleResponseListener;
import com.adhdriver.work.http.ParseSerilizable;
import com.adhdriver.work.http.RequestDriverParams;
import com.adhdriver.work.http.RequestPacking;
import com.adhdriver.work.presenter.driver.PresenterDriverScanPay;
import com.adhdriver.work.ui.activity.BaseActivity;
import com.adhdriver.work.ui.iview.driver.IScanPayView;
import com.adhdriver.work.ui.widget.dialog.doublebutton.DoubleButtonDialog;
import com.adhdriver.work.ui.widget.dialog.edit.EditDialog;
import com.adhdriver.work.utils.ToastUtil;
import com.adhdriver.work.utils.VersionUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.StringUtils;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import org.feezu.liuli.timeselector.Utils.TextUtil;

import java.util.Hashtable;
import java.util.List;

public class ScanPayActivity extends BaseActivity<IScanPayView, PresenterDriverScanPay> implements IScanPayView,
        DialogClickEditCallBack,
        DoubleDialogCallBack, OnClickListener {


    private PresenterDriverScanPay presenterDriverScanPay;


    /**
     * titile
     */
    private ImageView iv_common_back;
    private TextView tv_common_title;
    private ImageView iv_common_search;
    private RelativeLayout rl_parent;

    /**
     * 中间
     */
    private ImageView iv_scan_pay_icon;
    private TextView tv_text_qr_get_money;
    private TextView tv_get_money_chanel;
    private ImageView iv_qr;
    private TextView tv_get_money_sum;
    private TextView tv_confirm_get_paid;  //验证收款

    /**
     * 数据相关
     *
     * @param savedInstanceState
     */

    private float currentMoney;
    private String currentTag;//微信扫码、支付宝扫码 id
    private String currentOrderNo;

    private int QR_WIDTH = 200;
    private int QR_HEIGHT = 200;

//    private int QR_WIDTH = 400;
//    private int QR_HEIGHT = 400;

    private Order currentOrder;
    private PayScanBack payScanBack;


    /**
     * dialog相关
     *
     * @param savedInstanceState
     */


    private EditDialog editDialog;
    private DoubleButtonDialog doubleButtonDialog;


    private ParseSerilizable parseSerilizable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_pay2);

        parseSerilizable = new ParseSerilizable();

        initData();

    }

    @Override
    protected PresenterDriverScanPay creatPresenter() {
        if (null == presenterDriverScanPay) {
            presenterDriverScanPay = new PresenterDriverScanPay(this);
        }
        return presenterDriverScanPay;
    }

    private void initData() {

//        tv_get_money_sum.setText(ConstBase.RMB_UNIT + currentMoney);
        tv_get_money_sum.setText(this.getString(R.string.currency_unit) + currentMoney);


        getCurrentOrderFromServer(ConstTag.PayTag.SCAN_ORDER_INFO_INIT);

        getQRCodeFromServer();


    }


    /**
     * 获取当前订单信息
     *
     * @param type
     */
    private void getCurrentOrderFromServer(final String type) {


        Request<String> request = RequestPacking.getInstance()
                .getRequestParamForJson(HttpConst.URL.ORDER_INFO + HttpConst.SEPARATOR + currentOrderNo,
                        RequestMethod.GET,
                        null);


        CallServer.getInstance().add(HttpConst.HTTP_WHAT, request, new HttpSingleResponseListener<String>() {
            @Override
            protected void OnHttpStart(int what) {

            }

            @Override
            protected void OnHttpSuccessed(int what, Response<String> response) {

                currentOrder =parseSerilizable.getParseToObj(response.get(),Order.class);


                switch (type) {

                    case ConstTag.PayTag.SCAN_ORDER_INFO_INIT:
                        break;

                    case ConstTag.PayTag.SCAN_ORDER_INFO_GET_AFTER:

                        sendConfirmCodeToReciver();

                        break;

                }
            }

            @Override
            protected void onHttpFailed(int what, Response<String> response) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(response.get(), ErrorEntity.class);
                if(null!= errorEntity) {

                    ToastUtil.showMsg(getApplicationContext(), errorEntity.getError_message());
                }else {

                    ToastUtil.showMsg(getApplicationContext(), ConstError.PARSE_ERROR_MSG);

                }

            }

            @Override
            protected void OnHttpFinish(int what) {

            }
        });






    }


    /**
     * 获取二维码
     */
    private void getQRCodeFromServer() {


        String jsonString = RequestDriverParams.getQRCodeParam(ConstTag.PayTag.channel_order_type, currentOrderNo, currentTag, VersionUtil.getTheIMEI(), ConstParams.PaySide.RECIVER);

        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(HttpConst.URL.PAY_QR_CODE,
                RequestMethod.POST, jsonString);


        CallServer.getInstance().add(HttpConst.HTTP_WHAT, request, new HttpSingleResponseListener<String>() {
            @Override
            protected void OnHttpStart(int what) {

                showLoadDialog();

            }

            @Override
            protected void OnHttpSuccessed(int what, Response<String> response) {



                payScanBack = parseSerilizable.getParseToObj(response.get(), PayScanBack.class);

                String qr_url = payScanBack.getToken();

                if (!TextUtils.isEmpty(qr_url)) {

//                    createQRImage(qr_url);

                    createQRCodeWithLogo(qr_url, QR_HEIGHT, BitmapFactory.decodeResource(getResources(), R.drawable.img_qr_company));

                }
            }

            @Override
            protected void onHttpFailed(int what, Response<String> response) {


                ErrorEntity errorEntity = parseSerilizable.getParseToObj(response.get(), ErrorEntity.class);
                if(null!= errorEntity) {

                    ToastUtil.showMsg(getApplicationContext(), errorEntity.getError_message());
                }else {

                    ToastUtil.showMsg(getApplicationContext(), ConstError.PARSE_ERROR_MSG);

                }


            }

            @Override
            protected void OnHttpFinish(int what) {

                dismissLoadDialog();

            }
        });


    }


    public void createQRImage(String url) {
        try {
            //判断URL合法性
            if (url == null || "".equals(url) || url.length() < 1) {
                return;
            }
            Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            //图像数据转换，使用了矩阵转换
            BitMatrix bitMatrix = new QRCodeWriter().encode(url, BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT, hints);
            int[] pixels = new int[QR_WIDTH * QR_HEIGHT];
            //下面这里按照二维码的算法，逐个生成二维码的图片，
            //两个for循环是图片横列扫描的结果
            for (int y = 0; y < QR_HEIGHT; y++) {
                for (int x = 0; x < QR_WIDTH; x++) {
                    if (bitMatrix.get(x, y)) {
                        pixels[y * QR_WIDTH + x] = 0xff000000;
                    } else {
                        pixels[y * QR_WIDTH + x] = 0xffffffff;
                    }
                }
            }
            //生成二维码图片的格式，使用ARGB_8888
            Bitmap bitmap = Bitmap.createBitmap(QR_WIDTH, QR_HEIGHT, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, QR_WIDTH, 0, 0, QR_WIDTH, QR_HEIGHT);
            //显示到一个ImageView上面
            iv_qr.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }


    /**
     * 生成带logo的二维码，logo默认为二维码的1/5
     *
     * @param text 需要生成二维码的文字、网址等
     * @param size 需要生成二维码的大小（）
     * @param mBitmap logo文件
     * @return bitmap
     */

    private int IMAGE_HALFWIDTH = 72;


    public void createQRCodeWithLogo(String text, int size, Bitmap mBitmap) {
        try {
            IMAGE_HALFWIDTH = size / 10;
            Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            /*
             * 设置容错级别，默认为ErrorCorrectionLevel.L
             * 因为中间加入logo所以建议你把容错级别调至H,否则可能会出现识别不了
             */
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            BitMatrix bitMatrix = new QRCodeWriter().encode(text,
                    BarcodeFormat.QR_CODE, size, size, hints);

            int width = bitMatrix.getWidth();//矩阵高度
            int height = bitMatrix.getHeight();//矩阵宽度
            int halfW = width / 2;
            int halfH = height / 2;

            Matrix m = new Matrix();
            float sx = (float) 2 * IMAGE_HALFWIDTH / mBitmap.getWidth();
            float sy = (float) 2 * IMAGE_HALFWIDTH
                    / mBitmap.getHeight();
            m.setScale(sx, sy);
            //设置缩放信息
            //将logo图片按martix设置的信息缩放
            mBitmap = Bitmap.createBitmap(mBitmap, 0, 0,
                    mBitmap.getWidth(), mBitmap.getHeight(), m, false);

            int[] pixels = new int[size * size];
            for (int y = 0; y < size; y++) {
                for (int x = 0; x < size; x++) {
                    if (x > halfW - IMAGE_HALFWIDTH && x < halfW + IMAGE_HALFWIDTH
                            && y > halfH - IMAGE_HALFWIDTH
                            && y < halfH + IMAGE_HALFWIDTH) {
                        //该位置用于存放图片信息
                        //记录图片每个像素信息
                        pixels[y * width + x] = mBitmap.getPixel(x - halfW
                                + IMAGE_HALFWIDTH, y - halfH + IMAGE_HALFWIDTH);
                    } else {
                        if (bitMatrix.get(x, y)) {
                            pixels[y * size + x] = 0xff000000;
                        } else {
                            pixels[y * size + x] = 0xffffffff;
                        }
                    }
                }
            }
            Bitmap bitmap = Bitmap.createBitmap(size, size,
                    Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, size, 0, 0, size, size);


            iv_qr.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();

        }
    }


    @Override
    protected void initViews() {


        iv_common_back = findADHViewById(R.id.iv_common_back);
        tv_common_title = findADHViewById(R.id.tv_common_title);
        iv_common_search = findADHViewById(R.id.iv_common_search);
        rl_parent = findADHViewById(R.id.rl_parent);
        iv_common_search.setVisibility(View.GONE);


        /**
         * 中间
         */
        tv_text_qr_get_money = findADHViewById(R.id.tv_text_qr_get_money);
        iv_scan_pay_icon = findADHViewById(R.id.iv_scan_pay_icon);
        tv_get_money_chanel = findADHViewById(R.id.tv_get_money_chanel);
        iv_qr = findADHViewById(R.id.iv_qr);
        tv_get_money_sum = findADHViewById(R.id.tv_get_money_sum);
        tv_confirm_get_paid = findADHViewById(R.id.tv_confirm_get_paid);

        setInitTextToUi();


    }


    private void setInitTextToUi() {


        if (currentTag.equals(ConstTag.PayTag.WX)) {

            tv_text_qr_get_money.setTextColor(Color.parseColor("#E9AC2D"));

            iv_scan_pay_icon.setImageResource(R.drawable.img_scan_wx_qr_icon);

            tv_common_title.setText(this.getString(R.string.wx_scan_qr));


            tv_get_money_chanel.setText(this.getString(R.string.wx_scan_qr_pay_to_me));


            rl_parent.setBackgroundResource(R.drawable.shape_qr_wx_bg);

        } else if (currentTag.equals(ConstTag.PayTag.ALIPAY)) {


            tv_text_qr_get_money.setTextColor(Color.parseColor("#1497FF"));

            iv_scan_pay_icon.setImageResource(R.drawable.img_scan_ali_qr_icon);
            tv_common_title.setText(this.getString(R.string.ali_scan_qr));

            tv_get_money_chanel.setText(this.getString(R.string.ali_scan_qr_pay_to_me));

            rl_parent.setBackgroundResource(R.drawable.shape_qr_ali_bg);
        }

    }


    @Override
    protected void initListener() {

        iv_common_back.setOnClickListener(this);

        tv_confirm_get_paid.setOnClickListener(this);
    }

    @Override
    protected void processIntent() {


        Intent intent = this.getIntent();

        if (null != intent) {

            Bundle bundle = intent.getExtras();

            if (null != bundle) {


                currentTag = bundle.getString(ConstIntent.BundleKEY.SCAN_WAY_TO_GET_MONEY, ConstIntent.BundleValue.DEFAULT_STRING);
                currentMoney = bundle.getFloat(ConstIntent.BundleKEY.SCAN_MONEY);
                currentOrderNo = bundle.getString(ConstIntent.BundleKEY.DELIVER_ORDER_NO, ConstIntent.BundleValue.DEFAULT_STRING);

            }

        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_common_back:


                confirmPayOrNotInBackPress();


                break;

            case R.id.tv_confirm_get_paid:

                confirmPayedOrNot();


                break;
        }
    }


    /**
     * 1.根据支付渠道判断用户是否支付
     * 2.
     */
    private void confirmPayOrNotInBackPress() {


        switch (currentTag) {

            case ConstTag.PayTag.WX:


                doConfirmWxPayInBackPress();

                break;


            case ConstTag.PayTag.ALIPAY:

//                doConfirmAliPay();


                doConfirmAliPayInBackPress();

                break;

        }


    }


    /**
     * 点击返回按钮对alipay进行是否支付验证
     */
    private void doConfirmAliPayInBackPress() {

        if (null == payScanBack) {

            return;
        }


        String jsonString = RequestDriverParams.getAliPayCheckParam(payScanBack.getOut_trade_no(), "", ConstTag.PayTag.channel_os_type, ConstConfig.DRIVER_APP);

        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(
                HttpConst.URL.CHECK_ALI_PAY_RESULT,
                RequestMethod.POST,
                jsonString);


        CallServer.getInstance().add(HttpConst.HTTP_WHAT, request, new HttpSingleResponseListener<String>() {
            @Override
            protected void OnHttpStart(int what) {
                showLoadingDialog();
            }

            @Override
            protected void OnHttpSuccessed(int what, Response<String> response) {
                doPayBackCheckNextInPress(getCheckBack(response.get()));
            }

            @Override
            protected void onHttpFailed(int what, Response<String> response) {

            }

            @Override
            protected void OnHttpFinish(int what) {

                dismissLoadingDialog();

            }
        });


    }

    /**
     * 支付宝支付成功判断订单查询
     *
     * @param payBackCheck
     */
    private void doPayBackCheckNextInPress(boolean payBackCheck) {


        if (payBackCheck) {


            /**
             * 再次调用paid更新.....
             */

            doUpdateAliPaidFromServer();

        } else {


            shwoNotPayDialog(ConstTag.DialogTag.CHECK_PAY, "您还未收到相关费用，确认退出收款界面吗？", "取消", "确认");

        }


    }


    /**
     * wxPay验证
     */
    private void doConfirmWxPayInBackPress() {


        if (payScanBack == null) {


            return;
        }


        String jsonString = RequestDriverParams.getWXPayCheckParam(ConstConfig.DRIVER_APP, ConstTag.PayTag.channel_os_type, payScanBack.getOut_trade_no(), ConstParams.PaySide.RECIVER);

        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(
                HttpConst.URL.CHECK_WX_PAY_RESULT,
                RequestMethod.POST,
                jsonString);


        CallServer.getInstance().add(HttpConst.HTTP_WHAT, request, new HttpSingleResponseListener<String>() {
            @Override
            protected void OnHttpStart(int what) {
                showLoadingDialog();
            }

            @Override
            protected void OnHttpSuccessed(int what, Response<String> response) {
                doWxPayBackCheckNextInPress(getCheckBack(response.get()));
            }

            @Override
            protected void onHttpFailed(int what, Response<String> response) {

            }

            @Override
            protected void OnHttpFinish(int what) {
                dismissLoadDialog();
            }
        });


    }


    /**
     * 微信支付成功判断订单查询
     *
     * @param payBackCheck
     */
    private void doWxPayBackCheckNextInPress(boolean payBackCheck) {


        if (payBackCheck) {
            /**
             * 返回成功直接发送验证
             */
            doSendConfirmCodeAbout();
        } else {
            shwoNotPayDialog(ConstTag.DialogTag.CHECK_PAY, "您还未收到相关费用，确认退出收款界面吗？", "取消", "确认");
        }


    }

    /**
     * 支付提示的界面
     *
     * @param dialogTag
     * @param msg
     * @param cancelMes
     * @param sureMsg
     */
    private void shwoNotPayDialog(int dialogTag, String msg, String cancelMes, String sureMsg) {

        /**
         * 初始化dialog
         *
         * @param dialogTag 当前dialog标记
         * @param msg       传入的信息
         */

        if (doubleButtonDialog == null) {

            doubleButtonDialog = new DoubleButtonDialog(this);
            doubleButtonDialog.setTag(dialogTag);
            doubleButtonDialog.setDoubleDialogCallBack(this);
            doubleButtonDialog.setMessage(msg);
            doubleButtonDialog.setMsgCancel(cancelMes);
            doubleButtonDialog.setMsgSure(sureMsg);
            doubleButtonDialog.setCancelable(false);
            doubleButtonDialog.show();

        } else {

            doubleButtonDialog.setTag(dialogTag);
            doubleButtonDialog.setDoubleDialogCallBack(this);
            doubleButtonDialog.setMessage(msg);
            doubleButtonDialog.setMsgCancel(cancelMes);
            doubleButtonDialog.setMsgSure(sureMsg);
            doubleButtonDialog.setCancelable(false);
            doubleButtonDialog.show();
        }
    }


    private void confirmPayedOrNot() {


        switch (currentTag) {

            case ConstTag.PayTag.WX:


                doConfirmWxPay();

                break;


            case ConstTag.PayTag.ALIPAY:

                doConfirmAliPay();


                break;

        }


    }


    /**
     * wxPay验证
     */
    private void doConfirmWxPay() {


        if (payScanBack == null) {


            return;
        }

        String jsonString = RequestDriverParams.getWXPayCheckParam(ConstConfig.DRIVER_APP, ConstTag.PayTag.channel_os_type, payScanBack.getOut_trade_no(), ConstParams.PaySide.RECIVER);

        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(
                HttpConst.URL.CHECK_WX_PAY_RESULT,
                RequestMethod.POST,
                jsonString);


        CallServer.getInstance().add(HttpConst.HTTP_WHAT, request, new HttpSingleResponseListener<String>() {
            @Override
            protected void OnHttpStart(int what) {
                showLoadDialog();
            }

            @Override
            protected void OnHttpSuccessed(int what, Response<String> response) {
                doWxPayBackCheckNext(getCheckBack(response.get()));
            }

            @Override
            protected void onHttpFailed(int what, Response<String> response) {

            }

            @Override
            protected void OnHttpFinish(int what) {
                dismissLoadDialog();
            }
        });


    }


    /**
     * aliPay验证
     */

    private void doConfirmAliPay() {


        if (null == payScanBack) {

            return;
        }


        String jsonString = RequestDriverParams.getAliPayCheckParam(payScanBack.getOut_trade_no(), "", ConstTag.PayTag.channel_os_type, ConstConfig.DRIVER_APP);

        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(
                HttpConst.URL.CHECK_ALI_PAY_RESULT,
                RequestMethod.POST,
                jsonString);

        CallServer.getInstance().add(HttpConst.HTTP_WHAT, request, new HttpSingleResponseListener<String>() {
            @Override
            protected void OnHttpStart(int what) {
                showLoadDialog();
            }

            @Override
            protected void OnHttpSuccessed(int what, Response<String> response) {
                doPayBackCheckNext(getCheckBack(response.get()));

            }

            @Override
            protected void onHttpFailed(int what, Response<String> response) {

            }

            @Override
            protected void OnHttpFinish(int what) {
                dismissLoadDialog();
            }
        });


    }


    /**
     * 验证支付返回
     *
     * @param back
     * @return
     */
    private Boolean getCheckBack(String back) {

        boolean result = false;

        if (!TextUtil.isEmpty(back)) {

            result = Boolean.valueOf(back);

        }


        return result;

    }


    /**
     * 微信支付成功判断订单查询
     *
     * @param payBackCheck
     */
    private void doWxPayBackCheckNext(boolean payBackCheck) {


        if (payBackCheck) {


            /**
             * 返回成功直接发送验证吗
             */

            doSendConfirmCodeAbout();
        } else {

            ToastUtil.showMsg(getApplicationContext(), "用户还未付款，请稍等");

        }


    }


    /**
     * 支付宝支付成功判断订单查询
     *
     * @param payBackCheck
     */
    private void doPayBackCheckNext(boolean payBackCheck) {


        if (payBackCheck) {


            /**
             * 再次调用paid更新.....
             */

            doUpdateAliPaidFromServer();

        } else {

            ToastUtil.showMsg(getApplicationContext(), "用户还未付款，请稍等");

        }


    }


    /**
     * 拼接参数
     *
     * @return
     */
    private String getPaidValueToPath() {

        String path = HttpConst.QUESTION_MARK + "payer" + HttpConst.RQUAL_SIGN + ConstParams.PaySide.RECIVER + HttpConst.AND + "payment_record_id" + HttpConst.RQUAL_SIGN + Long.valueOf(payScanBack.getOut_trade_no());

        return path;
    }

    /**
     * 验签成功后 立刻更新 paid字段
     */
    private void doUpdateAliPaidFromServer() {

        if (null != currentOrder && null != payScanBack) {


            Request<String> request = RequestPacking.getInstance().getRequestParamForJson(
                    HttpConst.URL.UPDATE_ORDER_PAID + HttpConst.SEPARATOR + currentOrderNo + getPaidValueToPath(),
                    RequestMethod.PUT,
                    null);


            CallServer.getInstance().add(HttpConst.HTTP_WHAT, request, new HttpSingleResponseListener<String>() {
                @Override
                protected void OnHttpStart(int what) {
                    showLoadDialog();
                }

                @Override
                protected void OnHttpSuccessed(int what, Response<String> response) {
                    doSendConfirmCodeAbout();

                }

                @Override
                protected void onHttpFailed(int what, Response<String> response) {

                    ErrorEntity errorEntity = parseSerilizable.getParseToObj(response.get(), ErrorEntity.class);
                    if(null!= errorEntity) {

                        ToastUtil.showMsg(getApplicationContext(), errorEntity.getError_message());
                    }else {

                        ToastUtil.showMsg(getApplicationContext(), ConstError.PARSE_ERROR_MSG);

                    }
                }

                @Override
                protected void OnHttpFinish(int what) {
                    dismissLoadDialog();
                }
            });


        }


    }


    /**
     * 发送验证码操作
     */
    private void doSendConfirmCodeAbout() {


        if (null != currentOrder) {

            sendConfirmCodeToReciver();
        } else {

            getCurrentOrderFromServer(ConstTag.PayTag.SCAN_ORDER_INFO_GET_AFTER);

        }


    }


    private void checkShipperHasCodPay() {


        Fee fee = currentOrder.getFee();

        if (vertifyObject(fee)) {
            List<FeeDetail> details = fee.getDetails();

            if (null != details && details.size() > 0) {


                float showPayAll = 0;
                float paidAll = 0;


                boolean result = false;
                for (int i = 0; i < details.size(); i++) {

                    FeeDetail feeDetail = details.get(i);

                    String associated_addition_service = feeDetail.getAssociated_addition_service();

                    if (!TextUtils.isEmpty(associated_addition_service)
                            && associated_addition_service.equals(ConstTag.AdditionServiceTag.COD)) {

                        result = true;

                        float showpay = Float.valueOf(feeDetail.getSubtotal());
                        float paid = Float.valueOf(feeDetail.getPaid());

                        showPayAll += showpay;
                        paidAll += paid;
                    }
                }


                if (result) {

                    if (showPayAll - paidAll == 0.0) {

                        sendConfirmCodeToReciver();


                    } else {

                        ToastUtil.showMsg(getApplicationContext(), "用户还未付款，请稍等");

                    }

                }


            }


        }


    }


    private void checkReciverPayAll() {

        float shouldPay = getThePayAmount();

        float paied = getThePaidAmout();

        if (paied - shouldPay == 0.0) {
            /**
             * 这里弹出输入验证码的提示，
             */
            sendConfirmCodeToReciver();

        } else {

            ToastUtil.showMsg(getApplicationContext(), "用户还未付款，请稍等");

        }
    }


    /**
     * 给收货人发送验证码
     */
    private void sendConfirmCodeToReciver() {

        String receiverNum = "";

        To to = currentOrder.getTo();

        if (null != to) {

            receiverNum = to.getPhone();

        }

        String jsonString = RequestDriverParams.getConfrimCode(receiverNum, ConstTag.ConfirmCodeTag.ANYWAY);

        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(HttpConst.URL.GET_CONFIRMCODE,
                RequestMethod.POST,
                jsonString);


        CallServer.getInstance().add(HttpConst.HTTP_WHAT, request, new HttpSingleResponseListener<String>() {
            @Override
            protected void OnHttpStart(int what) {
                showLoadDialog();
            }

            @Override
            protected void OnHttpSuccessed(int what, Response<String> response) {
                ToastUtil.showMsg(getApplicationContext(), "已经给收货人发送验证码");

                initEditDialog("验证码", ConstTag.DialogTag.ENTER_RECIVER_CODE);
            }

            @Override
            protected void onHttpFailed(int what, Response<String> response) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(response.get(), ErrorEntity.class);
                if(null!= errorEntity) {

                    ToastUtil.showMsg(getApplicationContext(), errorEntity.getError_message());
                }else {

                    ToastUtil.showMsg(getApplicationContext(), ConstError.PARSE_ERROR_MSG);

                }
            }

            @Override
            protected void OnHttpFinish(int what) {
                dismissLoadDialog();
            }
        });


    }


    /**
     * 输入验证码的dialog
     */
    private void initEditDialog(String title, int tag) {


        if (editDialog == null) {

            editDialog = new EditDialog(this, title, tag);
            editDialog.setDialogClickEditCallBack(this);
            editDialog.setCancelable(false);
            editDialog.show();

        } else {

            editDialog.setDialogClickEditCallBack(this);
            editDialog.setTitle(title);
            editDialog.setTag(tag);
            editDialog.show();

        }


    }

    /**
     * 获取收货人应该之后
     *
     * @return
     */
    private float getThePayAmount() {

        float money = 0;


        Fee fee = null;
        List<FeeDetail> feeDetails = null;

        if (null != currentOrder) {

            fee = currentOrder.getFee();


            if (vertifyObject(fee)) {

                feeDetails = fee.getDetails();


                if (vertifyObject(feeDetails)) {


                    if (feeDetails.size() > 0) {


                        for (int i = 0; i < feeDetails.size(); i++) {


                            FeeDetail feeDetail = feeDetails.get(i);

                            String payer = feeDetail.getPayer();

                            String moneyTemp = feeDetail.getSubtotal();

                            String name = feeDetail.getName();

                            String associated_addition_service = feeDetail.getAssociated_addition_service();

                            /**
                             * 1.区分线上线下
                             * 2.区分是是否收货人付款
                             */

                            if (payer.equals(ConstParams.PaySide.RECIVER)) {

                                if (name.startsWith(ConstTag.Fee.freight)) {
                                    money += Float.valueOf(moneyTemp);


                                    Log.i("PayMoney", "收货人支付：" + name + " 价格：" + money);


                                }
                                if (!TextUtils.isEmpty(associated_addition_service) &&
                                        associated_addition_service.equals(ConstTag.AdditionServiceTag.COD)) {

                                    money += Float.valueOf(moneyTemp);


                                    Log.i("PayMoney", "收货人支付：" + name + " 价格：" + money);
                                }
                            }


                        }

                    }


                }

            }

        }

        return money;

    }


    /**
     * 获取已经收的款项
     *
     * @param
     * @return
     */
    private float getThePaidAmout() {

        float money = 0;


        Fee fee = null;

        List<FeeDetail> feeDetails = null;

        if (null != currentOrder) {

            fee = currentOrder.getFee();


            if (vertifyObject(fee)) {

                feeDetails = fee.getDetails();


                if (vertifyObject(feeDetails)) {


                    if (feeDetails.size() > 0) {


                        for (int i = 0; i < feeDetails.size(); i++) {


                            FeeDetail feeDetail = feeDetails.get(i);

                            String payer = feeDetail.getPayer();

                            String moneyPaid = feeDetail.getPaid();

                            String name = feeDetail.getName();

                            String associated_addition_service = feeDetail.getAssociated_addition_service();

                            /**
                             * 1.区分线上线下
                             * 2.区分是是否收货人付款
                             */

                            if (payer.equals(ConstParams.PaySide.RECIVER)) {

                                if (name.startsWith(ConstTag.Fee.freight)) {

                                    money += Float.valueOf(moneyPaid);


                                    Log.i("PayMoney", "收货人已经支付：" + name + " 价格：" + money);


                                }
                                if (!TextUtils.isEmpty(associated_addition_service) &&
                                        associated_addition_service.equals(ConstTag.AdditionServiceTag.COD)) {

                                    money += Float.valueOf(moneyPaid);


                                    Log.i("PayMoney", "收货人已经支付：" + name + " 价格：" + money);
                                }
                            }


                        }

                    }


                }

            }

        }

        return money;

    }


    private boolean vertifyObject(Object object) {

        boolean result = false;

        if (null != object) {

            result = true;

        }


        return result;

    }

    @Override
    public void dialogEditClickCancel(int tag) {
        if (null != editDialog && editDialog.isShowing()) {


            editDialog.dismiss();
            editDialog = null;

        }


    }

    @Override
    public void dialogEditClickSure(int tag, String text) {

//        enterConfrimCodeToFinishTheOrder(text);

        confirmVertifyCodeFromServer(text);
    }


    private void confirmVertifyCodeFromServer(final String code) {

        To to = currentOrder.getTo();
        String phoneReciver = to.getPhone();

        String param = RequestDriverParams.getVertifyConfrimCode(phoneReciver, code);

        Request<String> request = RequestPacking.getInstance().
                getRequestParamForJson(HttpConst.URL.VERTIFY_CONFIRMCODE
                        , RequestMethod.POST, param);


        CallServer.getInstance().add(HttpConst.HTTP_WHAT, request, new HttpSingleResponseListener<String>() {
            @Override
            protected void OnHttpStart(int what) {
                showLoadDialog();
            }

            @Override
            protected void OnHttpSuccessed(int what, Response<String> response) {
                if (null != editDialog && editDialog.isShowing()) {


                    editDialog.dismiss();
                    editDialog = null;

                }


                enterConfrimCodeToFinishTheOrder(code);
            }

            @Override
            protected void onHttpFailed(int what, Response<String> response) {

            }

            @Override
            protected void OnHttpFinish(int what) {

                dismissLoadDialog();

            }
        });


    }


    /**
     * 发送验证码到服务器来完成订单
     *
     * @param text
     */
    private void enterConfrimCodeToFinishTheOrder(String text) {


        String jsonString = RequestDriverParams.getReciverCodeParam(text);
        Request<String> request = RequestPacking.getInstance().
                getRequestParamForJson(
                        HttpConst.URL.VERTIFY_TO_FINISH + HttpConst.SEPARATOR + currentOrderNo,
                        RequestMethod.POST,
                        jsonString);


        CallServer.getInstance().add(HttpConst.HTTP_WHAT, request, new HttpSingleResponseListener<String>() {
            @Override
            protected void OnHttpStart(int what) {
                showLoadDialog();
            }

            @Override
            protected void OnHttpSuccessed(int what, Response<String> response) {
                ToastUtil.showMsg(getApplicationContext(), "订单完成");



                Bundle bundle = new Bundle();
                bundle.putSerializable(ConstIntent.BundleKEY.DELIVER_ORDER, currentOrder);


                openActivityAndFinishItself(OrderTakeDetailFinishActivity.class,bundle);

//                ScanPayActivity.this.setResult(ConstIntent.ResponseCode.PAY_FINISH_IN_PAY_SCAN);
//
//                ScanPayActivity.this.finish();
            }

            @Override
            protected void onHttpFailed(int what, Response<String> response) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(response.get(), ErrorEntity.class);
                if(null!= errorEntity) {

                    ToastUtil.showMsg(getApplicationContext(), errorEntity.getError_message());
                }else {

                    ToastUtil.showMsg(getApplicationContext(), ConstError.PARSE_ERROR_MSG);

                }
            }

            @Override
            protected void OnHttpFinish(int what) {

                dismissLoadDialog();

            }
        });


    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();


    }


    @Override
    protected void onDestroy() {

        Log.i("scanDestroy", "===========================onDestroy");


        super.onDestroy();


    }


    private void destroyDialog() {

        if (null != doubleButtonDialog && doubleButtonDialog.isShowing()) {
            doubleButtonDialog.dismiss();
            doubleButtonDialog = null;

        }

    }


    @Override
    public void dialogClickCancel(int tag) {

        if (null != doubleButtonDialog && doubleButtonDialog.isShowing()) {
            doubleButtonDialog.dismiss();

        }


    }

    @Override
    public void dialogClickSure(int tag) {

        destroyDialog();

        ScanPayActivity.this.setResult(ConstIntent.ResponseCode.PAY_DESTROY);

        ScanPayActivity.this.finish();

    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void dismissLoadingDialog() {

    }
}
