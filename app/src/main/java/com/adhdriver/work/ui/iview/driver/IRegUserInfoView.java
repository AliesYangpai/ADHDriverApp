package com.adhdriver.work.ui.iview.driver;

import com.adhdriver.work.entity.OssConfig;
import com.adhdriver.work.ui.iview.IBaseView;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;

/**
 * Created by Administrator on 2017/12/12.
 * 类描述  注册司机身份信息的ivew
 * 版本
 */

public interface IRegUserInfoView extends IBaseView {

    void onDataBackFail(int code, String errorMsg);
    /**
     * 输入司机姓名
     */
    void doVertifyErrorNullDriverName();

    /**
     * 输入司机身份证号
     */
    void doVertifyErrorNullIdentifyNo();


    /**
     * 输入司机合法身份证号
     */
    void doVertifyErrorUnLegalIdentifyNo();


    /**
     * 输入司机身份证正面
     */
    void doVertifyErrorNullIdentifyFrontPic();

    /**
     * 输入司机身份证反面
     */
    void doVertifyErrorNullIdentifyBackPic();



    void onDataBackSuccessForOssConfig(String accessToken);

    /**
     * 提交用户信息成功
     * @param accessToken
     */
    void onDataBackSuccessForCompleteUserInfo(String accessToken);


    /**
     * 进入成功设置车辆信息的界面
     * @param accessToken
     */
    void onDataBackSuccessForSetVehicleInfo(String accessToken);


    /**
     * 考试也是通过的  进入到主界面了
     */
    void onDataBackSuccessForExamPass();


    /**
     * 进入到考试界面
     * @param accessToken
     */
    void onDataBackSuccessForDoLearnAndExam(String accessToken);




    /**
     * 上传中....
     * @param request
     * @param currentSize
     * @param totalSize
     */
    void ossOnProgress(PutObjectRequest request, long currentSize, long totalSize);


    /**
     * 界面显示身份证 正面
     * @param url
     * @param drawableId
     */
    void ossOnSuccessLoadIdFrontPicToUi(String url, int drawableId);


    /**
     * 界面显示身份证反面
     * @param url
     * @param drawableId
     */
    void ossOnSuccessLoadIdBackPicToUi(String url, int drawableId);

    /**
     * 图片上传失败
     */
    void ossOnFailure();


    void doPermissionCheck();

    /**
     * 关闭权限时候的打开提醒
     */
    void doShowPermissionAlert();

    void doShowPhotoPopWindow();


}
