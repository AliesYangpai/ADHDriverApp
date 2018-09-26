package com.adhdriver.work.function;

import com.adhdriver.work.R;
import com.adhdriver.work.constant.ConstHz;
import com.adhdriver.work.constant.ConstSign;
import com.adhdriver.work.entity.driver.orders.Goods;
import com.adhdriver.work.utils.StringUtil;

import org.feezu.liuli.timeselector.Utils.TextUtil;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 2017/12/5.
 * 类描述
 * 版本
 */

public class FunctionGoods {


    /**
     * 获取货物体积
     * @param goods
     * @return
     */
    public String getGoodsVolume(Goods goods) {
        String str = ConstHz.NO_VOLUME;

        float height = Float.valueOf(goods.getHeight());
        float width = Float.valueOf(goods.getWidth());
        float length = Float.valueOf(goods.getLength());


        float volume = Float.parseFloat(new DecimalFormat(ConstSign.DECIMAL_2).format(height * width * length));

        if (volume > 0) {

            str = String.valueOf(volume) + ConstSign.VOLUME_UNIT;

        }

        return str;

    }


    /**
     * 货物重量 吨
     *
     * @param goods
     * @return
     */
    public String getTheWeightTonOrdefaultText(Goods goods) {

        String str = ConstHz.NO_WEIGHT;


        String weight = goods.getWeight();


        if (!TextUtil.isEmpty(weight)) {


            Float theWeight = Float.valueOf(weight);

            if (theWeight > 0) {

                str = String.valueOf(theWeight);

                str = StringUtil.valueKgToTun(str) + ConstSign.WEIGHT_TON_UNIT;

            }
        }


        return str;

    }
}
