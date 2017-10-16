package com.lanou.domain;

/**
 * Created by dllo on 17/10/16.
 */
public class Post {

//    职务id
    private int id;
//    职务名称
    private String pname;

    public Post(int id, String pname) {
        this.id = id;
        this.pname = pname;
    }

    public Post() {
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", pname='" + pname + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }
}
