package com.adhdriver.work.location.amap;

import com.amap.api.navi.AMapNaviViewOptions;

/**
 * Created by Administrator on 2018/1/3.
 * 类描述  导航界面View的配置项
 * 版本
 */

public class AmapNavigationViewOpition {


    public AmapNavigationViewOpition() {
    }


    /**
     * 原来的
     */

    public AMapNaviViewOptions getNaviViewOptions() {


        AMapNaviViewOptions option = new AMapNaviViewOptions();
        option.setLayoutVisible(false);
        option.setTilt(0);
        option.setTrafficBarEnabled(false); //光柱不显示
        option.setCrossDisplayShow(false);    //设置不显示路口放大
        option.setScreenAlwaysBright(true);//设置导航状态下屏幕是否一直开启。


        return option;
    }
}
