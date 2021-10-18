package com.example.kafshapp.model;

public class Comment {
    private int id;
    private String comment;
    private int userid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
// private boolean commentLike;

//    public boolean isCommentLike() {
//        return commentLike;
//    }
//
//    public void setCommentLike(boolean commentLike) {
//        this.commentLike = commentLike;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
