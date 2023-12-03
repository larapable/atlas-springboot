package com.example.BarangayConnect.Entity;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name = "tblemergency")
public class EmergencyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emergencyId")
    private int emergencyId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity user;

    private String typeOfIncident;
    private Date date;
    private String time;
    private String exactLocation;
    private String incidentDetails;
    
    public EmergencyEntity() {
    }

    public EmergencyEntity(int emergencyId, UserEntity user, String typeOfIncident, Date date, String time,
            String exactLocation, String incidentDetails) {
        this.emergencyId = emergencyId;
        this.user = user;
        this.typeOfIncident = typeOfIncident;
        this.date = date;
        this.time = time;
        this.exactLocation = exactLocation;
        this.incidentDetails = incidentDetails;
    }

    public void setEmergencyId(int emergencyId) {
        this.emergencyId = emergencyId;
    }

    public void setUser(UserEntity user) {
        this.user = user;
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

    public int getEmergencyId() {
        return emergencyId;
    }

    public UserEntity getUser() {
        return user;
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
}