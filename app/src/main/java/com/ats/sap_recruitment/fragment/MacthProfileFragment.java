package com.ats.sap_recruitment.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ats.sap_recruitment.R;
import com.ats.sap_recruitment.utils.CustomViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MacthProfileFragment extends Fragment {

    @BindView(R.id.vpMatchProfile)
    CustomViewPager vpMatchProfile;
    @BindView(R.id.sliding_tabs)
    TabLayout slidingTab;

    public MacthProfileFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_macth_profile, container, false);
        ButterKnife.bind(this, v);

        vpMatchProfile.setPagingEnabled(true);
        MatchProfilePagerAdapter adapter = new MatchProfilePagerAdapter(getContext(), getChildFragmentManager());
        vpMatchProfile.setAdapter(adapter);
        slidingTab.setupWithViewPager(vpMatchProfile);

//        slidingTab.addOnTabSelectedListener(
//                new TabLayout.ViewPagerOnTabSelectedListener(vpMatchProfile) {
//                    @Override
//                    public void onTabSelected(TabLayout.Tab tab) {
//                        super.onTabSelected(tab);
//                        int numTab = tab.getPosition();
//                        Log.e("In Tab listener", "onTabSelected: " + tab.getPosition()
//                        );
//                    }
//                });


        slidingTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.e("Selected tab", "onTabSelected: " + tab.getPosition());
                int position = tab.getPosition();
                new MatchProfilePagerAdapter(getContext(), getChildFragmentManager()).getItem(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.e("Selected tab", "onTabSelected: " + tab.getPosition());
                vpMatchProfile.setCurrentItem(tab.getPosition());
            }
        });

        return v;
    }


    public class MatchProfilePagerAdapter extends FragmentPagerAdapter {

        private Context mContext;

        public MatchProfilePagerAdapter(Context context, FragmentManager fm) {
            super(fm);
            mContext = context;
        }


        // This determines the fragment for each tab
        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new MatchProfileViewFragment();
            } else {
                return new InterestedProfileViewFragment();
            }
        }


        // This determines the number of tabs
        @Override
        public int getCount() {
            return 2;
        }

        // This determines the title for each tab
        @Override
        public CharSequence getPageTitle(int position) {
            // Generate title based on item position
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




