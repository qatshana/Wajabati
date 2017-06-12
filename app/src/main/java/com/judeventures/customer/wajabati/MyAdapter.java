package com.judeventures.customer.wajabati;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Abdel on 5/12/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private List<ListItem> listItems;
    private Context context;

    public MyAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
    final ListItem listItem= listItems.get(position);
        holder.texViewHead.setText(listItem.getHead());
        holder.textVewDes.setText(listItem.getDesc());

        Picasso.with(context)
                .load(listItem.getImageUrl())
                .resize(100,100)
                .into(holder.imageView);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"you clicked "+listItem.getHead(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView texViewHead;
        public TextView textVewDes;
        public ImageView imageView;
        public LinearLayout linearLayout;




        public ViewHolder(View itemView) {
            super(itemView);
            texViewHead=(TextView)itemView.findViewById(R.id.viewHead);
            textVewDes=(TextView)itemView.findViewById(R.id.textViewDesc);
            imageView=(ImageView)itemView.findViewById(R.id.imageView);
            linearLayout=(LinearLayout) itemView.findViewById(R.id.linearLayout);
        }
    }

}
