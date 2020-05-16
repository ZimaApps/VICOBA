package com.zimaapps.vicoba.ui.welcomeScreen;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.zimaapps.vicoba.AccountSettings;
import com.zimaapps.vicoba.HomeActivity;
import com.zimaapps.vicoba.R;
import com.zimaapps.vicoba.createCase;

import com.zimaapps.vicoba.ui.verifyPhoneScreen.VerifyPhoneActivity;

import static com.zimaapps.vicoba.storage.userNumber;

/**
 * Created by vihaan on 15/06/17.
 */

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        findViewById(R.id.agreeNContinueTVBtn).setOnClickListener(this);
        init();
    }

    private FirebaseAuth mAuth;
    private void init()
    {
        Log.e("AUTHENTIFICATION", "Calling authentification: ");
        mAuth = FirebaseAuth.getInstance();
    }


    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.agreeNContinueTVBtn:
                Intent intent = new Intent(this, VerifyPhoneActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("AUTHENTIFICATION", "Authentification started on start: ");
        // Check auth on Activity start
        if (mAuth.getCurrentUser() != null) {
            onAuthSuccess(mAuth.getCurrentUser());
        }else {
            Log.e("AUTHENTIFICATION", "NO USER NO USER: ");
        }
    }



    private void onAuthSuccess(FirebaseUser user) {
        Log.e("AUTHENTIFICATION", "USER PRESENT USER PRESENT GOING MAIN: "+user.getPhoneNumber().toString());
        userNumber = user.getPhoneNumber().toString();
        startActivity(new Intent(WelcomeActivity.this, HomeActivity.class));
        //startActivity(new Intent(WelcomeActivity.this, AccountSettings.class));
        finish();
    }



}
