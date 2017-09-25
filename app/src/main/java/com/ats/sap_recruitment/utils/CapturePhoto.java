package com.ats.sap_recruitment.utils;

import android.app.Service;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.os.Environment;
import android.os.IBinder;
import android.os.StrictMode;
import android.util.Base64;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by maxadmin on 23/8/17.
 */

public class CapturePhoto extends Service {

    private SurfaceHolder sHolder;
    static Camera mCamera = null;
    private Parameters parameters;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("CAM", "start");

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        Thread myThread = null;


    }

    @Override
    public void onStart(Intent intent, int startId) {

        super.onStart(intent, startId);

        if (Camera.getNumberOfCameras() >= 2) {

            try {
                mCamera = Camera.open(CameraInfo.CAMERA_FACING_FRONT);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (Camera.getNumberOfCameras() < 2) {
            try {
                mCamera = Camera.open();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        SurfaceView sv = new SurfaceView(getApplicationContext());


        try {
            mCamera.setPreviewDisplay(sv.getHolder());
            parameters = mCamera.getParameters();
            mCamera.setParameters(parameters);
            mCamera.startPreview();

            mCamera.takePicture(null, null, mCall);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        sHolder = sv.getHolder();
        sHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    Camera.PictureCallback mCall = new Camera.PictureCallback() {

        public void onPictureTaken(final byte[] data, Camera camera) {

            FileOutputStream outStream = null;
            try {

                File sd = new File(Environment.getExternalStorageDirectory() + File.separator + "SAP");
                if (!sd.exists()) {
                    sd.mkdirs();
                    Log.i("FO", "folder" + Environment.getExternalStorageDirectory());
                }

                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
                String tar = "SAP_" + (sdf.format(cal.getTime()));


                outStream = new FileOutputStream(sd + File.separator + tar + ".jpg");
                outStream.write(data);
                outStream.close();

                String imageStr = Base64.encodeToString(data, Base64.DEFAULT);
                Log.e("imageStr : ", "" + imageStr);

                Log.i("CAM", data.length + " byte written to:" + sd + tar + ".jpg");
                Log.e("PATH : ", "" + sd + File.separator + tar + ".jpg");
                camkapa(sHolder);


            } catch (FileNotFoundException e) {
                Log.d("CAM", e.getMessage());
            } catch (IOException e) {
                Log.d("CAM", e.getMessage());
            }
        }
    };


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void camkapa(SurfaceHolder sHolder) {

        if (null == mCamera)
            return;
        mCamera.stopPreview();
        mCamera.release();
        mCamera = null;
        Log.i("CAM", " closed");
    }


}
