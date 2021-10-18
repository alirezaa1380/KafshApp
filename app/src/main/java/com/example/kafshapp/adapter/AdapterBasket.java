package com.example.kafshapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kafshapp.Post;
import com.example.kafshapp.R;
import com.example.kafshapp.model.ShearedPerfernce;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterBasket extends RecyclerView.Adapter<AdapterBasket.Holder> {

    private Context context;
    private List<Post> list;
    private List<Post>filtercontact;

    public AdapterBasket(Context context, List<Post>list) {
        this.context = context;
        this.list = list;
        filtercontact=new ArrayList<>(list);
    }

    @NonNull
    @Override
    public AdapterBasket.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.custom_recycler_basket,parent,false);
        return new AdapterBasket.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterBasket.Holder holder, int position) {
        Post post=filtercontact.get(position);
        holder.textViewTitle.setText(post.getTitle());
        holder.textViewPrice.setText(post.getPrice()+"");
        holder.textViewoff.setText(post.getOff()+"");
        holder.textViewsum.setText(post.getColornumber()+"");
        Picasso.with(context).load(post.getImage()).into(holder.image);

        ShearedPerfernce shearedPerfernce=new ShearedPerfernce(context);
        holder.textViewAddress.setText(shearedPerfernce.getAddress());
        holder.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "درحال حاظر سرویس پرداخت موجود نمی باشد", Toast.LENGTH_SHORT).show();
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
        TextView textViewTitle
                ,textViewPrice
                ,textViewoff
                ,textViewsum
                ,textViewAddress;
        ImageView image;
        Button buttonSave;

        public Holder(@NonNull View itemView) {
            super(itemView);
            textViewTitle=itemView.findViewById(R.id.txt_title_basket);
            textViewPrice=itemView.findViewById(R.id.txt_price_basket);
            textViewoff=itemView.findViewById(R.id.txt_off_basket);
            textViewsum=itemView.findViewById(R.id.txt_sum_basket);
            image=itemView.findViewById(R.id.imv_image_basket);
            textViewAddress=itemView.findViewById(R.id.txt_address_basket);
            buttonSave=itemView.findViewById(R.id.button_save_basket);
        }
    }

}
