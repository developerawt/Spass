package offery.wizzo.in.offery.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import offery.wizzo.in.offery.R;
import offery.wizzo.in.offery.webApi.AppSharedPrefs;


public class MyAccountActivity extends Activity {
    TextView toolbarTv, txtNumberId;
    Button btnlogOutId;
    String mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        toolbarTv = (TextView) findViewById(R.id.header);
        txtNumberId = (TextView) findViewById(R.id.txtLoginNumberAccountId);
        btnlogOutId = (Button) findViewById(R.id.btnLogOutAccountId);
        mobile = AppSharedPrefs.getInstance(this).readPrefs("mobile");
        Log.e("MOBIle", mobile);
        txtNumberId.setText(mobile);
        btnlogOutId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutAlert(MyAccountActivity.this);
            }
        });
        toolbarTv.setText("My Account");
    }

    public void logoutAlert(final Context mContext) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MyAccountActivity.this);

        // Setting Dialog Title
        alertDialog.setTitle("Confirmation");

        // Setting Dialog Message
        alertDialog.setMessage("Are you sure you want deactivate account ?");

        // Setting Icon to Dialog
        alertDialog.setIcon(R.drawable.icons_unavailable);

        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(MyAccountActivity.this);
                SharedPreferences.Editor editor = sharedPrefs.edit();
                editor.clear();
                editor.commit();
                AppSharedPrefs.getInstance(MyAccountActivity.this).clear();
                Toast.makeText(mContext, "Account Deactivate Successfully...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MyAccountActivity.this, UserRegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to invoke NO event
                dialog.cancel();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}