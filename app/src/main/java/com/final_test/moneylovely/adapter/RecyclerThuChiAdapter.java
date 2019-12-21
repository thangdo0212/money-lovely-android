package com.final_test.moneylovely.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.final_test.moneylovely.R;
import com.final_test.moneylovely.event.EventClickDetail;
import com.final_test.moneylovely.model.Thu_Chi_Model;

import java.util.ArrayList;

public class RecyclerThuChiAdapter extends RecyclerView.Adapter<RecyclerThuChiAdapter.viewHolder> {
    ArrayList<Thu_Chi_Model> list;
    Context context;
    public EventClickDetail clickDetail;

    public RecyclerThuChiAdapter(ArrayList<Thu_Chi_Model> list , Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerThuChiAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.custom_recycler_thu_chi, viewGroup, false);
        return new RecyclerThuChiAdapter.viewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerThuChiAdapter.viewHolder viewHolder, final int i) {

        final Thu_Chi_Model model = list.get(i);

        viewHolder.txtTenCV.setText(model.getNameCV());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                clickDetail.onClickItemCv(i, model);
            }
        });

    }
    @Override
    public int getItemCount() {
        return list.size();
    }



    public class viewHolder extends RecyclerView.ViewHolder{

        TextView txtTenCV;
        ImageView img;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenCV = itemView.findViewById(R.id.txtTenCV);
            img = itemView.findViewById(R.id.test);

        }
    }

    public void setOnclickEvent(EventClickDetail eventClickDetail){
        this.clickDetail = eventClickDetail;
    }
}
