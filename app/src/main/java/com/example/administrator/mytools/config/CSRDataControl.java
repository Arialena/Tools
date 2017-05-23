package com.example.administrator.mytools.config;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/12.
 */

public class CSRDataControl {

    private static byte REQUEST_INFO_OPCODE= 0x01;

    public static void requestDeviceInfo (int deviceId) {
        byte [] data = {REQUEST_INFO_OPCODE,0,0,0,0,0,0,0,0,0};
//        DataModelApi.sendData(deviceId, data, false);
    }

    private void sendData(int deviceId){
        byte [] data = {(byte)0xF0,0x31,0x30,0x30,0x38,0x36,0x36,0x36,0x36,0x36,0x36,0x36,0x36,0x36};
        // we send the data using blocks so we don't need acknowledged packet.
//        DataModelApi.sendData(deviceId, data, false);
    }

    static public boolean sendData(int deviceId, String data) {
        if (data.length() > 8) {
            int numberOfPack = data.length() / 8;
            boolean m = (data.length() % 8 == 0);

            if (!m) {
                numberOfPack++;
            }

            ArrayList<String> list = new ArrayList();
            for (int i = 0; i < data.length();) {
                String sub = data.substring(i, i + 8 < data.length() ? i + 8 : data.length());
                list.add(sub);
                i += 8;
            }

            for (int i = 0; i < list.size(); i++) {
                byte[] bt = list.get(i).getBytes();

                byte[] pack = new byte[9];
                byte[] title = {(byte)0xF0};
                byte[] leixin = {(byte)0xA0};

                System.arraycopy(title, 0, pack, 0, 1);
                System.arraycopy(leixin, 0, pack, 1, 2);
                System.arraycopy(bt, 0, pack, 1, bt.length);
                System.arraycopy(bt, 0, pack, 1 + leixin.length, bt.length);

//                DataModelApi.sendData(deviceId, pack, false);

            }

        } else {
            byte[] bt = data.getBytes();

            byte[] pack = new byte[bt.length + 1];
            byte[] title = {(byte)0xF0};
            byte[] leixin = {(byte)0xA0};

            System.arraycopy(title, 0, pack, 0, 1);
            System.arraycopy(leixin, 0, pack, 1, 2);
            System.arraycopy(bt, 0, pack, 1, bt.length);

//            DataModelApi.sendData(deviceId, pack, false);
        }
        return true;
    }
}
