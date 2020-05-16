package com.zimaapps.vicoba.data;

import android.os.AsyncTask;
import android.util.Log;

import com.zimaapps.vicoba.AndroidMultiPartEntity;
import com.zimaapps.vicoba.HttpHandler;
import com.zimaapps.vicoba.User;
import com.zimaapps.vicoba.createCase;
import com.zimaapps.vicoba.data.model.LoggedInUser;

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
import java.util.ArrayList;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public static ArrayList dataModels;
    public static ArrayList dataModels2;
    public AndroidMultiPartEntity entity3;

    public Result<LoggedInUser> login(String username, String password) throws UnsupportedEncodingException {

        LoggedInUser User = null;
        //String url = "http://zimaapps.com/mobileApps/vicoba/login.php?username=0754244888&password=3333";
        entity3 = new AndroidMultiPartEntity(
                new AndroidMultiPartEntity.ProgressListener() {

                    @Override
                    public void transferred(long num) {
                        //publishProgress((int) ((num / (float) totalSize) * 100));
                    }
                });
        entity3.addPart("username", new StringBody(username));
        entity3.addPart("password", new StringBody(password));

        try {

        User user = new User("","","");

            Log.e("FROMM SERVERRRR", "RESULT: " + uploadFile());

        return new Result.Success<>(User);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }


    }

    public void logout() {
        // TODO: revoke authentication
    }

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

        User user = new User("","","");

        return responseString;


    }



}


