package com.adhdriver.work.udp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.adhdriver.work.callback.udp.ADHUdpCallBackListener;
import com.adhdriver.work.constant.ConstOss;
import com.adhdriver.work.constant.ConstSp;
import com.adhdriver.work.constant.ConstUdp;
import com.adhdriver.work.entity.UdpEntity;
import com.adhdriver.work.test.TestMessageUser;
import com.adhdriver.work.utils.SpUtil;
import com.adhdriver.work.verification.VertifyNotNull;
import com.google.gson.Gson;
import com.google.zxing.common.StringUtils;

import org.feezu.liuli.timeselector.Utils.TextUtil;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by Administrator on 2018/1/22.
 * 类描述
 * 版本
 */

public class ADHUdpHelper {


    private DatagramSocket socket;

    private InetAddress udpServerAddress;

    private int port;
    private String host;


    private ADHUdpCallBackListener adhUdpCallBackListener;

    public DatagramSocket getSocket() {
        return socket;
    }

    private Handler uiHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {

                case ConstUdp.HANDLER_CONNECT_SUCCESS: //连接成功


                    if (null != adhUdpCallBackListener) {
                        adhUdpCallBackListener.onConnect();
                    }
                    break;

                case ConstUdp.HANDLER_SEND_DATA_SUCCESS://数据发送成功

                    if (null != adhUdpCallBackListener) {


                        String data = getStringData(msg, ConstUdp.BUNDLE_UDP_SEND);


                        adhUdpCallBackListener.onSendData(data);
                    }
                    break;


                case ConstUdp.HANDLER_HEART_SUCCESS://心跳发送成功

                    if (null != adhUdpCallBackListener) {


                        String data = getStringData(msg, ConstUdp.BUNDLE_UDP_HEART);
                        adhUdpCallBackListener.onHeart(data);
                    }
                    break;


                case ConstUdp.HANDLER_RECEIVE_DATA_SUCCESS: //接收数据成功

                    if (null != adhUdpCallBackListener) {

                        String data = getStringData(msg, ConstUdp.BUNDLE_UDP_RECEIVE);
                        adhUdpCallBackListener.onReceiveData(data);
                    }
                    break;


                case ConstUdp.HANDLER_CLOSE_SUCCESS: //关闭成功

                    if (null != adhUdpCallBackListener) {
                        adhUdpCallBackListener.onClose();
                    }
                    break;


                case ConstUdp.HANDLER_RECONNECT:  //重新连入socket

                    if (null != adhUdpCallBackListener) {
                        adhUdpCallBackListener.onReConnect();

                    }
                    break;

            }


        }

    };


    /**
     * 获取String data
     *
     * @param msg
     * @param bundleTag
     * @return
     */
    private String getStringData(Message msg, String bundleTag) {

        Bundle bundle = msg.getData();

        String result = "";
        if (null != bundle) {

            result = bundle.getString(bundleTag);
        }

        return result;
    }

    public void setAdhUdpCallBackListener(ADHUdpCallBackListener adhUdpCallBackListener) {
        this.adhUdpCallBackListener = adhUdpCallBackListener;
    }

    public ADHUdpHelper(int port, String host) {
        this.port = port;
        this.host = host;


        try {
            socket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }


    /**
     * 启动链接sokect方法
     *
     * @param udpJsonUserEntity
     */
    public synchronized void doSocketConnect(final String udpJsonUserEntity) {


        new Thread(new Runnable() {
            @Override
            public void run() {


                InetAddress serverAddress = null;
                try {
                    serverAddress = InetAddress.getByName(host);
                    udpServerAddress = serverAddress;
                    if (null != socket) {
                        if (!socket.isConnected()) {
                            socket.connect(serverAddress, port);
                        }


                        if (socket.isConnected()) {
                            uiHandler.sendEmptyMessage(ConstUdp.HANDLER_CONNECT_SUCCESS);

                            byte data[] = udpJsonUserEntity.getBytes();  //把传输内容分解成字节
                            //创建一个DatagramPacket对象，并指定要讲这个数据包发送到网络当中的哪个、地址，以及端口号
                            DatagramPacket packet = new DatagramPacket(data, data.length, serverAddress, port);
                            //调用socket对象的send方法，发送数据
                            socket.send(packet);


                            Message message = uiHandler.obtainMessage();
                            message.what = ConstUdp.HANDLER_SEND_DATA_SUCCESS;
                            Bundle bundle = new Bundle();
                            bundle.putString(ConstUdp.BUNDLE_UDP_SEND, udpJsonUserEntity);
                            message.setData(bundle);

                            uiHandler.sendMessage(message);

                        }

                    }else {

                        socket = new DatagramSocket(port);
                        if (!socket.isConnected()) {
                            socket.connect(serverAddress, port);
                        }


                        if (socket.isConnected()) {
                            uiHandler.sendEmptyMessage(ConstUdp.HANDLER_CONNECT_SUCCESS);

                            byte data[] = udpJsonUserEntity.getBytes();  //把传输内容分解成字节
                            //创建一个DatagramPacket对象，并指定要讲这个数据包发送到网络当中的哪个、地址，以及端口号
                            DatagramPacket packet = new DatagramPacket(data, data.length, serverAddress, port);
                            //调用socket对象的send方法，发送数据
                            socket.send(packet);


                            Message message = uiHandler.obtainMessage();
                            message.what = ConstUdp.HANDLER_SEND_DATA_SUCCESS;
                            Bundle bundle = new Bundle();
                            bundle.putString(ConstUdp.BUNDLE_UDP_SEND, udpJsonUserEntity);
                            message.setData(bundle);

                            uiHandler.sendMessage(message);

                        }

                    }
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();


    }


    /**
     * 关闭socket的方法
     */
    public synchronized void doSocketClose() {



        if (null != socket && socket.isConnected()) {
            socket.close();
            socket = null;




            uiHandler.sendEmptyMessage(ConstUdp.HANDLER_CLOSE_SUCCESS);
        }
    }





    /**
     * 保持心跳的方法心跳方法
     *
     * @param userId
     */
    public synchronized void doSocketHeart(final String userId) {




        new Thread(new Runnable() {
            @Override
            public void run() {

                try {



                        if (!TextUtil.isEmpty(userId) && null != socket) {
                            byte data[] = userId.getBytes();  //把传输内容分解成字节
                            //创建一个DatagramPacket对象，并指定要讲这个数据包发送到网络当中的哪个、地址，以及端口号
                            DatagramPacket packet = new DatagramPacket(data, data.length, udpServerAddress, port);
                            //调用socket对象的send方法，发送数据
                            socket.send(packet);

                            Message message = uiHandler.obtainMessage();
                            message.what = ConstUdp.HANDLER_HEART_SUCCESS;
                            Bundle bundle = new Bundle();
                            bundle.putString(ConstUdp.BUNDLE_UDP_HEART, userId);
                            message.setData(bundle);

                            uiHandler.sendMessage(message);
                        }



                } catch (IOException e) {

                    e.printStackTrace();
                    if(SpUtil.getInstance().getBoolenValue(ConstSp.SP_KEY_IS_LOGIN_OR_NOT,ConstSp.SP_VALUE.IS_LOGIN_DEFAULT)) {

                        Message message = uiHandler.obtainMessage();
                        message.what = ConstUdp.HANDLER_RECONNECT;
                        uiHandler.sendEmptyMessage(message.what);


                    }
                }

            }
        }).start();



















    }


    /**
     * 接受数据的方法
     */
    public synchronized void doSocketReceiveData() {



        new Thread(new Runnable() {
            @Override
            public void run() {

                Log.i("UDP", "执行接受数据前 ");

                String result = "";
                byte[] data = null;
                DatagramPacket packet = null;

                try {
                    data = new byte[1024];
                    packet = new DatagramPacket(data, data.length);
                    if (null != socket && socket.isConnected()) {
                        socket.receive(packet);
                        result = new String(packet.getData(), packet.getOffset(), packet.getLength());
                        Message message = uiHandler.obtainMessage();
                        message.what = ConstUdp.HANDLER_RECEIVE_DATA_SUCCESS;
                        Bundle bundle = new Bundle();
                        bundle.putString(ConstUdp.BUNDLE_UDP_RECEIVE, result);
                        message.setData(bundle);
                        uiHandler.sendMessage(message);
                        Log.i("UDP", "执行接受数据后 " + result);

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }



            }
        }).start();


    }

}
