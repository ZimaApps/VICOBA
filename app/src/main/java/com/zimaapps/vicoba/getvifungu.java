package com.zimaapps.vicoba;


import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.zimaapps.vicoba.storage.kikobaId;


public class getvifungu extends AsyncTask<Void, String, JSONArray> {

    public static  JSONArray thedata;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();


    }

    @Override
    protected JSONArray doInBackground(Void... arg0) {

        thedata = new JSONArray();
        HttpHandler sh = new HttpHandler();
        // Making a request to url and getting response
        String url = "http://zimaapps.com/mobileApps/vicoba/getvifungu.php?kikobaId="+kikobaId;
        String jsonStr = sh.makeServiceCall(url);

        //Log.e("RESULT FROM SERVER", "Response from url: " + jsonStr);
        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);

                // Getting JSON Array node
                thedata = jsonObj.getJSONArray("vifungu");


            } catch (final JSONException e) {
                Log.e("RESULT FROM SERVER", "Json parsing error: " + e.getMessage());


            }

        } else {
            Log.e("RESULT FROM SERVER", "Couldn't get json from server.");

        }

        return thedata;
    }

    @Override
    protected void onPostExecute(JSONArray result) {
        super.onPostExecute(result);

        thedata = result;
        //Log.e("RESULT FROM SERVER", "Couldn't get json from serverXXXXXX..."+ result);

    }


}


