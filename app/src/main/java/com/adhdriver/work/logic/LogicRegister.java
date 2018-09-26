package com.adhdriver.work.logic;

import com.adhdriver.work.constant.ConstTag;

import org.feezu.liuli.timeselector.Utils.TextUtil;

/**
 * Created by Administrator on 2017/12/11.
 * 类描述  注册的逻辑
 * 版本
 */

public class LogicRegister {


    /**
     * 无身份证号 （没有填写用户信息）
     * @param identifyNo
     * @return
     */
    public boolean isNoIdentifyNo(String identifyNo) {

        return TextUtil.isEmpty(identifyNo);

    }






    /**
     * 认证通过 （用户信息认证通过）
     * @param certification
     * @return
     */
    public boolean isCertificationApproved(String certification) {

        return certification.equals(ConstTag.CertificationStatusTag.APPROVED);
    }

    /**
     * 认证中 （用户信息认证中）
     * @param certification
     * @return
     */
    public boolean isCertificationAuthorizing(String certification) {

        return certification.equals(ConstTag.CertificationStatusTag.AUTHORIZING);
    }

    /**
     * 认证失败  （用户信息认证失败）
     * @param certification
     * @return
     */
    public boolean isCertificationRejected(String certification) {

        return certification.equals(ConstTag.CertificationStatusTag.REJECTED);
    }




    /**
     * 考试通过
     * @param isPass
     * @return
     */
    public boolean isExamPass(boolean isPass) {

        return isPass;
    }




}
