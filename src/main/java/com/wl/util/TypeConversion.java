package com.wl.util;

/**
 * Created by Vincent on 2017/4/16.
 */
public class TypeConversion {

    /**
     * @Title:bytes2HexString
     * @Description:字节数组转16进制字符串
     * @param b
     *            字节数组
     * @return 16进制字符串
     * @throws
     */
    public static String bytes2HexString(byte[] b) {
        StringBuffer result = new StringBuffer();
        String hex;
        for (int i = 0; i < b.length; i++) {
            hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            result.append(hex.toUpperCase());
        }
        return result.toString();
    }


    /**
     * @Title:string2HexString
     * @Description:字符串转16进制字符串
     * @param strPart
     *            字符串
     * @return 16进制字符串
     * @throws
     */
    public static String string2HexString(String strPart) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < strPart.length(); i++) {
            int ch = (int) strPart.charAt(i);
            String strHex = Integer.toHexString(ch);
            hexString.append(strHex);
        }
        return hexString.toString();
    }

    /**
     * @Title:intToHexString
     * @Description:10进制数字转成16进制
     * @param a 转化数据
     * @param len 占用字节数
     * @return
     * @throws
     */
    private static String intToHexString(int a,int len){
        len<<=1;
        String hexString = Integer.toHexString(a);
        int b = len -hexString.length();
        if(b>0){
            for(int i=0;i<b;i++)  {
                hexString = "0" + hexString;
            }
        }
        return hexString;
    }

    /**
     * @Title:char2Byte
     * @Description:字符转成字节数据char-->integer-->byte
     * @param src
     * @return
     * @throws
     */
    public static Byte char2Byte(Character src) {
        return Integer.valueOf((int)src).byteValue();
    }

    /**
     * @Title:hexString2String
     * @Description:16进制字符串转字符串
     * @param src
     *            16进制字符串
     * @return 字节数组
     * @throws
     */
    public static String hexString2String(String src) {
        String temp = "";
        for (int i = 0; i < src.length() / 2; i++) {
            temp = temp
                    + (char) Integer.valueOf(src.substring(i * 2, i * 2 + 2),
                    16).byteValue();
        }
        return temp;
    }

    public static String sumHexStringBy2(String hexString,int index){
        int length = hexString.length();
        long result = 0;
        for(int i=0;i<length;){
            String subIn = hexString.substring(i, i+=index);
            long x = Long.parseLong(subIn, 16);
            result+=x;
        }
        String sum = Long.toHexString(result);
        return sum;
    }

}
