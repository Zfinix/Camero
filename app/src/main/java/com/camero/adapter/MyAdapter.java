package com.camero.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.camero.R;

import java.util.List;
import java.util.Random;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<String> mName;
    private List<String> mPhone;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    public Context Context;


    // name is passed into the constructor
    public MyAdapter(Context context, List<String> name, List<String> phone) {
        this.mInflater = LayoutInflater.from(context);
        this.Context = context;
        this.mName = name;
        this.mPhone = phone;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate( R.layout.rv, parent, false);
        return new ViewHolder(view);
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tName, tPhone;
        FloatingActionButton fab;

        ViewHolder(View itemView) {
            super(itemView);
            tName = itemView.findViewById(R.id.name);
            tPhone = itemView.findViewById(R.id.phone);
            fab = itemView.findViewById( R.id.fabx);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // binds the name to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String name = mName.get(position);
        String phone = mPhone.get(position);
        holder.tName.setText(name);
        holder.tPhone.setText(phone);
            int[] androidColors = Context.getResources().getIntArray(R.array.androidcolors);
            int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];

            holder.fab.setBackgroundTintList( ColorStateList.valueOf( randomAndroidColor ) );

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mName.size();
    }



    // convenience method for getting name at click position
    public String getItem(int id) {
        return mName.get(id);
    }

    // allows clicks events to be caught
   public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}