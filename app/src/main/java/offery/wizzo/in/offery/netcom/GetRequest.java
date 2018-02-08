package offery.wizzo.in.offery.netcom;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import offery.wizzo.in.offery.interfacefile.TaskCompleteListener;

public class GetRequest {
    public static offery.wizzo.in.offery.netcom.GetRequest getRequestInstance = null;

    public static offery.wizzo.in.offery.netcom.GetRequest getGetRequestInstance(Context context) {
        if (getRequestInstance == null) {
            getRequestInstance = new offery.wizzo.in.offery.netcom.GetRequest();
        }
        return getRequestInstance;
    }

    public ProgressDialog progressDialog = null;
    Context context;
    TaskCompleteListener taskCompleteListener;
    int requestType;
    String url = "";

    public void showDialog(Context context) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
        progressDialog = new ProgressDialog(context, AlertDialog.THEME_DEVICE_DEFAULT_DARK);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Loading...");
        progressDialog.show();
    }

    public void hideProgressDialog(Context context) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    public void GetRequest(Context context, String url, int reqType, TaskCompleteListener taskCompleteListener) {
        this.requestType = reqType;
        this.taskCompleteListener = taskCompleteListener;
        this.context = context;
        this.url = url;
        if (offery.wizzo.in.offery.netcom.CheckNetwork.checkConnection(context)) {
            new JSONAsyncTask().execute();
        } else {
            Toast.makeText(context, "Network Error", Toast.LENGTH_LONG).show();
        }
    }


    public void postRequest(Context context, HashMap<String, String> mData, String url, int reqType, TaskCompleteListener taskCompleteListener) {
        this.requestType = reqType;
        this.taskCompleteListener = taskCompleteListener;
        this.context = context;
        this.url = url;
        if (offery.wizzo.in.offery.netcom.CheckNetwork.checkConnection(context)) {
            new AsyncHttpPost(mData).execute();
        } else {
            Toast.makeText(context, "Network Error", Toast.LENGTH_LONG).show();
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
            showDialog(context);
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

            hideProgressDialog(context);
            if (result != null && !result.equalsIgnoreCase("")) {
                taskCompleteListener.onTaskComplete(requestType, result);
            } else {
                taskCompleteListener.onTaskError(requestType, result);
            }
        }

    }


    class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {

        String responseString = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showDialog(context);
        }

        @Override
        protected Boolean doInBackground(String... urls) {
            try {
                //------------------>>
                HttpGet httppost = new HttpGet(url);
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(httppost);

                // StatusLine stat = response.getStatusLine();
                int status = response.getStatusLine().getStatusCode();

                if (status == 200) {
                    HttpEntity entity = response.getEntity();
                    responseString = EntityUtils.toString(entity);
                    return true;
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {

                e.printStackTrace();
            }
            return false;
        }

        protected void onPostExecute(Boolean result) {
            hideProgressDialog(context);
            if (result) {
                taskCompleteListener.onTaskComplete(requestType, responseString);
            } else {
                taskCompleteListener.onTaskError(requestType, responseString);
            }
        }

    }
}

