package com.ats.sap_recruitment.adpter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ats.sap_recruitment.R;
import com.ats.sap_recruitment.bean.MainCat;

import java.util.ArrayList;

public class BindCategoryAdapter extends RecyclerView.Adapter<BindCategoryAdapter.MyViewHolder> {

    private static final String TAG = "BindCategoryAdapter";
    ArrayList<MainCat> dataset = new ArrayList<>();
    Context context;

    public BindCategoryAdapter(ArrayList<MainCat> dataset, Context context) {
        this.dataset = dataset;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rcl_categories_view, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        LinearLayout llViewMainCatData = holder.llViewMainCatData;
        final LinearLayout llViewSubCatData = holder.llViewSubCatData;
        TextView tvRclMainCat = holder.tvRclMainCat;
        ListView lsvCatViewSubCat = holder.lsvCatViewSubCat;


        tvRclMainCat.setText(dataset.get(position).getProfCatName());

        Log.e(TAG, "onBindViewHolder: ID : " + dataset.get(position).getProfCatId() + " MainCatName :" + dataset.get(position).getProfCatName());
        tvRclMainCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mainCatName = dataset.get(position).getProfCatName();
                ArrayList<String> subCategory = new ArrayList<>();
                MainCat mainCat = dataset.get(position);
                Log.e(TAG, "onClick: Selected Main Category : " + mainCat);

                if (!mainCat.getSubCats().isEmpty()) {

                    final ArrayList<String> subCatArraylisList = new ArrayList<>();
                    for (int i = 0; i < mainCat.getSubCats().size(); i++) {
                        subCatArraylisList.add(i, mainCat.getSubCats().get(i).getProfCatName());
                    }

                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, subCatArraylisList) {
                        @NonNull
                        @Override
                        public View getView(int pos, @Nullable View convertView, @NonNull ViewGroup parent) {

                            String selected = subCatArraylisList.get(pos);
                            // LayoutInflater inflater1 = getActivity().getLayoutInflater();
                            //View view1 = inflater1.inflate(R.layout.list_view_specialisation, null);
                            //  TextView textView = view1.findViewById(R.id.tvLsvSpecialTest);
                            // textView.setText(selected);

                            return super.getView(position, convertView, parent);
                        }
                    };
                    if (llViewSubCatData.getVisibility() == View.GONE) {
                        llViewSubCatData.setVisibility(View.VISIBLE);
                    } else {
                        llViewSubCatData.setVisibility(View.GONE);
                    }
                } else {

                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout llViewMainCatData;
        LinearLayout llViewSubCatData;
        TextView tvRclMainCat;
        ListView lsvCatViewSubCat;


        public MyViewHolder(View itemView) {
            super(itemView);

            this.llViewMainCatData = itemView.findViewById(R.id.llViewMainCatData);
            this.llViewSubCatData = itemView.findViewById(R.id.llViewSubCatData);
            this.tvRclMainCat = itemView.findViewById(R.id.tvRclMainCat);
            this.lsvCatViewSubCat = itemView.findViewById(R.id.lsvCatViewSubCat);
        }
    }
}
