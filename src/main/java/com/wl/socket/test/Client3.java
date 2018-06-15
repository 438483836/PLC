package com.wl.socket.test;

/**
 * @author jianghc
 * @create 2017-04-15 10:04
 **/
public class Client3 {

    public static final String IP_ADDR = "localhost";//SocketConfig.host;
    // public static final String IP_ADDR = "localhost";//服务器地址
    public static final int PORT = 2000;//SocketConfig.port.intValue();//服务器端口号
    /*
    * 16进制数字字符集
    */
    private static String hexString = "0123456789ABCDEF"; //此处不可随意改动

    public static void main(String[] args) {



      /*  System.out.println("客户端启动...");
            Socket socket = null;
            try {
                //创建一个流套接字并将其连接到指定主机上的指定端口号
                socket = new Socket(IP_ADDR, PORT);
                System.out.println("服务以当前连接状态:" + socket.isConnected());
                //读取服务器端数据
                DataInputStream input = new DataInputStream(socket.getInputStream());
                //向服务器端发送数据
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                //socket.setKeepAlive(true);
                new TestThread(socket);

            } catch (Exception e) {
                System.out.println("客户端异常:" + e.getMessage());
            } finally {
                *//*if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        socket = null;
                        System.out.println("客户端 finally 异常:" + e.getMessage());
                    }
                }*//*
            }*/
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

    /*
* 将字符串编码成16进制数字 */
    public static String encode(String str) {
        // 根据默认编码获取字节数组
        byte[] bytes = str.getBytes();
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        // 将字节数组中每个字节拆解成2位16进制整数
        for (int i = 0; i < bytes.length; i++) {
            sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
            sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
            sb.append(" ");
        }
        return sb.toString();
    }
}
