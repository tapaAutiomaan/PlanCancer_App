package com.plancancer.plancancernews.persistance.model;

/**
 * Created by Assou on 19/08/2015.
 */
public class NewsPostElements {
    private String title;
    private String pubDate;
    private String imageAvatar;
    private String brief;
    private String postId;
    private String author;
    private String postImage;

    public NewsPostElements() {

    }

    public NewsPostElements(String title, String pubDate, String imageUrl, String author, String brief,String postImage,String postId) {
        this.title=title;
        this.pubDate=pubDate;
        this.imageAvatar=imageUrl;
        this.author=author;
        this.brief=brief;
        this.postId=postId;
        this.postImage=postImage;
    }


    public String getImageAvatar() {
        return imageAvatar;
    }

    public String getBrief() {
        return brief;
    }

    public String getPostId() {
        return postId;
    }

    public String getPostImage() {
        return postImage;
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
