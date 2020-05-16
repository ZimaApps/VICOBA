package com.zimaapps.vicoba.ui.login;

import android.app.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.zimaapps.vicoba.AccountSettings;
import com.zimaapps.vicoba.AndroidMultiPartEntity;
import com.zimaapps.vicoba.R;
import com.zimaapps.vicoba.Splash;
import com.zimaapps.vicoba.createCase;
import com.zimaapps.vicoba.storage;
import com.zimaapps.vicoba.ui.login.LoginViewModel;
import com.zimaapps.vicoba.ui.login.LoginViewModelFactory;

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

import static com.zimaapps.vicoba.storage.userNumber;
import static com.zimaapps.vicoba.storage.userVicoba;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    public AndroidMultiPartEntity entity3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);


        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                try {

                    entity3 = new AndroidMultiPartEntity(
                            new AndroidMultiPartEntity.ProgressListener() {

                                @Override
                                public void transferred(long num) {
                                    //publishProgress((int) ((num / (float) totalSize) * 100));
                                }
                            });
                    entity3.addPart("username", new StringBody(usernameEditText.getText().toString()));
                    entity3.addPart("password", new StringBody(passwordEditText.getText().toString()));

                    new login().execute();

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
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

            String FILE_UPLOAD_URLXX = "http://zimaapps.com/mobileApps/vicoba/login.php";
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

                    LoginActivity.this.runOnUiThread(new Runnable() {
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
            if(result.equalsIgnoreCase("900")){
                createdialog("KUNA TATIZO LA MTADAO","Tafadhali jaribu tena. Kama tatizo litaendelea, piga namba 0754 244 888 kutujulisha kuhusu hili tatizo");


                //Intent intent = new Intent(getApplicationContext(), AccountSettings.class);
                //intent.putExtra("memberID", memberID);
                //intent.putExtra("kikobaId", kikobaId);


                //startActivity(intent);

            }else{
                JSONObject obj = null;
                try {
                    obj = new JSONObject(result);
                    Log.e("OBJECT", "from server: " + obj.getString("userId"));
                    storage.userName = obj.getString("userName");
                    userNumber = obj.getString("userPhone");
                    storage.userId = obj.getString("userId");

                    SQLiteDatabase mydatabase = openOrCreateDatabase("vicoba",MODE_PRIVATE,null);
                    mydatabase.execSQL("CREATE TABLE IF NOT EXISTS user(userName VARCHAR,userPhone VARCHAR,userId VARCHAR);");
                    mydatabase.execSQL("INSERT INTO user VALUES('"+storage.userName+"','"+userNumber+"','"+storage.userId+"');");

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

                Intent intent = new Intent(getApplicationContext(), Splash.class);

                startActivity(intent);

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

        final AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create(); // Read
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





}
