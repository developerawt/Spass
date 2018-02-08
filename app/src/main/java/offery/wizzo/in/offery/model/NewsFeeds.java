package offery.wizzo.in.offery.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by ist on 10/9/17.
 */

public class NewsFeeds extends RealmObject implements Serializable {

    @SerializedName("news")
    private String news;

    @SerializedName("new_id")
    private String new_id;

    @SerializedName("school_name")
    private String school_name;

    @SerializedName("school_address")
    private String school_address;

    @SerializedName("news_date")
    private String news_date;

    @SerializedName("news_time")
    private String news_time;

    @SerializedName("news_type")
    private String news_type;

    private String localPath;

    @SerializedName("file_size")
    private String videosize;

    @SerializedName("url")
    private String url;

    @SerializedName("school_logo")
    private String school_logo;

    private boolean isHeader = false;

    public boolean isHeader() {
        return isHeader;
    }

    public void setHeader(boolean header) {
        isHeader = header;
    }

    public String getSchool_logo() {
        return school_logo;
    }

    public void setSchool_logo(String school_logo) {
        this.school_logo = school_logo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public String getNews_date() {
        return news_date;
    }

    public void setNews_date(String news_date) {
        this.news_date = news_date;
    }

    public String getNews_time() {
        return news_time;
    }


    public void setNews_time(String news_time) {
        this.news_time = news_time;
    }

    public String getNews_type() {
        return news_type;
    }

    public void setNews_type(String news_type) {
        this.news_type = news_type;
    }

    public String getNew_id() {
        return new_id;
    }

    public void setNew_id(String new_id) {
        this.new_id = new_id;
    }


    public String getSchool_address() {
        return school_address;
    }

    public void setSchool_address(String school_address) {
        this.school_address = school_address;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public String getVideosize() {
        return videosize;
    }

    public void setVideosize(String videosize) {
        this.videosize = videosize;
    }
}
