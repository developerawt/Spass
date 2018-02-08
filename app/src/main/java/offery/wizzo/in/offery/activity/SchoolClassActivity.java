package offery.wizzo.in.offery.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import offery.wizzo.in.offery.R;
import offery.wizzo.in.offery.adapter.ClassListAdapter;
import offery.wizzo.in.offery.model.ClassModel;
import offery.wizzo.in.offery.model.ClassSetResponse;
import offery.wizzo.in.offery.model.ClassesModel;
import offery.wizzo.in.offery.model.ClassesParentModel;
import offery.wizzo.in.offery.model.DataBean;
import offery.wizzo.in.offery.netcom.CheckNetwork;
import offery.wizzo.in.offery.netcom.GetRequest;
import offery.wizzo.in.offery.interfacefile.TaskCompleteListener;
import offery.wizzo.in.offery.webApi.AppSharedPrefs;
import offery.wizzo.in.offery.webApi.UrlUtils;

/**
 * Created by ist on 10/9/17.
 */

public class SchoolClassActivity extends Activity implements View.OnClickListener {

    List<DataBean> groupList;
    List<ClassModel> childList;
    Map<String, List<ClassModel>> laptopCollection;
    ExpandableListView expListView;
    TextView toolbarTv;
    private HashMap<String, String> map;
    private StringBuilder srStringBuilder;
    private StringBuilder classSelected;
    private ArrayList<Object> newString;
    private Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_class);
        toolbarTv = (TextView) findViewById(R.id.header);
        toolbarTv.setText("Select Classes");

        submitBtn = (Button) findViewById(R.id.btmSubmitSchoolId);
        submitBtn.setOnClickListener(this);

        expListView = (ExpandableListView) findViewById(R.id.explistId);
        childList = new ArrayList<>();
        String data = getIntent().getExtras().getString("data");
        if (data != null && !data.equalsIgnoreCase(""))
            getDataFromServer(data);
    }


    public void getDataFromServer(String data) {

        if (!CheckNetwork.checkConnection(this)) {
            Toast.makeText(SchoolClassActivity.this, getResources().getString(R.string.network_error), Toast.LENGTH_LONG).show();
            return;
        }

        HashMap<String, String> map = new HashMap<>();
        map.put("schoolIds", data);
        GetRequest.getGetRequestInstance(this).postRequest(this, map, UrlUtils.GETCLASSES, 2, new TaskCompleteListener() {
            @Override
            public void onTaskComplete(int requestType, Object object) {
                String response = (String) object;
                if (response != null && !response.equalsIgnoreCase("")) {
                    setData(response);


                    Log.e("RESPNSE_MSG", response);
                }
            }

            @Override
            public void onTaskError(int requestType, Object object) {
                Toast.makeText(SchoolClassActivity.this, "Error", Toast.LENGTH_LONG).show();
            }
        });
    }


    public void setClassToServer() {

        if (!CheckNetwork.checkConnection(this)) {
            Toast.makeText(SchoolClassActivity.this, getResources().getString(R.string.network_error), Toast.LENGTH_LONG).show();
            return;
        }

        GetRequest.getGetRequestInstance(this).postRequest(this, map, UrlUtils.SETCLASSES, 2, new TaskCompleteListener() {
            @Override
            public void onTaskComplete(int requestType, Object object) {
                String response = (String) object;
                String resposeCode = "";

                if (response != null && !response.equalsIgnoreCase("")) {

                    Gson gson = new Gson();
                    ClassSetResponse classSetResponse = gson.fromJson(response.toString(), ClassSetResponse.class);
                    if (classSetResponse.getResponse() != null && classSetResponse.getResponse().size() > 0) {

                        for (int i = 0; i < classSetResponse.getResponse().size(); i++) {
                            resposeCode = classSetResponse.getResponse().get(i).getStatus();
                        }
                        if (resposeCode.equalsIgnoreCase("1")) {

                            startActivity(new Intent(SchoolClassActivity.this, MainActivity.class));
                            AppSharedPrefs.getInstance(SchoolClassActivity.this).writePrefs("ishome", "1");
                            finish();
                        } else {

                            Toast.makeText(SchoolClassActivity.this, "School and classes not inserted", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }

            @Override
            public void onTaskError(int requestType, Object object) {
                Toast.makeText(SchoolClassActivity.this, "Error", Toast.LENGTH_LONG).show();
            }
        });
    }


    public void sendClassesToServer() {

        if (!CheckNetwork.checkConnection(this)) {
            Toast.makeText(SchoolClassActivity.this, getResources().getString(R.string.network_error), Toast.LENGTH_LONG).show();
            return;
        }


    }


    public void createRequest() {
        if (laptopCollection != null) {
            srStringBuilder = new StringBuilder();
            newString = new ArrayList<>();
            Iterator myVeryOwnIterator = laptopCollection.keySet().iterator();
            map = new HashMap<>();
            while (myVeryOwnIterator.hasNext()) {
                String key = (String) myVeryOwnIterator.next();
                classSelected = new StringBuilder();
                ArrayList<ClassModel> value = (ArrayList<ClassModel>) laptopCollection.get(key);
                boolean isCheckedAnyone = false;
                for (int i = 0; i < value.size(); i++) {
                    if (value.get(i).isChecked()) {
                        newString.add(value.get(i).getClass_name());
                        isCheckedAnyone = true;
                        classSelected.append(value.get(i).getClass_id());
                        classSelected.append(",");
                    } else {
                    }
                }
                if (isCheckedAnyone) {
                    classSelected.setLength(classSelected.length() - 1);
                    String data = getId(key);
                    map.put(data, classSelected.toString());
                    srStringBuilder.append(data);
                    srStringBuilder.append(",");
                }
                else
                {
                    String data = getId(key);
                    srStringBuilder.append(data);
                    srStringBuilder.append(",");
                }
            }
        }
    }


    public String getId(String value) {
        String id = "";
        if (groupList != null && groupList.size() > 0) {
            for (int i = 0; i < groupList.size(); i++) {
                if (groupList.get(i).getName().equalsIgnoreCase(value)) {
                    id = groupList.get(i).getId();
                    break;
                }
            }
        }
        return id;
    }

    public void setData(String data) {
        laptopCollection = new HashMap<>();
        groupList = new ArrayList<>();
        Gson gson = new Gson();
        String name = "";
        ClassesParentModel.NewClassData persons = gson.fromJson(data, ClassesParentModel.NewClassData.class);
        for (int i = 0; i < persons.getResponse().size(); i++) {
            ArrayList<ClassesModel> classBeen = persons.getResponse();
            DataBean school = new DataBean();
            school.setId(classBeen.get(i).getSchool_id());
            name = getSchoolName(classBeen.get(i).getSchool_id());
            school.setName(name);
            groupList.add(school);
            childList = new ArrayList<>();
            ArrayList<ClassModel> classBeen1 = persons.getResponse().get(i).getClasses();

            for (int k = 0; k < classBeen1.size(); k++) {
                Log.d("arpit", classBeen1.get(k).getClass_name());
                childList.add(classBeen1.get(k));
            }
            laptopCollection.put(name, childList);
        }

        final ClassListAdapter expListAdapter = new ClassListAdapter(
                this, groupList, laptopCollection);
        expListView.setAdapter(expListAdapter);
        //setGroupIndicatorToRight();
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                final ClassModel selected = (ClassModel) expListAdapter.getChild(
                        groupPosition, childPosition);

                return true;
            }
        });

    }


    public String getSchoolName(String schoolId) {
        String name = "";
        if (GetSearchSchoolActivity.schoolNameList != null && GetSearchSchoolActivity.schoolNameList.size() > 0) {
            for (int i = 0; i < GetSearchSchoolActivity.schoolNameList.size(); i++) {
                if (GetSearchSchoolActivity.schoolNameList.get(i).getId().equalsIgnoreCase(schoolId)) {
                    name = GetSearchSchoolActivity.schoolNameList.get(i).getName();
                    break;
                }

            }


        }
        return name;
    }


    private void createGroupList() {

    }

    private void loadChild(String[] laptopModels) {

    }

    private void setGroupIndicatorToRight() {
        /* Get the screen width */
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;

        expListView.setIndicatorBounds(width - getDipsFromPixel(35), width
                - getDipsFromPixel(5));
    }

    // Convert pixel to dip
    public int getDipsFromPixel(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btmSubmitSchoolId:
                createRequest();
                if (newString != null && newString.size() > 0) {

                    srStringBuilder.setLength(srStringBuilder.length() - 1);
                    map.put("school_ids", srStringBuilder.toString());
                    map.put("mobile", AppSharedPrefs.getInstance(SchoolClassActivity.this).readPrefs(SchoolClassActivity.this, "mobile"));
                    setClassToServer();
                } else {
                //    Toast.makeText(this, "Please select any class", Toast.LENGTH_LONG).show();

                    map = new HashMap<>();
                    srStringBuilder.setLength(srStringBuilder.length() - 1);
                    map.put("school_ids", srStringBuilder.toString());
                    map.put("mobile", AppSharedPrefs.getInstance(SchoolClassActivity.this).readPrefs(SchoolClassActivity.this, "mobile"));
                    setClassToServer();

                }
                    break;
                }
        }
























}
