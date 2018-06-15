package com.wl.test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Vincent on 2018-06-12.
 */
public class md5JdkUtil {

    /**
     * 将byte数组转化为16进制输出
     * @param bytes
     * @return
     */
    public static String convertByteToHexString(byte[] bytes){
        String result = "";
        for(int i = 0; i < bytes.length; i++){
            int temp = bytes[i] & 0xff;
            String tempHex = Integer.toHexString(temp);
            if (tempHex.length() < 2){
                result += "0" + tempHex;
            }else {
                result += tempHex;
            }
        }
        return result;
    }

    /**
     * 使用jdk自带的md2方法
     * @param message
     * @return
     */
    public static String md2Jdk(String message){
        String temp = "";
        try {
            MessageDigest md2Digest = MessageDigest.getInstance("MD2");
            byte[] encodeMd2Digest = md2Digest.digest(message.getBytes());
            temp = convertByteToHexString(encodeMd2Digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return temp;
    }

    public static String md5Jdk(String message){
        String temp = "";
        try {
            MessageDigest md5Digest = MessageDigest.getInstance("MD5");
            byte[] encodeMd5Digest = md5Digest.digest(message.getBytes());
            temp = convertByteToHexString(encodeMd5Digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return temp;
    }

    public static void main(String[] args){
        String md2Encode = md2Jdk("hello jdk md");
        System.out.println("md2Jdk加密后为: " +md2Encode);
        System.out.println("md2Jdk加密文长度为: " +md2Encode.length());

        String md5Encode = md5Jdk("hello jdk md");
        System.out.println("md5Jdk加密后为: " +md5Encode);
        System.out.println("md5Jdk加密文长度为: " +md2Encode.length());
    }
}
