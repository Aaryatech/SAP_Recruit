package com.ats.sap_recruitment.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ats.sap_recruitment.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MatchProfileViewFragment extends Fragment {


    public MatchProfileViewFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_match_profile_view, container, false);
    }

}
