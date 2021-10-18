package com.example.kafshapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kafshapp.model.Comment;
import com.example.kafshapp.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterComment  extends RecyclerView.Adapter<AdapterComment.Holder> {

    private Context context;
    private List<Comment> list;
    private List<Comment>filtercontact;

    public AdapterComment(Context context, List<Comment>list) {
        this.context = context;
        this.list = list;
        filtercontact=new ArrayList<>(list);
    }

    @NonNull
    @Override
    public AdapterComment.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.custom_recycler_comment,parent,false);
        return new AdapterComment.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterComment.Holder holder, int position) {
        Comment comment=filtercontact.get(position);
        holder.textViewTitleComment.setText(comment.getComment());
        holder.textViewName.setText(comment.getName());

    }


    public void filter(String searchkew){
        searchkew=searchkew.toLowerCase();
        if (searchkew.isEmpty()){
            filtercontact=new ArrayList<>(list);
        }
        else {
            filtercontact=new ArrayList<>();
            for (int i = 0; i <list.size() ; i++) {
                String name=list.get(i).getComment();
                if (name.toLowerCase().contains(searchkew)){
                    filtercontact.add(list.get(i));

                }
            }
        }
        notifyDataSetChanged();
    }




    @Override
    public int getItemCount() {
        return filtercontact.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        TextView textViewTitleComment
                ,textViewName;
        public Holder(@NonNull View itemView) {
            super(itemView);
            textViewTitleComment=itemView.findViewById(R.id.txt_comment_title);
            textViewName=itemView.findViewById(R.id.txt_name_comment);
        }
    }
}


