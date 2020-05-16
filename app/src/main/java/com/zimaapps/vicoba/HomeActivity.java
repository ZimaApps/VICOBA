package com.zimaapps.vicoba;

import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.romellfudi.ussdlibrary.USSDApi;
import com.romellfudi.ussdlibrary.USSDController;
import com.zimaapps.vicoba.ui.main.SectionsPagerAdapter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import static com.zimaapps.vicoba.storage.kikobaname;

public class HomeActivity extends AppCompatActivity {
    public static Context context;
    private HashMap map;
    private  int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        TextView title = findViewById(R.id.title);
        title.setText(kikobaname);

        context = this;
        homeSectionsAdapter sectionsPagerAdapter = new homeSectionsAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        map = new HashMap<>();
        map.put("KEY_LOGIN",new HashSet<>(Arrays.asList("espere", "waiting", "loading", "esperando")));
        map.put("KEY_ERROR",new HashSet<>(Arrays.asList("problema", "problem", "error", "null")));

        callUSSD();

    }







    private void callUSSD(){

        //0692410353
        //USSDController.verifyAccesibilityAccess(this);
        USSDApi ussdApi1 = USSDController.getInstance(this);
        USSDApi ussdApi2 = USSDController.getInstance(this);
        USSDApi ussdApi3 = USSDController.getInstance(this);
        USSDApi ussdApi4 = USSDController.getInstance(this);
        USSDApi ussdApi5 = USSDController.getInstance(this);
        USSDApi ussdApi6 = USSDController.getInstance(this);
        USSDApi ussdApi7 = USSDController.getInstance(this);
        USSDApi ussdApi8 = USSDController.getInstance(this);
        //Intent svc = new Intent(Welcome.this, SplashLoadingService.class);



        ussdApi1.callUSSDInvoke("*150*60#",0, map, new USSDController.CallbackInvoke() {
            @Override
            public void responseInvoke(String message) {
                // first option list - select option 1
                Log.e("RESULT FROM USSD 0", "THE MESSAGE: " + message);
                ussdApi2.send("1",new USSDController.CallbackMessage(){
                    @Override
                    public void responseMessage(String message) {
                        // second option list - select option 1
                        Log.e("RESULT FROM USSD 1", "THE MESSAGE: " + message);
                        ussdApi3.send("1",new USSDController.CallbackMessage(){
                            @Override
                            public void responseMessage(String message) {
                                Log.e("RESULT FROM USSD 2", "THE MESSAGE: " + message);
                                ussdApi4.send("0754244888",new USSDController.CallbackMessage(){
                                    @Override
                                    public void responseMessage(String message) {
                                        Log.e("RESULT FROM USSD 3", "THE MESSAGE: " + message);
                                        ussdApi5.send("1000",new USSDController.CallbackMessage(){
                                            @Override
                                            public void responseMessage(String message) {
                                                Log.e("RESULT FROM USSD 4", "THE MESSAGE: " + message);
                                                ussdApi6.send("1011",new USSDController.CallbackMessage(){
                                                    @Override
                                                    public void responseMessage(String message) {
                                                        Log.e("RESULT FROM USSD 5", "THE MESSAGE: " + message);
                                                        ussdApi7.send("1",new USSDController.CallbackMessage(){
                                                            @Override
                                                            public void responseMessage(String message) {
                                                                Log.e("RESULT FROM USSD 6", "THE MESSAGE: " + message);


                                                            }
                                                        });

                                                    }
                                                });

                                            }
                                        });

                                    }
                                });

                            }
                        });
                    }
                });
            }

            @Override
            public void over(String message) {
                // message has the response string data from USSD
                // response no have input text, NOT SEND ANY DATA
            }

        });



        /*
        String simSlot = "0";
        ussdApi.callUSSDInvoke("0692410353",0, map, new USSDController.CallbackInvoke() {
            @Override
            public void responseInvoke(String message) {
                // first option list - select option 1
                ussdApi.send("*150*60#",new USSDController.CallbackMessage(){
                    @Override
                    public void responseMessage(String message) {
                        // second option list - select option 1
                        Log.e("RESULT FROM USSD B 1", "THE MESSAGE: " + message);
                        ussdApi.send("1",new USSDController.CallbackMessage(){
                            @Override
                            public void responseMessage(String message) {
                                Log.e("RESULT FROM USSD B 2", "THE MESSAGE: " + message);
                            }
                        });
                    }
                });
            }

            @Override
            public void over(String message) {
                // message has the response string data from USSD
                // response no have input text, NOT SEND ANY DATA
                Log.e("RESULT FROM USSD B 3", "THE MESSAGE: " + message);
            }

        });

*/

    }







}