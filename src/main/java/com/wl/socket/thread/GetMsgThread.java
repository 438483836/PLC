package com.wl.socket.thread;

import com.wl.socket.ScoketConfig;
import com.wl.socket.client.ClientGetMsg;

import javax.annotation.PostConstruct;

/**
 * @author jianghc
 * @create 2017-04-16 10:19
 **/
public class GetMsgThread {
    @PostConstruct
    public void initService() {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                int i = 0;
                try {
                    Thread.sleep(5 * 1000); //5秒后启动异步线程
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (true) {
                    if (i > 10) {
                        System.out.println("已失败10次，不再尝试连接");
                        break;
                    }
                    System.out.println("第"+i+"次尝试=====异步线程启动===>>>" + ScoketConfig.getIp() + "====>>" + ScoketConfig.getPort());
                    ClientGetMsg.getMsgFromPlc();
                    i++;
                }
            }
        });
       

    }

}

