package offery.wizzo.in.offery.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

import offery.wizzo.in.offery.R;
import offery.wizzo.in.offery.interfacefile.ApiInterface;
import offery.wizzo.in.offery.webApi.AppUrl;
import offery.wizzo.in.offery.webApi.SchoolList;


public class SplassclassActivity extends BaseActvitiy implements View.OnClickListener {
    ProgressDialog progressDialog;
    Spinner sp_school, sp_class;
    ArrayList<String> schoolId = new ArrayList<>();
    ArrayList<SchoolList> schoolLists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spass);
        getSchoolList();
        findViewById(R.id.submitBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplassclassActivity.this, MainActivity.class).putExtra("data", getIntent().getStringExtra("data"))
                        .putExtra("school_id", sp_school.getSelectedItem() + "")
                        .putExtra("class_name", sp_class.getSelectedItem() + ""));
            }
        });
    }

    void getSchoolList() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        ApiInterface apiService = AppUrl.getClient().create(ApiInterface.class);
        retrofit2.Call<ArrayList<SchoolList>> call = apiService.getSchoolList();

        call.enqueue(new retrofit2.Callback<ArrayList<SchoolList>>() {
            @Override
            public void onResponse(retrofit2.Call<ArrayList<SchoolList>> call, retrofit2.Response<ArrayList<SchoolList>> response) {
                progressDialog.dismiss();
                schoolLists = response.body();
                Log.e("Size", response.body().size() + " test");
                init();
            }

            @Override
            public void onFailure(retrofit2.Call<ArrayList<SchoolList>> call, Throwable t) {
                progressDialog.dismiss();
                t.printStackTrace();
            }
        });
    }

    @Override
    public void initView() {

    }

    void init() {
        sp_school = (Spinner) findViewById(R.id.sp_school);
        sp_class = (Spinner) findViewById(R.id.sp_class);

        if (schoolLists != null && schoolLists.size() > 0) {
            for (int i = 0; i < schoolLists.size(); i++) {
                if (schoolId.size() == 0) {
                    schoolId.add(schoolLists.get(i).school_id);
                } else {
                    boolean check = false;
                    for (int j = 0; j < schoolId.size(); j++) {
                        if (schoolLists.get(i).school_id.equalsIgnoreCase(schoolId.get(j))) {
                            check = true;
                        }
                    }

                    if (!check) {
                        schoolId.add(schoolLists.get(i).school_id);
                    }
                }
            }
        }

        ArrayAdapter<String> adp1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, schoolId);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_school.setAdapter(adp1);

        ArrayAdapter<String> adp2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                getClassName(schoolId.get(0)));
        adp2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_class.setAdapter(adp2);

        sp_school.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                // TODO Auto-generated method stub
                ArrayAdapter<String> adp2 = new ArrayAdapter<String>(SplassclassActivity.this, android.R.layout.simple_spinner_item,
                        getClassName(schoolId.get(position)));
                adp2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp_class.setAdapter(adp2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        sp_class.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    ArrayList<String> getClassName(String schoolID) {
        ArrayList<String> classList = new ArrayList<>();
        if (schoolLists != null && schoolLists.size() > 0) {
            for (int i = 0; i < schoolLists.size(); i++) {
                if (schoolLists.get(i).school_id.equalsIgnoreCase(schoolID)) {
                    classList.add(schoolLists.get(i).class_name);
                }
            }
        }
        return classList;
    }
}
