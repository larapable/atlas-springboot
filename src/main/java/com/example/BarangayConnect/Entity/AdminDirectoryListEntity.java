package com.example.BarangayConnect.Entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbladmindirectorylist")
public class AdminDirectoryListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admindirectorylistId")
    private int admindirectorylistId;

    @Column(name = "image_name")
    private String imageName;
    private String name;
    private String email;
    private int age;
    private String status;
    private Date birthdate;
    private String position;
    private String message;
    private int isdelete;

    public AdminDirectoryListEntity() {
    }

    public AdminDirectoryListEntity(int admindirectorylistId, String imageName, String name, String email,
            int age, String status, Date birthdate, String position, String message, int isdelete) {
        this.admindirectorylistId = admindirectorylistId;
        this.imageName = imageName;
        this.name = name;
        this.email = email;
        this.age = age;
        this.status = status;
        this.birthdate = birthdate;
        this.position = position;
        this.message = message;
        this.isdelete = isdelete;
    }

    public void setAdmindirectorylistId(int admindirectorylistId) {
        this.admindirectorylistId = admindirectorylistId;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setIsdelete(int isdelete) {
        this.isdelete = isdelete;
    }

    public int getAdmindirectorylistId() {
        return admindirectorylistId;
    }

    public String getImageName() {
        return imageName;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public String getStatus() {
        return status;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public String getPosition() {
        return position;
    }

    public String getMessage() {
        return message;
    }

    public int getIsdelete() {
        return isdelete;
    }
}
