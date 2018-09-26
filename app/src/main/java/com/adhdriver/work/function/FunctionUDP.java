package com.adhdriver.work.function;

import com.adhdriver.work.constant.ConstSp;
import com.adhdriver.work.constant.ConstUdp;
import com.adhdriver.work.entity.UdpContent;
import com.adhdriver.work.entity.UdpEntity;
import com.adhdriver.work.utils.SpUtil;
import com.adhdriver.work.utils.VersionUtil;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2018/1/22.
 * 类描述
 * 版本
 */

public class FunctionUDP {

    public String getUdpConnectJsonString () {

        UdpEntity udpEntity = new UdpEntity();
        UdpContent udpContent = new UdpContent();


        udpContent.setUserId(SpUtil.getInstance().getStringValue(ConstSp.SP_KEY_USER_ID,ConstSp.SP_VALUE.DEFAULT_STRING));
        udpContent.setPhone(SpUtil.getInstance().getStringValue(ConstSp.SP_KEY_SGIN_ACCOUNT,ConstSp.SP_VALUE.DEFAULT_STRING));
        udpContent.setEquipmentNo(VersionUtil.getTheIMEI());
        udpContent.setType(ConstUdp.TYPE_SEND);
        String userContentJson =  new Gson().toJson(udpContent);


        udpEntity.setUserId(SpUtil.getInstance().getStringValue(ConstSp.SP_KEY_USER_ID,ConstSp.SP_VALUE.DEFAULT_STRING));
        udpEntity.setToken(getSHA256StrJava(SpUtil.getInstance().getStringValue(ConstSp.SP_KEY_SGIN_ACCOUNT,ConstSp.SP_VALUE.DEFAULT_STRING)
                +SpUtil.getInstance().getStringValue(ConstSp.SP_KEY_USER_ID,ConstSp.SP_VALUE.DEFAULT_STRING)));

        udpEntity.setContent(userContentJson);
        udpEntity.setType(ConstUdp.TYPE_SEND);

        String userEntityJson =  new Gson().toJson(udpEntity);


        return userEntityJson;

    }


    public  String getSHA256StrJava(String str){
        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }


    /**
     * 将byte转为16进制
     * @param bytes
     * @return
     */
    private  String byte2Hex(byte[] bytes){
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i=0;i<bytes.length;i++){
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length()==1){
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }


}
