package com.example.administrator.mytools.net;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.administrator.mytools.MyAplication;

import java.util.Map;

/**
 * Created by Administrator on 2017/5/12.
 */

public class VolleyRequestUtil {

    public static StringRequest stringRequest;
    public static Context context;

    public static void RequestGet(Context context, String url, String tag, VolleyInterface volleyListenerInterface) {

        // 清除请求队列中的tag标记请求
        MyAplication.getRequestQueue().cancelAll(tag);

        stringRequest = new StringRequest(Request.Method.GET, url, volleyListenerInterface.responseListener(), volleyListenerInterface.errorListener());
        stringRequest.setTag(tag);

        MyAplication.getRequestQueue().add(stringRequest);
        MyAplication.getRequestQueue().start();
    }

    public static void RequestPost(Context context, String url, String tag, final Map<String, String> params, VolleyInterface volleyListenerInterface) {
        MyAplication.getRequestQueue().cancelAll(tag);
        stringRequest = new StringRequest(Request.Method.POST, url, volleyListenerInterface.responseListener(), volleyListenerInterface.errorListener()){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        stringRequest.setTag(tag);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(60 * 1000, 1, 1.0f));

        MyAplication.getRequestQueue().add(stringRequest);
        MyAplication.getRequestQueue().start();
    }



}
