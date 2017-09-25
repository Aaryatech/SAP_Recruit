package com.ats.sap_recruitment.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ats.sap_recruitment.R;

import java.util.ArrayList;
import java.util.List;

import static com.ats.sap_recruitment.activity.HomeActivity.counter;
import static com.ats.sap_recruitment.activity.HomeActivity.tvTitle;

public class AssesmentSummaryFragment extends Fragment {

    private TextView tvTimer;
    private Button btnEndTest;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public static int int_items = 3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_assesment_summary, container, false);
        Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), "Free_Serif.ttf");
        tvTitle.setText("Assesment Summary");
        tvTitle.setTypeface(myTypeface);

        tvTimer = (TextView) view.findViewById(R.id.tvAssesmentSummaryTimer);
        tvTimer.setTypeface(myTypeface);

        counter.setTextView(tvTimer);

        viewPager = view.findViewById(R.id.viewpager);
        createViewPager(viewPager);
        tabLayout = view.findViewById(R.id.tabs);
        tabLayout.setSelectedTabIndicatorHeight(0);
        tabLayout.setupWithViewPager(viewPager);
        createTabHeader();


        btnEndTest = (Button) view.findViewById(R.id.btnAssesmentEndTest);
        btnEndTest.setTypeface(myTypeface);

        btnEndTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Alert");
                builder.setMessage("Are you sure want to end the test?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        counter.stop();

                        Fragment fragment = new TestResultFragment();
                        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.content_frame, fragment, "HomeFragment");
                        ft.commit();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


        return view;
    }

    private void createViewPager(ViewPager viewPager) {
        MyAdapter adapter = new MyAdapter(getChildFragmentManager());
        adapter.addFrag(new NotAttentedFragment(), "Tab 1");
        adapter.addFrag(new NotSureFragment(), "Tab 2");
        adapter.addFrag(new ConfirmFragment(), "Tab 3");
        viewPager.setAdapter(adapter);
    }

    public void createTabHeader() {
        Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), "Free_Serif.ttf");

        TextView tabOne = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_tab_layout, null);
        tabOne.setBackgroundColor(getResources().getColor(R.color.color_bg_not_attend));
        tabOne.setTextColor(getResources().getColor(R.color.color_not_attend));
        tabOne.setText("Not Attented\n5");
        tabOne.setTypeface(myTypeface);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_tab_layout, null);
        tabTwo.setBackgroundColor(getResources().getColor(R.color.color_bg_not_sure));
        tabTwo.setTextColor(getResources().getColor(R.color.color_not_sure));
        tabTwo.setText("Not Sure\n3");
        tabTwo.setTypeface(myTypeface);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        TextView tabThree = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_tab_layout, null);
        tabThree.setBackgroundColor(getResources().getColor(R.color.color_bg_confirm));
        tabThree.setTextColor(getResources().getColor(R.color.color_confirm));
        tabThree.setText("Confirm\n2");
        tabThree.setTypeface(myTypeface);
        tabLayout.getTabAt(2).setCustomView(tabThree);
    }


    class MyAdapter extends FragmentPagerAdapter {

      /*  public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {

                case 0:
                    return new TestResultFragment();
                case 1:
                    return new TestFeedbackFragment();
                case 2:
                    return new TermsAndConditionsFragment();
            }
            return null;
        }

        @Override
        public int getCount() {

            return int_items;

        }

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position) {

                case 0:
                    return "All Flavours";
                case 1:
                    return "Fresh Cream";
                case 2:
                    return "Chocolate";
            }
            return null;
        }*/


        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public MyAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return int_items;
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        counter.setTextView(tvTimer);
    }
}
