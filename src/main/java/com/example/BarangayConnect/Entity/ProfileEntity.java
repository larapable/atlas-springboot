package com.example.BarangayConnect.Entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tblprofile")
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pid;
    private String mobileNumber;
    private String maritalStatus;
    private String citizenship;
    private String religion;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountUsername", referencedColumnName = "username")
    private LoginSignupEntity loginSignupEntity;

    public ProfileEntity() {
        super();
    }

    // Constructor with variables
    public ProfileEntity(int pid, String mobileNumber, String maritalStatus, String citizenship, String religion,
            LoginSignupEntity loginSignupEntity) {
        super();
        this.pid = pid;
        this.mobileNumber = mobileNumber;
        this.maritalStatus = maritalStatus;
        this.citizenship = citizenship;
        this.religion = religion;
        this.loginSignupEntity = loginSignupEntity;
    }

    // Getters and Setters
    public int getId() {
        return pid;
    }

    public void setId(int pid) {
        this.pid = pid;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public LoginSignupEntity getLoginSignupEntity() {
        return loginSignupEntity;
    }

    public void setLoginSignupEntity(LoginSignupEntity loginSignupEntity) {
        this.loginSignupEntity = loginSignupEntity;
    }
}
