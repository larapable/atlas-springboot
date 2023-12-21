package com.example.BarangayConnect.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tblreply")
public class ReplyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int replyId;

    @ManyToOne
    @JoinColumn(name = "forum_id")
    private ForumEntity forum;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private String reply;
    

    public ReplyEntity() {
    }

    public ReplyEntity(int replyId, ForumEntity forum, UserEntity user, String reply) {
        this.replyId = replyId;
        this.forum = forum;
        this.user = user;
        this.reply = reply;
    }

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    public ForumEntity getForum() {
        return forum;
    }

    public void setForum(ForumEntity forum) {
        this.forum = forum;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    
}
