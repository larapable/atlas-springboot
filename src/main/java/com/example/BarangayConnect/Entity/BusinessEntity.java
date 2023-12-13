package com.example.BarangayConnect.Entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblbusiness")
public class BusinessEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name = "busId")
    private int busId;
    private String busTitle;
    private Date date;
    private String busContent;
    private int isdelete;
    @Column(name = "photo_path")
    private String photoPath;

    public BusinessEntity(){
    }

    public BusinessEntity(int busId, String busTitle, Date date, String busContent, int isdelete, String photoPath) {
        this.busId = busId;
        this.busTitle = busTitle;
        this.date = date;
        this.busContent = busContent;
        this.isdelete = isdelete;
        this.photoPath = photoPath;
    }

    //SETTERS

    public void setBusId(int busId) {
        this.busId = busId;
    }

    public void setBusTitle(String busTitle) {
        this.busTitle = busTitle;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setBusContent(String busContent) {
        this.busContent = busContent;
    }

    public void setIsdelete(int isdelete) {
        this.isdelete = isdelete;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    //GETTERS

    public int getBusId() {
        return busId;
    }

    public String getBusTitle() {
        return busTitle;
    }

    public Date getDate() {
        return date;
    }

    public String getBusContent() {
        return busContent;
    }

    public int getIsdelete() {
        return isdelete;
    }

    public String getPhotoPath() {
        return this.photoPath;
    }
}
