package com.ats.sap_recruitment.adpter;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ats.sap_recruitment.R;
import com.ats.sap_recruitment.bean.MainCat;
import com.ats.sap_recruitment.fragment.CategoryWithJobPostFragment;

import java.util.ArrayList;

public class BindCategoryAdapter extends RecyclerView.Adapter<BindCategoryAdapter.MyViewHolder> {

    private static final String TAG = "BindCategoryAdapter";
    ArrayList<MainCat> dataset = new ArrayList<>();
    Context context;
    int jobId;

//    public BindCategoryAdapter(ArrayList<MainCat> dataset, Context context) {
//        this.dataset = dataset;
//        this.context = context;
//    }

    public BindCategoryAdapter(ArrayList<MainCat> dataset, Context context, int jobId) {
        this.dataset = dataset;
        this.context = context;
        this.jobId = jobId;
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
        final ListView lsvCatViewSubCat = holder.lsvCatViewSubCat;


        tvRclMainCat.setText(dataset.get(position).getProfCatName());

        Log.e(TAG, "onBindViewHolder: ID : " + dataset.get(position).getProfCatId() + " MainCatName :" + dataset.get(position).getProfCatName());
        tvRclMainCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String mainCatName = dataset.get(position).getProfCatName();
                ArrayList<String> subCategory = new ArrayList<>();
                MainCat mainCat = dataset.get(position);
                Log.e(TAG, "onClick: Selected Main Category : " + mainCat);

                if (!mainCat.getSubCats().isEmpty()) {

                    final ArrayList<String> subCatArraylisList = new ArrayList<>();
                    for (int i = 0; i < mainCat.getSubCats().size(); i++) {
                        subCatArraylisList.add(i, mainCat.getSubCats().get(i).getProfCatName());
                    }

                    Log.e(TAG, "onClick: subCatArraylisList : " + subCatArraylisList);

                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, subCatArraylisList) {
                        @NonNull
                        @Override
                        public View getView(int pos, @Nullable View convertView, @NonNull ViewGroup parent) {

                            String selected = subCatArraylisList.get(pos);
                            LayoutInflater inflater1 = LayoutInflater.from(parent.getContext());
                            View view1 = inflater1.inflate(R.layout.list_view_specialisation, null);
                            TextView textView = view1.findViewById(R.id.tvLsvSpecialTest);
                            textView.setText(selected);
                            return view1;
                        }
                    };

                    lsvCatViewSubCat.setAdapter(arrayAdapter);

                    if (llViewSubCatData.getVisibility() == View.GONE) {
                        llViewSubCatData.setVisibility(View.VISIBLE);
                        setListViewHeightBasedOnChildren(lsvCatViewSubCat);


                        lsvCatViewSubCat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                String selectedItem = subCatArraylisList.get(i);

                                Bundle bundle = new Bundle();
                                bundle.putString("subCat", selectedItem);
                                bundle.putString("mainCat", mainCatName);
                                bundle.putInt("jobId", jobId);
                                Log.e(TAG, "onBindViewHolder : JobId : " + jobId);
                                Fragment fragment = new CategoryWithJobPostFragment();
                                fragment.setArguments(bundle);
                                FragmentTransaction ft = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                                ft.replace(R.id.content_frame, fragment);
                                ft.addToBackStack("backToProfile");
                                ft.commit();
                            }
                        });
                    } else {
                        llViewSubCatData.setVisibility(View.GONE);
                    }
                } else {

                    Log.e(TAG, "onClick: else part executed");

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


    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, LinearLayoutCompat.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

}
