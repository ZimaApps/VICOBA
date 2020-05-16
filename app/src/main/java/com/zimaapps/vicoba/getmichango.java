package com.zimaapps.vicoba;


import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static com.zimaapps.vicoba.storage.ada;
import static com.zimaapps.vicoba.storage.chini;
import static com.zimaapps.vicoba.storage.faini;
import static com.zimaapps.vicoba.storage.hisa;
import static com.zimaapps.vicoba.storage.kiingilio;
import static com.zimaapps.vicoba.storage.kikobaId;

public class getmichango extends AsyncTask<Void, String, JSONArray> {

    public static  JSONArray michangodata;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();


    }

    @Override
    protected JSONArray doInBackground(Void... arg0) {

        michangodata = new JSONArray();
        HttpHandler sh = new HttpHandler();
        // Making a request to url and getting response
        String url = "http://96.46.181.182/FinancialSolutions/Vicoba/App/getmichango.php?kikobaId="+kikobaId;
        String jsonStr = sh.makeServiceCall(url);

        //Log.e("RESULT FROM SERVER", "Response from url: " + jsonStr);
        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);

                // Getting JSON Array node
                michangodata = jsonObj.getJSONArray("michango");



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

        michangodata = result;
        try {
            kiingilio = michangodata.getJSONObject(0).getString("kiingilio");
            ada = michangodata.getJSONObject(0).getString("ada");
            hisa = michangodata.getJSONObject(0).getString("hisa");
            faini = michangodata.getJSONObject(0).getString("faini");
            chini = michangodata.getJSONObject(0).getString("chini");


        }catch (JSONException n){

        }

    }





}


