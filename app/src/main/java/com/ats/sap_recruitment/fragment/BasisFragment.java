package com.ats.sap_recruitment.fragment;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.ats.sap_recruitment.R;

import static com.ats.sap_recruitment.activity.HomeActivity.tvTitle;

public class BasisFragment extends Fragment {

    private LinearLayout llLinux, llLinuxData, llHpux, llHpuxData, llAix, llAixData, llSolaris, llSolarisData, llWindows, llWindowsData;
    private TextView statusStrong, statusAverage, statusPoor, statusNA;
    private CheckBox cbLinux;
    private TextView tvHead, tvHeadLinux, tvHeadHpux, tvHeadAix, tvHeadSolaris, tvHeadWindows;
    private EditText edMonth, edYear;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_basis, container, false);

        Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), "Free_Serif.ttf");

        tvTitle.setText("SAP Profile - Basis OS");
        tvTitle.setTypeface(myTypeface);

        llLinux = (LinearLayout) view.findViewById(R.id.llLinux);
        llLinuxData = (LinearLayout) view.findViewById(R.id.llLinuxData);

        llHpux = (LinearLayout) view.findViewById(R.id.llHpux);
        llHpuxData = (LinearLayout) view.findViewById(R.id.llHpuxData);

        llAix = (LinearLayout) view.findViewById(R.id.llAix);
        llAixData = (LinearLayout) view.findViewById(R.id.llAixData);

        llSolaris = (LinearLayout) view.findViewById(R.id.llSolaris);
        llSolarisData = (LinearLayout) view.findViewById(R.id.llSolarisData);

        llWindows = (LinearLayout) view.findViewById(R.id.llWindows);
        llWindowsData = (LinearLayout) view.findViewById(R.id.llWindowsData);

        statusStrong = (TextView) view.findViewById(R.id.statusStrong);
        statusAverage = (TextView) view.findViewById(R.id.statusAverage);
        statusPoor = (TextView) view.findViewById(R.id.statusPoor);
        statusNA = (TextView) view.findViewById(R.id.statusNA);

        cbLinux = (CheckBox) view.findViewById(R.id.cbLinux);

        edMonth = view.findViewById(R.id.edBasisMonth);
        edYear = view.findViewById(R.id.edBasisYear);

        tvHead = view.findViewById(R.id.tvLabelBasisHead);
        tvHeadLinux = view.findViewById(R.id.tvHeadLinux);
        tvHeadHpux = view.findViewById(R.id.tvHeadHpux);
        tvHeadAix = view.findViewById(R.id.tvHeadAix);
        tvHeadSolaris = view.findViewById(R.id.tvHeadSolaris);
        tvHeadWindows = view.findViewById(R.id.tvHeadWindows);

        edMonth.setTypeface(myTypeface);
        edYear.setTypeface(myTypeface);

        tvHead.setTypeface(myTypeface);
        tvHeadLinux.setTypeface(myTypeface);
        tvHeadHpux.setTypeface(myTypeface);
        tvHeadAix.setTypeface(myTypeface);
        tvHeadSolaris.setTypeface(myTypeface);
        tvHeadWindows.setTypeface(myTypeface);

        llLinuxData.setVisibility(View.GONE);
        llHpuxData.setVisibility(View.GONE);
        llAixData.setVisibility(View.GONE);
        llSolarisData.setVisibility(View.GONE);
        llWindowsData.setVisibility(View.GONE);


        cbLinux.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    PopupMenu popup = new PopupMenu(getContext(), cbLinux);
                    popup.getMenuInflater()
                            .inflate(R.menu.popup_menu_status, popup.getMenu());

                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            if (menuItem.getTitle().equals("Strong")) {
                                statusStrong.setVisibility(View.VISIBLE);
                                statusAverage.setVisibility(View.GONE);
                                statusPoor.setVisibility(View.GONE);
                                statusNA.setVisibility(View.GONE);
                            } else if (menuItem.getTitle().equals("Average")) {
                                statusAverage.setVisibility(View.VISIBLE);
                                statusStrong.setVisibility(View.GONE);
                                statusPoor.setVisibility(View.GONE);
                                statusNA.setVisibility(View.GONE);
                            } else if (menuItem.getTitle().equals("Poor")) {
                                statusPoor.setVisibility(View.VISIBLE);
                                statusStrong.setVisibility(View.GONE);
                                statusAverage.setVisibility(View.GONE);
                                statusNA.setVisibility(View.GONE);
                            } else if (menuItem.getTitle().equals("NA")) {
                                statusNA.setVisibility(View.VISIBLE);
                                statusStrong.setVisibility(View.GONE);
                                statusAverage.setVisibility(View.GONE);
                                statusPoor.setVisibility(View.GONE);
                            } else {
                                statusStrong.setVisibility(View.GONE);
                                statusAverage.setVisibility(View.GONE);
                                statusPoor.setVisibility(View.GONE);
                                statusNA.setVisibility(View.GONE);
                            }
                            return true;
                        }
                    });
                    popup.show();
                } else {
                    statusStrong.setVisibility(View.GONE);
                    statusAverage.setVisibility(View.GONE);
                    statusPoor.setVisibility(View.GONE);
                    statusNA.setVisibility(View.GONE);
                }
            }
        });

        llLinux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (llLinuxData.getVisibility() == View.GONE) {
                    expand();
                } else {
                    collapse();
                }
            }
        });

        llHpux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (llHpuxData.getVisibility() == View.GONE) {
                    llHpuxData.setVisibility(View.VISIBLE);
                } else {
                    llHpuxData.setVisibility(View.GONE);
                }
            }
        });

        llAix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (llAixData.getVisibility() == View.GONE) {
                    llAixData.setVisibility(View.VISIBLE);
                } else {
                    llAixData.setVisibility(View.GONE);
                }
            }
        });

        llSolaris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (llSolarisData.getVisibility() == View.GONE) {
                    llSolarisData.setVisibility(View.VISIBLE);
                } else {
                    llSolarisData.setVisibility(View.GONE);
                }
            }
        });

        llWindows.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (llWindowsData.getVisibility() == View.GONE) {
                    llWindowsData.setVisibility(View.VISIBLE);
                } else {
                    llWindowsData.setVisibility(View.GONE);
                }
            }
        });
        return view;
    }

    public void expand() {
        llLinuxData.setVisibility(View.VISIBLE);
    }

    public void collapse() {
        llLinuxData.setVisibility(View.GONE);
    }


    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem item = menu.findItem(R.id.action_save);
        item.setVisible(true);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

}
