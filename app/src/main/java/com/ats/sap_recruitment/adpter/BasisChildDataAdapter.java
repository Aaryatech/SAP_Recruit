package com.ats.sap_recruitment.adpter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.ats.sap_recruitment.R;
import com.ats.sap_recruitment.bean.Activity;
import com.ats.sap_recruitment.bean.RekArray;

import java.util.ArrayList;

public class BasisChildDataAdapter extends RecyclerView.Adapter<BasisChildDataAdapter.MyViewHolder> {

    private static String TAG = "BasisChildDataAdapter";
    ArrayList<Activity> dataset;
    ArrayList<RekArray> rekArrayArrayList;
    private Context context;


    public BasisChildDataAdapter() {
    }

    public BasisChildDataAdapter(ArrayList<Activity> dataset, ArrayList<RekArray> rekArrayArrayList, Context context) {
        this.dataset = dataset;
        this.rekArrayArrayList = rekArrayArrayList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_basis, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        TextView activityName = holder.activityName;
        final CheckBox cbSelectChoice = holder.cbSelectChoice;
        final RelativeLayout rlRelativeSpinner = holder.rlRelativeSpinner;
        final Spinner spnChoice = holder.spnChoice;

        ArrayList<String> remarkArryList = new ArrayList<>();
        remarkArryList.clear();
        for (int i = 0; i < rekArrayArrayList.size(); i++) {
            remarkArryList.add(i, rekArrayArrayList.get(i).getRemrkTitle());
        }
        final ArrayAdapter arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, remarkArryList);
        spnChoice.setAdapter(arrayAdapter);
        Log.e(TAG, "onBindViewHolder: Save Flag " + dataset.get(position).getSavedFlag());
        activityName.setText(dataset.get(position).getActName());


        if (dataset.get(position).getSavedFlag().toString().equalsIgnoreCase("1")) {
            cbSelectChoice.setChecked(true);
        } else {
            cbSelectChoice.setChecked(false);
        }


        if (cbSelectChoice.isChecked()) {
            rlRelativeSpinner.setVisibility(View.VISIBLE);
            if (dataset.get(position).getRemrkId().toString().equalsIgnoreCase("0")) {
                Log.e(TAG, "onBindViewHolder: no value Still Set for actvity, default will set");
            } else {
                for (int i = 0; i < rekArrayArrayList.size(); i++) {
                    if (dataset.get(position).getRemrkId().toString().equalsIgnoreCase(rekArrayArrayList.get(i).getRemrkId())) {
                        String selectedItem = rekArrayArrayList.get(i).getRemrkTitle().toString();
                        Log.e(TAG, "onBindViewHolder: Selected Spinner value : " + selectedItem);
                        int selectionPosition = arrayAdapter.getPosition(selectedItem);
                        spnChoice.setSelection(selectionPosition);
                        break;
                    }
                }
            }

        } else {
            rlRelativeSpinner.setVisibility(View.INVISIBLE);
        }

        cbSelectChoice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.e(TAG, "onCheckedChanged:  check value : " + b);
                if (b) {
                    rlRelativeSpinner.setVisibility(View.VISIBLE);
                    if (dataset.get(position).getRemrkId().toString().equalsIgnoreCase("0")) {
                        Log.e(TAG, "onBindViewHolder: no value Still Set for actvity, default will set");
                    } else {
                        for (int i = 0; i < rekArrayArrayList.size(); i++) {
                            if (dataset.get(position).getRemrkId().toString().equalsIgnoreCase(rekArrayArrayList.get(i).getRemrkId())) {
                                String selectedItem = rekArrayArrayList.get(i).getRemrkTitle().toString();
                                Log.e(TAG, "onBindViewHolder: Selected Spinner value : " + selectedItem);
                                int selectionPosition = arrayAdapter.getPosition(selectedItem);
                                spnChoice.setSelection(selectionPosition);
                                break;
                            }
                        }
                    }

                } else {
                    rlRelativeSpinner.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView activityName;
        CheckBox cbSelectChoice;
        RelativeLayout rlRelativeSpinner;
        Spinner spnChoice;


        public MyViewHolder(View itemView) {
            super(itemView);

            this.activityName = itemView.findViewById(R.id.tvActivityName);
            this.cbSelectChoice = itemView.findViewById(R.id.cbRclSelect);
            this.rlRelativeSpinner = itemView.findViewById(R.id.rlRelativeSpinner);
            this.spnChoice = itemView.findViewById(R.id.spnStatusCode);
        }
    }

}
