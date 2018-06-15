package com.wl.socket;

/**
 * @author jianghc
 * @create 2017-04-29 11:32
 **/
public class ScoketConfig {
    private static String ip;

    private static Integer port;



    public static String packIp;

    public static Integer packPort;

    public static String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public static Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public static String getPackIp() {
        return packIp;
    }

    public  void setPackIp(String packIp) {
        ScoketConfig.packIp = packIp;
    }

    public static Integer getPackPort() {
        return packPort;
    }

    public  void setPackPort(Integer packPort) {
        ScoketConfig.packPort = packPort;
    }


}
