package com.wl.util;

import java.util.concurrent.CountDownLatch;

/**
 * 单例模式
 * 处理建包中 byte数组
 * Created by Vincent on 2017/5/20.
 */
public class PVariable {

    private static PVariable instance;

    private PVariable(){}

    public static PVariable getInstance(){
        if (instance == null){
            instance = new PVariable();
        }
        return instance;
    }

    public byte[] variable ;

    public byte[] getVariable() {
        return variable;
    }

    public void setVariable(int i, byte b) {
       variable[i]=b;
    }

    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(1);
        int threadCount = 1000;

        for (int i = 0; i < threadCount; i++) {
            new Thread() {

                @Override
                public void run() {
                    try {
                        // all thread to wait
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // test get instance
                    System.out.println(PVariable.getInstance().hashCode());
                }
            }.start();
        }

        // release lock, let all thread excute Singleton.getInstance() at the same time
        latch.countDown();
    }

}
