package com.example.BarangayConnect.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblaccount")
public class LoginSignupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private int userId;
    private String username;
    private String password;
    private String email;
    private String fname;
    private String lname;
    private String address;
    private String dateOfBirth;
    private String gender;
    @Column(name = "isVerified", columnDefinition = "BOOLEAN")
    private boolean isVerified;

    public LoginSignupEntity() {
        super();
    }

    public LoginSignupEntity(int userId, String username, String password, String email, String fname, String lname,
            String address, String dateOfBirth, String gender, boolean isVerified) {
        super();
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.isVerified = isVerified;
    }

    // Setters
    public void setId(int userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setIsVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    // Getters
    public int getId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getAddress() {
        return address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public boolean getIsVerified() {
        return isVerified;
    }

    public boolean isVerified() {
        return isVerified;
    }
}
