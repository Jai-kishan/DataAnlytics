package application;

import java.sql.Date;

public class postsData {

    private Integer postId;
    private Integer likes;
    private String author;
    private String content;
    private Date birth;
    private String share;
    private String image;


    // MAKE SURE THAT SAME DATATYPE THAT YOU WILL PUT THERE
    public postsData(Integer postId, Integer likes, String author, String content, Date birth, String share,
            String image) {
        this.postId = postId;
        this.likes = likes;
        this.author = author;
        this.content = content;
        this.birth = birth;
        this.share = share;
        this.image = image;
    }

    public postsData(Integer postId, Integer likes, String content) {
        this.postId = postId;
        this.likes = likes;
        this.content = content;
    }

    public postsData(Integer postId, String share, String content) {
        this.postId = postId;
        this.share = share;
        this.content = content;
    }

    public postsData(Integer postId, String author, String content, Date birth, Integer likes, String share) {
        this.postId = postId;
        this.likes = likes;
        this.author = author;
        this.content = content;
        this.birth = birth;
        this.share = share;
    }

    public Integer getStudentNum() {
        return postId;
    }

    public Integer getLikes() {
        return likes;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public Date getBirth() {
        return birth;
    }

    public String getShare() {
        return share;
    }

    public String getImage() {
        return image;
    }

}
