package com.zimaapps.vicoba;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.zimaapps.vicoba.ui.welcomeScreen.WelcomeActivity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

import static com.zimaapps.vicoba.AccountSettings.context;
import static com.zimaapps.vicoba.AccountSettings.inflater;
import static com.zimaapps.vicoba.AccountSettings.pageAdapter;
import static com.zimaapps.vicoba.AccountSettings.tabLayout;
import static com.zimaapps.vicoba.createCase.generateRandomStringByUUID;


import static com.zimaapps.vicoba.getvifungu.thedata;
import static com.zimaapps.vicoba.storage.kikobaId;
import static com.zimaapps.vicoba.storage.userId;

/**
 * A simple {@link Fragment} subclass.
 */
public class CallFragment extends Fragment {
    public LinearLayout vifunguView;
    public EditText kifungu;
    public AndroidMultiPartEntity entity3;
    final Dialog dialog = new Dialog(context);
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);


        View view = inflater.inflate(R.layout.fragment_call, container, false);
        vifunguView  = view.findViewById(R.id.vifungu);
        kifungu  = view.findViewById(R.id.kifungu);
        FloatingActionButton myFab = (FloatingActionButton) view.findViewById(R.id.floating_action_button);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                CardView writearea = view.findViewById(R.id.writearea);
                writearea.setVisibility(View.VISIBLE);
                CoordinatorLayout.LayoutParams p = (CoordinatorLayout.LayoutParams) myFab.getLayoutParams();
                p.setAnchorId(View.NO_ID);
                myFab.setLayoutParams(p);
                myFab.setVisibility(View.GONE);

            }
        });

        ImageView cancel = view.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                CardView writearea = view.findViewById(R.id.writearea);
                writearea.setVisibility(View.GONE);
                CoordinatorLayout.LayoutParams p = (CoordinatorLayout.LayoutParams) myFab.getLayoutParams();
                p.setAnchorId(View.NO_ID);
                myFab.setLayoutParams(p);
                myFab.setVisibility(View.VISIBLE);

            }
        });

        ImageView save = view.findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                createdialog("KUNA TATIZO LA MTADAO","Tafadhali jaribu tena. Kama tatizo litaendelea, piga namba 0754 244 888 kutujulisha kuhusu hili tatizo");


                CardView writearea = view.findViewById(R.id.writearea);
                writearea.setVisibility(View.GONE);
                CoordinatorLayout.LayoutParams p = (CoordinatorLayout.LayoutParams) myFab.getLayoutParams();
                p.setAnchorId(View.NO_ID);
                myFab.setLayoutParams(p);
                myFab.setVisibility(View.VISIBLE);

                String kifungux = kifungu.getText().toString();


                try{

                    entity3 = new AndroidMultiPartEntity(
                            new AndroidMultiPartEntity.ProgressListener() {

                                @Override
                                public void transferred(long num) {
                                    //publishProgress((int) ((num / (float) totalSize) * 100));
                                }
                            });

                    entity3.addPart("memberId", new StringBody(userId));
                    entity3.addPart("kikobaId", new StringBody(kikobaId));
                    entity3.addPart("kifunguId", new StringBody(generateRandomStringByUUID()));
                    entity3.addPart("editorId", new StringBody(userId));
                    entity3.addPart("kifungu", new StringBody(kifungux));
                    entity3.addPart("kifunguType", new StringBody("MEMBERS"));


                }catch (UnsupportedEncodingException ep){

                    Log.e("UnsupportedEncodingExce", "error: " + ep);

                }

                new UploadImagetoserverY().execute();



            }
        });



        JSONArray contacts = thedata;
        // looping through All Contacts
        try{
            for (int i = 0; i < contacts.length(); i++) {
                JSONObject c = contacts.getJSONObject(i);

                final String kifungu = c.getString("kifungu");

                //Log.e("RESULT FROM SERVER", "Json parsing error: " + Maelezo);
                //LayoutInflater inflaterx = LayoutInflater.from(context);
                View convertViewxxx = inflater.inflate(R.layout.senders, vifunguView, false);
                TextView fgfr  = convertViewxxx.findViewById(R.id.mesejix);
                fgfr.setText(kifungu);

                if (convertViewxxx.getParent() != null)
                    ((ViewGroup) convertViewxxx.getParent()).removeView(convertViewxxx); // <- fix



                vifunguView.addView(convertViewxxx, 0);


            }
        }catch (JSONException c){
            Log.e("FROM SERVER JSON ERROR", "Json parsing error: " + c);
        }


        //displayData();
        return view;



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







    /**
     * Uploading the file to server
     * */
    private class UploadImagetoserverY extends AsyncTask<Void, Integer, String> {
        @Override
        protected void onPreExecute() {

            Log.e("CALLLEEEED", "CALLLEEEED : ");

            super.onPreExecute();


        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            //progressDialog.setMessage("Uploading IMAGES..." +progress + " %"); // Setting Message

        }

        @Override
        protected String doInBackground(Void... params) {
            return uploadFile();
        }

        @SuppressWarnings("deprecation")
        private String uploadFile() {
            final String haso;

            String FILE_UPLOAD_URLXX = "http://zimaapps.com/mobileApps/vicoba/saveKifungu.php";
            //String FILE_UPLOAD_URLXX = "http://192.168.103.129:3000/upload";
            String responseString = null;


            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(FILE_UPLOAD_URLXX);

            try{
                URI website = new URI(FILE_UPLOAD_URLXX);
                httppost.setURI(website);
            }catch (URISyntaxException ex){
                Log.e("URISyntaxException", "error: " + ex);
                createdialog("KUNA TATIZO LA MTADAO","Tafadhali jaribu tena. Kama tatizo litaendelea, piga namba 0754 244 888 kutujulisha kuhusu hili tatizo");

            }



            try {



                //totalSize = entity3.getContentLength();
                httppost.setEntity(entity3);

                // Making server call
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity r_entity = response.getEntity();

                System.out.println("executing request " + httppost.getRequestLine());
                System.out.println("Now uploading your file ");

                //publishProgress(params.);

                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {

                    // Server response
                    responseString = EntityUtils.toString(r_entity);
                    //haso = responseString;

                } else {

                    Log.e("UPLOADING111PPP", "Response from server: " + Integer.toString(statusCode));



                }

            } catch (ClientProtocolException e) {
                responseString = e.toString();
                Log.e("ClientProtocolException", "ERROR from server: " + responseString);
                createdialog("KUNA TATIZO LA MTADAO","Tafadhali jaribu tena. Kama tatizo litaendelea, piga namba 0754 244 888 kutujulisha kuhusu hili tatizo");

            } catch (IOException e) {
                responseString = e.toString();
                Log.e("IOException", "ERROR from server: " + responseString);
                createdialog("KUNA TATIZO LA MTADAO","Tafadhali jaribu tena. Kama tatizo litaendelea, piga namba 0754 244 888 kutujulisha kuhusu hili tatizo");

            }

            return responseString;


        }

        @Override
        protected void onPostExecute(String result) {

            Log.e("IOException", "ERROR from server: " + result);
            if(result.equalsIgnoreCase("700")){

                new getvifungu().execute();
                dialog.dismiss();
                //new CallFragment().onCreate();

                AccountSettings.viewPager.setAdapter(pageAdapter);
                //AccountSettings.tabCalls.performClick();

                TabLayout.Tab newTab =  tabLayout.getTabAt(2);
                //TabLayout.Tab newTab = tabLayout.findViewById(R.id.tabCalls);
                newTab.select();

            }else{
                createdialog("KUNA TATIZO LA MTADAO","Tafadhali jaribu tena. Kama tatizo litaendelea, piga namba 0754 244 888 kutujulisha kuhusu hili tatizo");


            }



            super.onPostExecute(result);
        }
        @Override
        protected void onCancelled() {
            super.onCancelled();
            // Perform error post processing here...
        }

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

