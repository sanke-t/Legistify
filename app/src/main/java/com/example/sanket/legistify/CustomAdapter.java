package com.example.sanket.legistify;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sanket on 08-10-2015.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.V> {
    private ArrayList<Lawyer>l =new ArrayList<>();
    private LayoutInflater layout;
    static class V extends RecyclerView.ViewHolder {
        TextView l,a,p,f;
        public V(View itemView) {
            super(itemView);
            l=(TextView)itemView.findViewById(R.id.name);
            a=(TextView)itemView.findViewById(R.id.address);
            p=(TextView)itemView.findViewById(R.id.phone);
            f=(TextView)itemView.findViewById(R.id.field);
        }
    }

    public CustomAdapter(Context context,ArrayList<Lawyer>l)
    {
        layout=LayoutInflater.from(context);
        this.l=l;
        notifyItemRangeChanged(0,l.size());
    }

    @Override
    public V onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=layout.inflate(R.layout.lawyer,parent,false);
        V viewHolder=new V(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(V holder, int position) {
        Lawyer current =l.get(position);
        holder.l.setText(current.getName());
        holder.a.setText(current.getAddress());
        holder.p.setText(current.getPhone());
        holder.f.setText(current.getField());

    }

    @Override
    public int getItemCount() {
        return l.size();
    }
}

