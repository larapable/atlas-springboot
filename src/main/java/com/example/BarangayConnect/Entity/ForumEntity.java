package com.example.BarangayConnect.Entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;




@Entity
@Table(name="tblforum")
public class ForumEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int forumid;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity user;

    private String post;

    @Column(name = "isDeleted", columnDefinition = "BOOLEAN")
    private boolean isDeleted;
 

    public ForumEntity() {
    }

    public ForumEntity(int forumid, UserEntity user, String post,boolean isDeleted) {
        this.forumid = forumid;
        this.user = user;
        this.post = post;
        this.isDeleted = isDeleted;
    }


    public int getForumid() {
        return forumid;
    }

    public void setForumid(int forumid) {
        this.forumid = forumid;
    }


    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }
    
}
