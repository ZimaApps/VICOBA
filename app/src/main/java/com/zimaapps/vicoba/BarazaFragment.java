package com.zimaapps.vicoba;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

import static com.zimaapps.vicoba.HomeActivity.context;
import static com.zimaapps.vicoba.storage.kikobaId;


/**
 * A simple {@link Fragment} subclass.
 */
public class BarazaFragment extends Fragment {
    public LinearLayout vifunguView;
    public EditText kifungu;
    public AndroidMultiPartEntity entity3;
    final Dialog dialog = new Dialog(context);

    DatabaseReference database;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    ImageAdapter mAdapter;
    ArrayList<Image> images = new ArrayList<>();
    FirebaseUser fbUser;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.baraza_fragment, container, false);



        fbUser = FirebaseAuth.getInstance().getCurrentUser();
        if (fbUser == null) {
            finish();
            Log.e("FIREBASE","USER IS NOT PRESENT");
        }else{
            Log.e("FIREBASE","USER IS PRESENT");
        }

        database = FirebaseDatabase.getInstance().getReference();

        // Setup the RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView);


        mLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ImageAdapter(images, context);
        //recyclerView.setAdapter(mAdapter);
        recyclerView.setAdapter(mAdapter);




        // Get the latest 100 images
        Query imagesQuery = database.child("images/"+kikobaId).orderByKey().limitToFirst(100);
        imagesQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.e("FIREBASE","COUNT ");
                // A new image has been added, add it to the displayed list
                final Image image = dataSnapshot.getValue(Image.class);
                //mAdapter.addImage(images);
                images.add(image);

                Log.e("FIREBASE","THE IMAGES "+image.downloadUrl.toString());

                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





        FloatingActionButton fab = view.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, createPost.class);
                startActivity(intent);
            }
        });




        //displayData();
        return view;



    }















    private void finish() {
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_calls, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_call) {

            Intent intent = new Intent(context, HomeActivity.class);
            startActivity(intent);
        }
        return true;
    }










    public void createdialog(String thetitle, String themeseji){

        // custom dialog

        dialog.setTitle("Tafadhali Subiri...");
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.waitdialog);

        //dialog.dismiss();
        dialog.show();




    }




}

