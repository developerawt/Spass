package offery.wizzo.in.offery.webApi;

import com.google.gson.annotations.SerializedName;

/**
 * Created by WinSnit on 01-Sep-17.
 */

public class StarredList {
    @SerializedName("new_id")
    public String new_id;
    @SerializedName("news")
    public String news;
    @SerializedName("news_type")
    public String news_type;
    @SerializedName("news_class")
    public String news_class;
    @SerializedName("news_time")
    public String news_time;
    @SerializedName("news_date")
    public String news_date;
    @SerializedName("news_status")
    public String news_status;
    @SerializedName("school_id")
    public String school_id;
}
