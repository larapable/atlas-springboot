package com.example.BarangayConnect.Entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblannouncement")
public class AnnouncementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int annId;
    private String title;
    private String content;
    private Date date;

    public AnnouncementEntity() {
        super();
    }

    public AnnouncementEntity(int annId, String title, String content, Date date) {
        super();
        this.annId = annId;
        this.title = title;
        this.content = content;
        this.date = date;
    }

    //SETTERS
    public void setAnnId(int annId){
        this.annId = annId;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setContent(String content){
        this.content = content;
    }
    public void setDate(Date date){
        this.date = date;
    }

    //GETTERS
    public int getAnnId() {
        return annId;
    }
    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }
    public Date getDate() {
        return date;
    }

    
}