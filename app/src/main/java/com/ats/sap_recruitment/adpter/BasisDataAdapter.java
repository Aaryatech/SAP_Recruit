package com.ats.sap_recruitment.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ats.sap_recruitment.R;

import java.util.ArrayList;

/**
 * Created by datta on 13/10/17.
 */

public class BasisDataAdapter extends RecyclerView.Adapter<BasisDataAdapter.MyViewHolder> {

    ArrayList<String> dataset;
    private Context context;

    public BasisDataAdapter(ArrayList<String> dataset, Context context) {
        this.dataset = dataset;
        this.context = context;
    }

    @Override

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rcl_view_basis_layout, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        TextView tvHeadRclBasis = holder.tvHeadRclBasis;
        CheckBox cbRclSelect = holder.cbRclSelect;
        LinearLayout llRclBasis = holder.llRclBasis;
        final LinearLayout llRclBasisData = holder.llRclBasisData;
        TextView tvRclStatusCode = holder.tvRclStatusCode;

        tvHeadRclBasis.setText(dataset.get(position).toString());
        tvHeadRclBasis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (llRclBasisData.getVisibility() == View.GONE) {
                    llRclBasisData.setVisibility(View.VISIBLE);
                } else {
                    llRclBasisData.setVisibility(View.GONE);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvHeadRclBasis;
        CheckBox cbRclSelect;
        LinearLayout llRclBasis;
        LinearLayout llRclBasisData;
        TextView tvRclStatusCode;


        public MyViewHolder(View itemView) {
            super(itemView);

            this.tvHeadRclBasis = itemView.findViewById(R.id.tvRclBasisHead);
            this.cbRclSelect = itemView.findViewById(R.id.cbRclSelect);
            this.llRclBasis = itemView.findViewById(R.id.llRclBasis);
            this.llRclBasisData = itemView.findViewById(R.id.llRclBasisData);
            this.tvRclStatusCode = itemView.findViewById(R.id.tvRclStatusCode);
        }
    }
}
