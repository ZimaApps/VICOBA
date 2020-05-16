package com.zimaapps.vicoba;


        import android.app.Dialog;
        import android.content.Intent;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.Menu;
        import android.view.MenuInflater;
        import android.view.MenuItem;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.TextView;

        import androidx.fragment.app.DialogFragment;
        import androidx.fragment.app.Fragment;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import com.google.android.material.floatingactionbutton.FloatingActionButton;
        import com.google.android.material.snackbar.Snackbar;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;
        import com.google.firebase.database.ChildEventListener;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.Query;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.text.DecimalFormat;
        import java.util.ArrayList;

        import static com.zimaapps.vicoba.HomeActivity.context;
        import static com.zimaapps.vicoba.storage.kikobaId;


/**
 * A simple {@link Fragment} subclass.
 */
public class MimiFragment extends Fragment {
    public LinearLayout vifunguView;
    public EditText kifungu;
    public AndroidMultiPartEntity entity3;
    final Dialog dialog = new Dialog(context);

    DatabaseReference database;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    ImageAdapter mAdapter;
    ArrayList<Image> images = new ArrayList<>();
    FirebaseUser fbUser;
    View theview;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        theview = inflater.inflate(R.layout.mimi_fragment, container, false);

        ImageView editbtn = theview.findViewById(R.id.lastMessageTimeTV);
        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do something when the corky3 is clicked
                showDialog();
            }
        });

        setpresentdata();


        return theview;



    }

    void showDialog() {
        // Create the fragment and show it as a dialog.
        DialogFragment newFragment = changeUserDataDialog.newInstance();
        newFragment.show(getFragmentManager(), "changeUserDataDialog");
    }

public void setpresentdata(){
    TextView userName = theview.findViewById(R.id.nameTV);
    userName.setText(storage.userName);
    TextView userNamba = theview.findViewById(R.id.messageTV);
    userNamba.setText(storage.userNumber);
    TextView cheo = theview.findViewById(R.id.messageTVx);
    cheo.setText("Mjumbe");

    new getonlinedataHisa().execute();

}


    public class getonlinedataHisa extends AsyncTask<Void, String, JSONArray> {

        JSONArray thedata = new JSONArray();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected JSONArray doInBackground(Void... arg0) {


            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "http://zimaapps.com/mobileApps/vicoba/gethisadata.php?kikobaId="+kikobaId+"&userId="+storage.userId;
            String jsonStr = sh.makeServiceCall(url);

            //Log.e("RESULT FROM SERVER", "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    thedata = jsonObj.getJSONArray("generaldata");


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


            try {


                JSONObject value = (JSONObject) result.get(0);
                JSONArray hisadata = value.getJSONArray("hisadata");
                JSONObject hisadatax = (JSONObject) hisadata.get(0);
                TextView hisacount = theview.findViewById(R.id.messageTVwww);
                hisacount.setText("Idadi ya hisa zako : "+hisadatax.getString("c"));
                TextView hisacountxz = theview.findViewById(R.id.messageTVxwww);
                hisacountxz.setText("Thamani yake : "+putcommas(hisadatax.getInt("s"))+" /=");
                //////////////////////////////////////////////////////////////////////////////////

                JSONObject value1 = (JSONObject) result.get(1);
                JSONArray adadata = value1.getJSONArray("adadata");
                JSONObject adadatax = (JSONObject) adadata.get(0);
                TextView hisacount1 = theview.findViewById(R.id.messageTVwwww);
                hisacount1.setText("Idadi : "+adadatax.getString("c"));
                TextView hisacountxz1 = theview.findViewById(R.id.messageTVxwwww);
                hisacountxz1.setText("Thamani yake : "+putcommas(adadatax.getInt("s"))+" /=");

            } catch (JSONException e) {
                e.printStackTrace();
            }


            Log.e("RESULTFROM SERVER HISAA", "serverXXXXXX..."+ result);

        }


    }













    private void finish() {
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_calls, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_call) {

            Intent intent = new Intent(context, HomeActivity.class);
            startActivity(intent);
        }
        return true;
    }










    public void createdialog(String thetitle, String themeseji){

        // custom dialog

        dialog.setTitle("Tafadhali Subiri...");
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.waitdialog);

        //dialog.dismiss();
        dialog.show();




    }

    public String putcommas(int amount){

        String pattern = "###,###.##";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        decimalFormat.setGroupingSize(3);

        String number = decimalFormat.format(amount);

        return number;
    }




}

