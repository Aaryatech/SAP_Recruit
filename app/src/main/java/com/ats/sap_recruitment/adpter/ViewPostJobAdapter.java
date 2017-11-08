package com.ats.sap_recruitment.adpter;
/*
 * @author Datta Hujare, Nasik
 * @company Spokeinfotech
 * @since 25/9/17 6:20 PM
 * @project name SAP_Recruitment
 */

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ats.sap_recruitment.R;
import com.ats.sap_recruitment.bean.JobPost;
import com.ats.sap_recruitment.bean.JobsArry;

import java.util.ArrayList;

public class ViewPostJobAdapter extends RecyclerView.Adapter<ViewPostJobAdapter.MyViewHolder> {


    ArrayList<JobsArry> dataset;
    private Context context;

    public ViewPostJobAdapter(ArrayList<JobsArry> dataset, Context context) {
        this.dataset = dataset;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_all_post, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        TextView tvViewJobTitle = holder.tvViewJobTitle;
        TextView tvViewJobId = holder.tvViewJobId;
        TextView tvViewJobDate = holder.tvViewJobDate;
        TextView tvViewJobDesc = holder.tvViewJobDesc;
        TextView tvViewJobYear = holder.tvViewJobYear;
        TextView tvCardViewoption = holder.tvCardViewoption;


        tvViewJobTitle.setText(dataset.get(position).getJobTitle().toString());
        tvViewJobId.setText("" + dataset.get(position).getJobId());
        tvViewJobDesc.setText(dataset.get(position).getJobDesc().toString());
        tvViewJobDate.setText(dataset.get(position).getJobDate().toString());
        tvViewJobYear.setText(dataset.get(position).getJobExpMonth().toString() + " month " + dataset.get(position).getJobExpYear().toString() + " year");

        tvCardViewoption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(context, holder.tvCardViewoption);
                popupMenu.inflate(R.menu.pop_up_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_view:

                                break;
                            case R.id.menu_update_job:
                                break;


                        }

                        return false;
                    }
                });
                popupMenu.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvViewJobTitle;
        TextView tvViewJobId;
        TextView tvViewJobDate;
        TextView tvViewJobDesc;
        TextView tvViewJobYear;
        TextView tvCardViewoption;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.tvViewJobDesc = (TextView) itemView.findViewById(R.id.tvViewAllDescription);
            this.tvViewJobTitle = (TextView) itemView.findViewById(R.id.tvViewAllJobTitle);
            this.tvViewJobDate = (TextView) itemView.findViewById(R.id.tvViewAllPostedDate);
            this.tvViewJobId = (TextView) itemView.findViewById(R.id.tvViewAllJobId);
            this.tvViewJobYear = (TextView) itemView.findViewById(R.id.tvViewAllJobYear);
            this.tvCardViewoption = (TextView) itemView.findViewById(R.id.text_card_option);

        }
    }
}
