package offery.wizzo.in.offery.model;

import java.util.ArrayList;

/**
 * Created by ist on 10/9/17.
 */

public class NewsFeedBean {

    String status;
        ArrayList<NewsFeeds>feeds;

    public String getStatus() {
        return status;
    }

    public void setStatus(String statua) {
        this.status = statua;
    }

    public ArrayList<NewsFeeds> getFeeds() {
        return feeds;
    }

    public void setFeeds(ArrayList<NewsFeeds> feeds) {
        this.feeds = feeds;
    }
}
