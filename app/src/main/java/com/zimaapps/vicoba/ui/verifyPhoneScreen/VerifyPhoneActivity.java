package com.zimaapps.vicoba.ui.verifyPhoneScreen;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.zimaapps.vicoba.R;
import com.zimaapps.vicoba.createCase;
import com.zimaapps.vicoba.storage;


import java.util.concurrent.TimeUnit;

import static com.zimaapps.vicoba.storage.userId;
import static com.zimaapps.vicoba.storage.userNumber;


public class VerifyPhoneActivity extends AppCompatActivity{
    public String phoneNumber = "+255754244888";
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private EditText phoneNumberTxt;
    //private String phoneNumber;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    public String verificationIdx;

    private BroadcastReceiver smsBroadcastReceiver;
    IntentFilter filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
    public static final String SMS_BUNDLE = "pdus";




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone);


        /*smsBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.e("smsBroadcastReceiver", "onReceive");
                Bundle pudsBundle = intent.getExtras();
                Object[] pdus = (Object[]) pudsBundle.get(SMS_BUNDLE);
                SmsMessage messages = SmsMessage.createFromPdu((byte[]) pdus[0]);
                Log.e("RECEIVED MESSAGE",  messages.getMessageBody());

                String firebaseVerificationCode = messages.getMessageBody().trim().split(" ")[0];//only a number code
                //Toast.makeText(this, firebaseVerificationCode,Toast.LENGTH_SHORT).show();
                //String token = firebaseAutenticationService.getVerificationCode();//your service
                //firebaseAutenticationService.verifyPhoneNumberWithCode(token,verificationCode);

                PhoneAuthCredential credentialp = PhoneAuthProvider.getCredential(verificationIdx, firebaseVerificationCode);
                Log.e("XXXXX credentia:", credentialp.toString());
                storage.credential = credentialp;
                createcase();
            }
        };*/



        findViewById(R.id.nextBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSms(v);
            }
        });
        findViewById(R.id.nextBtn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSms2(v);
            }
        });

        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        mAuth.setLanguageCode("sw");


        initView();

        //PhoneAuthProvider.getInstance().verifyPhoneNumber("+255754244888", 60,  TimeUnit.SECONDS,   this,  mCallbacks);

    }





    private void initView() {
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onCodeAutoRetrievalTimeOut(String verificationId) {
                if (progressDialog != null) {
                    dismissProgressDialog(progressDialog);
                }
                Log.e("onVerificationFailed", "onVerificationFailed "+ verificationId);
                notifyUserAndRetry("Tumeshindwa kuhakiki namba yako ya simu, Tatizo linaweza kua ni network tu. Jaribu tena baadae kidogo!");
            }

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credentialx) {
                Log.e("onVerificationCompleted", "onVerificationCompleted:" + credentialx);
                if (progressDialog != null) {
                    dismissProgressDialog(progressDialog);
                }



                signInWithPhoneAuthCredential(credentialx);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Log.e("onVerificationFailed", "onVerificationFailed "+e);

                if (progressDialog != null) {
                    dismissProgressDialog(progressDialog);
                }

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    //notifyUserAndRetry("Tumeshindwa kuhakiki namba yako ya simu, wasiliana na Administrator kwa namba 0754244888" +e);
                    Log.e("Exception:", "FirebaseAuthInvalidCredentialsException" + e);
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    Log.e("Exception:", "FirebaseTooManyRequestsException" + e);
                    //notifyUserAndRetry("Tumeshindwa kuhakiki namba yako ya simu, wasiliana na Administrator kwa namba 0754244888" +e);

                }

                notifyUserAndRetry("Tumeshindwa kuhakiki namba yako ya simu, wasiliana na Administrator kwa namba 0754244888");
            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                //for low level version which doesn't do aoto verififcation save the verification code and the token

                Log.e("onCodeSent", "onCodeSent:" + verificationId);
                Log.e("Verification code:", verificationId);
                //resendVerificationCode(token);
                verificationIdx = verificationId;





            }
        };
    }


    private void showLoginActivity() {
        //Intent intent = new Intent(this, LoginActivity.class);
        //startActivity(intent);
    }





    public void onClickSms(final View view) {



        EditText phoneNumberx = findViewById(R.id.phoneNumberET);
        phoneNumber = phoneNumberx.getText().toString();
        String phoneNumberv = phoneNumber.replaceFirst("^0+(?!$)", "");
        Log.e("User Phone Number", phoneNumberv);

        if (phoneNumberv != null && !phoneNumberv.isEmpty() && phoneNumberv.length() ==9) {
            startPhoneNumberVerification("+255"+phoneNumberv);
            showProgressDialog(this, "Tunakutumia namba ya siri, tafadhali subiri", false);
            RelativeLayout bb = findViewById(R.id.phoneRLz);
            bb.setVisibility(View.VISIBLE);
        } else {
            showToast("Namba ya simu ulio ingiza sio sahihi, jaribu tena!");
        }
    }

    public void onClickSms2(final View view) {


        EditText phoneNumberx = findViewById(R.id.phoneNumberET);
        phoneNumber = phoneNumberx.getText().toString();
        EditText codex = findViewById(R.id.phoneNumberETx);
        String codexz = codex.getText().toString();
        String phoneNumberv = phoneNumber.replaceFirst("^0+(?!$)", "");
        Log.e("User Phone Number", phoneNumberv);

        if (phoneNumberv != null && !phoneNumberv.isEmpty() && phoneNumberv.length() ==9 && codexz != null && !codexz.isEmpty() && codexz.length() ==6) {
            startPhoneNumberVerification("+255"+phoneNumberv);
            showProgressDialog(this, "Tunakutumia namba ya siri, tafadhali subiri", false);

            PhoneAuthCredential credentialp = PhoneAuthProvider.getCredential(verificationIdx, "123456");
            Log.e("XXXXX credentia:", credentialp.toString());

            storage.credential = credentialp;
            createcase();

            RelativeLayout bb = findViewById(R.id.phoneRLz);
            bb.setVisibility(View.VISIBLE);
        } else {
            showToast("Namba ya simu, au namba ya siri ulio ingiza sio sahihi, jaribu tena!");
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }





    private void startPhoneNumberVerification(String phoneNumber) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }



    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            FirebaseUser user = task.getResult().getUser();
                            userNumber = user.getPhoneNumber();
                            userId = user.getUid();
                            Log.d("Sign in with phone auth", "Success " + user.getPhoneNumber());

                                createcase();
                        } else {
                            notifyUserAndRetry("Tumeshindwa kuhakiki namba yako ya simu, Jaribu tena");
                        }
                    }
                });
    }


    public void createcase(){

         Intent myIntent = new Intent(VerifyPhoneActivity.this, createCase.class);
                myIntent.putExtra("key", 100); //Optional parameters
        VerifyPhoneActivity.this.startActivity(myIntent);
    }

    /**
     * Method to show progress dialog
     *
     * @param mActivity
     * @param message
     * @param isCancelable
     * @return dialog
     */
    public ProgressDialog showProgressDialog(final Context mActivity, final String message, boolean isCancelable) {
        progressDialog = new ProgressDialog(mActivity);
        progressDialog.show();
        progressDialog.setCancelable(isCancelable);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage(message);
        return progressDialog;
    }

    /**
     * Method to dismiss progress dialog
     *
     * @param progressDialog
     */
    public final void dismissProgressDialog(ProgressDialog progressDialog) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    public void notifyUserAndRetry(String message) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        showLoginActivity();
                    }
                });

        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showLoginActivity();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }















}


