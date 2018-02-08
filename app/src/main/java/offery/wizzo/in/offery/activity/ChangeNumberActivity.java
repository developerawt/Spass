package offery.wizzo.in.offery.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hbb20.CountryCodePicker;

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

public class ChangeNumberActivity extends BaseActvitiy implements View.OnClickListener {
    EditText MobileNumber;
    private Button ChangeNumber;
    EditText codes;
    String countryCodeAndroid = "91";
    //Must ic_store root url and one place
    public static final String ROOT_URL = "http://ssappsnwebs.com/spass/admin2/api";
    String mobile;
    CountryCodePicker ccp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_number);
        initView();
        GetCountryZipCode();
        ccp = (CountryCodePicker) findViewById(R.id.pickerCountryId);
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
        mobile = AppSharedPrefs.getInstance(this).readPrefs("mobile");
        Log.e("MOBIle", mobile);
//        value.setText(mobile);
        ChangeNumber.setOnClickListener(this);

    }

    @Override
    public void initView() {
        MobileNumber = (EditText) findViewById(R.id.etMoblieNumberId);
        ChangeNumber = (Button) findViewById(R.id.btnSubmitChangeNumberId);
        codes = (EditText) findViewById(R.id.ed_texts);


    }


    private void insertNumber() {
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


        //uriString = i.getStringExtra("text_label");
        //Defining the method insertuser of our interface
        api.updateMobileNumber(
                //Passing the values by getting it from editTexts
                mobile,
                MobileNumber.getText().toString(),


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
                                    Intent in = new Intent(ChangeNumberActivity.this, MainActivity.class);
                                    startActivity(in);
                                    Toast.makeText(ChangeNumberActivity.this, "Thank you", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            });
                        } else if (result.equalsIgnoreCase("3")) {
                            finish();
                        } else {
                            Toast.makeText(ChangeNumberActivity.this, "Number Exishted.", Toast.LENGTH_SHORT).show();
                        }
                        UrlUtils.dismissDialog();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        // If any error occured displaying the error as toast
                        Toast.makeText(ChangeNumberActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                        UrlUtils.dismissDialog();
                    }
                }
        );
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
                codes.setText(CountryZipCode);
                codes.setSelection(MobileNumber.getText().length());
                Log.e("Code", CountryID + CountryZipCode);
                break;
            }
        }
        return CountryZipCode;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnSubmitChangeNumberId:
                if (MobileNumber.getText().length() > 13) {
                    Toast.makeText(getApplication(), "Please Insert Your Number ..", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    insertNumber();
                    UrlUtils.hideSoftKeyboRD(this);
                    break;

                }
        }
    }

    @Override
    public void onBackPressed() {
        Intent bck = new Intent(ChangeNumberActivity.this, MainActivity.class);
        startActivity(bck);
    }
}
