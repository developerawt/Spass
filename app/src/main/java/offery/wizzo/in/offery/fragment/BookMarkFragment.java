package offery.wizzo.in.offery.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import offery.wizzo.in.offery.activity.MainActivity;
import offery.wizzo.in.offery.R;
import offery.wizzo.in.offery.activity.GetSearchSchoolActivity;
import offery.wizzo.in.offery.adapter.ExpandableListAdapter;
import offery.wizzo.in.offery.model.DataBean;
import offery.wizzo.in.offery.model.Staff;
import offery.wizzo.in.offery.webApi.AppSharedPrefs;

/**
 * Created by Jauhar xlr on 4/18/2016.
 * mycreativecodes.in
 */
public class BookMarkFragment extends Fragment {
    String TAG;
    private ProgressDialog pDialog;
    public String url ="http://ssappsnwebs.com/spass/admin2/api/Api_control/fetchMembers";
   // public String url = "http://ssappsnwebs.com/spass/admin2/api/Api_control/getStaff";
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    String mobile;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_bookmark, null);

         mobile =  AppSharedPrefs.getInstance(getActivity()).readPrefs("mobile");
        Log.e("mobile",mobile);
        MainActivity.toolbar.setTitle("Directory");
        expListView = (ExpandableListView) rootView.findViewById(R.id.lvExp);
        listDataChild = new HashMap<>();
        listDataHeader = new ArrayList<>();
        listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader, listDataChild);
        // fragment_setting list adapter
        expListView.setAdapter(listAdapter);


        HashMap<String, String> params = new HashMap<String, String>();
        params.put("mobile", mobile);
        new AsyncHttpPost(params).execute();

        return rootView;
    }


    private void showProgressDialog() {
        if (pDialog != null && pDialog.isShowing()) {
            pDialog.dismiss();
            pDialog = null;
        }
        pDialog = new ProgressDialog(getActivity());
        pDialog.setTitle("Loading...");
        pDialog.setCancelable(false);
        pDialog.show();
    }

    private void hideProgressDialog() {
        if (pDialog.isShowing())
            pDialog.hide();
    }


// add the request object to the queue to be executed


    public class AsyncHttpPost extends AsyncTask<String, String, String> {
        private HashMap<String, String> mData = null;// post data

        /**
         * constructor
         */
        public AsyncHttpPost(HashMap<String, String> data) {
            mData = data;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressDialog();
        }

        /**
         * background
         */
        @Override
        protected String doInBackground(String... params) {
            byte[] result = null;
            String str = "";
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(url);// in this case, params[0] is URL
            try {
                // set up post data
                ArrayList<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
                Iterator<String> it = mData.keySet().iterator();
                while (it.hasNext()) {
                    String key = it.next();
                    nameValuePair.add(new BasicNameValuePair(key, mData.get(key)));
                }

                post.setEntity(new UrlEncodedFormEntity(nameValuePair, "UTF-8"));
                HttpResponse response = client.execute(post);
                StatusLine statusLine = response.getStatusLine();
                if (statusLine.getStatusCode() == HttpURLConnection.HTTP_OK) {
                    result = EntityUtils.toByteArray(response.getEntity());
                    str = new String(result, "UTF-8");
                    Log.d("respose", str);
                    setResult(str);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (Exception e) {
            }
            return str;
        }

        /**
         * on getting result
         */
        @Override
        protected void onPostExecute(String result) {
            // something...
            hideProgressDialog();
        }

    }


    public void setResult(String result) {
        ArrayList<Staff> newsArrayList = new ArrayList<>();
        try {
            JSONObject obj1=new JSONObject(result);
            JSONArray posts=obj1.getJSONArray("response");
           // JSONArray posts =  obj1.JSONArray("response");


            if (posts != null && posts.length() > 0)

            {
                for (int i = 0; i < posts.length(); i++) {
                    try {
                        JSONObject news = posts.getJSONObject(i);
                        String ststus=news.getString("status");
                        if(ststus.equals("1"))
                        {
                            JSONArray jsonArray=news.getJSONArray("data");
                            for(int j=0; j<jsonArray.length();j++)
                            {
                                JSONObject jsonObject =jsonArray.getJSONObject(j);
                                Staff news1 = new Staff();
                                news1.setSchoolId(jsonObject.getString("school_id"));
                                news1.setStaffschooluser(jsonObject.getString("staff_school_username"));
                                news1.setStaffName(jsonObject.getString("staff_name"));
                                news1.setSchoolName(getSchoolName(jsonObject.getString("school_id")));
                                news1.setStaffName(jsonObject.getString("staff_name"));
                                newsArrayList.add(news1);

                            }

                        }
                    } catch (Exception e) {
                        Log.e("msg",e.toString());
                    }

                }

            }
        } catch (Exception ew) {
            ew.printStackTrace();

        }
        addData(newsArrayList);
    }

    public void addData(ArrayList<Staff> newsArrayList) {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        List<String> top250 = null;
        Gson gson=new Gson();


        String empty_list = gson.toJson(new ArrayList<DataBean>());
        SharedPreferences sp = getActivity().getSharedPreferences("KEY", Context.MODE_PRIVATE);
        ArrayList<DataBean> mSelectedList = gson.fromJson(sp.getString("KEY", empty_list),
                new TypeToken<ArrayList<DataBean>>() {
                }.getType());
        Log.e("Data",mSelectedList.toString());

        for (int i = 0; i < mSelectedList.size(); i++) {
            listDataHeader.add(i,mSelectedList.get(i).getName());
        }


        if (listDataHeader != null && listDataHeader.size() > 0) {
            for (int j = 0; j < listDataHeader.size(); j++) {
                top250 = new ArrayList<String>();
                for (int k = 0; k < newsArrayList.size(); k++) {
                    if (newsArrayList.get(k).getSchoolName().equalsIgnoreCase(listDataHeader.get(j)))
                        top250.add(newsArrayList.get(k).getStaffName());
                    listDataChild.put(listDataHeader.get(j), top250);
                }
            }

        }
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                listAdapter.ExpandableListAdapter(getActivity(),listDataHeader,listDataChild);
            }
        });

    }


    public String getSchoolName(String id) {
        String name = "";

        if (GetSearchSchoolActivity.schoolNameList != null && GetSearchSchoolActivity.schoolNameList.size() > 0) {
            for (int i = 0; i < GetSearchSchoolActivity.schoolNameList.size(); i++) {
                if (GetSearchSchoolActivity.schoolNameList.get(i).getId().equalsIgnoreCase(id)) {
                    name = GetSearchSchoolActivity.schoolNameList.get(i).getName();

                }
            }

        }
        return name;

    }

    final String KEY_NOT_AVAILABLE = "N/A";

}




