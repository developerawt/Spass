package offery.wizzo.in.offery.webApi;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by WinSnit on 4/27/2017.
 */

public class TwilioVerification {
    private final String TAG = "TwilioVerification";
    Context ctx;
    String recipient,otp;
    String body;
    String sender;
    String twilioUrl ="https://api.twilio.com/2010-04-01/Accounts/AC84088e6cfd4bffcf847b443121e7dff7/Messages.json";
    String response = "";
    public TwilioVerification(Context ctx) {
        this.ctx = ctx;
    }

    public void sendMessage(String msgTo, String msgBody, final String otp)
    {
        this.recipient = msgTo;
        this.body = msgBody;
        this.otp=otp;
        this.sender = "+15859373124";

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {

                HashMap<String, String> data= new HashMap<>();
                data.put("From", sender);
                data.put("Body", body);
                data.put("To", recipient);
                try {
                    doPost(data);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
//            @Override
//            protected void onPostExecute(Void aVoid) {
//                InsertOtpActivity.value.setText(otp);
//            }
        }.execute();
    }

    public String doPost(HashMap<String, String> postData) throws IOException {
        URL url = new URL(twilioUrl);


        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setUseCaches(false);
            connection.setDoOutput(true);
            connection.setDoInput(true);

            connection.setReadTimeout(15000);
            connection.setConnectTimeout(15000);

            connection.setRequestMethod("POST");

            String postString = buildString(postData);
            byte[] postBytes = postString.getBytes("UTF-8");

            String base64EncodedCredentials = "Basic "
                    + Base64.encodeToString(
                    ("AC84088e6cfd4bffcf847b443121e7dff7" + ":" + "d92ad4063034969f1fb/ade191e4b3e67").getBytes(),
                    Base64.NO_WRAP);



            connection.setRequestProperty("Authorization", "Basic QUM4NDA4OGU2Y2ZkNGJmZmNmODQ3YjQ0MzEyMWU3ZGZmNzpkOTJhZDQwNjMwMzQ5NjlmMWZiYWRlMTkxZTRiM2U2Nw==");

            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//            connection.setRequestProperty("Content-Length", Integer.toString(postBytes.length));
            // Write parameter...
            OutputStream outStream = connection.getOutputStream();
            outStream.write(postBytes);
            outStream.flush();
            outStream.close();

            connection.connect();
            int resCode = connection.getResponseCode();
            Log.v(TAG, "Response Message: " + connection.getResponseMessage());

            if (resCode == HttpsURLConnection.HTTP_OK || resCode == HttpsURLConnection.HTTP_CREATED) {
               // InsertOtpActivity.value.setText(otp);
                String line;
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    response += line;
                }
            }

            else if (resCode == HttpsURLConnection.HTTP_BAD_REQUEST)
            {
                String line;
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    response += line;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    private String buildString(HashMap<String, String> postData) throws UnsupportedEncodingException {
        StringBuilder strBuilder = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : postData.entrySet()) {
            try {
                Log.v(TAG, "HTTPPOST ENTRY: " + entry.getKey() + " - " + entry.getValue());
                if (first)
                    first = false;
                else
                    strBuilder.append("&");

                strBuilder.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                strBuilder.append("=");
                strBuilder.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            } catch (Exception e) {

            }
        }

        return strBuilder.toString();
    }
}