package offery.wizzo.in.offery.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.exceptions.RealmException;
import offery.wizzo.in.offery.R;
import offery.wizzo.in.offery.adapter.ListRecyblerViewProfileLayout;
import offery.wizzo.in.offery.model.NewsFeeds;
import offery.wizzo.in.offery.model.NewsFeedsParentBean;
import offery.wizzo.in.offery.custom.PinnedHeaderItemDecoration;
import offery.wizzo.in.offery.netcom.CheckNetwork;
import offery.wizzo.in.offery.netcom.GsonRealmBuilder;
import offery.wizzo.in.offery.webApi.AppSharedPrefs;
import offery.wizzo.in.offery.webApi.News;
import offery.wizzo.in.offery.database.RealmController;




public class PeresonalNewsFragment extends Fragment implements ListRecyblerViewProfileLayout.ClickUpdates {
    String TAG;
    private ProgressDialog pDialog;
    private String tag_json_obj = "jobj_req";
    public static News school;
    RecyclerView rv_newsList;
    private SharedPreferences sharedPreferences;
    private String URL_JSON_OBJECT;
    public String url = "http://ssappsnwebs.com/spass/admin2/api/Api_control/fetchNewsFeed";
    public ArrayList<NewsFeeds> allNewsFromLocal, filteredNews;                         // Filtered list will ic_store filtered results
    private ArrayList<String> dateHeaders = new ArrayList<>();
    TextView tv_noData;

    ListRecyblerViewProfileLayout adapter;
    private boolean oncreate = false;
    boolean setUserVisibleHint = false;

    //new Variables form ankushpate18
    private Realm realm;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        try {
            if (oncreate && isVisibleToUser) {
                setUserVisibleHint = true;
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("reqType", "personal");
                params.put("mobile", AppSharedPrefs.getInstance(getActivity()).readPrefs(getActivity(), "mobile"));
                if (getActivity() != null && CheckNetwork.checkConnection(getActivity()))
                    new AsyncHttpPost(params).execute();

            }

        } catch (Exception r) {
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_general_news, null);
        rv_newsList = (RecyclerView) rootView.findViewById(R.id.rvNewsListGeneralNewsId);
        tv_noData = (TextView) rootView.findViewById(R.id.tvNoDataGeneralNewswId);
        oncreate = true;
        //get realm instance
        this.realm = RealmController.with(this).getRealm();
        // ic_refresh the realm instance
        RealmController.with(this).refresh();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rv_newsList.setLayoutManager(mLayoutManager);
        rv_newsList.addItemDecoration(new PinnedHeaderItemDecoration());
        allNewsFromLocal = new ArrayList<>();
        filteredNews = new ArrayList<>();
        adapter = new ListRecyblerViewProfileLayout(getActivity(), filteredNews, this);
        rv_newsList.setAdapter(adapter);

        getDataFromDatabase();


        if (!setUserVisibleHint) {
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("reqType", "personal");
            params.put("mobile", AppSharedPrefs.getInstance(getActivity()).readPrefs(getActivity(), "mobile"));
            if (CheckNetwork.checkConnection(getActivity()))
                new AsyncHttpPost(params).execute();
        }
        return rootView;
    }

    private void showProgressDialog() {
        try {
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.dismiss();
                pDialog = null;
            }
            pDialog = new ProgressDialog(getActivity(), AlertDialog.THEME_DEVICE_DEFAULT_DARK);
            pDialog.setTitle("Loading...");
            pDialog.setCancelable(false);
            pDialog.show();
        } catch (Exception e) {
        }
    }

    private void hideProgressDialog() {
        if (pDialog.isShowing())
            pDialog.hide();
    }

    public void setRequest() {
// Post params to be sent to the server
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("id", "10");
        JsonObjectRequest request_json = new JsonObjectRequest(url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //Process os success response
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        });

// add the request object to the queue to be executed

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request_json);
    }


    private void initRecyclerView(final ArrayList<NewsFeeds> listFromServer) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

//stuff that updates ui

                for (NewsFeeds b : listFromServer)
                    if ((RealmController.getInstance().getSingleNews(b.getNew_id()) == null)) {
                        if (b.isHeader() && dateHeaders.contains(b.getNews_date().trim())) {
                        } else {
                            try {
                                // Persist your data easily
                                realm.beginTransaction();
                                realm.copyToRealm(b);
                                realm.commitTransaction();

                            } catch (RealmException ex) {
                                ex.printStackTrace();
                                realm.commitTransaction();
                            }
                        }

                    }


                getDataFromDatabase();

                if (filteredNews != null && filteredNews.size() > 0) {
                    tv_noData.setVisibility(View.GONE);
                    rv_newsList.setVisibility(View.VISIBLE);
//                    adapter.setNewList(allNewsFromLocal);
                } else {
                    tv_noData.setVisibility(View.VISIBLE);
                    rv_newsList.setVisibility(View.GONE);
                }


            }
        });
    }

    @Override
    public void updateLocalPath(NewsFeeds news, String localPath) {
        if (filteredNews.contains(news)) {
            RealmController.getInstance().getRealm().beginTransaction();
            int index = filteredNews.indexOf(news);
            if (allNewsFromLocal.contains(news)) {
                allNewsFromLocal.get(allNewsFromLocal.indexOf(news)).setLocalPath(localPath);
            }
            filteredNews.get(index).setLocalPath(localPath);
            adapter.notifyItemChanged(index);
            RealmController.getInstance().getRealm().commitTransaction();

        } else {

            for (int i = 0; i < allNewsFromLocal.size(); i++) {
                if (filteredNews.size() > i)
                    if (filteredNews.get(i).getNew_id().trim().equals(news.getNew_id().trim())) {
                        RealmController.getInstance().getRealm().beginTransaction();
                        filteredNews.get(i).setLocalPath(localPath);
                        adapter.notifyItemChanged(i);
                        RealmController.getInstance().getRealm().beginTransaction();
                    }

                if (allNewsFromLocal.get(i).getNew_id().trim().equals(news.getNew_id().trim())) {
                    RealmController.getInstance().getRealm().beginTransaction();
                    allNewsFromLocal.get(i).setLocalPath(localPath);
                    RealmController.getInstance().getRealm().beginTransaction();
                }


            }


        }
    }


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


    public void updateSearchText(String newText) {
        if (newText.equals("")) {
            filteredNews.clear();
            filteredNews.addAll(allNewsFromLocal);
        } else {
            filteredNews.clear();
            for (NewsFeeds b : allNewsFromLocal) {
                if (b.getNews().toLowerCase().contains(newText.toLowerCase()))
                    filteredNews.add(b);
            }

        }
        adapter.notifyDataSetChanged();
    }


    public void setResult(String result) {
        try {
            ArrayList<NewsFeeds> newListFromServer = new ArrayList<>();
//            allNewsFromLocal = new ArrayList<>();

            if (result != null && !result.equalsIgnoreCase("")) {
                Gson gson = GsonRealmBuilder.get();
                String dateAndTime = "";
                String date = "";

                NewsFeedsParentBean newsFeedsParentBean = gson.fromJson(result, NewsFeedsParentBean.class);


                for (int i = 0; i < newsFeedsParentBean.getResponse().size(); i++) {
                    if (newsFeedsParentBean.getResponse().get(i).getStatus() != null && newsFeedsParentBean.getResponse().get(i).getStatus().equalsIgnoreCase("1")) {
                        ArrayList<NewsFeeds> arrayList = newsFeedsParentBean.getResponse().get(i).getFeeds();


                        for (int k = 0; k < arrayList.size(); k++) {
//
                            newListFromServer.add(arrayList.get(k));
                        }
                    } else {

                        showtoast();
                        break;
                    }
                }
                Collections.reverse(newListFromServer);
                initRecyclerView(newListFromServer);
            }
        } catch (Exception ew) {
            ew.printStackTrace();

        }
    }


    final String KEY_NOT_AVAILABLE = "N/A";


    public void showtoast() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(), "No data found", Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(broadcastReceiver);
        } catch (Exception e) {
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            LocalBroadcastManager.getInstance(getActivity()).registerReceiver(broadcastReceiver, new IntentFilter("PUSH"));
        } catch (Exception e) {

        }
    }


    /**
     * Will fetch personal news from {@link RealmController}
     * ic_store news in {@link #filteredNews} and ic_refresh {@link #adapter}
     * */
    private void getDataFromDatabase() {
        String dateAndTime = "";
        String date = "";
        allNewsFromLocal.clear();
        dateHeaders.clear();
        filteredNews.clear();

        RealmResults<NewsFeeds> dbList = RealmController.with(getActivity()).getNews("personal");
//        Collections.reverse(dbList);
        ArrayList<NewsFeeds> tempListFromDb= new ArrayList<>(dbList);
        Collections.reverse(tempListFromDb);

        for (int i = 0; i < tempListFromDb.size(); i++)
            if (tempListFromDb.get(i).getNews_type().trim().equals("personal")) {
                date = tempListFromDb.get(i).getNews_date();

                if (!date.equalsIgnoreCase(dateAndTime)) {
                                /*An Object will be added to show date only*/
                    NewsFeeds newsFeeds = new NewsFeeds();
                    int id = Integer.valueOf(tempListFromDb.get(i).getNew_id());
                    id = (id * id - id);
                    id = Integer.reverse(id);
//                                newsFeeds.setNew_id(String.valueOf(id));
                    newsFeeds.setHeader(true);
                    newsFeeds.setNews_type("personal");
                    newsFeeds.setNews(tempListFromDb.get(i).getNews());
                    newsFeeds.setNews_date(tempListFromDb.get(i).getNews_date());
                    newsFeeds.setNews_time(tempListFromDb.get(i).getNews_time());
                    newsFeeds.setSchool_logo(tempListFromDb.get(i).getSchool_logo());
                    newsFeeds.setSchool_name(tempListFromDb.get(i).getSchool_name());
                    newsFeeds.setNew_id(String.valueOf(id));
                    newsFeeds.setVideosize(tempListFromDb.get(i).getVideosize());

                    allNewsFromLocal.add(newsFeeds);
                    dateHeaders.add(tempListFromDb.get(i).getNews_date());


                }
                dateAndTime = tempListFromDb.get(i).getNews_date();
                allNewsFromLocal.add(tempListFromDb.get(i));
            }

        filteredNews.addAll(allNewsFromLocal);
        adapter.notifyDataSetChanged();
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {


            if (intent.getExtras() != null) {
                String type = intent.getExtras().getString("type");
                if (type != null && !type.equalsIgnoreCase("general")) {

                    try {


                        HashMap<String, String> params = new HashMap<String, String>();
                        params.put("reqType", "personal");
                        params.put("mobile", AppSharedPrefs.getInstance(getActivity()).readPrefs(getActivity(), "mobile"));
                        if (getActivity() != null)
                            if (CheckNetwork.checkConnection(getActivity()))
                                new AsyncHttpPost(params).execute();

                    } catch (Exception e) {

                    }
                }
            }
        }
    };

}



