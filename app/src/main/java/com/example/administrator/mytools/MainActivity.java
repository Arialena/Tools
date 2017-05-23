package com.example.administrator.mytools;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.VolleyError;
import com.example.administrator.mytools.net.VolleyInterface;
import com.example.administrator.mytools.net.VolleyRequestUtil;
import com.example.administrator.mytools.scan.ZbarCaptureActivity;
import com.example.administrator.mytools.scan.ZxingCaptureActivity;
import com.example.administrator.mytools.sharedpreferences.SaveActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private Button zxingBtn, zbarBtn, sharedpreferencesBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        zxingBtn.findViewById(R.id.zxingScan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ZxingCaptureActivity.class);
                startActivity(intent);
            }
        });

        zbarBtn.findViewById(R.id.zbarScan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ZbarCaptureActivity.class);
                startActivity(intent);
            }
        });
        sharedpreferencesBtn.findViewById(R.id.shardPreferences).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SaveActivity.class);
                startActivity(intent);
            }
        });
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
