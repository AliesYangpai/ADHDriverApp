package com.adhdriver.work.ui.adapter;

import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.constant.ConstHz;
import com.adhdriver.work.constant.ConstParams;
import com.adhdriver.work.constant.ConstPublishHitchhik;
import com.adhdriver.work.constant.ConstSign;
import com.adhdriver.work.constant.ConstTag;
import com.adhdriver.work.entity.driver.orders.City;
import com.adhdriver.work.entity.driver.orders.County;
import com.adhdriver.work.entity.driver.orders.Divided;
import com.adhdriver.work.entity.driver.orders.Fee;
import com.adhdriver.work.entity.driver.orders.FeeDetail;
import com.adhdriver.work.entity.driver.orders.FixedPriceFee;
import com.adhdriver.work.entity.driver.orders.From;
import com.adhdriver.work.entity.driver.orders.Goods;
import com.adhdriver.work.entity.driver.orders.Order;
import com.adhdriver.work.entity.driver.orders.To;
import com.adhdriver.work.entity.driver.orders.Vehicle;
import com.adhdriver.work.entity.driver.temp.PublishRoute;
import com.adhdriver.work.utils.DoubleUtil;
import com.adhdriver.work.utils.ImgUtil;
import com.adhdriver.work.utils.VehicleUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import org.feezu.liuli.timeselector.Utils.TextUtil;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Administrator on 2017/11/20.
 * 类描述  已接单的adapter
 * 版本
 */

public class PublishRoutesAdapter extends BaseQuickAdapter<PublishRoute, BaseViewHolder> {


    public PublishRoutesAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, PublishRoute publishRoute) {


        helper.setText(R.id.tv_publish_time, publishRoute.getDepart_time())
                .setText(R.id.tv_publish_time, publishRoute.getDepart_address())
                .setText(R.id.tv_publish_end_place, publishRoute.getDestination_address())
                .addOnClickListener(R.id.tv_cancel_publish);


        TextView tv_publish_state = helper.getView(R.id.tv_publish_state);
        RelativeLayout rl_bottom_layout = helper.getView(R.id.rl_bottom_layout);


        String status = publishRoute.getStatus();


        switch (status) {

            case ConstPublishHitchhik.PENDING:

                rl_bottom_layout.setVisibility(View.VISIBLE);
                tv_publish_state.setText(mContext.getString(R.string.hitchhike_publish_pendding));
                tv_publish_state.setBackgroundColor(Color.parseColor("#ff9402"));

                break;


            case ConstPublishHitchhik.SUCCEED:
                rl_bottom_layout.setVisibility(View.GONE);
                tv_publish_state.setText(mContext.getString(R.string.hitchhike_publish_succeed));
                tv_publish_state.setBackgroundColor(Color.parseColor("#68bcff"));


                break;

            case ConstPublishHitchhik.FAILED:
                rl_bottom_layout.setVisibility(View.GONE);
                tv_publish_state.setText(mContext.getString(R.string.hitchhike_publish_failed));
                tv_publish_state.setBackgroundColor(Color.parseColor("#7c7c7c"));
                break;

            case ConstPublishHitchhik.CANCELED:
                rl_bottom_layout.setVisibility(View.GONE);
                tv_publish_state.setText(mContext.getString(R.string.hitchhike_publish_canceled));
                tv_publish_state.setBackgroundColor(Color.parseColor("#7c7c7c"));

                break;
            case ConstPublishHitchhik.COMPLETED:
                rl_bottom_layout.setVisibility(View.GONE);
                tv_publish_state.setText(mContext.getString(R.string.hitchhike_publish_complete));
                tv_publish_state.setBackgroundColor(Color.parseColor("#68bcff"));
                break;

        }


    }





}
