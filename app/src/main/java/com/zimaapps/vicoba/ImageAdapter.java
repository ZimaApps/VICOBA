package com.zimaapps.vicoba;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private ArrayList<Image> mDataset;
    private Context mActivity;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public TextView messageTV;
        public TextView lastMessageTimeTV;
        public ImageView mImageView;


        public ViewHolder(View v) {
            super(v);
            mTextView = v.findViewById(R.id.nameTV);
            messageTV = v.findViewById(R.id.messageTV);
            lastMessageTimeTV = v.findViewById(R.id.lastMessageTimeTV);
            mImageView = v.findViewById(R.id.imageView);

        }
    }

    public ImageAdapter(ArrayList<Image> myDataset, Context activity) {
        mDataset = myDataset;
        mActivity = activity;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_view, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Image image = (Image) mDataset.get(position);
        holder.mTextView.setText(image.namex);
        holder.messageTV.setText(image.ujumbe);
        holder.lastMessageTimeTV.setText(image.time);
        if(image.downloadUrl == null || image.downloadUrl.equalsIgnoreCase("")){

        }else {
            Picasso.get().load(image.downloadUrl).into(holder.mImageView);
        }


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void addImage(Image image) {
        mDataset.add(0, image);
        notifyDataSetChanged();
    }



}
