package application;

import java.sql.Date;

public class studentData {

    private Integer postId;
    private Integer likes;
    private String course;
    private String firstName;
    private String author;
    private String content;
    private Date birth;
    private String share;
    private String image;
    private Double firstSem;
    private Double secondSem;
    private Double finals;

    // MAKE SURE THAT SAME DATATYPE THAT YOU WILL PUT THERE 
    public studentData(Integer postId, Integer likes, String course, String firstName, String author, String content, Date birth, String share, String image) {
        this.postId = postId;
        this.likes = likes;
        this.course = course;
        this.firstName = firstName;
        this.author = author;
        this.content = content;
        this.birth = birth;
        this.share = share;
        this.image = image;
    }

    public studentData(Integer postId, Integer likes, String course, Double firstSem, Double secondSem, Double finals) {
        this.postId = postId;
        this.likes = likes;
        this.course = course;
        this.firstSem = firstSem;
        this.secondSem = secondSem;
        this.finals = finals;
    }

    public studentData(Integer postId, Integer likes, String content ) {
    this.postId = postId;
    this.likes = likes;
    this.content = content;
}


    public studentData(Integer postId, String share, String content ) {
    this.postId = postId;
    this.share = share;
    this.content = content;
}



    public studentData(Integer postId, String firstName, String author, String content, Date birth, Integer likes, String share) {
        this.postId = postId;
        this.likes = likes;
        this.firstName = firstName;
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


    public String getCourse() {
        return course;
    }

    public String getFirstName() {
        return firstName;
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

    public Double getFirstSem() {
        return firstSem;
    }

    public Double getSecondSem() {
        return secondSem;
    }

    public Double getFinals() {
        return finals;
    }

}
