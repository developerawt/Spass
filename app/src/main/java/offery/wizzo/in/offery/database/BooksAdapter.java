package offery.wizzo.in.offery.database;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.NetworkImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;
import io.realm.Realm;
import offery.wizzo.in.offery.R;
import offery.wizzo.in.offery.interfacefile.ApiCalls;
import offery.wizzo.in.offery.model.NewsFeeds;
import offery.wizzo.in.offery.custom.PinnedHeaderItemDecoration;
import offery.wizzo.in.offery.netcom.CheckNetwork;
import offery.wizzo.in.offery.webApi.AppSharedPrefs;
import offery.wizzo.in.offery.webApi.UrlUtils;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class BooksAdapter extends RealmRecyclerViewAdapter<NewsFeeds> implements PinnedHeaderItemDecoration.PinnedHeaderAdapter  {

    private Context context;
    private Realm realm;

    public BooksAdapter(Context context) {

        this.context = context;
    }

    // create new views (invoked by the layout manager)
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.raw_school_header, parent, false);
            return new HeaderViewHolder(itemView);

        } else {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.raw_school_list, parent, false);
            return new MyViewHolder(itemView);
        }
    }


    @Override
    public boolean isPinnedViewType(int viewType) {
        if (viewType ==0) {
            return true;
        } else {
            return false;
        }
    }

    // replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, int position) {

        realm = RealmController.getInstance().getRealm();

        if (viewHolder instanceof MyViewHolder) {
            MyViewHolder myViewHolder = (MyViewHolder) viewHolder;
            myViewHolder.bindData(getItem(position), context, position);
        } else if (viewHolder instanceof HeaderViewHolder) {
            HeaderViewHolder myViewHolder = (HeaderViewHolder) viewHolder;
            myViewHolder.bindData(getItem(position), context, position);

        }

        

    }

    // return the size of your data set (invoked by the layout manager)
    public int getItemCount() {

        if (getRealmAdapter() != null) {
            return getRealmAdapter().getCount();
        }
        return 0;
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder {




        public TextView songTitleis, usernameis, ratingis;
        RelativeLayout rootLayout;
        TextView date1, txt_title, txt_author, txt_detail, txttime;
        ImageView chatimage;
        CircleImageView urlImageView;
        NetworkImageView imageView;
        ImageView blank_img, clr_img;

        public MyViewHolder(View view) {
            super(view);
            urlImageView = (CircleImageView) itemView.findViewById(R.id.imgIconSchoolListId);

            chatimage = (ImageView) itemView.findViewById(R.id.imgChatImageSchoolListId);

            this.txt_title = (TextView) itemView.findViewById(R.id.txtSchoolNameSchoolListId);
            this.txt_author = (TextView) itemView.findViewById(R.id.txtNewsSchoolListId);
            this.txt_detail = (TextView) itemView.findViewById(R.id.txtDateSchoolListId);
            this.txttime = (TextView) itemView.findViewById(R.id.txtTimeSchoolListId);
            this.blank_img = (ImageView) itemView.findViewById(R.id.imgNormalStarSchoolListId);
            this.clr_img = (ImageView) itemView.findViewById(R.id.imgYellowStarSchoolListId);
            //   this.date1=(TextView)itemView.findViewById(R.id.date1);
            // this.fullView = (TextView) itemView.findViewById(R.id.fullview);
            Log.e("status", String.valueOf(AppSharedPrefs.getInstance(view.getContext()).readBooleanPrefs("status")));


            chatimage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String itemUrl = getItem(getAdapterPosition()).getUrl();
                    if (itemUrl != null && !itemUrl.equals("") &&itemUrl.endsWith(".mp4"))
                    {
                        Toast.makeText(context, "video clicked", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setDataAndType(Uri.parse(itemUrl), "video/mp4");
                        context.startActivity(intent);
                    }
                }
            });


            blank_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppSharedPrefs.getInstance(itemView.getContext()).writeBooleanPrefs("status", true);

                    blank_img.setVisibility(View.GONE);
                    clr_img.setVisibility(View.VISIBLE);
                    insert_star(getItem(getAdapterPosition()).getNew_id(), "1");
                }
            });
            clr_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppSharedPrefs.getInstance(itemView.getContext()).writeBooleanPrefs("status", false);
                    blank_img.setVisibility(View.VISIBLE);
                    clr_img.setVisibility(View.GONE);
                    insert_star(getItem(getAdapterPosition()).getNew_id(), "0");

                }
            });
        }


        public void bindData(NewsFeeds news, Context context, int position) {
            try {

                txt_title.setText(news.getSchool_name());
                txt_author.setText(Html.fromHtml(news.getNews()));
                txt_detail.setText(dateAndTimeInHoursHeader(news.getNews_date()));
                txttime.setText(dateAndTimeInHours(news.getNews_time()));

                Glide.with(itemView.getContext())
                        .load(news.getSchool_logo())
                        .error(R.drawable.ic_user_profile)
                        .override(200, 200)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.ic_user_profile)
                        .into(urlImageView);

                if (news.getUrl() != null && !news.getUrl().equals("")) {
                    chatimage.setVisibility(View.VISIBLE);

                    if (news.getUrl().endsWith(".mp4"))
                    {
                        Glide.with(itemView.getContext())
                                .load(R.mipmap.ic_play_button)
                                .dontAnimate().dontTransform()
                                .error(R.mipmap.ic_play_button).diskCacheStrategy(DiskCacheStrategy.ALL)
                                .placeholder(R.mipmap.ic_play_button)
                                .into(chatimage);
                    }
                    else

                        Glide.with(itemView.getContext())
                                .load(news.getUrl())
                                .dontAnimate().dontTransform()
                                .error(R.drawable.ic_gallery).diskCacheStrategy(DiskCacheStrategy.ALL)
                                .placeholder(R.drawable.ic_gallery)
                                .into(chatimage);
                } else {
                    chatimage.setVisibility(View.GONE);
                }


            } catch (Exception e) {
                e.printStackTrace();
            }


        }





}


    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        public TextView songTitleis, usernameis, ratingis;
        RelativeLayout rootLayout;
        TextView date1, txt_title, txt_author, txt_detail, txttime;
        ImageView chatimage;
        CircleImageView urlImageView;
        NetworkImageView imageView;
        ImageView blank_img, clr_img;

        public HeaderViewHolder(View view) {
            super(view);
            this.txt_detail = (TextView) itemView.findViewById(R.id.date);

        }

        public void bindData(NewsFeeds news, Context context, int position) {
            txt_detail.setText(dateAndTimeInHoursHeader(news.getNews_date()));

        }

    }




    private void insert_star(String newsId, String status) {
        if (!CheckNetwork.checkConnection(context)) {
            Toast.makeText(context, context.getResources().getString(R.string.network_error), Toast.LENGTH_LONG).show();
            return;
        }

        String mobile = AppSharedPrefs.getInstance(context).readPrefs(context, "mobile");
        UrlUtils.showProgress(context);
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(context.getResources().getString(R.string.base_url)) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ApiCalls api = adapter.create(ApiCalls.class);

        //uriString = i.getStringExtra("text_label");
        //Defining the method insertuser of our interface
        api.insertstatus(

                //Passing the values by getting it from editTexts
                mobile,
                status,
                newsId,

                //Creating an anonymous callback
                new Callback<Response>() {
                    @Override
                    public void success(retrofit.client.Response response, retrofit.client.Response response2) {
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
                            Toast.makeText(context, "successful", Toast.LENGTH_SHORT).show();

                            ((Activity) context).runOnUiThread(new Runnable() {
                                public void run() {

                                }
                            });
                        } else if (result.equalsIgnoreCase("3")) {

                            Toast.makeText(context, "This mobile number already registerd", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(context, "New selected is wrong.", Toast.LENGTH_SHORT).show();
                        }
                        UrlUtils.dismissDialog();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        // If any error occured displaying the error as toast
                        Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                        UrlUtils.dismissDialog();

                    }

                });
    }


    public String dateAndTimeInHours(final String date) {
        String dateAndTime = "";
        try {


            SimpleDateFormat dateFormatter = new SimpleDateFormat("hh:mm:ss");
            Date date1 = dateFormatter.parse(date);

// Get time from date
            SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm a");
            String displayValue = timeFormatter.format(date1);
            if (displayValue != null) {
                dateAndTime = displayValue;
                return dateAndTime;
            }

        } catch (Exception e) {

        }

        return dateAndTime;

    }


    public String dateAndTimeInHoursHeader(final String date) {
        String dateAndTime = "";
        try {


            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = dateFormatter.parse(date);

// Get time from date
            SimpleDateFormat timeFormatter = new SimpleDateFormat("dd MMM yyyy");
            String displayValue = timeFormatter.format(date1);
            if (displayValue != null) {
                dateAndTime = displayValue;
                return dateAndTime;
            }

        } catch (Exception e) {

        }

        return dateAndTime;

    }
}