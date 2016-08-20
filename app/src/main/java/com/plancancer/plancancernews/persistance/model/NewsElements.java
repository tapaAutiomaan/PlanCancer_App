package com.plancancer.plancancernews.persistance.model;

/**
 * Created by Assou on 19/08/2015.
 */
public class NewsElements {
    private String title;
    private String pubDate;
    private String imageUrl;
    private String description;

    private String author;
    private String content;






    public NewsElements() {

    }


    public NewsElements(String title, String pubDate, String imageUrl, String author) {
        this.setTitle(title);
        this.setPubDate(pubDate);
        this.setImageUrl(imageUrl);
        this.author=author;
        //this.setDescription(description);
    }


    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
