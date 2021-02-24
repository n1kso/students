package com.example.students.Entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.NotNull;

@Entity
public class Student {

    @Id(autoincrement = true)
    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private Date birthDate;
    private long groupaId;
    @ToOne(joinProperty = "groupaId")
    private Groupa groupa;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1943931642)
    private transient StudentDao myDao;
    @Generated(hash = 113396491)
    private transient Long groupa__resolvedKey;
    
    @Generated(hash = 413113853)
    public Student(Long id, String name, String surname, String patronymic, Date birthDate,
            long groupaId) {
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
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1807605250)
    public Groupa getGroupa() {
        long __key = this.groupaId;
        if (groupa__resolvedKey == null || !groupa__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            GroupaDao targetDao = daoSession.getGroupaDao();
            Groupa groupaNew = targetDao.load(__key);
            synchronized (this) {
                groupa = groupaNew;
                groupa__resolvedKey = __key;
            }
        }
        return groupa;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2022908357)
    public void setGroupa(@NotNull Groupa groupa) {
        if (groupa == null) {
            throw new DaoException(
                    "To-one property 'groupaId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.groupa = groupa;
            groupaId = groupa.getId();
            groupa__resolvedKey = groupaId;
        }
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return this.id;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1701634981)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getStudentDao() : null;
    }
   

    


}
