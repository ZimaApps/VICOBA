package com.zimaapps.vicoba;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputEditText;

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

import static android.content.Context.MODE_PRIVATE;
import static com.zimaapps.vicoba.HomeActivity.context;
import static com.zimaapps.vicoba.storage.userId;
import static com.zimaapps.vicoba.storage.userName;
import static com.zimaapps.vicoba.storage.userNumber;

public class changeUserDataDialog extends DialogFragment {
    View v;
    public AndroidMultiPartEntity entity3;

    static changeUserDataDialog newInstance() {
        return new changeUserDataDialog();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.changeuserdata, container, false);
        Button button3x = v.findViewById(R.id.loginz);
        button3x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do something when the corky is clicked
                Log.e("CALLLEEEED", "CALLLEEEED : ");
                createcase();
            }
        });
        TextInputEditText creatornamez = v.findViewById(R.id.creatornamez);
        creatornamez.setText(userName);
        TextInputEditText creatorphonez = v.findViewById(R.id.creatorphonez);
        creatorphonez.setText(storage.userNumber);
        TextInputEditText creatorpasswordz = v.findViewById(R.id.creatorpasswordz);
        creatorpasswordz.setText("****");



        return v;
    }





    public void createcase(){
        //TextInputEditText kikobaname = v.findViewById(R.id.kikobaname);
        String kikobanamex = "hellodcfd";
        TextInputEditText creatorname = v.findViewById(R.id.creatornamez);
        String creatornamex = creatorname.getText().toString();
        TextInputEditText creatorphone = v.findViewById(R.id.creatorphonez);
        String creatorphonex = creatorphone.getText().toString();
        TextInputEditText password = v.findViewById(R.id.creatorpasswordz);
        String passwordx = password.getText().toString();



        if(kikobanamex == "" || kikobanamex.length() < 4){

            createdialog("JINA LA KIKOBA","Jina la kikoba linatakiwa liwe la kipekee, lenye kuanzia herufi tatu na kuendelea. Liwe linaweza kutambulika na wajumbe wa kikoba kirahisi. Jina hili unaweza kubadilisha baadae kwenye settings.");


        }else{

            if(creatornamex == "" || creatornamex.length() < 3 ){

                createdialog("JINA LAKO","Tafadhali, Andika majina yako yote matatu ili uweze kutambulika kirahisi na wajumbe. Majina kamili yanaongeza heshima ya kikoba.");


            }else{


                if(creatorphonex == ""){

                    createdialog("NAMBA YA SIMU","Namba yako ya simu inatakiwa iwe katika mfumo kama huu - 0754244868 ");


                }else{

                    if(passwordx == "" || passwordx.length() < 4){

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


                            entity3.addPart("kikobaname", new StringBody(kikobanamex));
                            entity3.addPart("creatorname", new StringBody(creatornamex));
                            entity3.addPart("creatorphone", new StringBody(creatorphonex));
                            entity3.addPart("password", new StringBody(passwordx));
                            entity3.addPart("creatorid", new StringBody(storage.userId));
                            //entity3.addPart("kikobaid", new StringBody(kikobaId));
                            Log.e("UnsupportedEncodingExce", "kikobanamex: " + kikobanamex);
                            Log.e("UnsupportedEncodingExce", "creatornamex: " + creatornamex);
                            Log.e("UnsupportedEncodingExce", "creatorphonex: " + creatorphonex);
                            Log.e("UnsupportedEncodingExce", "passwordx: " + passwordx);


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
            ProgressBar loading = v.findViewById(R.id.progressy);
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

            String FILE_UPLOAD_URLXX = "http://zimaapps.com/mobileApps/vicoba/updateUser.php";
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
            ProgressBar loading = v.findViewById(R.id.progressy);
            loading.setVisibility(View.GONE);
            Log.e("IOException", "ERROR from server: " + result);
            if(result.equalsIgnoreCase("700")){

                TextInputEditText creatorname = v.findViewById(R.id.creatornamez);
                String creatornamex = creatorname.getText().toString();
                TextInputEditText creatorphone = v.findViewById(R.id.creatorphonez);
                String creatorphonex = creatorphone.getText().toString();


                userName = creatornamex;
                userNumber = creatorphonex;

                SQLiteDatabase mydatabase = context.openOrCreateDatabase("vicoba",MODE_PRIVATE,null);
                mydatabase.delete("user", null, null);
                mydatabase.execSQL("CREATE TABLE IF NOT EXISTS user(userName VARCHAR,userPhone VARCHAR,userId VARCHAR);");

                mydatabase.execSQL("INSERT INTO user VALUES('"+userName+"','"+userNumber+"','"+storage.userId+"');");




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

        final AlertDialog alertDialog = new AlertDialog.Builder(context).create(); // Read
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
        return UUID.randomUUID().toString();
    }





}