package com.zimaapps.vicoba;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.zimaapps.vicoba.AccountSettings.context;

import static com.zimaapps.vicoba.storage.creatorid;
import static com.zimaapps.vicoba.storage.creatorname;
import static com.zimaapps.vicoba.storage.creatorphone;
import static com.zimaapps.vicoba.storage.kikobaname;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private JSONArray mDataset;
    public Context contextx;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View view;
        public MyViewHolder(View v) {
            super(v);
            view = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(JSONArray myDataset, Welcome welcome) {

        mDataset = myDataset;
        contextx = welcome;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.my_text_view, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        try {
            TextView jinao = holder.view.findViewById(R.id.kikobanamep);
            JSONObject obj = new JSONObject(mDataset.getString(position));
            
            jinao.setText(obj.getString("kikobaname").toUpperCase());
        } catch (JSONException e) {
            e.printStackTrace();
        }


            holder.view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    try {

                    JSONObject obj = new JSONObject(mDataset.getString(position));
                    storage.kikobaId = obj.getString("kikobaid");
                    kikobaname = obj.getString("kikobaname");
                     creatorid = obj.getString("creatorid");
                    creatorname = obj.getString("creatorname");
                    creatorphone = obj.getString("creatorphone");
                    Intent intent = new Intent(contextx, HomeActivity.class);
                    contextx.startActivity(intent);

                } catch (Exception e) {
                    e.printStackTrace();
                }

                }
            });


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {

        return mDataset.length();
    }
}
