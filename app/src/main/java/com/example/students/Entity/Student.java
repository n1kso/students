package com.example.students.Entity;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Transient;

@Entity
public class Student implements Parcelable {

    @Id(autoincrement = true)
    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private Date birthDate;
    private Long groupaId;
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
    
    @Generated(hash = 987904711)
    public Student(Long id, String name, String surname, String patronymic, Date birthDate,
            Long groupaId) {
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

    protected Student(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        name = in.readString();
        surname = in.readString();
        patronymic = in.readString();
        long tmpDate = in.readLong();
        this.birthDate = tmpDate == -1 ? null : new Date(tmpDate);
        if (in.readByte() == 0) {
            groupaId = null;
        } else {
            groupaId = in.readLong();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        dest.writeString(name);
        dest.writeString(surname);
        dest.writeString(patronymic);
        dest.writeLong(birthDate != null ? birthDate.getTime() : -1);
        if (groupaId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(groupaId);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

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
    @Generated(hash = 1980003515)
    public Groupa getGroupa() {
        Long __key = this.groupaId;
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
    @Generated(hash = 1984555448)
    public void setGroupa(Groupa groupa) {
        synchronized (this) {
            this.groupa = groupa;
            groupaId = groupa == null ? null : groupa.getId();
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
    public void setGroupaId(Long groupaId) {
        this.groupaId = groupaId;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1701634981)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getStudentDao() : null;
    }
   

    


}
