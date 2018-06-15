package com.wl.socket.test;

/**
 * @author jianghc
 * @create 2017-05-21 13:53
 **/
public class Test {

    public static boolean[] f = new boolean[48];


    public static void main(String[] args) {
        f[0] = true;

        test();


        /* int i = Integer.parseInt("ff", 16);
        int a = Integer.valueOf("11111111", 2);
        String bString = "0000000100000000";
        byte[] b = ByteUtil.hexStr2ByteArray(binaryString2hexString(bString));
        System.out.println(binaryString2hexString(bString));*/
    }

    public static String binaryString2hexString(String bString) {
        if (bString == null || bString.equals("") || bString.length() % 8 != 0)
            return null;
        StringBuffer tmp = new StringBuffer();
        int iTmp = 0;
        for (int i = 0; i < bString.length(); i += 4) {
            iTmp = 0;
            for (int j = 0; j < 4; j++) {
                iTmp += Integer.parseInt(bString.substring(i + j, i + j + 1)) << (4 - j - 1);
            }
            tmp.append(Integer.toHexString(iTmp));
        }
        return tmp.toString();
    }


    private static void test() {
        for (int i = 0; i < 3; i++) {
            int start = i * 16;
            int end = (i + 1) * 16 - 1;
            StringBuffer sb = new StringBuffer();
            int index = 0;
            while (end >= start) {
                sb.append(f[end] ? 1 : 0);
                if (sb.length() == 8) {
                    int bt = Integer.valueOf(sb.toString(), 2);



                    sb = new StringBuffer();
                }
                end--;
            }

            System.out.println("=====>" + sb.toString());


        }
    }


    private int arr2Ten(boolean[] f) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 8; i++) {
            sb.append(f[7 - i] ? 1 : 0);
        }
        return Integer.valueOf(sb.toString(), 2);
    }


}
