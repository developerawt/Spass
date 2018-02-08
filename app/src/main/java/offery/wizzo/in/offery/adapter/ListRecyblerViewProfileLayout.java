package offery.wizzo.in.offery.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
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
import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;
import offery.wizzo.in.offery.R;
import offery.wizzo.in.offery.activity.FullScreenImageActivity;
import offery.wizzo.in.offery.interfacefile.ApiCalls;
import offery.wizzo.in.offery.model.NewsFeeds;
import offery.wizzo.in.offery.custom.PinnedHeaderItemDecoration;
import offery.wizzo.in.offery.netcom.CheckNetwork;
import offery.wizzo.in.offery.webApi.AppSharedPrefs;
import offery.wizzo.in.offery.webApi.FileDownloader;
import offery.wizzo.in.offery.webApi.UrlUtils;
import offery.wizzo.in.offery.database.RealmController;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by arpitjoshi on 1/9/17.
 */

public class ListRecyblerViewProfileLayout extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements PinnedHeaderItemDecoration.PinnedHeaderAdapter {

    private final Activity contxt;
    private ArrayList<NewsFeeds> moviesList;
    public static final String ROOT_URL = "http://ssappsnwebs.com/spass/admin2/api";
    OnItemClickedListener onItemClickListener;
    String mobile;
    String status;
    String news_id;
    String current, previous;
    ClickUpdates updator;

    public interface ClickUpdates {
        public void updateLocalPath(NewsFeeds news, String localPath);
    }

    @Override
    public boolean isPinnedViewType(int viewType) {
        if (viewType == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (moviesList.get(position).isHeader()) {
            return 0;
        } else {
            return 1;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView songTitleis, usernameis, ratingis;
        RelativeLayout rootLayout;
        TextView date1, txt_title, txt_author, txt_detail, txttime, videosize;
        ImageView chatimage;
        CircleImageView urlImageView;
        NetworkImageView imageView;
        ImageView blank_img, clr_img;
        WebView textContent;

        public MyViewHolder(View view) {
            super(view);
            urlImageView = (CircleImageView) itemView.findViewById(R.id.imgIconSchoolListId);

            chatimage = (ImageView) itemView.findViewById(R.id.imgChatImageSchoolListId);

            this.txt_title = (TextView) itemView.findViewById(R.id.txtSchoolNameSchoolListId);
            this.videosize = (TextView) itemView.findViewById(R.id.txtVideoSizeSchoolListId);
            this.txt_author = (TextView) itemView.findViewById(R.id.txtNewsSchoolListId);
            this.txt_detail = (TextView) itemView.findViewById(R.id.txtDateSchoolListId);
            this.txttime = (TextView) itemView.findViewById(R.id.txtTimeSchoolListId);
            this.blank_img = (ImageView) itemView.findViewById(R.id.imgNormalStarSchoolListId);
            this.clr_img = (ImageView) itemView.findViewById(R.id.imgYellowStarSchoolListId);
            this.textContent=(WebView)itemView.findViewById(R.id.wbTextContentSchoolListId);
            //   this.date1=(TextView)itemView.findViewById(R.id.date1);
            // this.fullView = (TextView) itemView.findViewById(R.id.fullview);
            Log.e("status", String.valueOf(AppSharedPrefs.getInstance(contxt).readBooleanPrefs("status")));


            chatimage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (moviesList.get(getAdapterPosition()).getUrl() != null &&
                            !moviesList.get(getAdapterPosition()).getUrl().equals("")
                            && moviesList.get(getAdapterPosition()).getUrl().endsWith(".mp4")) {
//                        Toast.makeText(contxt, "video clicked", Toast.LENGTH_SHORT).show();

                        if (moviesList.get(getAdapterPosition()).getLocalPath() != null &&
                                !moviesList.get(getAdapterPosition()).getLocalPath().trim().equals("")) {
                            try {
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.setDataAndType(Uri.parse(moviesList.get(getAdapterPosition()).getLocalPath()), "video/mp4");
                                contxt.startActivity(intent);
                            }catch (Exception e){

                            }
                        } else if (!CheckNetwork.checkConnection(contxt)) {
                            Toast.makeText(contxt, contxt.getResources().getString(R.string.network_error), Toast.LENGTH_LONG).show();
                        } else {
                            String[] paths = new String[1];

                            paths[0] = moviesList.get(getAdapterPosition()).getUrl();
                            new ASDownloadVideos(getAdapterPosition()).execute(paths);
                        }
                    }

                    //code for image
                    else {
                        if (moviesList.get(getAdapterPosition()).getLocalPath() != null &&
                                !moviesList.get(getAdapterPosition()).getLocalPath().trim().equals("")) {
                            Intent i = new Intent(contxt, FullScreenImageActivity.class);
                            i.putExtra("img_url", (moviesList.get(getAdapterPosition()).getLocalPath().trim()));
                            contxt.startActivity(i);
                        } else if (!CheckNetwork.checkConnection(contxt)) {
                            Toast.makeText(contxt, contxt.getResources().getString(R.string.network_error), Toast.LENGTH_LONG).show();
                        } else {
                            String[] paths = new String[1];

                            paths[0] = moviesList.get(getAdapterPosition()).getUrl();
                            new ASDownloadImages(getAdapterPosition()).execute(paths);
                        }

                    }
                }
            });
        }


        public void bindData(ArrayList<NewsFeeds> moviesList, Context context, int position) {
            try {


                txt_title.setText(moviesList.get(position).getSchool_name());
                txt_author.setText(Html.fromHtml(moviesList.get(position).getNews()));
                txt_detail.setText(dateAndTimeInHoursHeader(moviesList.get(position).getNews_date()));
                txttime.setText(dateAndTimeInHours(moviesList.get(position).getNews_time()));
                videosize.setText(moviesList.get(position).getVideosize());
                textContent.loadData(moviesList.get(position).getNews(), "text/html", "utf-8");

                Glide.with(contxt)
                        .load(moviesList.get(position).getSchool_logo())
                        .error(R.drawable.ic_user_profile)
                        .override(200, 200)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.ic_user_profile)
                        .into(urlImageView);

                if (moviesList.get(position).getUrl() != null && !moviesList.get(position).getUrl().equals("")) {
                    chatimage.setVisibility(View.VISIBLE);
                    videosize.setVisibility(View.VISIBLE);
                    if (moviesList.get(position).getUrl().endsWith(".mp4")) {
                        //videosize.setVisibility(View.VISIBLE);
                        Glide.with(contxt)
                                .load(R.mipmap.ic_play_button)
                                .dontAnimate().dontTransform()
                                .error(R.mipmap.ic_play_button).diskCacheStrategy(DiskCacheStrategy.ALL)
                                .placeholder(R.mipmap.ic_play_button)
                                .into(chatimage);
                    }else
                        Glide.with(contxt)
                                .load(moviesList.get(position).getUrl())
                                .dontAnimate().dontTransform()
                                .error(R.drawable.ic_gallery).diskCacheStrategy(DiskCacheStrategy.ALL)
                                .placeholder(R.drawable.ic_gallery)
                                .into(chatimage);
                } else {
                    chatimage.setVisibility(View.GONE);
                    videosize.setVisibility(View.GONE);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            blank_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppSharedPrefs.getInstance(contxt).writeBooleanPrefs("status", true);
                    status = "1";
                    blank_img.setVisibility(View.GONE);
                    clr_img.setVisibility(View.VISIBLE);
                    insert_star();
                }
            });
            clr_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppSharedPrefs.getInstance(contxt).writeBooleanPrefs("status", false);
                    status = "0";
                    blank_img.setVisibility(View.VISIBLE);
                    clr_img.setVisibility(View.GONE);
                    insert_star();
                }
            });
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

        public void bindData(ArrayList<NewsFeeds> moviesList, Context context, int position) {
            txt_detail.setText(dateAndTimeInHoursHeader(moviesList.get(position).getNews_date()));

        }

    }


    public void setOnItemClickListener(OnItemClickedListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;


    }

    public ListRecyblerViewProfileLayout(Activity context, ArrayList<NewsFeeds> moviesList, ClickUpdates updater) {
        this.moviesList = moviesList;
        this.contxt = context;
        this.updator = updater;
    }

    public void setNewList(ArrayList<NewsFeeds> moviesList) {
        this.moviesList = moviesList;
        notifyDataSetChanged();
    }

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
    public long getItemId(int position) {

        return position;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        try {
            news_id = moviesList.get(position).getNew_id();
            Log.e("idssssssss", news_id);

            if (holder instanceof MyViewHolder) {
                MyViewHolder myViewHolder = (MyViewHolder) holder;
                myViewHolder.bindData(moviesList, contxt, position);
            } else if (holder instanceof HeaderViewHolder) {
                HeaderViewHolder myViewHolder = (HeaderViewHolder) holder;
                myViewHolder.bindData(moviesList, contxt, position);

            }
        } catch (Exception e) {

        }
    }


    private void insert_star() {
        if (!CheckNetwork.checkConnection(contxt)) {
            Toast.makeText(contxt, contxt.getResources().getString(R.string.network_error), Toast.LENGTH_LONG).show();
            return;
        }

        mobile = AppSharedPrefs.getInstance(contxt).readPrefs(contxt, "mobile");
        UrlUtils.showProgress(contxt);
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ApiCalls api = adapter.create(ApiCalls.class);

        //uriString = i.getStringExtra("text_label");
        //Defining the method insertuser of our interface
        api.insertstatus(

                //Passing the values by getting it from editTexts
                mobile,
                status,
                news_id,

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
                            Toast.makeText(contxt, "successful", Toast.LENGTH_SHORT).show();

                            ((Activity) contxt).runOnUiThread(new Runnable() {
                                public void run() {

                                }
                            });
                        } else if (result.equalsIgnoreCase("3")) {

                            Toast.makeText(contxt, "This mobile number already registerd", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(contxt, "New selected is wrong.", Toast.LENGTH_SHORT).show();
                        }
                        UrlUtils.dismissDialog();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        // If any error occured displaying the error as toast
                        Toast.makeText(contxt, error.toString(), Toast.LENGTH_LONG).show();
                        UrlUtils.dismissDialog();

                    }

                });
    }


    private class ASDownloadVideos extends AsyncTask<String, Void, String> {
        int newsPosition;

        public ASDownloadVideos(int newsPosition) {
            this.newsPosition = newsPosition;
        }

        @Override
        protected void onPreExecute() {
            UrlUtils.showProgress(contxt);
        }

        @Override
        protected String doInBackground(String... params) {
            String response = null;
            try {
                response = new FileDownloader().downloadFile(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            if (s != null) {
//                NewsFeeds newsFromDb= RealmController.with(contxt).getSingleNews(moviesList.get(newsPosition).getNew_id());
                RealmController.with(contxt).updateLocalPath(moviesList.get(newsPosition).getNew_id(), s);
                updator.updateLocalPath(moviesList.get(newsPosition), s);       //Will update Fragment list
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse(s), "video/mp4");
                contxt.startActivity(intent);
            }
            UrlUtils.dismissDialog();
        }
    }


    private class ASDownloadImages extends AsyncTask<String, Void, String> {
        int newsPosition;

        public ASDownloadImages(int newsPosition) {
            this.newsPosition = newsPosition;
        }

        @Override
        protected void onPreExecute() {
            UrlUtils.showProgress(contxt);
        }

        @Override
        protected String doInBackground(String... params) {
            String response = null;
            try {
                response = new FileDownloader().downloadFile(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            if (s != null) {
//                NewsFeeds newsFromDb= RealmController.with(contxt).getSingleNews(moviesList.get(newsPosition).getNew_id());
                RealmController.with(contxt).updateLocalPath(moviesList.get(newsPosition).getNew_id(), s);

                updator.updateLocalPath(moviesList.get(newsPosition), "file://" + s);       //Will update Fragment list
                Intent i = new Intent(contxt, FullScreenImageActivity.class);
                i.putExtra("img_url", s);
                contxt.startActivity(i);
            }
            UrlUtils.dismissDialog();
        }
    }

















        /*
        if (items.get(position).getTitle() != null && items.get(position).getTitle().length() > 2) {
            holder.txt_title.setText(items.get(position).getTitle());
        } else {
            holder.txt_title.setText(KEY_NOT_AVAILABLE);
        }
        holder.imageView.setImageUrl(items.get(position).getImageUrl(), imageLoader);

    }


   /* if (items.get(position).getAuthor() != null && items.get(position).getAuthor().length() > 2) {
        holder.txt_author.setText(items.get(position).getAuthor());
    } else {
        holder.txt_author.setText(KEY_NOT_AVAILABLE);
    }

    if (items.get(position).getText() != null && items.get(position).getText().length() > 2) {
        holder.txt_detail.setText(items.get(position).getText());
    } else {
        holder.txt_detail.setText(KEY_NOT_AVAILABLE);
    }*/

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

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public interface OnItemClickedListener {
        void onItemClicked(int position);
    }
}




