package com.example.administrator.mytools;

import android.app.Application;
import android.bluetooth.BluetoothAdapter;
import android.os.Environment;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/12 0012.
 */

public class MyAplication extends Application {
    private static MyAplication myApplication = null;

    public static MyAplication getApplication(){
        if (myApplication == null){
            myApplication = new MyAplication();
        }
        return myApplication;
    }

    private static RequestQueue queue;
    private static List<Map<String, Object>> deviceList;
    private static File file;

    public static List<Map<String, Object>> getArrayList() {
        return deviceList;
    }

    public static void setArrayList(List<Map<String, Object>> arrayList) {
        MyAplication.deviceList = arrayList;
    }

    public static File getFile() {
        return file;
    }

    public static void setFile(File file) {
        MyAplication.file = file;
    }

    public static RequestQueue getRequestQueue(){
        return queue;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        queue = Volley.newRequestQueue(getApplicationContext());
        deviceList = new ArrayList<>();
        file = new File(Environment.getExternalStorageDirectory(), "myData");
    }

    public void updateDeviceListWith(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject json = jsonArray.getJSONObject(i);
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("hardwareID", json.get("hardwareID"));
                map.put("deviceID", json.get("deviceID"));
                map.put("departmentID", json.get("departmentID"));
                map.put("tk", json.get("tk"));
                deviceList.add(map);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        List<String> deviceStr = new ArrayList<>();
        for (int i = 0; i < deviceList.size(); i++){
            JSONObject object = new JSONObject(deviceList.get(i));
            deviceStr.add(object.toString());
        }

        updateDiviceListWith(deviceStr);
        testData();

    }

    public void updateDiviceListWith(List<String> deviceStr) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(deviceStr.toString().getBytes());
            if (fileOutputStream != null){
                fileOutputStream.close();
            }
            } catch (IOException e) {
                e.printStackTrace();
        }
    }

    public void updateDeviceListWith(String deviceID, String hardwareID) {

    }

    public void testData(){
        List<Map<String, Object>> deviceList = MyAplication.getArrayList();
        deviceList = getDataFromSDcard("myData");

        String hardwareID = null;
        String deviceID = null;
        String departmentID = null;
        String tk= null;

        for(Map<String,Object> map:deviceList){
            if(map!=null){
                hardwareID = (String) map.get("hardwareID");
                deviceID = (String) map.get("deviceID");
                departmentID = (String) map.get("departmentID");
                tk = (String) map.get("tk");

                Log.d(">>>>", hardwareID +"---"+deviceID+"---"+departmentID+"---" +tk);
            }else{
                return;
            }
        }
    }

    public List<Map<String, Object>> getDataFromSDcard(String fileName){

        //读取文件内容保存到resultStr
        String deviceStr = null;
        File file = new File(Environment.getExternalStorageDirectory(),fileName);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] b = new byte[fileInputStream.available()];
            fileInputStream.read(b);
            deviceStr = new String(b);
            if(fileInputStream != null){
                fileInputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //将读取的String结果转化成List<>
        List<Map<String, Object>> deviceList = MyAplication.getArrayList();
        try {
            JSONArray jsonArray = new JSONArray(deviceStr);
            if (jsonArray.length() > 0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("hardwareID", jsonObject.get("hardwareID"));
                    map.put("deviceID", jsonObject.get("deviceID"));
                    map.put("departmentID", jsonObject.get("departmentID"));
                    map.put("tk", jsonObject.get("tk"));
                    deviceList.add(map);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return deviceList;
    }
}
