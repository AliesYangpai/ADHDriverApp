package com.adhdriver.work.ui.activity.driver;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.constant.ConstEvent;
import com.adhdriver.work.constant.ConstIntent;
import com.adhdriver.work.entity.EventEntity;
import com.adhdriver.work.presenter.driver.PresenterDriverVehicleNoSetting;
import com.adhdriver.work.ui.activity.BaseActivity;
import com.adhdriver.work.ui.iview.driver.IVehicleNoSettiingView;
import com.adhdriver.work.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class VehicleNoSettingActivity extends BaseActivity<IVehicleNoSettiingView,PresenterDriverVehicleNoSetting> implements
        IVehicleNoSettiingView,
        View.OnClickListener,
        KeyboardView.OnKeyboardActionListener,
        View.OnTouchListener,
        View.OnFocusChangeListener{


    private PresenterDriverVehicleNoSetting presenterDriverVehicleNoSetting;



    /**
     * title
     */


    private ImageView iv_common_back;
    private TextView tv_common_title;
    private ImageView iv_common_search;


    private EditText editProvince;
    private EditText edit1;
    private EditText edit2;
    private EditText edit3;
    private EditText edit4;
    private EditText edit5;
    private EditText edit6;

    private EditText[] mArray;
    private Context mContext;
    private KeyboardView mKeyboardView;
    /**
     * 省份简称键盘
     */
    private Keyboard province_keyboard;
    /**
     * 数字与大写字母键盘
     */
    private Keyboard number_keyboar;
    private EditText currentEdit;
    private int currentIndex;


    /**
     * 数据相关
     * @param savedInstanceState
     */

    private int currentSetVehicleType;  //用来区分 设置车牌号来自于 注册时，还是来自于 添加车辆


    private void addTextChangedListern() {

        for (int i = 0; i < mArray.length; i++) {
            final int j = i;
            mArray[j].addTextChangedListener(new TextWatcher() {
                private CharSequence temp;
                private int sStart;
                private int sEnd;

                @Override
                public void onTextChanged(CharSequence s, int start,
                                          int before, int count) {
                    temp = s;
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start,
                                              int count, int after) {


                }

                @Override
                public void afterTextChanged(Editable s) {
                    sStart = mArray[j].getSelectionStart();
                    sEnd = mArray[j].getSelectionEnd();

                    if (temp.length() == 1 && j < mArray.length - 1) {
                        mArray[j + 1].setFocusable(true);
                        mArray[j + 1].setFocusableInTouchMode(true);
                        mArray[j + 1].requestFocus();

                    }
                    if (temp.length() > 1) {

                        s.delete(0, 1);
                        int tSelection = sStart;
                        mArray[j].setText(s);
                        mArray[j].setSelection(tSelection);
                        mArray[j].setFocusable(true);

                    }
                }
            });
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_no_setting);
    }

    @Override
    protected PresenterDriverVehicleNoSetting creatPresenter() {
        if(null == presenterDriverVehicleNoSetting) {
            presenterDriverVehicleNoSetting = new PresenterDriverVehicleNoSetting(this);
        }
        return presenterDriverVehicleNoSetting;
    }

    @Override
    protected void initViews() {


        /**
         * titile
         *
         */
        iv_common_back = findADHViewById(R.id.iv_common_back);
        tv_common_title = findADHViewById(R.id.tv_common_title);
        iv_common_search = findADHViewById(R.id.iv_common_search);
        iv_common_search.setVisibility(View.VISIBLE);
        tv_common_title.setText(this.getString(R.string.setting_vehicle_no));
        iv_common_search.setImageResource(R.drawable.img_sure);

        /**
         * 键盘相关
         */


        mContext = this;
        province_keyboard = new Keyboard(mContext, R.xml.province_abbreviation);
        number_keyboar = new Keyboard(mContext, R.xml.number_or_letters);
        mKeyboardView = (KeyboardView)
                findViewById(R.id.keyboard_view);
        mKeyboardView.setKeyboard(province_keyboard);
        mKeyboardView.setEnabled(true);
        mKeyboardView.setPreviewEnabled(false);


        /**
         * 编辑按钮相关
         */
        editProvince = (EditText) findViewById(R.id.edit_province);
        edit1 = (EditText) findViewById(R.id.edit_1);
        edit2 = (EditText) findViewById(R.id.edit_2);
        edit3 = (EditText) findViewById(R.id.edit_3);
        edit4 = (EditText) findViewById(R.id.edit_4);
        edit5 = (EditText) findViewById(R.id.edit_5);
        edit6 = (EditText) findViewById(R.id.edit_6);
        mArray = new EditText[]{editProvince, edit1,
                edit2, edit3, edit4, edit5, edit6};


    }

    @Override
    protected void initListener() {

        /**
         * title
         */
        iv_common_back.setOnClickListener(this);
        iv_common_search.setOnClickListener(this);

        /**
         * 键盘相关
         */
        mKeyboardView.setOnKeyboardActionListener(this);

        /**
         * 键盘按钮相关
         */
        editProvince.setOnFocusChangeListener(this);
        edit1.setOnFocusChangeListener(this);
        edit2.setOnFocusChangeListener(this);
        edit3.setOnFocusChangeListener(this);
        edit4.setOnFocusChangeListener(this);
        edit5.setOnFocusChangeListener(this);
        edit6.setOnFocusChangeListener(this);

        editProvince.setOnTouchListener(this);
        edit1.setOnTouchListener(this);
        edit2.setOnTouchListener(this);
        edit3.setOnTouchListener(this);
        edit4.setOnTouchListener(this);
        edit5.setOnTouchListener(this);
        edit6.setOnTouchListener(this);


        addTextChangedListern();

        editProvince.setFocusable(true);
        editProvince.requestFocus();

    }

    @Override
    protected void processIntent() {

        Intent intent = this.getIntent();

        if(null != intent) {

            Bundle extras = intent.getExtras();

            if(null != extras) {

                currentSetVehicleType = extras.getInt(ConstIntent.BundleKEY.SET_VEHICLE_NO);

            }
        }

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_common_back:
                this.finish();
                break;


            case R.id.iv_common_search:

                getCarNumBackOrNot(getVehicleNo());
                break;

        }
    }





    private void getCarNumBackOrNot(String num) {



        if(TextUtils.isEmpty(num)) {


            ToastUtil.showMsg(getApplicationContext(),getString(R.string.enter_legal_vehicle_num));

            return;
        }


        if(num.length() == 7) {


            postEventToSetCar(num);


        }else {

            ToastUtil.showMsg(getApplicationContext(),getString(R.string.please_finish_vehicle_num));

        }


    }




    private void  postEventToSetCar(String num) {


        EventEntity eventEntity = new EventEntity();
        eventEntity.setNotifyMsg(num);
        switch (currentSetVehicleType) {

            case ConstIntent.BundleValue.REG_TO_SET_VEHICLE_NO:

                eventEntity.setNotifyTag(ConstEvent.REG_TO_SET_VECHICLE_NUM);

                break;

            case ConstIntent.BundleValue.ADD_TO_SET_VEHICLE_NO:



                eventEntity.setNotifyTag(ConstEvent.ADD_SET_VECHICLE_NUM);

                break;

        }






        EventBus.getDefault().post(eventEntity);
        this.finish();
    }

    /**
     * 获取车辆号码
     * @return
     */
    private String getVehicleNo() {


        String num = "";

        num = editProvince.getText().toString().trim()+edit1.getText().toString().trim() +edit2.getText().toString().trim()+edit3.getText().toString().trim()+
                edit4.getText().toString().trim()+edit5.getText().toString().trim()+edit6.getText().toString().trim();



        return num;

    }






    @Override
    public void onPress(int primaryCode) {

    }

    @Override
    public void onRelease(int primaryCode) {

    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {

        Editable editable = currentEdit.getText();
        int start = currentEdit.getSelectionStart();
        if (primaryCode == -1) {//
            //隐藏键盘
            if (isShow()) {
                hideKeyboard();
            }


        } else if (primaryCode == -3) {// 回退

            Log.i("Tag1111", "onKey currentIndex= " + currentIndex);
            if (editable != null) {
                //没有输入内容时软键盘重置为省份简称软键盘
                editable.clear();

                if (currentIndex > 0) {
                    currentIndex = currentIndex - 1;
//                        Log.i("Tag1111", "onKeyChange currentIndex= " + currentIndex);
                    mArray[currentIndex].setFocusable(true);
                    mArray[currentIndex].requestFocus();

                }


            }

        } else {
            editable.insert(start, Character.toString((char) primaryCode));
            if (currentIndex == 6 && mArray[6].getText().length() > 0) {


                hideKeyboard();
            }


        }

    }

    @Override
    public void onText(CharSequence text) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }

    /**
     * 指定切换软键盘 isnumber false表示要切换为省份简称软键盘 true表示要切换为数字软键盘
     */

    public void changeKeyboard(boolean isnumber) {
        if (isnumber) {
            mKeyboardView.setKeyboard(number_keyboar);
        } else {
            mKeyboardView.setKeyboard(province_keyboard);
        }
    }


    /**
     * 软键盘展示
     */
    public void showKeyboard() {
        int visibility = mKeyboardView.getVisibility();
        if (visibility == View.GONE || visibility == View.INVISIBLE) {
            mKeyboardView.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 软键盘隐藏
     */
    public void hideKeyboard() {
        int visibility = mKeyboardView.getVisibility();
        if (visibility == View.VISIBLE) {
            mKeyboardView.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 禁掉系统软键盘
     */
    public void hideSoftInputMethod() {

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        int currentVersion = Build.VERSION.SDK_INT;
        String methodName = null;
        if (currentVersion >= 16) {
            // 4.2
            methodName = "setShowSoftInputOnFocus";
        } else if (currentVersion >= 14) {
            // 4.0
            methodName = "setSoftInputShownOnFocus";
        }
        if (methodName == null) {
            currentEdit.setInputType(InputType.TYPE_NULL);
        } else {
            Class<EditText> cls = EditText.class;
            Method setShowSoftInputOnFocus;
            try {
                setShowSoftInputOnFocus = cls.getMethod(methodName,
                        boolean.class);
                setShowSoftInputOnFocus.setAccessible(true);
                setShowSoftInputOnFocus.invoke(currentEdit, false);
            } catch (NoSuchMethodException e) {
                currentEdit.setInputType(InputType.TYPE_NULL);
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 软键盘展示状态
     */
    public boolean isShow() {
        return mKeyboardView.getVisibility() == View.VISIBLE;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isShow()) {
                hideKeyboard();
            } else {
                finish();
            }
        }
        return false;
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        if (hasFocus) {

            currentEdit = (EditText) v;

            for (int i = 0; i < mArray.length; i++) {

                EditText temp = mArray[i];
                if (temp == (EditText) v) {
                    currentIndex = i;
                }
            }

            if (currentIndex == 0) {
                changeKeyboard(false);
            } else {
                changeKeyboard(true);
            }
            hideSoftInputMethod();

            if (!isShow()) {
                showKeyboard();
            }


        }


    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {


        return super.onTouchEvent(event);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        if (!isShow()) {
            showKeyboard();

        }
        return false;

    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void dismissLoadingDialog() {

    }
}
