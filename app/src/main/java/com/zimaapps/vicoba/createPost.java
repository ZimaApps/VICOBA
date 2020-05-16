package com.zimaapps.vicoba;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.zimaapps.vicoba.HomeActivity.context;

import static com.zimaapps.vicoba.storage.kikobaId;
import static com.zimaapps.vicoba.storage.userName;
import static com.zimaapps.vicoba.storage.userNumber;

public class createPost extends AppCompatActivity {
    public LinearLayout vifunguView;
    public EditText kifungu;
    public AndroidMultiPartEntity entity3;
    final Dialog dialog = new Dialog(context);
    DatabaseReference database;
    public Uri uri = null;

    static final int RC_PERMISSION_READ_EXTERNAL_STORAGE = 1;
    static final int RC_IMAGE_GALLERY = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        database = FirebaseDatabase.getInstance().getReference();

        ImageButton btn = findViewById(R.id.selectimage);
        btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                uploadImage();
            }
        });



    }





    public void uploadImage() {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, RC_PERMISSION_READ_EXTERNAL_STORAGE);
        } else {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent, RC_IMAGE_GALLERY);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == RC_PERMISSION_READ_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, RC_IMAGE_GALLERY);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_IMAGE_GALLERY && resultCode == RESULT_OK) {
            uri = data.getData();

            ImageView vui = findViewById(R.id.theimage);
            Picasso.get().load(uri).into(vui);

        }
    }




    public void createdialog(String thetitle, String themeseji){

        // custom dialog

        dialog.setTitle(thetitle+" : "+ themeseji);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.waitdialog);

        //dialog.dismiss();
        dialog.show();




    }


    public void preuploadData(View v){


        String timeStampx = new SimpleDateFormat("dd/MM HHmm").format(new Date());
        String timex = timeStampx;
        String ujumbe = "";


        EditText kifungu = findViewById(R.id.kifungu);
        String kifungux = kifungu.getText().toString();


        if(uri == null && kifungux.trim().equalsIgnoreCase("")){
            createdialog("Tatizo","Andika ujumbe au chagua picha");
        }else if(uri !=null && kifungux.trim().equalsIgnoreCase("")){
            ujumbe = "";
            uploadData(ujumbe);

        }else if(uri !=null && kifungux.trim().length() > 2){
            ujumbe = kifungux;
            uploadData(ujumbe);
        }else if(uri ==null && kifungux.trim().length() > 2){
            ujumbe = kifungux;
            Log.e("SEND FIREBASE","MESEJI TUPU");
            String key = database.child("images").push().getKey();
            Image image = new Image(key, userNumber, "", userName, timex, ujumbe);
            database.child("images").child(kikobaId + "/" + key).setValue(image);

        }







    }


    public void uploadData(String ujumbe){
        UploadTask uploadTask= null;
        String timeStampx = new SimpleDateFormat("dd/MM HHmm").format(new Date());
        String timex = timeStampx;

        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        StorageReference imagesRef = storageRef.child("images");
        StorageReference userRef = imagesRef.child(kikobaId);
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String filename = kikobaId + "_" + timeStamp;
        StorageReference fileRef = userRef.child(filename);
        uploadTask = fileRef.putFile(uri);

        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }

                // Continue with the task to get the download URL
                return fileRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();


                    Toast.makeText(context, "Upload finished!", Toast.LENGTH_SHORT).show();

                    String key = database.child("images").push().getKey();
                    Image image = new Image(key, userNumber, downloadUri.toString(), userName, timex, ujumbe);
                    database.child("images").child(kikobaId + "/" + key).setValue(image);


                } else {
                    // Handle failures
                    // ...
                    createdialog("Tatizo","La mtandao, tafadali jaribu tena");
                }
            }
        });
    }




}
