package com.wl.socket.thread;

import com.wl.cache.ExpressCache;
import com.wl.service.ExpressService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * @author jianghc
 * @create 2017-04-16 10:19
 **/
public class InitCacheThread {
    @Autowired
    private ExpressService expressService;

    @PostConstruct
    public void initService() {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println("====【加载包裹缓存】=======>");
                    new ExpressCache().loadData2Cache(expressService.findAll());
                    System.out.println("====【包裹缓存加载完毕】=======>");
                } catch (Exception e) {
                    System.out.println("====【包裹缓存加载失败】=======>");
                }
            }
        });
        thread.start();
    }

}

