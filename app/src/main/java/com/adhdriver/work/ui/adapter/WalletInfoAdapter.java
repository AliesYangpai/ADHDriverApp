package com.adhdriver.work.ui.adapter;

import android.support.annotation.LayoutRes;

import com.adhdriver.work.R;
import com.adhdriver.work.constant.ConstLocalData;
import com.adhdriver.work.entity.driver.wallet.FundsDetial;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by Administrator on 2017/11/20.
 * 类描述  跨城未接单的adapter
 * 版本
 */

public class WalletInfoAdapter extends BaseQuickAdapter<FundsDetial, BaseViewHolder> {


    public WalletInfoAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, FundsDetial fundsDetial) {





        helper.setText(R.id.tv_deal_order_no, fundsDetial.getFund_flow_no())
                .setText(R.id.tv_deal_time, fundsDetial.getCreate_time())
                .setText(R.id.tv_pay_type, fundsDetial.getFlow_direction())
                .setText(R.id.tv_flow_comment, fundsDetial.getFlow_comment())
                .setText(R.id.tv_income_money,getMoneyByMode(fundsDetial));


    }


    /**
     * 获得money
     * @param fundsDetial
     * @return
     */
    private String getMoneyByMode(FundsDetial fundsDetial) {


        String money = "";
        switch (fundsDetial.getPay_mode()) {

            case ConstLocalData.WITHDRAWALS:

                money = fundsDetial.getOutlay();
                break;

            default:

                money = fundsDetial.getIncome();
                break;
        }
        return money;
    }



}
