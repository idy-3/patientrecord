package com.example.patientrecord.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Patient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable =false)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private Integer age;
    @Column(unique=true, nullable = false, updatable =false)
    private String patientNo;
    private String history;
    @Column(name="isDeleted", columnDefinition = "boolean default false")
    private Boolean isDeleted = false;

    public Patient() {}

    public Patient(String name, String email, String phone, String patientNo, String history){
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.patientNo = patientNo;
        this.history = history;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPatientNo() {
        return patientNo;
    }

    public void setPatientNo(String patientNo) {
        this.patientNo = patientNo;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", patientNo=" + patientNo + '\'' +
                ", history='" + history + '\'' +
                ", isDeleted='" + isDeleted + '\'' +
                '}';
    }
}
