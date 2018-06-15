package com.wl.socket.thread;

import com.wl.service.ExpressService;
import com.wl.socket.client.PackGetMsg;
import com.wl.socket.client.PackSendMsg;
import com.wl.socket.manage.PackSocketManage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * @author jianghc
 * @create 2017-05-21 08:17
 **/
public class PackGetThread {
    @Autowired
    private ExpressService expressService;

    @PostConstruct
    public void initService() {
        Thread packGet = new Thread(new Runnable() {
            public void run() {
                int i = 0;
                try {
                    Thread.sleep(5 * 1000); //5秒后启动异步线程
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (true) {
                    if (PackSocketManage.socket == null) {
                        PackSocketManage.initSocket();
                    }
                    if (i > 10) {
                        break;
                    }
                    PackGetMsg.getMsgFromPlc(expressService);
                }
            }
        });

        Thread packSend = new Thread(new Runnable() {
            public void run() {
                int i = 0;
                try {
                    Thread.sleep(10 * 1000); //5秒后启动异步线程
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (true) {
                    if (PackSocketManage.socket == null) {
                        PackSocketManage.initSocket();
                    }
                    if (i > 10) {
                        break;
                    }
                    try {
                        PackSendMsg.sendMsgToPlc();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


            }
        });
    


    }


}
