package com.adhdriver.work.function;

import com.adhdriver.work.entity.driver.orders.From;
import com.adhdriver.work.entity.driver.orders.PathPoint;
import com.adhdriver.work.entity.driver.orders.To;

import org.feezu.liuli.timeselector.Utils.TextUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/24.
 * 类描述   途径点的功能
 * 版本
 */

public class FunctionPathPoint {


    /**
     * 根据控件特性，这里使用反向遍历
     *
     * @param pathPoints
     * @return
     */
    public String[] getPathPointsArray(List<PathPoint> pathPoints) {

        String[] arry = new String[pathPoints.size()];

        for (int i = pathPoints.size() - 1; i >= 0; i--) {

            arry[pathPoints.size() - (i + 1)] = pathPoints.get(i).getStreet_address();
        }

        return arry;

    }


    /**
     * 整合所有路径
     * @param from
     * @param to
     * @param pathPoints
     * @return
     */
    public String[] getHasPathPointAllWay(From from,To to,List<PathPoint> pathPoints) {




        List<PathPoint> pathPointTemp = new ArrayList<>();


        for (PathPoint pathPoint:pathPoints) {

            pathPointTemp.add(pathPoint);
        }





        PathPoint pathPointStart = new PathPoint();
        pathPointStart.setStreet_address(from.getStreet_address());

        PathPoint pathPointEnd = new PathPoint();
        pathPointEnd.setStreet_address(to.getStreet_address());



        pathPointTemp.add(0,pathPointStart);
        pathPointTemp.add(pathPointEnd);

        String[] arry  =  getPathPointsArray(pathPointTemp);

        return arry;
    }



    public String[] getPathPointsArrayForOverOffice(From from,To to) {

        String[] arry = new String[2];


        arry[0] = to.getStreet_address();
        arry[1] = from.getStreet_address();

        return arry;

    }


    public String[] getNotPathPointWay(From from,To to) {

        String[] arry = new String[2];


        arry[0] = to.getStreet_address();
        arry[1] = from.getStreet_address();

        return arry;

    }








    public int currentDriverPathPointStep(List<PathPoint> pathPoints) {

        int step = 0;

        for (int i = 0; i < pathPoints.size(); i++) {
            PathPoint pathPoint = pathPoints.get(i);
            String time = pathPoint.getArrival_time();
            if(TextUtil.isEmpty(time)) {
                step = i+1;
                break;
            }
        }

        return  step;
    }


    /**
     * 获取获取未到达的 step索引
     * @param pathPoints
     * @return
     */
    public int currentDriverPathPointStepIndex(List<PathPoint> pathPoints) {

        int step = 0;

        for (int i = 0; i < pathPoints.size(); i++) {
            PathPoint pathPoint = pathPoints.get(i);
            String time = pathPoint.getArrival_time();
            if(TextUtil.isEmpty(time)) {
                step = i;
                break;
            }
        }

        return  step;
    }



    /**
     * 获取有多少条途径点未抵达
     * @param pathPoints
     * @return
     */
    public int currentDriverNotArrivePathPointCount(List<PathPoint> pathPoints) {

        int count = 0;

        for (int i = 0; i < pathPoints.size(); i++) {
            PathPoint pathPoint = pathPoints.get(i);
            String time = pathPoint.getArrival_time();
            if(TextUtil.isEmpty(time)) {

                count+=1;
            }
        }

        return  count;
    }





    public boolean isHasPassPoint(List<PathPoint> pathPoints) {

        return null != pathPoints && pathPoints.size()>0;
    }
}
