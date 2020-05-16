package com.zimaapps.vicoba;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

import static com.zimaapps.vicoba.storage.kikobaId;
import static com.zimaapps.vicoba.storage.userId;
import static com.zimaapps.vicoba.storage.userName;
import static com.zimaapps.vicoba.storage.userNumber;

public class createCaseTwo extends AppCompatActivity {

    public AndroidMultiPartEntity entity3;
    public String cheo = "MKITI";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_case_two);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button button3x = (Button)findViewById(R.id.login);
        // Register the onClick listener with the implementation above
        button3x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do something when the corky is clicked
                Log.e("CALLLEEEED", "CALLLEEEED : ");
                createcase();
            }
        });
        RadioButton mkiti = findViewById(R.id.mkiti);
        mkiti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("UnsupportedEncodingExce", "error: MKITI");
                cheo = "MKITI";
            }
        });

        RadioButton katibu = findViewById(R.id.katibu);
        katibu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("UnsupportedEncodingExce", "error: KATIBU");
                cheo = "KATIBU";
            }
        });

        RadioButton mjumbe = findViewById(R.id.mjumbe);
        mjumbe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("UnsupportedEncodingExce", "error: MJUMBE");
                cheo = "MJUMBE";
            }
        });


        TextInputEditText creatorname = findViewById(R.id.creatornamexx);
        creatorname.setText(userName);
        TextInputEditText creatorphone = findViewById(R.id.creatorphonexx);
        creatorphone.setText(userNumber);


    }




    public void createcase(){
        TextInputEditText kikobaname = findViewById(R.id.kikobanamexx);
        String kikobanamex = kikobaname.getText().toString();
        TextInputEditText creatorname = findViewById(R.id.creatornamexx);
        String creatornamex = creatorname.getText().toString();
        TextInputEditText creatorphone = findViewById(R.id.creatorphonexx);
        String creatorphonex = creatorphone.getText().toString();
        TextInputEditText password = findViewById(R.id.creatorpasswordxx);
        String passwordx = "12345";

        if(kikobanamex == "" || kikobanamex.length() < 4){

            createdialog("JINA LA KIKOBA","Jina la kikoba linatakiwa liwe la kipekee, lenye kuanzia herufi tatu na kuendelea. Liwe linaweza kutambulika na wajumbe wa kikoba kirahisi. Jina hili unaweza kubadilisha baadae kwenye settings.");


        }else{

            if(creatornamex == "" || creatornamex.length() < 3 ){

                createdialog("JINA LAKO","Tafadhali, Andika majina yako yote matatu ili uweze kutambulika kirahisi na wajumbe. Majina kamili yanaongeza heshima ya kikoba.");


            }else{


                if(creatorphonex == ""){

                    createdialog("NAMBA YA SIMU","Namba yako ya simu inatakiwa iwe katika mfumo kama huu - 0754244868 ");


                }else{

                    if(passwordx == "" || passwordx.length() < 2){

                        createdialog("NENO LA SIRI","Neno lako la siri linatakiwa liwe herufi nne na kuendelea. Usilisahau neno hili, sababu litaitajika katika kufanya miamala mbalimbali ya kikoba");



                    }else{

                        ////////////////////////////////PREPARING VALUES////////////////////////////////

                        try{

                            entity3 = new AndroidMultiPartEntity(
                                    new AndroidMultiPartEntity.ProgressListener() {

                                        @Override
                                        public void transferred(long num) {
                                            //publishProgress((int) ((num / (float) totalSize) * 100));
                                        }
                                    });

                            userId = generateRandomStringByUUID();
                            kikobaId = generateRandomStringByUUID();
                            entity3.addPart("kikobaname", new StringBody(kikobanamex));
                            entity3.addPart("creatorname", new StringBody(creatornamex));
                            entity3.addPart("creatorphone", new StringBody(creatorphonex));
                            entity3.addPart("password", new StringBody(passwordx));
                            entity3.addPart("creatorid", new StringBody(userId));
                            entity3.addPart("kikobaid", new StringBody(kikobaId));
                            Log.e("UnsupportedEncodingExce", "kikobanamex: " + kikobanamex);
                            Log.e("UnsupportedEncodingExce", "creatornamex: " + creatornamex);
                            Log.e("UnsupportedEncodingExce", "creatorphonex: " + creatorphonex);
                            Log.e("UnsupportedEncodingExce", "passwordx: " + passwordx);
                            Log.e("UnsupportedEncodingExce", "memberID: " + userId);
                            Log.e("UnsupportedEncodingExce", "kikobaId: " + kikobaId);

                        }catch (UnsupportedEncodingException ep){

                            Log.e("UnsupportedEncodingExce", "error: " + ep);

                        }

                        new UploadImagetoserverY().execute();


                        //////////////////////////////////////////////////////////////////////////////////////////////


                    }

                }
            }
        }



    }




    /**
     * Uploading the file to server
     * */
    private class UploadImagetoserverY extends AsyncTask<Void, Integer, String> {
        @Override
        protected void onPreExecute() {

            Log.e("CALLLEEEED", "CALLLEEEED : ");
            ProgressBar loading = findViewById(R.id.loading);
            loading.setVisibility(View.VISIBLE);
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

            String FILE_UPLOAD_URLXX = "http://zimaapps.com/mobileApps/vicoba/createNewKikobaTwo.php";
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

                    createCaseTwo.this.runOnUiThread(new Runnable() {
                        public void run() {
                            createdialog("KUNA TATIZO LA MTADAO","Tafadhali jaribu tena. Kama tatizo litaendelea, piga namba 0754 244 888 kutujulisha kuhusu hili tatizo");

                        }
                    });


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
            ProgressBar loading = findViewById(R.id.loading);
            loading.setVisibility(View.GONE);
            Log.e("IOException", "ERROR from server: " + result);
            if(result.equalsIgnoreCase("700")){



                Intent intent = new Intent(getApplicationContext(), AccountSettings.class);

                startActivity(intent);

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

        final AlertDialog alertDialog = new AlertDialog.Builder(createCaseTwo.this).create(); // Read
        // Update
        alertDialog.setTitle(thetitle);
        alertDialog.setMessage(themeseji);
        alertDialog.setButton(Dialog.BUTTON_POSITIVE, "Ok",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        //progressDialog.dismiss();
                        alertDialog.dismiss();


                    }


                });

        alertDialog.show();
    }





    public static String generateRandomStringByUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }



}
