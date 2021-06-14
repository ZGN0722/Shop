package com.ch.ebusiness.entity;

import org.springframework.web.multipart.MultipartFile;

public class Store {
    private Integer id;
    private String sname;
    private Integer slevef;
    private String spicture;
    private String cpicture;
    private Integer buser_id;
    private MultipartFile fileName1;
    private MultipartFile fileName2;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Integer getBuser_id() {
        return buser_id;
    }

    public String getCpicture() {
        return cpicture;
    }

    public Integer getSlevef() {
        return slevef;
    }

    public String getSname() {
        return sname;
    }

    public String getSpicture() {
        return spicture;
    }

    public void setBuser_id(Integer buser_id) {
        this.buser_id = buser_id;
    }

    public void setCpicture(String cpicture) {
        this.cpicture = cpicture;
    }

    public void setSlevef(Integer slevef) {
        this.slevef = slevef;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public void setSpicture(String spicture) {
        this.spicture = spicture;
    }

    public MultipartFile getFileName1() {
        return fileName1;
    }

    public void setFileName1(MultipartFile fileName1) {
        this.fileName1 = fileName1;
    }

    public MultipartFile getFileName2() {
        return fileName2;
    }

    public void setFileName2(MultipartFile fileName2) {
        this.fileName2 = fileName2;
    }
}
