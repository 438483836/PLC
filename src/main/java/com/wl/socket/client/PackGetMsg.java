package com.wl.socket.client;

import com.wl.service.ExpressService;
import com.wl.socket.manage.PackSocketManage;
import com.wl.util.ByteUtil;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author jianghc
 * @create 2017-04-15 10:04
 **/
public class PackGetMsg {
    public static byte[] buf = new byte[179];

    public static void getMsgFromPlc(ExpressService expressService) {
        //创建一个流套接字并将其连接到指定主机上的指定端口号
        Socket socket = PackSocketManage.socket;
        System.out.println("【集包】服务以当前连接状态:" + socket.isConnected());
        //读取服务器端数据
        DataInputStream input = null;
        try {
            input = new DataInputStream(socket.getInputStream());

            int readnum = 0;
            while (true) {
                //Thread.sleep(500);
                readnum = input.read(buf);
                if (readnum > 0) {
                    System.out.println(ByteUtil.toHexString1(buf));
                    while ((readnum = input.read(buf)) > 0) {
                        //解析下包口数据
                        saveWeightbyCode(buf,expressService);

                        // System.out.println("集包：" + ByteUtil.toHexString1(buf));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void saveWeightbyCode(byte[] buf,ExpressService expressService) {
        int i = 7;
        String str = hex2str(buf[i]);
        StringBuffer sb = new StringBuffer(str);
        i++;
        while (i <= 22) {
            str = hex2str(buf[i]);
            if ("101".equals(str)) {
                break;
            }
            sb.append(str);
            i++;
        }
        try {
           // System.out.println("==重量转型 before==>"+buf[25]+"" +buf[26]);
            Integer x = Integer.parseInt(buf[25]+"" +buf[26]);
            x=(x==null?0:x);//单位为10g
           // System.out.println("==重量转型 after==>"+x);
            expressService.saveWeight(sb.toString(), x);
        }catch (Exception e){
            System.out.println("==数据转型异常==>"+buf[23] + "" + buf[24]+""+buf[25]+"" + buf[26]+":::::"+e.getMessage());
        }
        try {

        } catch (Exception e) {
            System.out.println("==重量保存异常==>"+e.getMessage());
        }
    }

    private static String hex2str(byte b) {
        Byte db = new Byte(b);
        int i = db.intValue();
        return i < 10 ? "0" + i : i + "";
    }


}
