package com.example.administrator.mytools;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.VolleyError;
import com.example.administrator.mytools.net.VolleyInterface;
import com.example.administrator.mytools.net.VolleyRequestUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    private void netRequest(){
        String url = "";

        VolleyRequestUtil.RequestGet(MainActivity.this, url, "GET",
                new VolleyInterface(MainActivity.this, VolleyInterface.mListener, VolleyInterface.mErrorListener) {
            @Override
            public void onSuccess(String result) {
                try {
                    //数据处理
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray array=jsonObject.getJSONArray("ob");
                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }

            @Override
            public void onError(VolleyError error) {

            }
        });
    }
}
