package offery.wizzo.in.offery.webApi;

import java.util.ArrayList;

/**
 * Created by WinSnit on 12-Jul-17.
 */

public class News {
    String uuid, url, author, title, text, crawled,image;
    private String name;
    private String imageUrl;
    private String authorName;
    private ArrayList<String> powers;

    public News() {

    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public News(String uuid, String url, String author, String title, String text, String crawled) {
        this.uuid = uuid;
        this.url = url;
        this.author = author;
        this.title = title;
        this.text = text;
        this.image= image;
        this.crawled = crawled;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getimage() {
        return image;
    }

    public void setimage(String uuid) {
        this.image = image;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCrawled() {
        return crawled;
    }

    public void setCrawled(String crawled) {
        this.crawled = crawled;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public ArrayList<String> getPowers() {
        return powers;
    }

    public void setPowers(ArrayList<String> powers) {
        this.powers = powers;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getAuthorName() {
        return authorName;
    }
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

}
