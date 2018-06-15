package com.wl.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Vincent on 2017/4/16.
 */
public class CheckUtil {



    public static String checkCode(String barCode){
        if (null == barCode){

        }else{
            String code = barCode.substring(barCode.indexOf("A5A5") + 4, barCode.lastIndexOf("5A5A"));
            //条码截取 转换成16进制String
            String byteCode =  TypeConversion.string2HexString(code);
            //16进制String 正则过滤
            String str = getNumbers(byteCode);
            System.out.println(str);
            //两两相加
            String res = TypeConversion.sumHexStringBy2(str,2);
            System.out.println(res);
            return res;
        }
        return null;
    }


    public static String getCode(String code){
        String byteCode = TypeConversion.string2HexString(code);
       if (byteCode.length() < 30){

        }
        String str = "A5A5010301"+byteCode+"00000000000000000000002100000000005A5A";
      //  System.out.println(str);
        return str;
    }


    public static String  getCodeAndNum(String code,String num){
        String byteCode = TypeConversion.string2HexString(code);
        String str = "A5A50103"+num+byteCode+"00000000000000000000002100000000005A5A";
        return str;
    }

    /**
     *
     * @param code 条码
     * @param num 上位机编号
     * @param slogans 隔口号
     * @return
     */
    public static String getCodeAndNumAndSlogans(String code, String num, String slogans){
        String byteCode = TypeConversion.string2HexString(code);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("A5A50103");
        String swNum = TypeConversion.string2HexString(num);
        //String slogansCode = TypeConversion.string2HexString(slogans);
        stringBuffer.append(swNum);
        if (byteCode.length() < 32){
            int j = 0;
            int i = 32 - byteCode.length();
            String val = String.valueOf(i);
            String str = String.format("%0"+ val+"d",j);
            String newByteCode = byteCode + str;
            stringBuffer.append(newByteCode);
          //  System.out.println(str);
        }else{
            String str = "A5A50103"+swNum+byteCode+slogans+"00000000005A5A";
            return  str;
        }
        stringBuffer.append(slogans);
        stringBuffer.append("00000000005A5A");
        //String str ="A5A50103"+swNum+newByteCode+slogansCode+"00000000005A5A";
       // System.out.println(stringBuffer.toString());
        return stringBuffer.toString();
    }

    public static String resultOfPLC(byte[] plc){
        if (plc == null){

        }else {
            String res = new String(plc);
            String code = res.substring(res.indexOf("A5A5") + 4, res.lastIndexOf("5A5A"));
           // System.out.println(code);
            return code;
        }
        return null;
    }



    public static String getNumbers(String content) {
        Pattern pattern = Pattern.compile("^[1-9]\\d*$");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }




    public static void main(String[] args){
       /* String barCode = "471011111111111";
        String num ="1";
        String slogans = "12";
        getCodeAndNumAndSlogans(barCode,num,slogans);*/

        byte[] b = null;
    }

}
