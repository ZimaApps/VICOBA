package com.zimaapps.vicoba;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;


import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;


import static com.zimaapps.vicoba.storage.ada;
import static com.zimaapps.vicoba.storage.chini;
import static com.zimaapps.vicoba.storage.faini;
import static com.zimaapps.vicoba.storage.fainiyamikopo;
import static com.zimaapps.vicoba.storage.hisa;
import static com.zimaapps.vicoba.storage.kiingilio;
import static com.zimaapps.vicoba.storage.kikobaId;
import static com.zimaapps.vicoba.storage.mikopo;
import static com.zimaapps.vicoba.storage.riba;
import static com.zimaapps.vicoba.storage.state;
import static com.zimaapps.vicoba.storage.tenure;


public class AccountSettings extends AppCompatActivity {

        Toolbar toolbar;
        public static TabLayout tabLayout;
        public static ViewPager viewPager;
        public static PageAdapter pageAdapter;
        TabItem tabChats;

        TabItem tabStatus;
        public static TabItem tabCalls;
        public static Context context;
        public static LayoutInflater inflater;
        public static String serverResult;
        public String Section;
        public String kiingilio = "0";
        public String what = "";
        public String thevalue = "0";


    List<WeakReference<Fragment>> fragList = new ArrayList<WeakReference<Fragment>>();
    @Override
    public void onAttachFragment (Fragment fragment) {
        fragList.add(new WeakReference(fragment));
    }



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_settings);

            toolbar = findViewById(R.id.toolbar);
            toolbar.setTitle("ANDAA KATIBA");
            setSupportActionBar(toolbar);

            //kikobaId = "43081ff7-2690-4218-b156-6ce16e199c72";

            context = this;
            Section ="";
            tabLayout = findViewById(R.id.tablayout);
            tabChats = findViewById(R.id.tabChats);
            tabStatus = findViewById(R.id.tabStatus);
            tabCalls = findViewById(R.id.tabCalls);
            viewPager = findViewById(R.id.viewPager);

            pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
            //viewPager.setAdapter(pageAdapter);
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    state = false;
                    Log.e("FRAGMENT CHANGE", "ONPAGESCROLLED.");
                }
                @Override
                public void onPageSelected(int position) {
                    // Here's your instance
                    state = false;
                    Log.e("FRAGMENT CHANGE", "ONPAGE SELECTED.");
                }
                @Override
                public void onPageScrollStateChanged(int state) {
                    storage.state = false;
                    Log.e("FRAGMENT CHANGE", "ONPAGE SCROLL STATE CHANGED.");
                }
            });
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager.setCurrentItem(tab.getPosition());
                    if (tab.getPosition() == 1) {
                        toolbar.setBackgroundColor(ContextCompat.getColor(AccountSettings.this,
                                R.color.colorPrimary));
                        tabLayout.setBackgroundColor(ContextCompat.getColor(AccountSettings.this,
                                R.color.colorPrimary));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            getWindow().setStatusBarColor(ContextCompat.getColor(AccountSettings.this,
                                    R.color.colorPrimary));
                        }
                    } else if (tab.getPosition() == 2) {
                        toolbar.setBackgroundColor(ContextCompat.getColor(AccountSettings.this, R.color.colorPrimary));
                        tabLayout.setBackgroundColor(ContextCompat.getColor(AccountSettings.this, R.color.colorPrimary));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            getWindow().setStatusBarColor(ContextCompat.getColor(AccountSettings.this, R.color.colorPrimary));
                        }
                    } else if (tab.getPosition() == 3) {
                        toolbar.setBackgroundColor(ContextCompat.getColor(AccountSettings.this,
                                R.color.colorPrimary));
                        tabLayout.setBackgroundColor(ContextCompat.getColor(AccountSettings.this,
                                R.color.colorPrimary));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            getWindow().setStatusBarColor(ContextCompat.getColor(AccountSettings.this,
                                    R.color.colorPrimary));
                        }
                    }
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
            viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));



            ////////////////////////LOAD ALL DATA FOR PREFILL//////////////////


            new getmichangonow().execute();
            new getvifungu().execute();

        }




    ///////////////////////////////////////////////////////////////////////////////////////////////////

    public void saveWhat(String whatx, String value){
        Log.e("IMEFIKA HAPAxxxx", "Couldn't "+what);
        what = whatx;
        thevalue = value;
        new saveFragmentData().execute();

    }





    public class saveFragmentData extends AsyncTask<Void, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.e("NETWORK CONNECTION", "CALLED XX.");

        }

        @Override
        protected String doInBackground(Void... arg0) {
            serverResult = "";
            //Log.e("RESULT FROM SERVER", "this json HAPAA.");
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "http://zimaapps.com/mobileApps/vicoba/updatewhat.php?kikobaId="+kikobaId+"&what="+what+"&value="+thevalue.replaceAll("[,]", "");
            //String url = "http://zimaapps.com/mobileApps/vicoba/updatewhat.php?kikobaId=43081ff7-2690-4218-b156-6ce16e199c72&what="+what+"&value="+thevalue.replaceAll("[,]", "");
            String jsonStr = sh.makeServiceCall(url);

            //Log.e("RESULT FROM SERVER", "this json."+jsonStr);

            Log.e("RESULT FROM SERVER", "Response from url: " + jsonStr);
            if (jsonStr != null) {
                serverResult = jsonStr;

            } else {
                Log.e("RESULT FROM SERVER", "Couldn't get json from server.");

            }

            return serverResult;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //Log.e("RESULT FROM SERVERxixix", "Couldn't get json from server."+result);
            serverResult = result;
            setWhatadata(serverResult);


        }


    }








    /////////////////////////////////////////////////////

    public void setWhatadata(String whats){
        Log.e("RESULT SERVERxxxxHAPAA", "Couldn't "+whats);
        Fragment frag = getVisibleFragment();

        if(what.trim().equalsIgnoreCase("kiingilio")){
            if(whats.trim().equalsIgnoreCase("13") || whats.trim().equalsIgnoreCase("")){
                ((ProgressBar) frag.getView().findViewById(R.id.kiingilioloading)).setVisibility(View.GONE);
                ((ImageView) frag.getView().findViewById(R.id.kiingiliook)).setVisibility(View.GONE);
                ((ImageView) frag.getView().findViewById(R.id.kiingilioerror)).setVisibility(View.VISIBLE);
                Log.e("RESULT FROM SERVERxiPPP", "ERRRRRRORRRRRR."+whats);
            }else{
                ((ProgressBar) frag.getView().findViewById(R.id.kiingilioloading)).setVisibility(View.GONE);
                ((ImageView) frag.getView().findViewById(R.id.kiingiliook)).setVisibility(View.VISIBLE);
                ((ImageView) frag.getView().findViewById(R.id.kiingilioerror)).setVisibility(View.GONE);
                Log.e("RESULT FROM SERVERxiPPP", "GOOOODDDDDD."+whats);
            }


        }

        if(what.trim().equalsIgnoreCase("ada")){
            if(whats.trim().equalsIgnoreCase("13") || whats.trim().equalsIgnoreCase("")){
                ((ProgressBar) frag.getView().findViewById(R.id.adaloading)).setVisibility(View.GONE);
                ((ImageView) frag.getView().findViewById(R.id.adaok)).setVisibility(View.GONE);
                ((ImageView) frag.getView().findViewById(R.id.adaerror)).setVisibility(View.VISIBLE);
                Log.e("RESULT FROM SERVERxiPPP", "ERRRRRRORRRRRR."+whats);
            }else{
                ((ProgressBar) frag.getView().findViewById(R.id.adaloading)).setVisibility(View.GONE);
                ((ImageView) frag.getView().findViewById(R.id.adaok)).setVisibility(View.VISIBLE);
                ((ImageView) frag.getView().findViewById(R.id.adaerror)).setVisibility(View.GONE);
                Log.e("RESULT FROM SERVERxiPPP", "GOOOODDDDDD."+whats);
            }


        }

        if(what.trim().equalsIgnoreCase("hisa")){
            if(whats.trim().equalsIgnoreCase("13") || whats.trim().equalsIgnoreCase("")){
                ((ProgressBar) frag.getView().findViewById(R.id.hisaloading)).setVisibility(View.GONE);
                ((ImageView) frag.getView().findViewById(R.id.hisaok)).setVisibility(View.GONE);
                ((ImageView) frag.getView().findViewById(R.id.hisaerror)).setVisibility(View.VISIBLE);
                Log.e("RESULT FROM SERVERxiPPP", "ERRRRRRORRRRRR."+whats);
            }else{
                ((ProgressBar) frag.getView().findViewById(R.id.hisaloading)).setVisibility(View.GONE);
                ((ImageView) frag.getView().findViewById(R.id.hisaok)).setVisibility(View.VISIBLE);
                ((ImageView) frag.getView().findViewById(R.id.hisaerror)).setVisibility(View.GONE);
                Log.e("RESULT FROM SERVERxiPPP", "GOOOODDDDDD."+whats);
            }


        }

        if(what.trim().equalsIgnoreCase("faini")){
            if(whats.trim().equalsIgnoreCase("13") || whats.trim().equalsIgnoreCase("")){
                ((ProgressBar) frag.getView().findViewById(R.id.fainiloading)).setVisibility(View.GONE);
                ((ImageView) frag.getView().findViewById(R.id.fainiok)).setVisibility(View.GONE);
                ((ImageView) frag.getView().findViewById(R.id.fainierror)).setVisibility(View.VISIBLE);
                Log.e("RESULT FROM SERVERxiPPP", "ERRRRRRORRRRRR."+whats);
            }else{
                ((ProgressBar) frag.getView().findViewById(R.id.fainiloading)).setVisibility(View.GONE);
                ((ImageView) frag.getView().findViewById(R.id.fainiok)).setVisibility(View.VISIBLE);
                ((ImageView) frag.getView().findViewById(R.id.fainierror)).setVisibility(View.GONE);
                Log.e("RESULT FROM SERVERxiPPP", "GOOOODDDDDD."+whats);
            }


        }

        if(what.trim().equalsIgnoreCase("chini")){
            if(whats.trim().equalsIgnoreCase("13") || whats.trim().equalsIgnoreCase("")){
                ((ProgressBar) frag.getView().findViewById(R.id.chiniloading)).setVisibility(View.GONE);
                ((ImageView) frag.getView().findViewById(R.id.chiniok)).setVisibility(View.GONE);
                ((ImageView) frag.getView().findViewById(R.id.chinierror)).setVisibility(View.VISIBLE);
                Log.e("RESULT FROM SERVERxiPPP", "ERRRRRRORRRRRR."+whats);
            }else{
                ((ProgressBar) frag.getView().findViewById(R.id.chiniloading)).setVisibility(View.GONE);
                ((ImageView) frag.getView().findViewById(R.id.chiniok)).setVisibility(View.VISIBLE);
                ((ImageView) frag.getView().findViewById(R.id.chinierror)).setVisibility(View.GONE);
                Log.e("RESULT FROM SERVERxiPPP", "GOOOODDDDDD."+whats);
            }


        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////
        Fragment frag2 = getVisibleFragment2();

        if(what.trim().equalsIgnoreCase("riba")){
            if(whats.trim().equalsIgnoreCase("13") || whats.trim().equalsIgnoreCase("")){
                ((ProgressBar) frag2.getView().findViewById(R.id.ribaloading)).setVisibility(View.GONE);
                ((ImageView) frag2.getView().findViewById(R.id.ribaok)).setVisibility(View.GONE);
                ((ImageView) frag2.getView().findViewById(R.id.ribaerror)).setVisibility(View.VISIBLE);
                Log.e("RESULT FROM SERVERxiPPP", "ERRRRRRORRRRRR."+whats);
            }else{
                ((ProgressBar) frag2.getView().findViewById(R.id.ribaloading)).setVisibility(View.GONE);
                ((ImageView) frag2.getView().findViewById(R.id.ribaok)).setVisibility(View.VISIBLE);
                ((ImageView) frag2.getView().findViewById(R.id.ribaerror)).setVisibility(View.GONE);
                Log.e("RESULT FROM SERVERxiPPP", "GOOOODDDDDD."+whats);
            }


        }

        if(what.trim().equalsIgnoreCase("fainiyamikopo")){
            if(whats.trim().equalsIgnoreCase("13") || whats.trim().equalsIgnoreCase("")){
                ((ProgressBar) frag2.getView().findViewById(R.id.fainiyamikopoloading)).setVisibility(View.GONE);
                ((ImageView) frag2.getView().findViewById(R.id.fainiyamikopook)).setVisibility(View.GONE);
                ((ImageView) frag2.getView().findViewById(R.id.fainiyamikopoerror)).setVisibility(View.VISIBLE);
                Log.e("RESULT FROM SERVERxiPPP", "ERRRRRRORRRRRR."+whats);
            }else{
                ((ProgressBar) frag2.getView().findViewById(R.id.fainiyamikopoloading)).setVisibility(View.GONE);
                ((ImageView) frag2.getView().findViewById(R.id.fainiyamikopook)).setVisibility(View.VISIBLE);
                ((ImageView) frag2.getView().findViewById(R.id.fainiyamikopoerror)).setVisibility(View.GONE);
                Log.e("RESULT FROM SERVERxiPPP", "GOOOODDDDDD."+whats);
            }


        }






    }










    ///////////////////////////////////GET MICHANGO DATA////////////////////////////////


    public class getmichangonow extends AsyncTask<Void, String, JSONArray> {

        public JSONArray michangodata;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected JSONArray doInBackground(Void... arg0) {

            michangodata = new JSONArray();
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "http://zimaapps.com/mobileApps/vicoba/getkatiba.php?kikobaId="+kikobaId;
            //String url = "http://zimaapps.com/mobileApps/vicoba/getkatiba.php?kikobaId=43081ff7-2690-4218-b156-6ce16e199c72";
            String jsonStr = sh.makeServiceCall(url);

            //Log.e("RESULT FROM SERVER", "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    michangodata = jsonObj.getJSONArray("katiba");



                } catch (final JSONException e) {
                    Log.e("RESULT FROM SERVER", "Json parsing error: " + e.getMessage());


                }

            } else {
                Log.e("RESULT FROM SERVER", "Couldn't get json from server.");

            }

            return michangodata;
        }

        @Override
        protected void onPostExecute(JSONArray result) {
            super.onPostExecute(result);
            Log.e("RESULT FROM SERVER", "GET MICHANGO ."+result);
            storage.groupdata = result;
            michangodata = result;
            try {
                kiingilio = commalize2(michangodata.getJSONObject(0).getString("kiingilio"));
                ada = commalize2(michangodata.getJSONObject(0).getString("ada"));
                hisa = commalize2(michangodata.getJSONObject(0).getString("hisa"));
                faini = commalize2(michangodata.getJSONObject(0).getString("faini"));
                chini = commalize2(michangodata.getJSONObject(0).getString("chini"));
                mikopo = michangodata.getJSONObject(0).getString("mikopo");

                riba = michangodata.getJSONObject(0).getString("riba");
                tenure = michangodata.getJSONObject(0).getString("tenure");
                fainiyamikopo = commalize2(michangodata.getJSONObject(0).getString("fainiyamikopo"));

                Log.e("SHOW ADAPTER", "CALLING ADAPTER .");
                prepareFragmentsandLoadData();




            }catch (JSONException n){

            }

        }





    }





    public void prepareFragmentsandLoadData(){

        Log.e("SHOW ADAPTER", "ADAPTER IS BEING CALLED .");
        viewPager.setAdapter(pageAdapter);


        Fragment frag = getVisibleFragment();
        ((EditText) frag.getView().findViewById(R.id.kiingilio)).setText(kiingilio);
        ((EditText) frag.getView().findViewById(R.id.ada)).setText(ada);
        ((EditText) frag.getView().findViewById(R.id.hisa)).setText(hisa);
        ((EditText) frag.getView().findViewById(R.id.faini)).setText(faini);
        ((EditText) frag.getView().findViewById(R.id.chini)).setText(chini);

        ((ProgressBar) frag.getView().findViewById(R.id.kiingilioloading)).setVisibility(View.GONE);
        ((ProgressBar) frag.getView().findViewById(R.id.adaloading)).setVisibility(View.GONE);
        ((ProgressBar) frag.getView().findViewById(R.id.hisaloading)).setVisibility(View.GONE);
        ((ProgressBar) frag.getView().findViewById(R.id.fainiloading)).setVisibility(View.GONE);
        ((ProgressBar) frag.getView().findViewById(R.id.chiniloading)).setVisibility(View.GONE);
        ((ImageView) frag.getView().findViewById(R.id.chiniok)).setVisibility(View.GONE);
        ////////////////////////////////////////////////////////////////////////////////////////////
        Fragment frag2 = getVisibleFragment2();
        ((EditText) frag2.getView().findViewById(R.id.fainikiasiamount)).setText(fainiyamikopo);
        ((EditText) frag2.getView().findViewById(R.id.ribaasilimia)).setText(riba);

    }







    public Fragment getVisibleFragment(){
        Fragment frag =null;
        ArrayList<Fragment> ret = new ArrayList<Fragment>();
        for(WeakReference<Fragment> ref : fragList) {
            Fragment f = ref.get();
            Log.e("FRAGMENT LIST", "LIST SIZE."+fragList.size());

            if(f != null) {
                Log.e("FRAGMENT TAGS", "THE TAG ALL."+f.getTag().toString());
                frag = fragList.get(0).get();

            }

        }

        return frag;

    }

    public Fragment getVisibleFragment2(){
        Fragment frag =null;
        ArrayList<Fragment> ret = new ArrayList<Fragment>();
        for(WeakReference<Fragment> ref : fragList) {
            Fragment f = ref.get();
            Log.e("FRAGMENT LIST", "LIST SIZE."+fragList.size());

            if(f != null) {
                Log.e("FRAGMENT TAGS", "THE TAG ALL."+f.getTag().toString());
                frag = fragList.get(1).get();

            }

        }

        return frag;

    }



    public String commalize2(String co){

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setGroupingUsed(true);
        decimalFormat.setGroupingSize(3);

        float fx = Float.parseFloat(co.replaceAll("[,]", ""));
        return  decimalFormat.format(fx);
    }

    public boolean checkinternet() {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        //we are connected to a network
        connected = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;


        return connected;


    }



}
