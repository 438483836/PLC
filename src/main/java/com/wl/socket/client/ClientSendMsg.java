package com.wl.socket.client;

import com.wl.util.ByteUtil;
import com.wl.util.CheckUtil;

import java.io.DataOutputStream;
import java.net.Socket;

/**
 * @author jianghc
 * @create 2017-04-15 10:04
 **/
public class ClientSendMsg {

    public static void sendMsgToPlc(String barCode, String smt, String exit) throws Exception {
        Socket socket = null;
        try {
            //创建一个流套接字并将其连接到指定主机上的指定端口号
            socket = ClientGetMsg.socket;//new Socket(ScoketConfig.getIp(), ScoketConfig.getPort());
            //向服务器端发送数据
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            //TODO 获取上件信息
            out.write(ByteUtil.hexStr2ByteArray(CheckUtil.getCodeAndNumAndSlogans(barCode,smt,exit)));
        } catch (Exception e) {
            throw new Exception("客户端连接失败！" + e.getMessage());
        }
    }


}
