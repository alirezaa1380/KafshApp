package com.example.kafshapp.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kafshapp.Post;
import com.example.kafshapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterGroup extends RecyclerView.Adapter<AdapterGroup.Holder> {
    private Context context;
    private List<Post> list;
    private List<Post>filtercontact;

    public AdapterGroup(Context context, List<Post>list) {
        this.context = context;
        this.list = list;
        filtercontact=new ArrayList<>(list);
    }

    @NonNull
    @Override
    public AdapterGroup.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.custom_recycler,parent,false);
        return new AdapterGroup.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterGroup.Holder holder, int position) {
        Post post=filtercontact.get(position);
        holder.textViewTitlePost.setText(post.getTitle());
        Picasso.with(context).load(post.getImage()).into(holder.imageViewImagePost);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                int sumPrice=post.getPrice()+20000-post.getOff();
                bundle.putInt("id_group",post.getId());
                bundle.putString("title",post.getTitle());
                bundle.putString("image",post.getImage());
                bundle.putString("color",post.getColor());
                bundle.putString("color1",post.getColor3());
                bundle.putString("color2",post.getColor4());
                bundle.putString("color3",post.getColor5());
                bundle.putString("colornumber1",post.getColornumber()+"");
                bundle.putString("colornumber2",post.getColornumber2()+"");
                bundle.putString("colornumber3",post.getColornumber3()+"");
                bundle.putString("colornumber4",post.getColornumber4()+"");
                bundle.putString("price",post.getPrice()+"");
                bundle.putString("off",post.getOff()+"");
                bundle.putString("sumprice",sumPrice+"");
                Navigation.findNavController(v).navigate(R.id.action_frGroup_to_frItemSelected,bundle);
            }
        });
    }


    public void filter(String searchkew){
        searchkew=searchkew.toLowerCase();
        if (searchkew.isEmpty()){
            filtercontact=new ArrayList<>(list);
        }
        else {
            filtercontact=new ArrayList<>();
            for (int i = 0; i <list.size() ; i++) {
                String name=list.get(i).getTitle();
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
        ImageView imageViewImagePost;
        TextView textViewTitlePost;
        public Holder(@NonNull View itemView) {
            super(itemView);
            imageViewImagePost=itemView.findViewById(R.id.imv_image_post);
            textViewTitlePost=itemView.findViewById(R.id.txt_title_post);

        }
    }
}
