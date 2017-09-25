package com.ats.sap_recruitment.fragment;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ats.sap_recruitment.R;
import com.ats.sap_recruitment.activity.HomeActivity;
import com.ats.sap_recruitment.utils.CapturePhoto;
import com.ats.sap_recruitment.utils.PermissionsUtil;

import java.util.Calendar;

import static com.ats.sap_recruitment.activity.HomeActivity.counter;
import static com.ats.sap_recruitment.activity.HomeActivity.tvTitle;

public class AssesmentStartFragment extends Fragment {

    private Button btnConfirm, btnNotSure;
    private ImageView ivSummary;
    Intent service;
    private TextView tvTimer, tvQueNo, tvQuestion;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_assesment_start, container, false);
        Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), "Free_Serif.ttf");
        tvTitle.setText("Assesment Start");
        tvTitle.setTypeface(myTypeface);


        if (PermissionsUtil.checkAndRequestPermissions(getActivity())) {
        }

        tvTimer = (TextView) view.findViewById(R.id.tvAssesmentStartTimer);
        tvQueNo = view.findViewById(R.id.tvAssesmentQueNo);
        tvQuestion = view.findViewById(R.id.tvAssesmentQue);

        tvTimer.setTypeface(myTypeface);
        tvQueNo.setTypeface(myTypeface);
        tvQuestion.setTypeface(myTypeface);

        counter.setTextView(tvTimer);

        btnConfirm = (Button) view.findViewById(R.id.btnAssesmentConfirm);
        btnNotSure = (Button) view.findViewById(R.id.btnAssesmentNotSure);

        btnConfirm.setTypeface(myTypeface);
        btnNotSure.setTypeface(myTypeface);

        ivSummary = view.findViewById(R.id.ivTestSummary);

        ivSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new AssesmentSummaryFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment, "AssessmentSummary");
                ft.commit();
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

/*Calendar cal = Calendar.getInstance();

        service = new Intent(getBaseContext(), CapPhoto.class);
        cal.add(Calendar.SECOND, 15);
        //TAKE PHOTO EVERY 15 SECONDS
        PendingIntent pintent = PendingIntent.getService(this, 0, service, 0);
        AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        alarm.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                60 * 60 * 1000, pintent);
        startService(service);*/

                Calendar cal = Calendar.getInstance();

                service = new Intent(getContext(), CapturePhoto.class);
                cal.add(Calendar.SECOND, 15);
                getActivity().startService(service);


                Fragment fragment = new AssesmentSummaryFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment, "AssessmentSummary");
                ft.commit();
            }
        });

        btnNotSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new AssesmentSummaryFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment, "AssessmentSummary");
                ft.commit();
            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        counter.setTextView(tvTimer);
    }
}
