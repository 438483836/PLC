package com.wl.socket.client;

import com.wl.socket.manage.PackSocketManage;
import com.wl.util.ByteUtil;
import com.wl.util.CheckUtil;
import sun.security.krb5.internal.crypto.Aes256;

import java.io.DataOutputStream;
import java.net.Socket;

/**
 * @author jianghc
 * @create 2017-04-15 10:04
 **/
public class PackSendMsg {

    public static boolean[] f = new boolean[48];

    private static byte[] bytes = new byte[19];


    public static void setBytes(Integer i, boolean flag) {
        f[i] = flag;
        setBt();
    }


    public static void sendMsgToPlc() throws Exception {
        Socket socket = null;
        try {
            //创建一个流套接字并将其连接到指定主机上的指定端口号
            socket = PackSocketManage.socket;//new Socket(ScoketConfig.getIp(), ScoketConfig.getPort());
            //向服务器端发送数据
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            //TODO 获取上件信息
            initMsg();

            while (true) {
                Thread.sleep(100);
                out.write(bytes);
            }
        } catch (Exception e) {
            throw new Exception("客户端连接失败！" + e.getMessage());
        }
    }


    //初始化数据
    private static void initMsg() {
        bytes[0] = (byte) 165;//码头
        bytes[1] = (byte) 165;//码头
        bytes[2] = (byte) 1;//数据类型
        bytes[3] = (byte) 23;//数据长度
        bytes[4] = (byte) 0;//上位机编号
        bytes[11] = (byte) 0;//其它
        bytes[12] = (byte) 0;//其它
        bytes[13] = (byte) 0;//其它
        bytes[14] = (byte) 0;//其它
        bytes[15] = (byte) 0;//校验
        bytes[16] = (byte) 0;//校验
        bytes[17] = (byte) 90;//码尾
        bytes[18] = (byte) 90;//码尾
    }


    private static void setBt() {
        for (int i = 0; i < 3; i++) {
            int start = i * 16;
            int end = (i + 1) * 16 - 1;
            StringBuffer sb = new StringBuffer();
            int index = 0;
            int a = 1;
            while (end >= start) {
                sb.append(f[end] ? 1 : 0);
                if (sb.length() == 8) {
                    int bt = Integer.valueOf(sb.toString(), 2);
                    bytes[5 + i * 2 + a] = (byte) bt;
                    a = 0;
                    sb = new StringBuffer();
                }
                end--;
            }
        }
    }


    private int arr2Ten(boolean[] f) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 8; i++) {
            sb.append(f[7 - i] ? 1 : 0);
        }
        return Integer.valueOf(sb.toString(), 2);
    }


}
