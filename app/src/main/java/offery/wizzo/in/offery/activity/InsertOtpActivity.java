package offery.wizzo.in.offery.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import offery.wizzo.in.offery.model.LoggedUserBean;
import offery.wizzo.in.offery.netcom.CheckNetwork;
import offery.wizzo.in.offery.webApi.AppSharedPrefs;
import offery.wizzo.in.offery.webApi.TwilioVerification;
import offery.wizzo.in.offery.webApi.UrlUtils;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;


public class InsertOtpActivity extends BaseActvitiy implements View.OnClickListener {
    public static EditText InsertOtp;
    private Button submitButton;
    String uriString;
    TextView resendotpId, setTimeId;
    LinearLayout CountTimerId;
    //    must use root url from string resources
    public static final String ROOT_URL = " ";
    RelativeLayout lyyy;
    private CountDownTimer countDownTimer;
    private boolean timerHasStarted = false;
    private final long startTime = 59 * 1000;
    private final long interval = 1 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_otp);


        initView();
        Intent i = getIntent();
//        String otp = i.getStringExtra("otp");
//        Log.e("000000", "121212" + otp);
//        sendOtpFromTwilio();
        sendTwilioOtp();
        countDownTimer = new MyCountDownTimer(startTime, interval);
        countDownTimer.start();
        setTimeId.setText(setTimeId.getText() + String.valueOf(startTime / 1000));
        submitButton.setOnClickListener(this);
        lyyy.setOnClickListener(this);
        resendotpId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resendotpId.setVisibility(View.GONE);
                Toast.makeText(InsertOtpActivity.this, "Text Message Send..", Toast.LENGTH_SHORT).show();
                sendTwilioOtp();
                CountTimerId.setVisibility(View.VISIBLE);
                countDownTimer.start();
                setTimeId.setText(setTimeId.getText() + String.valueOf(startTime / 1000));
            }
        });
    }


    @Override
    public void initView() {
        setTimeId = (TextView) findViewById(R.id.txtSetTimeOtpId);
        resendotpId = (TextView) findViewById(R.id.txtResendOtpId);
        CountTimerId = (LinearLayout) findViewById(R.id.linerCountOtpId);
        InsertOtp = (EditText) findViewById(R.id.etOtpId);
        submitButton = (Button) findViewById(R.id.btnsubmitOtpId);
        lyyy = (RelativeLayout) findViewById(R.id.relativeMainOtpId);
    }


    private void insertOtp() {
        if (!CheckNetwork.checkConnection(this)) {
            Toast.makeText(this, getResources().getString(R.string.network_error), Toast.LENGTH_LONG).show();
            return;
        }
        UrlUtils.showProgress(this);
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(getResources().getString(R.string.base_url)) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ApiCalls api = adapter.create(ApiCalls.class);

        Intent i = getIntent();
//        String otp = i.getStringExtra("otp");
//        Log.e("000000", "121212" + otp);

        //uriString = i.getStringExtra("text_label");
        //Defining the method insertuser of our interface

        api.sendOTP(uriString = i.getStringExtra("mobile"),
                InsertOtp.getText().toString(),
                //Creating an anonymous callback
                new Callback<retrofit.client.Response>() {
                    @Override
                    public void success(retrofit.client.Response response, retrofit.client.Response response2) {
                        BufferedReader reader = null;

                        //An string to ic_store output from the server
                        String output = "";
                        String result = "";

                        try {
                            //Initializing buffered reader
                            reader = new BufferedReader(new InputStreamReader(response.getBody().in()));
//                            int status = response.getStatus();
//                            Log.e("ststus", String.valueOf(status));

                            //Reading the output in the string
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
                                    Intent in = new Intent(InsertOtpActivity.this, GetSearchSchoolActivity.class);
                                    startActivity(in);
                                    String mobile = getIntent().getStringExtra("mobile");
                                    String token = getIntent().getStringExtra("token");
                                    String deviceId = getIntent().getStringExtra("deviceId");
                                    LoggedUserBean user = new LoggedUserBean(mobile, token, deviceId);
                                    AppSharedPrefs.getInstance(InsertOtpActivity.this).saveLoggedUser(user);
                                    Toast.makeText(InsertOtpActivity.this, "User verified successfully", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            });
                        } else if (result.equalsIgnoreCase("3")) {

                            Intent in = new Intent(InsertOtpActivity.this, GetSearchSchoolActivity.class);
                            startActivity(in);
                            Toast.makeText(InsertOtpActivity.this, "Login Successfully Done..", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(InsertOtpActivity.this, "OTP is wrong.", Toast.LENGTH_SHORT).show();
                        }
                        UrlUtils.dismissDialog();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        // If any error occured displaying the error as toast
                        Toast.makeText(InsertOtpActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                        UrlUtils.dismissDialog();
                    }
                }
        );
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equalsIgnoreCase("otp")) {
                final String message = intent.getStringExtra("message");
                InsertOtp.setText(message);
                if (!InsertOtp.equals("")) {
                    CountTimerId.setVisibility(View.GONE);
                    resendotpId.setVisibility(View.GONE);
                } else {
                    CountTimerId.setVisibility(View.VISIBLE);
                }

            }
        }
    };

    private void sendTwilioOtp() {
        new TwilioVerification(this).sendMessage(getIntent().getStringExtra("country_code") + getIntent().getStringExtra("mobile"), getResources().getString(R.string.your_otp_code_is) + getIntent().getStringExtra("otp"), getIntent().getStringExtra("otp"));

    }

    @Override
    public void onResume() {
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter("otp"));
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }


    public void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.relativeMainOtpId:
                hideSoftKeyboard(InsertOtpActivity.this);
                break;

            case R.id.btnsubmitOtpId:
                if (InsertOtp.getText().toString().matches("")) {
                    Toast.makeText(getApplication(), "Please Insert OTP ..", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    insertOtp();
                    UrlUtils.hideSoftKeyboRD(this);
                    break;

                }
        }
    }

    public class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long startTime, long interval) {
            super(startTime, interval);

        }

        @Override
        public void onFinish() {
            CountTimerId.setVisibility(View.GONE);
            resendotpId.setVisibility(View.VISIBLE);

        }

        @Override
        public void onTick(long millisUntilFinished) {
            setTimeId.setText("00:" + millisUntilFinished / 1000);
        }
    }
}


