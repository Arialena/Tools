package com.example.administrator.mytools.config;

/**
 * Created by Administrator on 2017/5/23 0023.
 */

public class ConfigBitPperation {

    public static byte[] byteFromInt(int deviceNum){
        byte[] uuidHashbt = new byte[2];

        uuidHashbt[0] = (byte)((deviceNum >>> 8) & 0xff );
        uuidHashbt[1] = (byte)((deviceNum >>> 0) & 0xff );


        return uuidHashbt;
    }

    public static int intFromByte(byte[] bytes){

        int deviceNum = (int) (((bytes[0] & 0xFF)<<8)
                |(bytes[1] & 0xFF));

        return deviceNum;
    }
}
