package com.plancancer.plancancernews.persistance.model;

/**
 * Created by Yazid on 17/05/2016.
 */
public class PlanCancerPostListItem {
    private String header;
    private String title;
    private String link;
    private String postId;
    private boolean comments;


    public PlanCancerPostListItem(){

    }

    public PlanCancerPostListItem(String header, String title, String link, String postId, boolean comments) {
        this.header = header;
        this.title = title;
        this.link = link;
        this.postId = postId;
        this.comments = comments;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public boolean isComments() {
        return comments;
    }

    public void setComments(boolean comments) {
        this.comments = comments;
    }
}
