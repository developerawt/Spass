package offery.wizzo.in.offery.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;
import com.hbb20.CountryCodePicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Locale;

import offery.wizzo.in.offery.R;
import offery.wizzo.in.offery.interfacefile.ApiCalls;
import offery.wizzo.in.offery.fcm.Config;
import offery.wizzo.in.offery.fcm.NotificationUtils;
import offery.wizzo.in.offery.netcom.CheckNetwork;

import offery.wizzo.in.offery.webApi.AppSharedPrefs;
import offery.wizzo.in.offery.webApi.UrlUtils;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;


public class UserRegisterActivity extends BaseActvitiy implements View.OnClickListener {
    private static final String TAG = UserRegisterActivity.class.getSimpleName();
    EditText RegistrationNumber;
    private Button submitButton;
    private ProgressBar pb;
    String Number;
    String deviceno;
    String token;
    EditText code;
    RelativeLayout llll;
    String countryCodeAndroid = "91";
    CountryCodePicker ccp;

    TextView content;
    StringBuilder sb = new StringBuilder();
    public static final String ROOT_URL = "http://ssappsnwebs.com/spass/admin2/api";
    private BroadcastReceiver mRegistrationBroadcastReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        final TelephonyManager mTelephony = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String countryCode = mTelephony.getSimCountryIso();
        GetCountryZipCode();
        deviceno = mTelephony.getDeviceId();
        // value.setText(countryCode );
        Log.e("code", countryCode);
        Log.e("Device_no", deviceno);
        llll.setOnClickListener(this);
        ccp = (CountryCodePicker) findViewById(R.id.pickerCountryCodeId);
        String code1 = ccp.getDefaultCountryCode();
        Log.e("code1", code1);
        ccp.setDefaultCountryUsingNameCode(code1);
        ccp.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                countryCodeAndroid = ccp.getSelectedCountryCode();
                Log.d("Country Code", countryCodeAndroid);
            }
        });

        submitButton.setOnClickListener(this);
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // checking for type intent filter
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
//                   Getting the registration token from the intent
                    token = intent.getStringExtra("token");
                    Log.e("Token", token);
                    ;
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);

                    displayFirebaseRegId();

                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    // new push notification is received

                    String message = intent.getStringExtra("message");

                    Toast.makeText(getApplicationContext(), "Push notification: " + message, Toast.LENGTH_LONG).show();

//                    txtMessage.setText(message);
                }
            }
        };

        displayFirebaseRegId();
    }

    // Fetches reg id from shared preferences
    // and displays on the screen
    private void displayFirebaseRegId() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        String regId = pref.getString("regId", null);
        token = regId;
        Log.e(TAG, "Firebase reg id: " + regId);

        if (!TextUtils.isEmpty(regId))
            Log.e("Firebase Reg Id: ", regId);
        else
            Log.e("Firebase Reg Id: ", " ");
    }

    @Override
    protected void onResume() {
        super.onResume();

        // register GCM registration complete receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.REGISTRATION_COMPLETE));

        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));

        // clear the notification area when the app is opened
        NotificationUtils.clearNotifications(getApplicationContext());
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }
    @Override
    public void initView() {
//        content = (TextView) findViewById(R.id.content);
        RegistrationNumber = (EditText) findViewById(R.id.etMobileNumberRegisterId);
        submitButton = (Button) findViewById(R.id.btnSubmitRegisterId);
        llll = (RelativeLayout) findViewById(R.id.relativeMainRegisterId);
        code = (EditText) findViewById(R.id.ed_text);


    }


    private void insertUser() {

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
        if (token == null) {
            token = "123456789";
        }
        //Defining the method insertuser of our interface
        api.insertUser(
                //Passing the values by getting it from editTexts
                RegistrationNumber.getText().toString(),
                deviceno,
                token,
                new Callback<retrofit.client.Response>() {
                    @Override
                    public void success(retrofit.client.Response response, retrofit.client.Response response2) {
                        BufferedReader reader = null;
                        //An string to ic_store output from the server
                        String output = "";
                        String Otp = "";

                        try {
                            //Initializing buffered reader
                            reader = new BufferedReader(new InputStreamReader(response.getBody().in()));
                            //Reading the output in the string
                            output = reader.readLine();

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
                                    Otp = obj.getString("otp");
                                    Log.e("ststus", Otp);
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

                        Intent intent = new Intent(UserRegisterActivity.this, InsertOtpActivity.class);
                        String number = RegistrationNumber.getText().toString();
                        intent.putExtra("mobile", number);
                        intent.putExtra("token", token);
                        intent.putExtra("device_id", deviceno);
                        intent.putExtra("otp", Otp);
                        intent.putExtra("country_code", "+" + ccp.getSelectedCountryCode());

                        startActivity(intent);
                        Toast.makeText(UserRegisterActivity.this, "Text Message Send..", Toast.LENGTH_SHORT).show();
                        AppSharedPrefs.getInstance(UserRegisterActivity.this).writePrefs("mobile", number);

                        finish();
                        Log.e("Otp", output);
                        UrlUtils.dismissDialog();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        // If any error occured displaying the error as toast
                        Toast.makeText(UserRegisterActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                        UrlUtils.dismissDialog();
                    }
                }
        );
    }

    public static String getUserCountry(Context context) {
        try {
            final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            final String simCountry = tm.getSimCountryIso();
            if (simCountry != null && simCountry.length() == 2) { // SIM country code is available
                return simCountry.toLowerCase(Locale.US);
            } else if (tm.getPhoneType() != TelephonyManager.PHONE_TYPE_CDMA) { // device is not 3G (would be unreliable)
                String networkCountry = tm.getNetworkCountryIso();
                if (networkCountry != null && networkCountry.length() == 2) { // network country code is available
                    return networkCountry.toLowerCase(Locale.US);
                }
            }
        } catch (Exception e) {
        }
        return null;
    }


    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
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
                code.setText("+" + CountryZipCode);
                code.setSelection(RegistrationNumber.getText().length());
                Log.e("Code", CountryID + CountryZipCode);
                break;
            }
        }
        return CountryZipCode;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.relativeMainRegisterId:
                hideSoftKeyboard(UserRegisterActivity.this);
                break;

            case R.id.btnSubmitRegisterId:
                //if (value.getText().toString().matches("")) {
                if (RegistrationNumber.getText().toString().equals("") && RegistrationNumber.getText().toString().length() < 15) {
                    Toast.makeText(getApplication(), "Please enter mobile number", Toast.LENGTH_SHORT).show();

                } else {
                    insertUser();
                    //GetText();
                    UrlUtils.hideSoftKeyboRD(this);

                }
                break;
        }
    }


    public void GetText() throws UnsupportedEncodingException {
        // Get user defined values
        Number = RegistrationNumber.getText().toString();

        // Create data variable for sent values to server

        String data = URLEncoder.encode("mobile", "UTF-8")
                + "=" + URLEncoder.encode(Number, "UTF-8");

        String text = "";
        BufferedReader reader = null;

        // Send data
        try {

            // Defined URL  where to send data
            URL url = new URL("http://ssappsnwebs.com/spass/admin2/api/Api_control/getMobileStatus");

            // Send POST data request

            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();

            // Get the server response

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while ((line = reader.readLine()) != null) {
                // Append server response in string
                sb.append(line + "\n");
            }


            text = sb.toString();
        } catch (Exception ex) {

        } finally {
            try {

                reader.close();
            } catch (Exception ex) {
            }
        }

        // Show response on activity
        content.setText(text);

    }

}



