package com.wl.socket.test;

import org.springframework.util.StringUtils;

import java.io.*;
import java.net.Socket;

/**
 * @author jianghc
 * @create 2017-04-15 10:04
 **/
public class Client {

    public static final String IP_ADDR = "192.168.0.103";//SocketConfig.host;
    // public static final String IP_ADDR = "localhost";//服务器地址
    public static final int PORT = 2000;//SocketConfig.port.intValue();//服务器端口号
    /*
    * 16进制数字字符集
    */
    private static String hexString = "0123456789ABCDEF"; //此处不可随意改动

    public static void main(String[] args) {
        System.out.println("客户端启动...");
            Socket socket = null;
            try {
                //创建一个流套接字并将其连接到指定主机上的指定端口号
                socket = new Socket(IP_ADDR, PORT);
                System.out.println("服务以当前连接状态:" + socket.isConnected());
                //读取服务器端数据
                DataInputStream input = new DataInputStream(socket.getInputStream());
                //向服务器端发送数据
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                   // String s = "A5 A5 01 03 01 34 37 31 30 31 31 00 00 00 00 00 00 00 00 00 00 00 21 00 00 00 00 00 5A 5A";//new BufferedReader(new InputStreamReader(System.in)).readLine();
                //TODO:查询数据库拼接16进制字符串
                while (true) {
                    out.write(hexStr2ByteArray("A5A501030134373130313100000000000000000000002100000000005A5A"));
                }


            } catch (Exception e) {
                e.printStackTrace();
               // System.out.println("客户端异常:" + e.getMessage());
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



    public static String toHexString1(byte[] b) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < b.length; ++i) {
            buffer.append(toHexString1(b[i]));
        }
        return buffer.toString();
    }

    public static String toHexString1(byte b) {
        String s = Integer.toHexString(b & 0xFF);
        if (s.length() == 1) {
            return "0" + s;
        } else {
            return s;
        }
    }


    public static byte[] hexStr2ByteArray(String hexString) {
        if (StringUtils.isEmpty(hexString))
            throw new IllegalArgumentException("this hexString must not be empty");

        hexString = hexString.toLowerCase();
        final byte[] byteArray = new byte[hexString.length() / 2];
        int k = 0;
        for (int i = 0; i < byteArray.length; i++) {
            //因为是16进制，最多只会占用4位，转换成字节需要两个16进制的字符，高位在先
            //将hex 转换成byte   "&" 操作为了防止负数的自动扩展
            // hex转换成byte 其实只占用了4位，然后把高位进行右移四位
            // 然后“|”操作  低四位 就能得到 两个 16进制数转换成一个byte.
            //
            byte high = (byte) (Character.digit(hexString.charAt(k), 16) & 0xff);
            byte low = (byte) (Character.digit(hexString.charAt(k + 1), 16) & 0xff);
            byteArray[i] = (byte) (high << 4 | low);
            k += 2;
        }
        return byteArray;
    }
}
