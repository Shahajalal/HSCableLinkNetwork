package shahajalal.example.shahajalal.hscablelinknetwork;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapterForUserInfo extends RecyclerView.Adapter<RecyclerAdapterForUserInfo.myviewhoder>{
    private List<Contact> list=new ArrayList<>();

    Context ctx;
    RecyclerAdapterForUserInfo(List<Contact> list, Context ctx){
        this.ctx=ctx;
        this.list=list;

    }

    @NonNull
    @Override
    public myviewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_viewforreseller,parent,false);
        return new myviewhoder(view,ctx,list);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewhoder holder, int position) {

        holder.username.setText(list.get(position).getName());
        holder.father.setText(list.get(position).getFathername());
        holder.village.setText(list.get(position).getVillage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class myviewhoder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView username,father,village;
        List<Contact> contacts=new ArrayList<Contact>();
        Context ctx;
        public myviewhoder(View itemView,Context ctx,List<Contact> contacts) {
            super(itemView);
            this.contacts=contacts;
            this.ctx=ctx;
            itemView.setOnClickListener(this);
            username=itemView.findViewById(R.id.usernametextview);
            father=itemView.findViewById(R.id.fathernametxtviewid);
            village=itemView.findViewById(R.id.villagetxtviewid);
        }

        @Override
        public void onClick(View v) {
            int position=getAdapterPosition();
            Contact contact=this.contacts.get(position);
            Intent intent=new Intent(this.ctx,Showbills.class);
            intent.putExtra("name",contact.getName());
            intent.putExtra("father",contact.getFathername());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            this.ctx.startActivity(intent);
        }
    }

    public void setFilter(ArrayList<Contact> newList){
        list=new ArrayList<>();
        list.addAll(newList);
        notifyDataSetChanged();
    }
}
