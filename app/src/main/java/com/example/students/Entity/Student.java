package com.example.students.Entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Student {

    @Id(autoincrement = true)
    private long id;
    private String name;
    private String surname;
    private String patronymic;
    private Date birthDate;
    private long groupaId;
    @Generated(hash = 230802912)
    public Student(long id, String name, String surname, String patronymic,
            Date birthDate, long groupaId) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.birthDate = birthDate;
        this.groupaId = groupaId;
    }
    @Generated(hash = 1556870573)
    public Student() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return this.surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getPatronymic() {
        return this.patronymic;
    }
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }
    public Date getBirthDate() {
        return this.birthDate;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    public long getGroupaId() {
        return this.groupaId;
    }
    public void setGroupaId(long groupaId) {
        this.groupaId = groupaId;
    }
   

    


}
