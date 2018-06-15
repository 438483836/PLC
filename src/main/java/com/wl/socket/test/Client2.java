package com.wl.socket.test;

import com.wl.util.ByteUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author jianghc
 * @create 2017-04-15 10:04
 **/
public class Client2 {

    public static final String IP_ADDR = "192.168.0.103";//SocketConfig.host;
    // public static final String IP_ADDR = "localhost";//服务器地址
    public static final int PORT = 2000;//SocketConfig.port.intValue();//服务器端口号
    /*
    * 16进制数字字符集
    */
    private static String hexString = "0123456789ABCDEF"; //此处不可随意改动

    public static void main(String[] args) {
        System.out.println("客户端启动...");
            //Socket socket = null;
            try {
                //创建一个流套接字并将其连接到指定主机上的指定端口号
               final Socket socket = new Socket(IP_ADDR, PORT);
                System.out.println("服务以当前连接状态:" + socket.isConnected());
                //读取服务器端数据

                //向服务器端发送数据


                Thread threadGet = new Thread(new Runnable() {
                    public void run() {
                      /*  try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }*/
                        DataInputStream input = null;
                        try {
                            input = new DataInputStream(socket.getInputStream());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        byte[] buf = new byte[1024];
                        int readnum = 0;
                        while (true) {
                            try {
                                readnum = input.read(buf);
                                if (readnum > 0) {
                                    System.out.println(ByteUtil.toHexString1(buf));
                                    while ((readnum = input.read(buf)) > 0) {
                                        System.out.println("接收=====>");
                                        System.out.println(ByteUtil.toHexString1(buf));
                                    }
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                });


                final Thread threadSend = new Thread(new Runnable() {
                    public void run() {
                       /* try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }*/
                        DataOutputStream out = null;
                        try {
                            out = new DataOutputStream(socket.getOutputStream());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        while (true) {
                            System.out.println("===发送+===>");
                            try {
                                out.write(ByteUtil.hexStr2ByteArray("A501030134373130313100000000000000000000002100000000005A5A"));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });


                threadGet.start();
                threadSend.start();



            } catch (Exception e) {
                System.out.println("客户端异常:" + e.getMessage());
            } finally {
               /* if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        socket = null;
                        System.out.println("客户端 finally 异常:" + e.getMessage());
                    }
                }*/
            }
        }




}
