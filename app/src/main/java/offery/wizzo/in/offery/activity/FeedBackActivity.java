package offery.wizzo.in.offery.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import offery.wizzo.in.offery.R;
import offery.wizzo.in.offery.interfacefile.ApiCalls;
import offery.wizzo.in.offery.netcom.CheckNetwork;
import offery.wizzo.in.offery.webApi.AppSharedPrefs;
import offery.wizzo.in.offery.webApi.UrlUtils;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class FeedBackActivity extends BaseActvitiy implements View.OnClickListener {
    AppCompatEditText Feedback;
    private Button SendFeedBack;
    String uriString;
    public static final String ROOT_URL = "http://ssappsnwebs.com/spass/admin2/api";
    String mobile;
    TextView content;
    RelativeLayout fddd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        initView();
        mobile = AppSharedPrefs.getInstance(this).readPrefs(this, "mobile");
        Log.e("MOBIle", mobile);
        SendFeedBack.setOnClickListener(this);
        fddd.setOnClickListener(this);


    }

    @Override
    public void initView() {
//        content = (TextView) findViewById(R.id.content);
        Feedback = (AppCompatEditText) findViewById(R.id.etfeedbackId);
        SendFeedBack = (Button) findViewById(R.id.btnSendFeedBackId);
        fddd = (RelativeLayout) findViewById(R.id.relativeMainId);
    }


    private void insertfeedback() {
        if (!CheckNetwork.checkConnection(this)) {
            Toast.makeText(this, getResources().getString(R.string.network_error), Toast.LENGTH_LONG).show();
            return;
        }
        UrlUtils.showProgress(this);
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ApiCalls api = adapter.create(ApiCalls.class);
        api.insertfeed(

                //Passing the values by getting it from editTexts
                mobile,
                Feedback.getText().toString(),


                //Creating an anonymous callback
                new Callback<Response>() {
                    @Override
                    public void success(Response response, Response response2) {
                        BufferedReader reader = null;

                        //An string to ic_store output from the server
                        String output = "";
                        String result = "";

                        try {
                            //Initializing buffered reader
                            reader = new BufferedReader(new InputStreamReader(response.getBody().in()));
                            output = reader.readLine();
                            // JSONObject json= new JSONObject(output);  //your response
                            try {
                                JSONObject jsonObj = new JSONObject(output);

                                // If you have array
                                JSONArray resultArray = jsonObj.getJSONArray("response"); // Here you will get the Array

                                // Iterate the loop
                                for (int i = 0; i < resultArray.length(); i++) {
                                    // get value with the NODE key
                                    JSONObject obj = resultArray.getJSONObject(i);
                                    String name = obj.getString("comment");

                                    // If you have object
                                    result = obj.getString("status");
                                    Log.e("ststus", result);
                                }
                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                                UrlUtils.dismissDialog();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            UrlUtils.dismissDialog();
                        }

                        Log.e("response", output);
                        if (result.equalsIgnoreCase("0")) {
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    Intent in = new Intent(FeedBackActivity.this, GetSearchSchoolActivity.class);
                                    startActivity(in);
                                    Toast.makeText(FeedBackActivity.this, "Thank you for your valuable activity_feed_back", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            });
                        } else if (result.equalsIgnoreCase("3")) {
                            Intent in = new Intent(FeedBackActivity.this, GetSearchSchoolActivity.class);
                            startActivity(in);
                            Toast.makeText(FeedBackActivity.this, "Aleardy exist", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(FeedBackActivity.this, "Thank you for your valuable activity_feed_back", Toast.LENGTH_SHORT).show();
                        }
                        UrlUtils.dismissDialog();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        // If any error occured displaying the error as toast
                        Toast.makeText(FeedBackActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                        UrlUtils.dismissDialog();
                    }
                }
        );
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        Intent bck = new Intent(FeedBackActivity.this, MainActivity.class);
        startActivity(bck);
        finish();
    }

    public String GetCountryZipCode() {
        String CountryID = "";
        String CountryZipCode = "";

        TelephonyManager manager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        //getNetworkCountryIso
        CountryID = manager.getSimCountryIso().toUpperCase();
        String[] rl = this.getResources().getStringArray(R.array.CountryCodes);
        for (int i = 0; i < rl.length; i++) {
            String[] g = rl[i].split(",");
            if (g[1].trim().equals(CountryID.trim())) {
                CountryZipCode = g[0];
                Feedback.setText(CountryZipCode);
                Log.e("Code", CountryID + CountryZipCode);
                break;
            }
        }
        return CountryZipCode;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.relativeMainId:
                hideSoftKeyboard(FeedBackActivity.this);
                break;

            case R.id.btnSendFeedBackId:
                if (Feedback.getText().toString().matches("")) {
                    Toast.makeText(getApplication(), "Please Insert OTP ..", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    try {
                        insertfeedback();
                        //GetText();
                        UrlUtils.hideSoftKeyboRD(this);
                        break;
                    } catch (Exception ex) {
                        content.setText("Network exception");
                    }

                }
        }


    }

}
