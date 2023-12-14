package com.example.BarangayConnect.Entity;

import java.sql.Date;
import javax.persistence.*;

@Entity
@Table(name = "tblannouncements")
public class AnnouncementsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "announcementId")
    private int announcementId;
    private String announcementTitle;
    private Date date;
    private String announcementContent;
    private int isdelete;
    
    public AnnouncementsEntity() {
    }

    public AnnouncementsEntity(int announcementId, String announcementTitle, Date date, String announcementContent, int isdelete) {
        this.announcementId = announcementId;
        this.announcementTitle = announcementTitle;
        this.date = date;
        this.announcementContent = announcementContent;
        this.isdelete = isdelete;
    }
    //SETTERS

    public void setAnnouncementId(int announcementId) {
        this.announcementId = announcementId;
    }

    public void setAnnouncementTitle(String announcementTitle) {
        this.announcementTitle = announcementTitle;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAnnouncementContent(String announcementContent) {
        this.announcementContent = announcementContent;
    }

    public void setIsdelete(int isdelete) {
        this.isdelete = isdelete;
    }

    //GETTERS

    public int getAnnouncementId() {
        return announcementId;
    }

    public String getAnnouncementTitle() {
        return announcementTitle;
    }

    public Date getDate() {
        return date;
    }

    public String getAnnouncementContent() {
        return announcementContent;
    }

    public int getIsdelete() {
        return isdelete;
    }
}