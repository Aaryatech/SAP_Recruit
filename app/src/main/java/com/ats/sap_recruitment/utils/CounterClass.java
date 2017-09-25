package com.ats.sap_recruitment.utils;

import android.os.CountDownTimer;
import android.util.Log;

import java.util.concurrent.TimeUnit;

/**
 * Created by maxadmin on 24/8/17.
 */

public class CounterClass extends CountDownTimer {


    private static String hms;
    private static CounterClass instance;

    public CounterClass(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    public static CounterClass initInstance(long millisInFuture, long countDownInterval) {
        if (instance == null) {
            instance = new CounterClass(millisInFuture, countDownInterval);
        }
        return instance;
    }

    public static CounterClass getInstance() throws Exception {
        if (instance == null) {
            throw new Exception("Parameters not initialized. Initiate with initInstance");
        } else {
            return instance;
        }
    }

    public static String getFormatedTime() {
        return hms;
    }

    @Override
    public void onTick(long l) {
        long millis = l;
        hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
        );
    }

    @Override
    public void onFinish() {

    }
}
