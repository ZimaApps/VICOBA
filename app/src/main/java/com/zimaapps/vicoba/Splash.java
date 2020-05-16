package com.zimaapps.vicoba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteBindOrColumnIndexOutOfRangeException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.zimaapps.vicoba.ui.login.LoginActivity;

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

import static com.zimaapps.vicoba.storage.userName;
import static com.zimaapps.vicoba.storage.userNumber;
import static com.zimaapps.vicoba.storage.userVicoba;

public class Splash extends AppCompatActivity {
    public AndroidMultiPartEntity entity3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        entity3 = new AndroidMultiPartEntity(
                new AndroidMultiPartEntity.ProgressListener() {

                    @Override
                    public void transferred(long num) {
                        //publishProgress((int) ((num / (float) totalSize) * 100));
                    }
                });


        checkIfsignedin();
    }

    public void checkIfsignedin(){
        try{
            SQLiteDatabase mydatabase = openOrCreateDatabase("vicoba",MODE_PRIVATE,null);
            Cursor resultSet = mydatabase.rawQuery("Select * from user",null);
            //resultSet.moveToFirst();
            if( resultSet != null && resultSet.moveToFirst() ){
                String userNamex = resultSet.getString(0);
                userName = userNamex;
                String userPhonex = resultSet.getString(1);
                userNumber = userPhonex;
                String userIdx = resultSet.getString(2);
                storage.userId = userIdx;
                Log.e("SQL RESULT","THE ID " +userIdx);
                Log.e("SQL RESULT","THE NAME " +userNamex);
                Log.e("SQL RESULT","THE NAMBA " +userPhonex);
                resultSet.close();

                try {
                    entity3.addPart("userId", new StringBody(storage.userId));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                new login().execute();

            }else{

                startActivity(new Intent(Splash.this, Choice.class));
            }



        }catch (SQLException v){
            Log.e("SQL ERROR","THE ERROR " +v.getMessage().toString());
            startActivity(new Intent(Splash.this, Choice.class));
        }



    }



    /**
     * Uploading the file to server
     * */
    private class login extends AsyncTask<Void, Integer, String> {
        @Override
        protected void onPreExecute() {

            Log.e("CALLLEEEED", "CALLLEEEED : ");
            //ProgressBar loading = findViewById(R.id.loading);
            //loading.setVisibility(View.VISIBLE);
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

            String FILE_UPLOAD_URLXX = "http://zimaapps.com/mobileApps/vicoba/getvicoba.php";
            //String FILE_UPLOAD_URLXX = "http://192.168.103.129:3000/upload";
            String responseString = null;


            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(FILE_UPLOAD_URLXX);

            try{
                URI website = new URI(FILE_UPLOAD_URLXX);
                httppost.setURI(website);
            }catch (URISyntaxException ex){
                Log.e("URISyntaxException", "error: " + ex);
                //createdialog("KUNA TATIZO LA MTADAO","Tafadhali jaribu tena. Kama tatizo litaendelea, piga namba 0754 244 888 kutujulisha kuhusu hili tatizo");

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
                //createdialog("KUNA TATIZO LA MTADAO","Tafadhali jaribu tena. Kama tatizo litaendelea, piga namba 0754 244 888 kutujulisha kuhusu hili tatizo");

            } catch (IOException e) {
                responseString = e.toString();
                Log.e("IOException", "ERROR from server: " + responseString);
                //createdialog("KUNA TATIZO LA MTADAO","Tafadhali jaribu tena. Kama tatizo litaendelea, piga namba 0754 244 888 kutujulisha kuhusu hili tatizo");

            }

            return responseString;


        }

        @Override
        protected void onPostExecute(String result) {
            //ProgressBar loading = findViewById(R.id.loading);
            //loading.setVisibility(View.GONE);
            Log.e("IOException", "ERROR from server: " + result);
            if(result.equalsIgnoreCase("900")){
                //createdialog("KUNA TATIZO LA MTADAO","Tafadhali jaribu tena. Kama tatizo litaendelea, piga namba 0754 244 888 kutujulisha kuhusu hili tatizo");


                //Intent intent = new Intent(getApplicationContext(), AccountSettings.class);
                //intent.putExtra("memberID", memberID);
                //intent.putExtra("kikobaId", kikobaId);


                //startActivity(intent);

            }else{
                JSONObject obj = null;
                try {
                    obj = new JSONObject(result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JSONArray arr = null;
                try {
                    arr = obj.getJSONObject("vicoba").getJSONArray("vicoba");
                    userVicoba = arr;
                    for (int i = 0; i < arr.length(); i++)
                    {
                        try {
                            String kikobaname = arr.getJSONObject(i).getString("kikobaname");
                            Log.e("OBJECT TWO", "KIKOBA NAME: " + kikobaname);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                startActivity(new Intent(Splash.this, Welcome.class));

            }



            super.onPostExecute(result);
        }
        @Override
        protected void onCancelled() {
            super.onCancelled();
            // Perform error post processing here...
        }

    }





}
