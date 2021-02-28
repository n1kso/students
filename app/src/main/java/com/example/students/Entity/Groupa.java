package com.example.students.Entity;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.NotNull;

@Entity
public class Groupa implements Parcelable {

    @Id(autoincrement = true)
    private Long id;
    @NotNull
    private String caption;
    @NotNull
    private Long facultyId;

    @ToMany(referencedJoinProperty = "groupaId")
    private List<Student> students;

    @ToOne(joinProperty = "facultyId")
    private Faculty faculty;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 2047761952)
    private transient GroupaDao myDao;

    @Generated(hash = 1574542061)
    public Groupa(Long id, @NotNull String caption, @NotNull Long facultyId) {
        this.id = id;
        this.caption = caption;
        this.facultyId = facultyId;
    }

    @Generated(hash = 951378057)
    public Groupa() {
    }

    protected Groupa(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        caption = in.readString();
        if (in.readByte() == 0) {
            facultyId = null;
        } else {
            facultyId = in.readLong();
        }
        students = in.createTypedArrayList(Student.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        dest.writeString(caption);
        if (facultyId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(facultyId);
        }
        dest.writeTypedList(students);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Groupa> CREATOR = new Creator<Groupa>() {
        @Override
        public Groupa createFromParcel(Parcel in) {
            return new Groupa(in);
        }

        @Override
        public Groupa[] newArray(int size) {
            return new Groupa[size];
        }
    };

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaption() {
        return this.caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Long getFacultyId() {
        return this.facultyId;
    }

    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }

    @Generated(hash = 2040065736)
    private transient Long faculty__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 996628955)
    public Faculty getFaculty() {
        Long __key = this.facultyId;
        if (faculty__resolvedKey == null || !faculty__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            FacultyDao targetDao = daoSession.getFacultyDao();
            Faculty facultyNew = targetDao.load(__key);
            synchronized (this) {
                faculty = facultyNew;
                faculty__resolvedKey = __key;
            }
        }
        return faculty;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 586566501)
    public void setFaculty(@NotNull Faculty faculty) {
        if (faculty == null) {
            throw new DaoException(
                    "To-one property 'facultyId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.faculty = faculty;
            facultyId = faculty.getId();
            faculty__resolvedKey = facultyId;
        }
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1343226253)
    public List<Student> getStudents() {
        if (students == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            StudentDao targetDao = daoSession.getStudentDao();
            List<Student> studentsNew = targetDao._queryGroupa_Students(id);
            synchronized (this) {
                if (students == null) {
                    students = studentsNew;
                }
            }
        }
        return students;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 238993120)
    public synchronized void resetStudents() {
        students = null;
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

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1871409401)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getGroupaDao() : null;
    }
    

 


}
