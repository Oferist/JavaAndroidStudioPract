package com.example.nekrasovglebandreevich_5practpart1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SortAdapter extends RecyclerView.Adapter<SortAdapter.SortViewHolder> {
    private Context mContext;
    private ArrayList<SortItem> mSortList;

    public static class SortViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mNameTextView;
        public TextView mDescriptionTextView;

        public SortViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mNameTextView = itemView.findViewById(R.id.nameTextView);
            mDescriptionTextView = itemView.findViewById(R.id.descriptionTextView);
        }
    }

    public SortAdapter(Context context, ArrayList<SortItem> sortList) {
        mContext = context;
        mSortList = sortList;
    }

    @NonNull
    @Override
    public SortViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.sort_item, parent, false);
        return new SortViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SortViewHolder holder, int position) {
        SortItem currentItem = mSortList.get(position);

        holder.mImageView.setImageResource(currentItem.getImageResource());
        holder.mNameTextView.setText(currentItem.getName());
        holder.mDescriptionTextView.setText(currentItem.getDescription());
    }

    @Override
    public int getItemCount() {
        return mSortList.size();
    }
}
