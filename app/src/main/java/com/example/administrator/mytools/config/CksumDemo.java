package com.example.administrator.mytools.config;

/**
 * Created by Administrator on 2017/5/5 0005.
 */

public class CksumDemo {

    //    生成校验码
    public static byte cksum(byte[] a) {

        byte b = a[0];

        for (int i = 1; i < a.length; i++) {
            byte c = a[i];
            b = (byte) (b ^ c);
        }

        return b;
    }

    //    校验发送数据是否被篡改
    public static boolean checkSum(byte[] a) {

        byte b;
        byte[] byte3 = new byte[a.length - 1];
        b = a[a.length - 1];

        System.arraycopy(a, 0, byte3, 0, a.length - 1);
        byte byte4 = cksum(byte3);

        if (byte4 == b) {
            return true;
        }  else {
            return false;
        }
    }

    //    生成带有校验码的数据
    public static byte[] addCheckSum(byte[] a) {

        byte byte2 = cksum(a);
        byte[] byte3 = new byte[a.length + 1];

        System.arraycopy(a, 0, byte3, 0, a.length);
        byte3[byte3.length - 1] = byte2;

        return byte3;
    }
}
