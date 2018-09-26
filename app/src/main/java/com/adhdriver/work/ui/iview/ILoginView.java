package com.adhdriver.work.ui.iview;

import com.adhdriver.work.entity.driver.token.TokenInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/12/8.
 * 类描述 登陆的Iview
 * 版本
 */

public interface ILoginView extends IBaseView{



    void onDataBackFail(int code, String errorMsg);


    void onDataBackSuccessForLoginJsonObject(String accessToken);

    void onDataBackSuccessForLoginJsonArray(List<TokenInfo> list);

    void onDataBackSuccessForOssConfig(String accessToken);


    void onDataBackSuccessForSetIdentifyInfo(String accessToken);


    void onDataBackSuccessForSetVehicleInfo(String accessToken);


    /**
     * 身份证信息审核授权中
     */
    void onDataBackSuccessForIndentifyInfoAuthorizing();


    /**
     * 身份中信息授权失败
     */
    void onDataBackSuccessForIndentifyInfoAuthorReject(String accessToken);




    /**
     * 进入考试学习界面
     * @param accessToken
     */
    void onDataBackSuccessForDoLearnAndExam(String accessToken);




    /**
     * 车辆信息审核失败，请重新上传
     * @param accessToken
     */
    void onDataBackSuccessForVehicleReject(String accessToken);

    /**
     * 车辆信息正在审核中
     * @param
     */
    void onDataBackSuccessForForVehicleAuthorizing();

    /**
     * 设置推送
     * @param accessToken
     */
    void  onDataBackSuccessForAllAcessGoToMain(String accessToken);





    /**
     * 提示
     * 请输入用户名
     */
    void doVertifyErrorNullUsername();

    /**
     * 请输入合法电话号码
     */
    void doVertifyErrorUnlegalUsername();


    /**
     * 密码必须是8-16位字母数字组合
     */
    void doVertifyErrorPasswordBetween8_16();




    /**
     * 提示请输密码
     */
    void doVertifyErrorNullPassword();


}
