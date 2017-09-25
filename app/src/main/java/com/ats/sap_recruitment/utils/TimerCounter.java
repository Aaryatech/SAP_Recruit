package com.ats.sap_recruitment.utils;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.ats.sap_recruitment.R;
import com.ats.sap_recruitment.fragment.TestResultFragment;

import java.util.concurrent.TimeUnit;

/**
 * Created by maxadmin on 24/8/17.
 */

public class TimerCounter {

    //private static TimerCounter timer = new TimerCounter();
    private CountDownTimer countdownTimer;
    private TextView tv1;
    private static String count;
    private FragmentTransaction mFragmentTransaction;
    private FragmentManager mFragmentManager;
    Context context;


    public TimerCounter(Context context) {
        this.context = context;
    }

    public static String getInstance() {
        return count;
    }

    public void createAndStart(int seconds) {
        stop();
        countdownTimer = new CountDownTimer(seconds * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                count = "" + millisUntilFinished / 1000;
                //tv1.setText("Seconds remaining: " + millisUntilFinished / 1000);

               /* tv1.setText(String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))
                        )
                );*/

                tv1.setText(String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))
                        )
                );

            }

            public void onFinish() {
                count = "0";
                tv1.setText("00:00");

                final AppCompatActivity activity = (AppCompatActivity) context;
                final TextView tvCounter = new TextView(context);

                Fragment fragment = new TestResultFragment();
                FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment, "HomeFragment");
                ft.commit();

            }
        };
        countdownTimer.start();

    }


    public void stop() {
        if (countdownTimer != null) {
            countdownTimer.cancel();
            countdownTimer = null;
        }

    }


    public void setTextView(TextView tv) {
        this.tv1 = tv;
    }
}
