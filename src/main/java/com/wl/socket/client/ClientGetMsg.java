package com.wl.socket.client;

import com.wl.socket.ScoketConfig;
import com.wl.util.ByteUtil;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author jianghc
 * @create 2017-04-15 10:04
 **/
public class ClientGetMsg {

    public static Socket socket = null;


    public static void  getMsgFromPlc() {
        System.out.println("接收客户端启动...");

        try {
            //创建一个流套接字并将其连接到指定主机上的指定端口号
            socket = new Socket(ScoketConfig.getIp(), ScoketConfig.getPort());
            System.out.println("服务以当前连接状态:" + socket.isConnected());
            //读取服务器端数据
            DataInputStream input = new DataInputStream(socket.getInputStream());
            byte[] buf = new byte[1024];
            int readnum = 0;
            while (true) {
                //Thread.sleep(500);
                readnum = input.read(buf);
                if (readnum > 0) {
                    System.out.println(ByteUtil.toHexString1(buf));
                    while ((readnum = input.read(buf)) > 0) {
                        System.out.println("上件："+ByteUtil.toHexString1(buf));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("客户端异常:" + e.getMessage());
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    socket = null;
                    System.out.println("客户端 finally 异常:" + e.getMessage());
                }
            }
        }
    }


}
