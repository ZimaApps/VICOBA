package com.zimaapps.vicoba;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.DecimalFormat;

import static com.zimaapps.vicoba.AccountSettings.context;

import static com.zimaapps.vicoba.storage.faini;
import static com.zimaapps.vicoba.storage.fainiyamikopo;
import static com.zimaapps.vicoba.storage.kiingilio;
import static com.zimaapps.vicoba.storage.mikopo;
import static com.zimaapps.vicoba.storage.riba;
import static com.zimaapps.vicoba.storage.state;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatusFragment extends Fragment {
    public View mikopoSectionView2;
    public String source = "1";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        mikopoSectionView2 = inflater.inflate(R.layout.fragment_status, container, false);

        EditText b1x = mikopoSectionView2.findViewById(R.id.ribaasilimia);
        b1x.setText(kiingilio);
        b1x.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    state = true;
                } else {

                }
            }
        });

        EditText b1xs = mikopoSectionView2.findViewById(R.id.fainikiasiamount);
        b1xs.setText(faini);
        b1xs.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    state = true;
                } else {

                }
            }
        });


        ////////////////////////////MIKOPO YES NO LISTERNERS/////////////////////////////////
        RadioButton mikopondioradio = mikopoSectionView2.findViewById(R.id.mikopondio);
        mikopondioradio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AccountSettings)getActivity()).Section = "mikopo";
                ((AccountSettings)getActivity()).thevalue = "yes";
                mikopo = "yes";
                ((AccountSettings)getActivity()).new saveFragmentData().execute();
                //mikopoSectionView.findViewById(R.id.kiingiliodata).setVisibility(View.VISIBLE);
                mikopoSectionView2.findViewById(R.id.otherdetails).setVisibility(View.VISIBLE);
            }
        });
        RadioButton mikopohapanaradio = mikopoSectionView2.findViewById(R.id.mikopohapana);
        mikopohapanaradio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AccountSettings)getActivity()).what = "mikopo";
                ((AccountSettings)getActivity()).thevalue = "no";
                mikopo = "no";
                ((AccountSettings)getActivity()).new saveFragmentData().execute();
                mikopoSectionView2.findViewById(R.id.otherdetails).setVisibility(View.GONE);

            }
        });
        ///////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////MIKOPO YES NO LISTERNERS/////////////////////////////////
        RadioButton chelewafainindioradio = mikopoSectionView2.findViewById(R.id.chelewafainindio);
        chelewafainindioradio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AccountSettings)getActivity()).Section = "fainiyamikopo";
                //((AccountSettings)getActivity()).thevalue = "0";
                //mikopo = "yes";
                //((AccountSettings)getActivity()).new saveFragmentData().execute();
                mikopoSectionView2.findViewById(R.id.fainikiasi).setVisibility(View.VISIBLE);

            }
        });
        RadioButton chelewafainihapanaradio = mikopoSectionView2.findViewById(R.id.chelewafainihapana);
        chelewafainihapanaradio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AccountSettings)getActivity()).what = "fainiyamikopo";
                ((AccountSettings)getActivity()).thevalue = "0";
                fainiyamikopo = "0";
                ((AccountSettings)getActivity()).new saveFragmentData().execute();
                mikopoSectionView2.findViewById(R.id.fainikiasi).setVisibility(View.GONE);
            }
        });
        ///////////////////////////////////////////////////////////////////////////////////////



        JSONArray contactsx = storage.groupdata;
        // looping through All Contacts
        try{

            ////////////////////////////////////////////////////////////////////////////////////////////


            if(contactsx.getJSONObject(0).getString("mikopo").equalsIgnoreCase("yes")){
                ((RadioGroup) mikopoSectionView2.findViewById(R.id.mikopoyesno)).check(R.id.mikopondio);
            }else{
                ((RadioGroup) mikopoSectionView2.findViewById(R.id.mikopoyesno)).check(R.id.mikopohapana);
            }
            ((EditText) mikopoSectionView2.findViewById(R.id.ribaasilimia)).setText(contactsx.getJSONObject(0).getString("riba"));
            Log.e("THE RIBAAAA", "RIBAAAA ."+riba);

            if(contactsx.getJSONObject(0).getString("fainiyamikopo").equalsIgnoreCase("")){
                ((RadioGroup) mikopoSectionView2.findViewById(R.id.radiosfvgff)).check(R.id.chelewafainindio);
                //((EditText) mikopoSectionView2.findViewById(R.id.fainikiasiamount)).setText(contactsx.getJSONObject(0).getString("fainiyamikopo"));

            }else {
                ((RadioGroup) mikopoSectionView2.findViewById(R.id.radiosfvgff)).check(R.id.chelewafainihapana);
            }

            if(contactsx.getJSONObject(0).getString("tenure").equalsIgnoreCase("monthly")){
                ((RadioGroup) mikopoSectionView2.findViewById(R.id.radiosfvg)).check(R.id.kilamwezi);
            }
            if(contactsx.getJSONObject(0).getString("tenure").equalsIgnoreCase("weekly")){
                ((RadioGroup) mikopoSectionView2.findViewById(R.id.radiosfvg)).check(R.id.kilawiki);
            }
            if(contactsx.getJSONObject(0).getString("tenure").equalsIgnoreCase("daily")){
                ((RadioGroup) mikopoSectionView2.findViewById(R.id.radiosfvg)).check(R.id.kilasiku);
            }


        }catch (JSONException c){
            Log.e("FROM SERVER JSON ERROR", "Json parsing error: " + c);
        }




        ////////////////////////////////////RIBA TEXT AREA LISTERNER////////////////////////////////////
        EditText ribax =mikopoSectionView2.findViewById(R.id.ribaasilimia);

        ribax.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.e("RIBA LISTERNER", "TEXT: " + s);
                if(state) {
                    Log.e("RIBA LISTERNER 1", "TEXT: " + s);
                    if (source == "1") {
                        Log.e("RIBA LISTERNER 2", "TEXT: " + s);
                        mikopoSectionView2.findViewById(R.id.ribaloading).setVisibility(View.VISIBLE);
                        mikopoSectionView2.findViewById(R.id.ribaok).setVisibility(View.GONE);
                        ((AccountSettings) getActivity()).Section = "riba";
                        String zz = ribax.getText().toString();
                        if (zz == null || zz.isEmpty() || zz == "" || zz == "0") {
                            ((AccountSettings) getActivity()).saveWhat("riba", "0");
                            mikopoSectionView2.findViewById(R.id.ribaloading).setVisibility(View.GONE);
                            mikopoSectionView2.findViewById(R.id.ribaok).setVisibility(View.GONE);
                        } else {
                            ((AccountSettings) getActivity()).saveWhat("riba", zz);
                            ribax.setText(commalize(zz));
                            ribax.setSelection(ribax.getText().length());
                        }


                    } else {
                        source = "1";
                        mikopoSectionView2.findViewById(R.id.ribaloading).setVisibility(View.VISIBLE);
                        mikopoSectionView2.findViewById(R.id.ribaok).setVisibility(View.GONE);
                        ((AccountSettings) getActivity()).Section = "riba";
                        String zz = ribax.getText().toString();
                        if (zz == null || zz.isEmpty() || zz == "" || zz == "0") {
                            ((AccountSettings) getActivity()).saveWhat("riba", "0");
                            mikopoSectionView2.findViewById(R.id.ribaloading).setVisibility(View.GONE);
                            mikopoSectionView2.findViewById(R.id.ribaok).setVisibility(View.GONE);
                        } else {
                            ((AccountSettings) getActivity()).saveWhat("riba", zz);
                            //hisa.setText(commalize(hisa.getText().toString()));
                        }

                    }

                }

            }
        });
        ///////////////////////////////////////////////////////////////////////////////////////////





        ////////////////////////////////////FAINI TEXT AREA LISTERNER////////////////////////////////////
        EditText ribaxz =mikopoSectionView2.findViewById(R.id.fainikiasiamount);

        ribaxz.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.e("RIBA LISTERNER", "TEXT: " + s);
                if(state) {
                    Log.e("RIBA LISTERNER 1", "TEXT: " + s);
                    if (source == "1") {
                        Log.e("RIBA LISTERNER 2", "TEXT: " + s);
                        mikopoSectionView2.findViewById(R.id.fainiyamikopoloading).setVisibility(View.VISIBLE);
                        mikopoSectionView2.findViewById(R.id.fainiyamikopook).setVisibility(View.GONE);
                        ((AccountSettings) getActivity()).Section = "fainiyamikopo";
                        String zz = ribaxz.getText().toString();
                        if (zz == null || zz.isEmpty() || zz == "" || zz == "0") {
                            ((AccountSettings) getActivity()).saveWhat("fainiyamikopo", "0");
                            mikopoSectionView2.findViewById(R.id.fainiyamikopoloading).setVisibility(View.GONE);
                            mikopoSectionView2.findViewById(R.id.fainiyamikopook).setVisibility(View.GONE);
                        } else {
                            ((AccountSettings) getActivity()).saveWhat("fainiyamikopo", zz);
                            ribaxz.setText(commalize(zz));
                            ribaxz.setSelection(ribaxz.getText().length());
                        }


                    } else {
                        source = "1";
                        mikopoSectionView2.findViewById(R.id.fainiyamikopoloading).setVisibility(View.VISIBLE);
                        mikopoSectionView2.findViewById(R.id.fainiyamikopook).setVisibility(View.GONE);
                        ((AccountSettings) getActivity()).Section = "fainiyamikopo";
                        String zz = ribaxz.getText().toString();
                        if (zz == null || zz.isEmpty() || zz == "" || zz == "0") {
                            ((AccountSettings) getActivity()).saveWhat("fainiyamikopo", "0");
                            mikopoSectionView2.findViewById(R.id.fainiyamikopoloading).setVisibility(View.GONE);
                            mikopoSectionView2.findViewById(R.id.fainiyamikopook).setVisibility(View.GONE);
                        } else {
                            ((AccountSettings) getActivity()).saveWhat("fainiyamikopo", zz);
                            //hisa.setText(commalize(hisa.getText().toString()));
                        }

                    }

                }

            }
        });
        ///////////////////////////////////////////////////////////////////////////////////////////








        return mikopoSectionView2;
    }




    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_status, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_call) {
            Intent intent = new Intent(context, HomeActivity.class);

            startActivity(intent);
        }
        return true;
    }


    public String commalize(String co){
        source = "2";

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setGroupingUsed(true);
        decimalFormat.setGroupingSize(3);

        float fx = Float.parseFloat(co.replaceAll("[,]", ""));
        return  decimalFormat.format(fx);
    }

}
