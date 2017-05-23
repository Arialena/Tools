package com.example.administrator.mytools.scan;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.mytools.R;
import com.example.administrator.mytools.zbar.QRCodeView;

/**
 * Created by Administrator on 2017/3/21.
 *
 * 扫描二维码界面类
 */

public class ZbarCaptureActivity extends AppCompatActivity implements QRCodeView.Delegate{

    private static final String TAG = ZbarCaptureActivity.class.getSimpleName();

    private ImageView back_ibtn;


    /**
     * Called when the activity is first created.
     */
    private QRCodeView mQRCodeView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zbar_scan);


        back_ibtn = (ImageView) findViewById(R.id.back_ibtn);
        back_ibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mQRCodeView = (ZBarView) findViewById(R.id.zbarview);
        mQRCodeView.setDelegate(this);

        mQRCodeView.startSpot();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mQRCodeView.startCamera();
//        mQRCodeView.startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT);
        mQRCodeView.startSpot();
        mQRCodeView.showScanRect();
    }

    @Override
    protected void onStop() {
        mQRCodeView.stopCamera();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mQRCodeView.onDestroy();
        super.onDestroy();
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    @Override
    public void onScanQRCodeSuccess(String result) {

        if (result.equals("1824073759")){

            finish();
        }
        Log.i(TAG, "result:" + result);
        vibrate();
        mQRCodeView.startSpot();
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Log.e(TAG, "打开相机出错");
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
    }
}
