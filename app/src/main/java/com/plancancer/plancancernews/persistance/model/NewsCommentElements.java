package com.plancancer.plancancernews.persistance.model;

/**
 * Created by Assou on 19/08/2015.
 */
public class NewsCommentElements {
    private String title;
    private String pubDate;

    public String getImageAvatar() {
        return imageAvatar;
    }

    public String getBrief() {
        return brief;
    }

    public String getPostId() {
        return postId;
    }

    private String imageAvatar;
    private String brief;
    private String postId;

    public void setImageAvatar(String imageAvatar) {
        this.imageAvatar = imageAvatar;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    private String author;


    public NewsCommentElements() {

    }


    public NewsCommentElements(String title, String pubDate, String imageUrl, String author,String brief) {
        this.title=title;
        this.pubDate=pubDate;
        this.imageAvatar=imageUrl;
        this.author=author;
        this.brief=brief;
    }


    public String getAuthor() {
        return author;
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




}
