package com.adhdriver.work.entity.driver.wallet;

import com.adhdriver.work.entity.driver.pay.aliAbout.AliAuthInfo;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/6.
 * 类描述  我的钱包实体类
 * 版本
 */

public class Wallet implements Serializable {


    private int id;//自增id
    private String wallet_id;//钱包id
    private String user_id;//用户id
    private String organization_id;//组织id
    private String balance;//余额
    private String bank_card_no;//银行卡号
    private boolean has_pay_pwd;//


    private AliAuthInfo alipay_auth_info;
    private WxAuthInfo wx_pay_auth_info;




    private String deposit_amount;//押金（新加入）







   public Wallet() {
    }


    public String getWallet_id() {
        return wallet_id;
    }

    public void setWallet_id(String wallet_id) {
        this.wallet_id = wallet_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(String organization_id) {
        this.organization_id = organization_id;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getBank_card_no() {
        return bank_card_no;
    }

    public void setBank_card_no(String bank_card_no) {
        this.bank_card_no = bank_card_no;
    }


    public AliAuthInfo getAlipay_auth_info() {
        return alipay_auth_info;
    }

    public void setAlipay_auth_info(AliAuthInfo alipay_auth_info) {
        this.alipay_auth_info = alipay_auth_info;
    }

    public boolean isHas_pay_pwd() {
        return has_pay_pwd;
    }

    public void setHas_pay_pwd(boolean has_pay_pwd) {
        this.has_pay_pwd = has_pay_pwd;
    }


    public WxAuthInfo getWx_pay_auth_info() {
        return wx_pay_auth_info;
    }

    public void setWx_pay_auth_info(WxAuthInfo wx_pay_auth_info) {
        this.wx_pay_auth_info = wx_pay_auth_info;
    }


    public String getDeposit_amount() {
        return deposit_amount;
    }

    public void setDeposit_amount(String deposit_amount) {
        this.deposit_amount = deposit_amount;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id=" + id +
                ", wallet_id='" + wallet_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", organization_id='" + organization_id + '\'' +
                ", balance='" + balance + '\'' +
                ", bank_card_no='" + bank_card_no + '\'' +
                ", has_pay_pwd=" + has_pay_pwd +
                ", alipay_auth_info=" + alipay_auth_info +
                ", wx_pay_auth_info=" + wx_pay_auth_info +
                ", deposit_amount='" + deposit_amount + '\'' +
                '}';
    }
}
