package offery.wizzo.in.offery.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import offery.wizzo.in.offery.R;


public class ContactActivity extends Activity {
    LinearLayout linerMainId;
    TextView txtEmailId, TxtWebId, TxtMobileId;
    TextView toolbarTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        toolbarTv = (TextView) findViewById(R.id.header);
        toolbarTv.setText("Contact info");
        txtEmailId = (TextView) findViewById(R.id.txtEmailContactId);
        TxtWebId = (TextView) findViewById(R.id.txtUrlContactId);
        TxtMobileId = (TextView) findViewById(R.id.txtNumberContactId);
    }


}