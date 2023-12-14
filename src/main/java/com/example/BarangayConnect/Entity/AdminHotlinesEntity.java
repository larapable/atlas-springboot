package com.example.BarangayConnect.Entity;

import javax.persistence.*;

@Entity
@Table(name = "tbladminhotlinenumbers")
public class AdminHotlinesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotlineId")
    private int hotlineId;

    private String title;
    private String hotlinenumber;
    private int isdelete;
    
    public AdminHotlinesEntity() {
    }

    public AdminHotlinesEntity(int hotlineId, String title, String hotlinenumber) {
        this.hotlineId = hotlineId;
        this.title = title;
        this.hotlinenumber = hotlinenumber;
    }

    public void setHotlineId(int hotlineId) {
        this.hotlineId = hotlineId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setHotlinenumber(String hotlinenumber) {
        this.hotlinenumber = hotlinenumber;
    }

    public void setIsdelete(int isdelete) {
        this.isdelete = isdelete;
    }

    public int getHotlineId() {
        return hotlineId;
    }

    public String getTitle() {
        return title;
    }

    public String getHotlinenumber() {
        return hotlinenumber;
    }

    public int getIsdelete() {
        return isdelete;
    }
}
