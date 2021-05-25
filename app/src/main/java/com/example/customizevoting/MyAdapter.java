package com.example.customizevoting;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class MyAdapter extends FirestoreRecyclerAdapter<Model,MyAdapter.MyViewHolder> {

    public MyAdapter(@NonNull FirestoreRecyclerOptions<Model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Model model) {
        holder.listvname.setText(model.votingname);
        holder.listid.setText(model.votercode);
        holder.listname1.setText(model.candidate1name);
        holder.listname2.setText(model.candidate2name);
        holder.listname3.setText(model.candidate3name);
        holder.listname4.setText(model.candidate4name);
        holder.listname5.setText(model.candidate5name);
        holder.listname6.setText(model.candidate6name);
        holder.pass.setText(model.password);
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(holder.relativeLayout.getContext(),EnterPassword.class);
                intent.putExtra("password",model.getPassword());
                intent.putExtra("votingname",model.getVotingname());
                intent.putExtra("name1",model.getCandidate1name());
                intent.putExtra("name2",model.getCandidate2name());
                intent.putExtra("name3",model.getCandidate3name());
                intent.putExtra("name4",model.getCandidate4name());
                intent.putExtra("name5",model.getCandidate5name());
                intent.putExtra("name6",model.getCandidate6name());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holder.relativeLayout.getContext().startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list,parent,false);
        return new MyViewHolder(view);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView listvname,listid,listname1,listname2,listname3,
                listname4,listname5,listname6,pass;
        RelativeLayout relativeLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            listvname=itemView.findViewById(R.id.listvname);
            listid=itemView.findViewById(R.id.listid);
            pass=itemView.findViewById(R.id.pass);
            relativeLayout=itemView.findViewById(R.id.relativelayout);
            listname1=itemView.findViewById(R.id.listname1);
            listname2=itemView.findViewById(R.id.listname2);
            listname3=itemView.findViewById(R.id.listname3);
            listname4=itemView.findViewById(R.id.listname4);
            listname5=itemView.findViewById(R.id.listname5);
            listname6=itemView.findViewById(R.id.listname6);

        }
    }
}
