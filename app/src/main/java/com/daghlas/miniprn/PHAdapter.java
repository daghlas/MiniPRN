package com.daghlas.miniprn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PHAdapter extends RecyclerView.Adapter<PHAdapter.MyViewHolder> {

    Context context;
    List<PHModel> phModelList;
    public  PHAdapter(Context context, List<PHModel> phModelList){
        this.context = context;
        this.phModelList = phModelList;
    }
    @NonNull
    @Override
    public PHAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.payment_history_row, parent, false);
        return new PHAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PHAdapter.MyViewHolder holder, int position) {
        PHModel phModel =phModelList.get(position);
        holder.prnNumber.setText(phModel.getReferenceNumber());
        holder.date.setText(phModel.getDatePaid());
        holder.amount.setText(phModel.getAmountPaid());
        holder.description.setText(phModel.getDescription());
    }
    @Override
    public int getItemCount() {
        return phModelList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView prnNumber, date, amount, description;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            prnNumber = itemView.findViewById(R.id.prnNumber);
            date = itemView.findViewById(R.id.date);
            amount = itemView.findViewById(R.id.amount);
            description = itemView.findViewById(R.id.desc);
        }
    }
}
