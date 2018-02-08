package offery.wizzo.in.offery.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

import br.com.mauker.materialsearchview.MaterialSearchView;
import offery.wizzo.in.offery.*;
import offery.wizzo.in.offery.adapter.SimpleItemRecyclerViewAdapter;
import offery.wizzo.in.offery.model.DataBean;
import offery.wizzo.in.offery.model.SchoolBean;
import offery.wizzo.in.offery.netcom.GetRequest;
import offery.wizzo.in.offery.interfacefile.TaskCompleteListener;
import offery.wizzo.in.offery.webApi.UrlUtils;


public class GetSearchSchoolActivity extends BaseActvitiy implements SimpleItemRecyclerViewAdapter.onClickEvent, View.OnClickListener {
    private RecyclerView mRecyclerView;
    private ArrayList<SchoolBean> mArrayList = new ArrayList<>();
    private SimpleItemRecyclerViewAdapter mAdapter;
    private EditText searchBox;
    LinearLayout lnr;

    private EditText search_box;
    private Button submitButton;
    public static ArrayList<DataBean> schoolNameList;
    TextView toolbarTitle, text_header;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private MaterialSearchView searchView;
    ImageView srch;
    ImageView back_button;
    SharedPreferences sp;
    String flagExist;
    ArrayList<String> existList;
    public static boolean good = true;
    private ArrayList<DataBean> mDatabeanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_school);
        initView();
        try {
            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
            Gson gson = new Gson();
            String json = sharedPrefs.getString("TAG", null);
            Type type = new TypeToken<ArrayList<DataBean>>() {
            }.getType();
            mDatabeanList = gson.fromJson(json, type);

            Log.e("arrayList", "" + mDatabeanList.toString());


            for (int i = 0; i < mDatabeanList.size(); i++) {

            }

        } catch (Exception e) {

        }

        submitButton.setOnClickListener(this);
        srch.setOnClickListener(this);
        back_button.setOnClickListener(this);
        toolbarTitle = (TextView) findViewById(R.id.header);

        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mArrayList != null && mArrayList.size() > 0) {
                    mAdapter.getFilter().filter(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        setmAdapter();
    }


    public void setmAdapter() {

        GetRequest.getGetRequestInstance(this).GetRequest(this, UrlUtils.GETSCHOOL_LIST, 1, new TaskCompleteListener() {
            @Override
            public void onTaskComplete(int requestType, Object object) {
                if (requestType == 1 && object != null) {
                    mArrayList = getListItemData((String) object);
                    try{
                        if (mDatabeanList.size() > 0) {
                            for (int i = 0; i < mArrayList.size(); i++) {
                                for (int j = 0; j < mDatabeanList.size(); j++) {
                                    String getlaatsid = "" + mArrayList.get(i).getSchoolId();
                                    if (getlaatsid.equals(mDatabeanList.get(j).getId())) {
                                        mArrayList.get(i).setChecked(true);
                                    }
                                }

                            }
                        }
                    }
                    catch (Exception e){

                    }

                    mAdapter = new SimpleItemRecyclerViewAdapter(GetSearchSchoolActivity.this, mArrayList, GetSearchSchoolActivity.this, flagExist);
                    mRecyclerView.setAdapter(mAdapter);

                }
            }

            @Override
            public void onTaskError(int requestType, Object object) {

            }
        });

    }


    ////////////
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.item_list);
        searchBox = (EditText) findViewById(R.id.search_box11);
        submitButton = (Button) findViewById(R.id.btnSubmitSearchSchoolId);
        srch = (ImageView) findViewById(R.id.tv_header_title);
        lnr = (LinearLayout) findViewById(R.id.linear_srch);
        back_button = (ImageView) findViewById(R.id.backks);
        text_header = (TextView) findViewById(R.id.txtHaderNameSearchSchoolId);

    }

    private ArrayList<SchoolBean> getListItemData(String array) {
        ArrayList<SchoolBean> listViewItems = new ArrayList<SchoolBean>();
        try {
            JSONObject obj = new JSONObject(array);
            JSONArray jr = obj.getJSONArray("response");
            for (int i = 0; i < jr.length(); i++) {

                JSONObject jb = (JSONObject) jr.getJSONObject(i);
                SchoolBean schoolBean = new SchoolBean();
                String useid = jb.getString("id");
                String name = jb.getString("name");
                String address = jb.getString("addres");

                schoolBean.setSchoolId(Integer.parseInt(useid));
                schoolBean.setSchoolName(name);
                schoolBean.setAddress(address);
                listViewItems.add(schoolBean);
            }
            return listViewItems;

        } catch (Exception e) {
        }
        return listViewItems;

    }


    @Override
    public void onCLickEvent(View view, int position) {
        switch (view.getId()) {
            case R.id.checkUncheckedBox:

                if (mAdapter.getmFilteredList() != null && mAdapter.getmFilteredList().size() > 0) {
                    if (!mAdapter.getmFilteredList().get(position).isChecked()) {
                        mAdapter.getmFilteredList().get(position).setChecked(true);
                    } else {
                        mAdapter.getmFilteredList().get(position).setChecked(false);
                    }
                    mAdapter.notifyDataSetChanged();
                }
                break;

        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.backks:
                lnr.setVisibility(View.GONE);
                text_header.setVisibility(View.VISIBLE);
                srch.setVisibility(View.VISIBLE);
                break;

            case R.id.tv_header_title:
                Animation zoomout = AnimationUtils.loadAnimation(GetSearchSchoolActivity.this, R.anim.anim_slide_in_left);
                lnr.setAnimation(zoomout);
                lnr.startAnimation(zoomout);
                text_header.setVisibility(View.GONE);
                lnr.setVisibility(View.VISIBLE);
                srch.setVisibility(View.GONE);
                break;

            case R.id.btnSubmitSearchSchoolId:

                String data = getStringValues();


                if (data != null && !data.equals("")) {
                    Log.e("dayta", "data" + data);
                    Intent ip = new Intent(this, SchoolClassActivity.class);
                    ip.putExtra("data", data);

                    startActivity(ip);
                    finish();
                } else {
                    Toast.makeText(this, "No record selected", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }


    public String getStringValues() {
        String data = "";
        if (mAdapter.getmFilteredList() != null && mAdapter.getmFilteredList().size() > 0) {
            StringBuilder srStringBuilder = new StringBuilder();
            schoolNameList = new ArrayList<>();
            for (int i = 0; i < mAdapter.getmFilteredList().size(); i++) {
                if (mAdapter.getmFilteredList().get(i).isChecked()) {
                    SchoolBean schoolBean = mAdapter.getmFilteredList().get(i);
                    srStringBuilder.append(schoolBean.getSchoolId());
                    String Check_School_Id = String.valueOf(schoolBean.getSchoolId());

                    Log.e("check", "id" + Check_School_Id);
                    srStringBuilder.append(",");
                    DataBean dataBean = new DataBean();
                    dataBean.setId(String.valueOf(schoolBean.getSchoolId()));
                    dataBean.setName(schoolBean.getSchoolName());
                    schoolNameList.add(dataBean);
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(schoolNameList);
                    sp = this.getSharedPreferences("KEY", Context.MODE_PRIVATE);
                    sp.edit().putString("KEY", jsonString).commit();


                    SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
                    SharedPreferences.Editor editor = sharedPrefs.edit();
                    Gson gson1 = new Gson();

                    String json = gson1.toJson(schoolNameList);

                    editor.putString("TAG", json);
                    editor.commit();

                } else {

                }
            }
            if (srStringBuilder.length() == 0) {
                return data;
            } else {
                srStringBuilder.setLength(srStringBuilder.length() - 1);
                Log.d("GetSearchDa", "" + srStringBuilder.toString());
                data = srStringBuilder.toString();
            }


        }
        return data;
    }

    public static void writeList(Context context, ArrayList<DataBean> list, String prefix) {
        SharedPreferences prefs = context.getSharedPreferences("YourApp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        int size = prefs.getInt(prefix + "_size", 0);

        // clear the previous data if exists
        for (int i = 0; i < size; i++)
            editor.remove(prefix + "_" + i);

        // write the current list
        for (int i = 0; i < list.size(); i++)
            editor.putString(prefix + "_" + i, String.valueOf(list.get(i).getName()));

        editor.putInt(prefix + "_size", list.size());
        editor.commit();
    }
}

