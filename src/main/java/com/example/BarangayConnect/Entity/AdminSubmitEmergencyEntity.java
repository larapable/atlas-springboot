package com.example.BarangayConnect.Entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbladminemergency")
public class AdminSubmitEmergencyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adminemergencyId")
    private int adminemergencyId;

    private String typeOfIncident;
    private Date date;
    private String time;
    private String exactLocation;
    private String incidentDetails;
    private int isdelete;
    
    public AdminSubmitEmergencyEntity() {
    }

    public AdminSubmitEmergencyEntity(int adminemergencyId, String typeOfIncident, Date date, String time,
            String exactLocation, String incidentDetails, int isdelete) {
        this.adminemergencyId = adminemergencyId;
        this.typeOfIncident = typeOfIncident;
        this.date = date;
        this.time = time;
        this.exactLocation = exactLocation;
        this.incidentDetails = incidentDetails;
        this.isdelete = isdelete;
    }

    public void setAdminemergencyId(int adminemergencyId) {
        this.adminemergencyId = adminemergencyId;
    }

    public void setTypeOfIncident(String typeOfIncident) {
        this.typeOfIncident = typeOfIncident;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setExactLocation(String exactLocation) {
        this.exactLocation = exactLocation;
    }

    public void setIncidentDetails(String incidentDetails) {
        this.incidentDetails = incidentDetails;
    }

    public void setIsdelete(int isdelete) {
        this.isdelete = isdelete;
    }

    public int getAdminemergencyId() {
        return adminemergencyId;
    }

    public String getTypeOfIncident() {
        return typeOfIncident;
    }

    public Date getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getExactLocation() {
        return exactLocation;
    }

    public String getIncidentDetails() {
        return incidentDetails;
    }
    
    public int getIsdelete() {
        return isdelete;
    }
}
