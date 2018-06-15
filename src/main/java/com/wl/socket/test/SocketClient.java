package com.wl.socket.test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
/**
 * @author jianghc
 * @create 2017-04-16 16:00
 **/
public class SocketClient {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public SocketClient(){
        try {
            socket=new Socket("192.168.0.120",2000);
            out=new ObjectOutputStream(socket.getOutputStream());
            ReadThread readThread=new ReadThread();
            readThread.start();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String msg){
        System.out.println(msg);
        try {
            out.writeObject(msg);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class ReadThread extends Thread{
        boolean runFlag=true;
        public void run(){
            try {
                in=new ObjectInputStream(socket.getInputStream());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            while(runFlag){
                if(socket.isClosed()){
                    return;
                }
                try {
                    Object obj=in.readObject();
                    if(obj instanceof String){
                        System.out.println("Client recive:"+obj);
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        public void exit(){
            runFlag=false;
        }
    }

    public static void main(String[] args) {
        SocketClient socketClient=new SocketClient();
        System.out.println("build socketClient");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        socketClient.sendMessage("A5 A5 01 1E 01 00 01 02 03 04 05 06 07 08 09 0A 0B 0C 0D 0E 0F 10 00 05 00 01 AE 5A 5A");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        socketClient.sendMessage("Hello second.");
    }

}
