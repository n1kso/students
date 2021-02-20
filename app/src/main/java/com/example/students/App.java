package com.example.students;

import android.app.Application;
import android.content.Context;

import com.example.students.Entity.DaoMaster;
import com.example.students.Entity.DaoSession;
import com.example.students.Entity.FacultyDao;
import com.example.students.Entity.GroupaDao;
import com.example.students.Entity.StudentDao;

import org.greenrobot.greendao.database.Database;

public class App extends Application {
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        // regular SQLite database
        ExampleOpenHelper helper = new ExampleOpenHelper(this, "students-db1");
        Database db = helper.getWritableDb();

        // encrypted SQLCipher database
        // note: you need to add SQLCipher to your dependencies, check the build.gradle file
        // ExampleOpenHelper helper = new ExampleOpenHelper(this, "notes-db-encrypted");
        // Database db = helper.getEncryptedWritableDb("encryption-key");

        daoSession = new DaoMaster(db).newSession();
//        daoSession.clear();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public static class ExampleOpenHelper extends DaoMaster.OpenHelper {

        public ExampleOpenHelper(Context context, String name) {
            super(context, name);
        }

        @Override
        public void onCreate(Database db) {
            super.onCreate(db);

            db.execSQL("INSERT INTO " + FacultyDao.TABLENAME + " (" +
                    FacultyDao.Properties.Id.columnName + ", " +
                    FacultyDao.Properties.Name.columnName +
                    ") VALUES(1, 'Экономический')");
            db.execSQL("INSERT INTO " + FacultyDao.TABLENAME + " (" +
                    FacultyDao.Properties.Id.columnName + ", " +
                    FacultyDao.Properties.Name.columnName +
                    ") VALUES(2, 'Энергетический')");
            db.execSQL("INSERT INTO " + FacultyDao.TABLENAME + " (" +
                    FacultyDao.Properties.Id.columnName + ", " +
                    FacultyDao.Properties.Name.columnName +
                    ") VALUES(3, 'Исторический')");

            db.execSQL("INSERT INTO " + GroupaDao.TABLENAME + " (" +
                    GroupaDao.Properties.Id.columnName + ", " +
                    GroupaDao.Properties.Caption.columnName + ", " +
                    GroupaDao.Properties.FacultyId.columnName +
                    ") VALUES(1, 'ИПО', 4)");
            db.execSQL("INSERT INTO " + GroupaDao.TABLENAME + " (" +
                    GroupaDao.Properties.Id.columnName + ", " +
                    GroupaDao.Properties.Caption.columnName + ", " +
                    GroupaDao.Properties.FacultyId.columnName +
                    ") VALUES(2, 'ИСиТ', 4)");
            db.execSQL("INSERT INTO " + GroupaDao.TABLENAME + " (" +
                    GroupaDao.Properties.Id.columnName + ", " +
                    GroupaDao.Properties.Caption.columnName + ", " +
                    GroupaDao.Properties.FacultyId.columnName +
                    ") VALUES(3, 'ПМ', 5)");
            db.execSQL("INSERT INTO " + StudentDao.TABLENAME + " (" +
                    StudentDao.Properties.Id.columnName + ", " +
                    StudentDao.Properties.Name.columnName + ", " +
                    StudentDao.Properties.Surname.columnName + ", " +
                    StudentDao.Properties.Patronymic.columnName + ", " +
                    StudentDao.Properties.BirthDate.columnName + ", " +
                    StudentDao.Properties.GroupaId.columnName +
//                    ") VALUES(1, 'Василий', 'Фролов', 'Олегович', '1996-01-01 10:00:00', 1)");
                    ") VALUES(1, 'Василий', 'Фролов', 'Олегович', '1996-01-01', 1)");
            db.execSQL("INSERT INTO " + StudentDao.TABLENAME + " (" +
                    StudentDao.Properties.Id.columnName + ", " +
                    StudentDao.Properties.Name.columnName + ", " +
                    StudentDao.Properties.Surname.columnName + ", " +
                    StudentDao.Properties.Patronymic.columnName + ", " +
                    StudentDao.Properties.BirthDate.columnName + ", " +
                    StudentDao.Properties.GroupaId.columnName +
//                    ") VALUES(1, 'Василий', 'Фролов', 'Олегович', '1996-01-01 10:00:00', 1)");
                    ") VALUES(2, 'Анатолий', 'Ужаков', 'Васильевич', '1986-06-12', 2)");
            db.execSQL("INSERT INTO " + StudentDao.TABLENAME + " (" +
                    StudentDao.Properties.Id.columnName + ", " +
                    StudentDao.Properties.Name.columnName + ", " +
                    StudentDao.Properties.Surname.columnName + ", " +
                    StudentDao.Properties.Patronymic.columnName + ", " +
                    StudentDao.Properties.BirthDate.columnName + ", " +
                    StudentDao.Properties.GroupaId.columnName +
//                    ") VALUES(1, 'Василий', 'Фролов', 'Олегович', '1996-01-01 10:00:00', 1)");
                    ") VALUES(1, 'Анна', 'Федотова', 'Сергеевна', '1996-04-22', 3)");

        }
    }
}
