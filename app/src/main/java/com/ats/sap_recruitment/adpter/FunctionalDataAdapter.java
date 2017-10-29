package com.ats.sap_recruitment.adpter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.ats.sap_recruitment.R;

import java.util.ArrayList;

public class FunctionalDataAdapter extends RecyclerView.Adapter<FunctionalDataAdapter.MyViewHolder> {

    ArrayList<String> dataset;
    Context context;

    public FunctionalDataAdapter(ArrayList<String> dataset, Context context) {
        this.dataset = dataset;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rcl_view_basis_layout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        TextView tvHeadRclFunctional = holder.tvHeadRclFunctional;
        final CheckBox cbRclSelect = holder.cbRclSelect;
        LinearLayout llRclFunctional = holder.llRclFunctional;
        final LinearLayout llRclFunctionalData = holder.llRclFunctionalData;
      //  final TextView tvRclStatusCode = holder.tvRclStatusCode;

        tvHeadRclFunctional.setText(dataset.get(position).toString());

        tvHeadRclFunctional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (llRclFunctionalData.getVisibility() == View.GONE) {
                    llRclFunctionalData.setVisibility(View.VISIBLE);

//                    cbRclSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                        @Override
//                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                            if (b) {
//                                PopupMenu popup = new PopupMenu(context, cbRclSelect);
//                                popup.getMenuInflater()
//                                        .inflate(R.menu.popup_menu_status, popup.getMenu());
//
//                                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                                    @Override
//                                    public boolean onMenuItemClick(MenuItem menuItem) {
//                                        if (menuItem.getTitle().equals("Strong")) {
//                                            tvRclStatusCode.setVisibility(View.VISIBLE);
//                                            tvRclStatusCode.setText("Strong");
//                                            tvRclStatusCode.setTextColor(context.getResources().getColor(R.color.colorWhite));
//                                            tvRclStatusCode.setBackgroundColor(context.getResources().getColor(R.color.color_bg_strong));
//                                        } else if (menuItem.getTitle().equals("Average")) {
//                                            tvRclStatusCode.setVisibility(View.VISIBLE);
//                                            tvRclStatusCode.setText("Average");
//                                            tvRclStatusCode.setTextColor(context.getResources().getColor(R.color.colorWhite));
//                                            tvRclStatusCode.setBackgroundColor(context.getResources().getColor(R.color.color_bg_average));
//                                        } else if (menuItem.getTitle().equals("Poor")) {
//
//                                            tvRclStatusCode.setVisibility(View.VISIBLE);
//                                            tvRclStatusCode.setText("Poor");
//                                            tvRclStatusCode.setTextColor(context.getResources().getColor(R.color.colorText2));
//                                            tvRclStatusCode.setBackgroundColor(context.getResources().getColor(R.color.color_bg_poor));
//                                        } else if (menuItem.getTitle().equals("NA")) {
//                                            tvRclStatusCode.setVisibility(View.VISIBLE);
//                                            tvRclStatusCode.setText("NA");
//                                            tvRclStatusCode.setTextColor(context.getResources().getColor(R.color.colorText3));
//                                            tvRclStatusCode.setBackgroundColor(context.getResources().getColor(R.color.color_bg_na));
//                                        } else {
//                                            tvRclStatusCode.setVisibility(View.GONE);
//                                        }
//                                        return true;
//                                    }
//                                });
//                                popup.show();
//
//                            } else {
//                                tvRclStatusCode.setVisibility(View.GONE);
//                            }
//                        }
//                    });

                } else {
                    llRclFunctionalData.setVisibility(View.GONE);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvHeadRclFunctional;
        CheckBox cbRclSelect;
        LinearLayout llRclFunctional;
        LinearLayout llRclFunctionalData;
        //TextView tvRclStatusCode;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.tvHeadRclFunctional = itemView.findViewById(R.id.tvRclBasisHead);
            this.cbRclSelect = itemView.findViewById(R.id.cbRclSelect);
            this.llRclFunctional = itemView.findViewById(R.id.llRclBasis);
            this.llRclFunctionalData = itemView.findViewById(R.id.llRclBasisData);
           // this.tvRclStatusCode = itemView.findViewById(R.id.tvRclStatusCode);
        }
    }
}
