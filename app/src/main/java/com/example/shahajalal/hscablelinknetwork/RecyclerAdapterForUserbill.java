package com.example.shahajalal.hscablelinknetwork;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapterForUserbill extends RecyclerView.Adapter<RecyclerAdapterForUserbill.myviewhoder>{
    private List<ContactForBill> list=new ArrayList<>();
    RecyclerAdapterForUserbill(List<ContactForBill> list){
        this.list=list;
    }

    @NonNull
    @Override
    public myviewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.itemviewforbills,parent,false);
        return new myviewhoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewhoder holder, int position) {
        holder.month.setText(list.get(position).getMonthname());
        holder.charge.setText(list.get(position).getMonthcharge());
        holder.amount.setText(list.get(position).getDepositamount());
        holder.owoings.setText(list.get(position).getOwoings());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class myviewhoder extends RecyclerView.ViewHolder{

        TextView month,charge,amount,owoings;
        List<ContactForBill> contacts=new ArrayList<ContactForBill>();
        Context ctx;
        public myviewhoder(View itemView) {
            super(itemView);
            this.contacts=contacts;
            this.ctx=ctx;
            month=itemView.findViewById(R.id.monthnameid);
            charge=itemView.findViewById(R.id.chargeid);
            amount=itemView.findViewById(R.id.givenid);
            owoings=itemView.findViewById(R.id.owoings);
        }

    }
}
