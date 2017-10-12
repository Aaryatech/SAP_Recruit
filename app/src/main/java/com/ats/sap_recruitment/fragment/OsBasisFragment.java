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

public class OsBasisFragment extends Fragment {

    private LinearLayout llLinux, llLinuxData, llHpux, llHpuxData, llAix, llAixData, llSolaris, llSolarisData, llWindows, llWindowsData;
    private TextView statusAct1, statusAct2, statusAct3, statusAct4, statusAct5;
    private CheckBox cbLinux, cbHpux, cbAix, cbSolarise, cbWindows;
    private TextView tvHead, tvHeadLinux, tvHeadHpux, tvHeadAix, tvHeadSolaris, tvHeadWindows;
    private EditText edMonth, edYear;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_os_basis, container, false);

        Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), "Free_Serif.ttf");

        tvTitle.setText("SAP Profile - Basis OS");
        tvTitle.setTypeface(myTypeface);

        llLinux = view.findViewById(R.id.llLinux);
        llLinuxData = view.findViewById(R.id.llLinuxData);

        llHpux = view.findViewById(R.id.llHpux);
        llHpuxData = view.findViewById(R.id.llHpuxData);

        llAix = view.findViewById(R.id.llAix);
        llAixData = view.findViewById(R.id.llAixData);

        llSolaris = view.findViewById(R.id.llSolaris);
        llSolarisData = view.findViewById(R.id.llSolarisData);

        llWindows = view.findViewById(R.id.llWindows);
        llWindowsData = view.findViewById(R.id.llWindowsData);

        statusAct1 = view.findViewById(R.id.statusAct1);
        statusAct2 = view.findViewById(R.id.statusAct2);
        statusAct3 = view.findViewById(R.id.statusAct3);
        statusAct4 = view.findViewById(R.id.statusAct4);
        statusAct5 = view.findViewById(R.id.statusAct5);


        cbLinux = view.findViewById(R.id.cbLinux);
        cbHpux = view.findViewById(R.id.cbHpux);
        cbAix = view.findViewById(R.id.cbAix);
        cbSolarise = view.findViewById(R.id.cbSolaris);
        cbWindows = view.findViewById(R.id.cbWindows);


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
                                statusAct1.setVisibility(View.VISIBLE);
                                statusAct1.setText("Strong");
                                statusAct1.setTextColor(getResources().getColor(R.color.colorWhite));
                                statusAct1.setBackgroundColor(getResources().getColor(R.color.color_bg_strong));
                            } else if (menuItem.getTitle().equals("Average")) {
                                statusAct1.setVisibility(View.VISIBLE);
                                statusAct1.setText("Average");
                                statusAct1.setTextColor(getResources().getColor(R.color.colorWhite));
                                statusAct1.setBackgroundColor(getResources().getColor(R.color.color_bg_average));
                            } else if (menuItem.getTitle().equals("Poor")) {

                                statusAct1.setVisibility(View.VISIBLE);
                                statusAct1.setText("Poor");
                                statusAct1.setTextColor(getResources().getColor(R.color.colorText2));
                                statusAct1.setBackgroundColor(getResources().getColor(R.color.color_bg_poor));
                            } else if (menuItem.getTitle().equals("NA")) {
                                statusAct1.setVisibility(View.VISIBLE);
                                statusAct1.setText("NA");
                                statusAct1.setTextColor(getResources().getColor(R.color.colorText3));
                                statusAct1.setBackgroundColor(getResources().getColor(R.color.color_bg_na));
                            } else {
                                statusAct1.setVisibility(View.GONE);
                            }
                            return true;
                        }
                    });
                    popup.show();
                } else {
                    statusAct1.setVisibility(View.GONE);
                }
            }
        });

        cbHpux.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b) {
                    PopupMenu popup = new PopupMenu(getContext(), cbHpux);
                    popup.getMenuInflater()
                            .inflate(R.menu.popup_menu_status, popup.getMenu());

                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            if (menuItem.getTitle().equals("Strong")) {
                                statusAct2.setVisibility(View.VISIBLE);
                                statusAct2.setText("Strong");
                                statusAct2.setTextColor(getResources().getColor(R.color.colorWhite));
                                statusAct2.setBackgroundColor(getResources().getColor(R.color.color_bg_strong));
                            } else if (menuItem.getTitle().equals("Average")) {
                                statusAct2.setVisibility(View.VISIBLE);
                                statusAct2.setText("Average");
                                statusAct2.setTextColor(getResources().getColor(R.color.colorWhite));
                                statusAct2.setBackgroundColor(getResources().getColor(R.color.color_bg_average));
                            } else if (menuItem.getTitle().equals("Poor")) {

                                statusAct2.setVisibility(View.VISIBLE);
                                statusAct2.setText("Poor");
                                statusAct2.setTextColor(getResources().getColor(R.color.colorText2));
                                statusAct2.setBackgroundColor(getResources().getColor(R.color.color_bg_poor));
                            } else if (menuItem.getTitle().equals("NA")) {
                                statusAct2.setVisibility(View.VISIBLE);
                                statusAct2.setText("NA");
                                statusAct2.setTextColor(getResources().getColor(R.color.colorText3));
                                statusAct2.setBackgroundColor(getResources().getColor(R.color.color_bg_na));
                            } else {
                                statusAct2.setVisibility(View.GONE);
                            }
                            return true;
                        }
                    });
                    popup.show();
                } else {
                    statusAct2.setVisibility(View.GONE);
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
