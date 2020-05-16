package com.zimaapps.vicoba;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.text.DecimalFormat;

import static com.zimaapps.vicoba.AccountSettings.context;

import static com.zimaapps.vicoba.storage.ada;
import static com.zimaapps.vicoba.storage.chini;
import static com.zimaapps.vicoba.storage.faini;
import static com.zimaapps.vicoba.storage.hisa;
import static com.zimaapps.vicoba.storage.kiingilio;
import static com.zimaapps.vicoba.storage.state;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment{
    public String source = "1";
    public View michangoSectionView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);

        michangoSectionView = inflater.inflate(R.layout.fragment_chat, container, false);

        EditText b1 = michangoSectionView.findViewById(R.id.kiingilio);
        b1.setText(kiingilio);
        b1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    state = true;
                } else {

                }
            }
        });
        EditText b2 = michangoSectionView.findViewById(R.id.ada);
        b2.setText(ada);
        b2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    state = true;
                } else {

                }
            }
        });
        EditText b3 = michangoSectionView.findViewById(R.id.hisa);
        b3.setText(hisa);
        b3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    state = true;
                } else {

                }
            }
        });
        EditText b4 = michangoSectionView.findViewById(R.id.faini);
        b4.setText(faini);
        b4.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    state = true;
                } else {

                }
            }
        });
        EditText b5 = michangoSectionView.findViewById(R.id.chini);
        b5.setText(chini);
        b5.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    state = true;
                } else {

                }
            }
        });

        ////////////////////////////KIINGILIO LISTERNERS/////////////////////////////////
        RadioButton kiingilioradiondio = michangoSectionView.findViewById(R.id.kiingiliondio);
        kiingilioradiondio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AccountSettings)getActivity()).Section = "kiingilio";
                michangoSectionView.findViewById(R.id.kiingiliodata).setVisibility(View.VISIBLE);

            }
        });
        RadioButton kiingilioradiohapana = michangoSectionView.findViewById(R.id.kiingiliohapana);
        kiingilioradiohapana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AccountSettings)getActivity()).what = "kiingilio";
                ((AccountSettings)getActivity()).thevalue = "0";
                kiingilio = "0";
                EditText oo = michangoSectionView.findViewById(R.id.kiingilio);
                oo.setText(kiingilio);
                michangoSectionView.findViewById(R.id.kiingiliodata).setVisibility(View.GONE);
                ((AccountSettings)getActivity()).new saveFragmentData().execute();

            }
        });

        EditText kiingilio =michangoSectionView.findViewById(R.id.kiingilio);

        kiingilio.addTextChangedListener(new TextWatcher() {
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
                if(state) {
                    if (source == "1") {

                        michangoSectionView.findViewById(R.id.kiingilioloading).setVisibility(View.VISIBLE);
                        michangoSectionView.findViewById(R.id.kiingiliook).setVisibility(View.GONE);
                        ((AccountSettings) getActivity()).Section = "kiingilio";
                        String zz = kiingilio.getText().toString();
                        if (zz == null || zz.isEmpty() || zz == "" || zz == "0") {
                            ((AccountSettings) getActivity()).saveWhat("kiingilio", "0");
                            michangoSectionView.findViewById(R.id.kiingilioloading).setVisibility(View.GONE);
                            michangoSectionView.findViewById(R.id.kiingiliook).setVisibility(View.GONE);
                        } else {
                            ((AccountSettings) getActivity()).saveWhat("kiingilio", zz);
                            kiingilio.setText(commalize(zz));
                            kiingilio.setSelection(kiingilio.getText().length());
                        }


                    } else {
                        source = "1";
                        michangoSectionView.findViewById(R.id.kiingilioloading).setVisibility(View.VISIBLE);
                        michangoSectionView.findViewById(R.id.kiingiliook).setVisibility(View.GONE);
                        ((AccountSettings) getActivity()).Section = "kiingilio";
                        String zz = kiingilio.getText().toString();
                        if (zz == null || zz.isEmpty() || zz == "" || zz == "0") {
                            ((AccountSettings) getActivity()).saveWhat("kiingilio", "0");
                            michangoSectionView.findViewById(R.id.kiingilioloading).setVisibility(View.GONE);
                            michangoSectionView.findViewById(R.id.kiingiliook).setVisibility(View.GONE);
                        } else {
                            ((AccountSettings) getActivity()).saveWhat("kiingilio", zz);
                            //kiingilio.setText(commalize(kiingilio.getText().toString()));
                        }

                    }
                }


            }
        });
        ///////////////////////////////////////////////////////////////////////////////////////////



        ////////////////////////////ADA LISTERNERS/////////////////////////////////
        RadioButton adaradiondio = michangoSectionView.findViewById(R.id.adandio);
        adaradiondio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AccountSettings)getActivity()).Section = "ada";
                michangoSectionView.findViewById(R.id.adadata).setVisibility(View.VISIBLE);

            }
        });
        RadioButton adaradiohapana = michangoSectionView.findViewById(R.id.adahapana);
        adaradiohapana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AccountSettings)getActivity()).what = "ada";
                ((AccountSettings)getActivity()).thevalue = "0";
                ada = "0";
                EditText oo = michangoSectionView.findViewById(R.id.ada);
                oo.setText(ada);
                michangoSectionView.findViewById(R.id.adadata).setVisibility(View.GONE);
                ((AccountSettings)getActivity()).new saveFragmentData().execute();

            }
        });

        EditText ada =michangoSectionView.findViewById(R.id.ada);

        ada.addTextChangedListener(new TextWatcher() {
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
                if(state) {
                if (source == "1") {

                    michangoSectionView.findViewById(R.id.adaloading).setVisibility(View.VISIBLE);
                    michangoSectionView.findViewById(R.id.adaok).setVisibility(View.GONE);
                    ((AccountSettings) getActivity()).Section = "ada";
                    String zz = ada.getText().toString();
                    if (zz == null || zz.isEmpty() || zz == "" || zz == "0") {
                        ((AccountSettings) getActivity()).saveWhat("ada", "0");
                        michangoSectionView.findViewById(R.id.adaloading).setVisibility(View.GONE);
                        michangoSectionView.findViewById(R.id.adaok).setVisibility(View.GONE);
                    } else {
                        ((AccountSettings) getActivity()).saveWhat("ada", zz);
                        ada.setText(commalize(zz));
                        ada.setSelection(ada.getText().length());
                    }


                } else {
                    source = "1";
                    michangoSectionView.findViewById(R.id.adaloading).setVisibility(View.VISIBLE);
                    michangoSectionView.findViewById(R.id.adaok).setVisibility(View.GONE);
                    ((AccountSettings) getActivity()).Section = "ada";
                    String zz = ada.getText().toString();
                    if (zz == null || zz.isEmpty() || zz == "" || zz == "0") {
                        ((AccountSettings) getActivity()).saveWhat("ada", "0");
                        michangoSectionView.findViewById(R.id.adaloading).setVisibility(View.GONE);
                        michangoSectionView.findViewById(R.id.adaok).setVisibility(View.GONE);
                    } else {
                        ((AccountSettings) getActivity()).saveWhat("ada", zz);
                        //ada.setText(commalize(ada.getText().toString()));
                    }

                }

            }

            }
        });
        ///////////////////////////////////////////////////////////////////////////////////////////



        ////////////////////////////HISA LISTERNERS/////////////////////////////////
        RadioButton hisaradiondio = michangoSectionView.findViewById(R.id.hisandio);
        hisaradiondio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AccountSettings)getActivity()).Section = "hisa";
                michangoSectionView.findViewById(R.id.hisadata).setVisibility(View.VISIBLE);

            }
        });
        RadioButton hisaradiohapana = michangoSectionView.findViewById(R.id.hisahapana);
        hisaradiohapana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AccountSettings)getActivity()).what = "hisa";
                ((AccountSettings)getActivity()).thevalue = "0";
                hisa = "0";
                EditText oo = michangoSectionView.findViewById(R.id.hisa);
                oo.setText(hisa);
                michangoSectionView.findViewById(R.id.hisadata).setVisibility(View.GONE);
                ((AccountSettings)getActivity()).new saveFragmentData().execute();

            }
        });

        EditText hisa =michangoSectionView.findViewById(R.id.hisa);

        hisa.addTextChangedListener(new TextWatcher() {
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
                if(state) {
                if (source == "1") {

                    michangoSectionView.findViewById(R.id.hisaloading).setVisibility(View.VISIBLE);
                    michangoSectionView.findViewById(R.id.hisaok).setVisibility(View.GONE);
                    ((AccountSettings) getActivity()).Section = "hisa";
                    String zz = hisa.getText().toString();
                    if (zz == null || zz.isEmpty() || zz == "" || zz == "0") {
                        ((AccountSettings) getActivity()).saveWhat("hisa", "0");
                        michangoSectionView.findViewById(R.id.hisaloading).setVisibility(View.GONE);
                        michangoSectionView.findViewById(R.id.hisaok).setVisibility(View.GONE);
                    } else {
                        ((AccountSettings) getActivity()).saveWhat("hisa", zz);
                        hisa.setText(commalize(zz));
                        hisa.setSelection(hisa.getText().length());
                    }


                } else {
                    source = "1";
                    michangoSectionView.findViewById(R.id.hisaloading).setVisibility(View.VISIBLE);
                    michangoSectionView.findViewById(R.id.hisaok).setVisibility(View.GONE);
                    ((AccountSettings) getActivity()).Section = "hisa";
                    String zz = hisa.getText().toString();
                    if (zz == null || zz.isEmpty() || zz == "" || zz == "0") {
                        ((AccountSettings) getActivity()).saveWhat("hisa", "0");
                        michangoSectionView.findViewById(R.id.hisaloading).setVisibility(View.GONE);
                        michangoSectionView.findViewById(R.id.hisaok).setVisibility(View.GONE);
                    } else {
                        ((AccountSettings) getActivity()).saveWhat("hisa", zz);
                        //hisa.setText(commalize(hisa.getText().toString()));
                    }

                }

            }

            }
        });
        ///////////////////////////////////////////////////////////////////////////////////////////



        ////////////////////////////FAINI LISTERNERS/////////////////////////////////
        RadioButton fainiradiondio = michangoSectionView.findViewById(R.id.fainindio);
        fainiradiondio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AccountSettings)getActivity()).Section = "faini";
                michangoSectionView.findViewById(R.id.fainidata).setVisibility(View.VISIBLE);

            }
        });
        RadioButton fainiradiohapana = michangoSectionView.findViewById(R.id.fainihapana);
        fainiradiohapana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AccountSettings)getActivity()).what = "faini";
                ((AccountSettings)getActivity()).thevalue = "0";
                faini = "0";
                EditText oo = michangoSectionView.findViewById(R.id.faini);
                oo.setText(faini);
                michangoSectionView.findViewById(R.id.fainidata).setVisibility(View.GONE);
                ((AccountSettings)getActivity()).new saveFragmentData().execute();

            }
        });

        EditText faini =michangoSectionView.findViewById(R.id.faini);

        faini.addTextChangedListener(new TextWatcher() {
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
                if(state) {
                if (source == "1") {

                    michangoSectionView.findViewById(R.id.fainiloading).setVisibility(View.VISIBLE);
                    michangoSectionView.findViewById(R.id.fainiok).setVisibility(View.GONE);
                    ((AccountSettings) getActivity()).Section = "faini";
                    String zz = faini.getText().toString();
                    if (zz == null || zz.isEmpty() || zz == "" || zz == "0") {
                        ((AccountSettings) getActivity()).saveWhat("faini", "0");
                        michangoSectionView.findViewById(R.id.fainiloading).setVisibility(View.GONE);
                        michangoSectionView.findViewById(R.id.fainiok).setVisibility(View.GONE);
                    } else {
                        ((AccountSettings) getActivity()).saveWhat("faini", zz);
                        faini.setText(commalize(zz));
                        faini.setSelection(faini.getText().length());
                    }


                } else {
                    source = "1";
                    michangoSectionView.findViewById(R.id.fainiloading).setVisibility(View.VISIBLE);
                    michangoSectionView.findViewById(R.id.fainiok).setVisibility(View.GONE);
                    ((AccountSettings) getActivity()).Section = "faini";
                    String zz = faini.getText().toString();
                    if (zz == null || zz.isEmpty() || zz == "" || zz == "0") {
                        ((AccountSettings) getActivity()).saveWhat("faini", "0");
                        michangoSectionView.findViewById(R.id.fainiloading).setVisibility(View.GONE);
                        michangoSectionView.findViewById(R.id.fainiok).setVisibility(View.GONE);
                    } else {
                        ((AccountSettings) getActivity()).saveWhat("faini", zz);
                        //faini.setText(commalize(faini.getText().toString()));
                    }

                }

            }

            }
        });
        ///////////////////////////////////////////////////////////////////////////////////////////



        ////////////////////////////CHINI LISTERNERS/////////////////////////////////
        RadioButton chiniradiondio = michangoSectionView.findViewById(R.id.chinindio);
        chiniradiondio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AccountSettings)getActivity()).Section = "chini";
                michangoSectionView.findViewById(R.id.chinidata).setVisibility(View.VISIBLE);

            }
        });
        RadioButton chiniradiohapana = michangoSectionView.findViewById(R.id.chinihapana);
        chiniradiohapana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AccountSettings)getActivity()).what = "chini";
                ((AccountSettings)getActivity()).thevalue = "0";
                chini = "0";
                EditText oo = michangoSectionView.findViewById(R.id.chini);
                oo.setText(chini);
                michangoSectionView.findViewById(R.id.chinidata).setVisibility(View.GONE);
                ((AccountSettings)getActivity()).new saveFragmentData().execute();

            }
        });

        EditText chini =michangoSectionView.findViewById(R.id.chini);

        chini.addTextChangedListener(new TextWatcher() {
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
                if(state) {
                if (source == "1") {

                    michangoSectionView.findViewById(R.id.chiniloading).setVisibility(View.VISIBLE);
                    michangoSectionView.findViewById(R.id.chiniok).setVisibility(View.GONE);
                    ((AccountSettings) getActivity()).Section = "chini";
                    String zz = chini.getText().toString();
                    if (zz == null || zz.isEmpty() || zz == "" || zz == "0") {
                        ((AccountSettings) getActivity()).saveWhat("chini", "0");
                        michangoSectionView.findViewById(R.id.chiniloading).setVisibility(View.GONE);
                        michangoSectionView.findViewById(R.id.chiniok).setVisibility(View.GONE);
                    } else {
                        ((AccountSettings) getActivity()).saveWhat("chini", zz);
                        chini.setText(commalize(zz));
                        chini.setSelection(chini.getText().length());
                    }


                } else {
                    source = "1";
                    michangoSectionView.findViewById(R.id.chiniloading).setVisibility(View.VISIBLE);
                    michangoSectionView.findViewById(R.id.chiniok).setVisibility(View.GONE);
                    ((AccountSettings) getActivity()).Section = "chini";
                    String zz = chini.getText().toString();
                    if (zz == null || zz.isEmpty() || zz == "" || zz == "0") {
                        ((AccountSettings) getActivity()).saveWhat("chini", "0");
                        michangoSectionView.findViewById(R.id.chiniloading).setVisibility(View.GONE);
                        michangoSectionView.findViewById(R.id.chiniok).setVisibility(View.GONE);
                    } else {
                        ((AccountSettings) getActivity()).saveWhat("chini", zz);
                        //chini.setText(commalize(chini.getText().toString()));
                    }

                }

            }

            }
        });
        ///////////////////////////////////////////////////////////////////////////////////////////






        return michangoSectionView;
    }





















    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_chats, menu);
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
