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
@Table(name="tblrequest")
public class RequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int docid;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity user;

    private String lastname;
    private String firstname;
    private String middlename;
    private String suffix;
    private String birthdate;
    private int age;
    private String gender;
    private int numcopies;
    private String purok;
    private String purpose;
    private String doctype;
    private String others;
    private String type;
    private String contactnum;
    private String email;
    private String track;
    @Column(name = "isDeleted", columnDefinition = "BOOLEAN")
    private boolean isDeleted;
    

    
    public RequestEntity() {
    }


    public RequestEntity(int docid, String lastname, String firstname, String middlename, String suffix,
            String birthdate, int age, String gender, int numcopies, String purok, String purpose, String doctype,
            String others, String type, String contactnum, String email, String track, UserEntity user,boolean isDeleted) {
        this.docid = docid;
        this.lastname = lastname;
        this.firstname = firstname;
        this.middlename = middlename;
        this.suffix = suffix;
        this.birthdate = birthdate;
        this.age = age;
        this.gender = gender;
        this.numcopies = numcopies;
        this.purok = purok;
        this.purpose = purpose;
        this.doctype = doctype;
        this.others = others;
        this.type = type;
        this.contactnum = contactnum;
        this.email = email;
        this.track = track;
        this.user = user;
        this.isDeleted = isDeleted;
    }


    public int getDocid() {
        return docid;
    }


    public void setDocid(int docid) {
        this.docid = docid;
    }


    public String getLastname() {
        return lastname;
    }


    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    public String getFirstname() {
        return firstname;
    }


    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }


    public String getMiddlename() {
        return middlename;
    }


    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }


    public String getSuffix() {
        return suffix;
    }


    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }


    public String getBirthdate() {
        return birthdate;
    }


    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }


    public int getAge() {
        return age;
    }


    public void setAge(int age) {
        this.age = age;
    }


    public String getGender() {
        return gender;
    }


    public void setGender(String gender) {
        this.gender = gender;
    }


    public int getNumcopies() {
        return numcopies;
    }


    public void setNumcopies(int numcopies) {
        this.numcopies = numcopies;
    }


    public String getPurok() {
        return purok;
    }


    public void setPurok(String purok) {
        this.purok = purok;
    }


    public String getPurpose() {
        return purpose;
    }


    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }


    public String getDoctype() {
        return doctype;
    }


    public void setDoctype(String doctype) {
        this.doctype = doctype;
    }


    public String getOthers() {
        return others;
    }


    public void setOthers(String others) {
        this.others = others;
    }


    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }


    public String getContactnum() {
        return contactnum;
    }


    public void setContactnum(String contactnum) {
        this.contactnum = contactnum;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getTrack() {
        return track;
    }


    public void setTrack(String track) {
        this.track = track;
    }
    
    
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
    
    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

 
    
}