package com.adhdriver.work.function;

import com.adhdriver.work.constant.ConstTag;
import com.adhdriver.work.entity.driver.orders.Fee;
import com.adhdriver.work.entity.driver.orders.FeeDetail;

import org.feezu.liuli.timeselector.Utils.TextUtil;

import java.util.List;

/**
 * Created by Administrator on 2018/1/24.
 * 类描述
 * 版本
 */

public class FunctionOrder {


    /**
     * 获取代收货款的标签
     * @param fee
     * @return
     */
    public String getCod(Fee fee) {

        String  cod = "";

        if (null != fee) {

            List<FeeDetail> details = fee.getDetails();


            if(null == details) {

                return cod;
            }
            for (FeeDetail feeDetail : details) {
                String assAddService = feeDetail.getAssociated_addition_service();
                if (!TextUtil.isEmpty(assAddService)) {
                    cod = assAddService;
                    break;
                }
            }
        }
        return cod;
    }
}
