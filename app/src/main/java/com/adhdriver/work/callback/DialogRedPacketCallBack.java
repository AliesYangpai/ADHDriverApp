package com.adhdriver.work.callback;

import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/7/10.
 * 类描述  首单奖励的点击回调接口
 * 版本
 */

public interface DialogRedPacketCallBack {




    /**
     *
     * @param rl_red_packet_big_bg 大背景图
     * @param rl_carve_up_about  carveUp背景

     * @param tv_start_carve_up  立即瓜分
     * @param rl_rp_show_packet_about 红包袋布局
     * @param tv_start_to_check   立即查看
     */
    public void startCarveUpClick(
            RelativeLayout rl_red_packet_big_bg,
            RelativeLayout rl_carve_up_about,
            TextView tv_start_carve_up,
            RelativeLayout rl_rp_show_packet_about,
            TextView tv_start_to_check
    );



    public void startGetAwardBunds();


}
