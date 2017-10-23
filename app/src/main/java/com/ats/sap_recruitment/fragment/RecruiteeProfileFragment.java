package com.ats.sap_recruitment.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.ats.sap_recruitment.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecruiteeProfileFragment extends Fragment {

    @BindView(R.id.vpRecruiteeProfile)
    ViewPager vpPager;
    @BindView(R.id.sliding_strip)
    PagerSlidingTabStrip pagerSlidingTabStrip;

    FragmentPagerAdapter adapterViewPager;

    public RecruiteeProfileFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recruitee_profile, container, false);
        ButterKnife.bind(this, view);

        adapterViewPager = new RecruiteeViewPagerAdapter(getChildFragmentManager(), getContext());
        vpPager.setAdapter(adapterViewPager);
        pagerSlidingTabStrip.setViewPager(vpPager);
        return view;
    }


    public static class RecruiteeViewPagerAdapter extends FragmentPagerAdapter {


        private Context mContext;

        public RecruiteeViewPagerAdapter(FragmentManager fm, Context mContext) {
            super(fm);
            this.mContext = mContext;
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new MatchProfileViewFragment();
            } else {
                return new InterestedProfileViewFragment();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return mContext.getString(R.string.match_pf);
                case 1:
                    return mContext.getString(R.string.interested_pf);
                default:
                    return null;
            }
        }
    }

}
