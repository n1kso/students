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

        ExampleOpenHelper helper = new ExampleOpenHelper(this, "students-db1");
        Database db = helper.getWritableDb();

        daoSession = new DaoMaster(db).newSession();
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
            //Начальная инициализация
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
                    ") VALUES(1, 'ИПО', 1)");
            db.execSQL("INSERT INTO " + GroupaDao.TABLENAME + " (" +
                    GroupaDao.Properties.Id.columnName + ", " +
                    GroupaDao.Properties.Caption.columnName + ", " +
                    GroupaDao.Properties.FacultyId.columnName +
                    ") VALUES(2, 'ИСиТ', 2)");
            db.execSQL("INSERT INTO " + GroupaDao.TABLENAME + " (" +
                    GroupaDao.Properties.Id.columnName + ", " +
                    GroupaDao.Properties.Caption.columnName + ", " +
                    GroupaDao.Properties.FacultyId.columnName +
                    ") VALUES(3, 'ПМ', 3)");
            db.execSQL("INSERT INTO " + StudentDao.TABLENAME + " (" +
                    StudentDao.Properties.Id.columnName + ", " +
                    StudentDao.Properties.Name.columnName + ", " +
                    StudentDao.Properties.Surname.columnName + ", " +
                    StudentDao.Properties.Patronymic.columnName + ", " +
                    StudentDao.Properties.BirthDate.columnName + ", " +
                    StudentDao.Properties.GroupaId.columnName +
                    ") VALUES(1, 'Василий', 'Фролов', 'Олегович', 161648946, 1)");
            db.execSQL("INSERT INTO " + StudentDao.TABLENAME + " (" +
                    StudentDao.Properties.Id.columnName + ", " +
                    StudentDao.Properties.Name.columnName + ", " +
                    StudentDao.Properties.Surname.columnName + ", " +
                    StudentDao.Properties.Patronymic.columnName + ", " +
                    StudentDao.Properties.BirthDate.columnName + ", " +
                    StudentDao.Properties.GroupaId.columnName +
                    ") VALUES(2, 'Анатолий', 'Ужаков', 'Васильевич', '1986-06-12', 2)");
            db.execSQL("INSERT INTO " + StudentDao.TABLENAME + " (" +
                    StudentDao.Properties.Id.columnName + ", " +
                    StudentDao.Properties.Name.columnName + ", " +
                    StudentDao.Properties.Surname.columnName + ", " +
                    StudentDao.Properties.Patronymic.columnName + ", " +
                    StudentDao.Properties.BirthDate.columnName + ", " +
                    StudentDao.Properties.GroupaId.columnName +
                    ") VALUES(3, 'Анна', 'Федотова', 'Олеговна', '1996-04-22', 3)");
            db.execSQL("INSERT INTO " + StudentDao.TABLENAME + " (" +
                    StudentDao.Properties.Id.columnName + ", " +
                    StudentDao.Properties.Name.columnName + ", " +
                    StudentDao.Properties.Surname.columnName + ", " +
                    StudentDao.Properties.Patronymic.columnName + ", " +
                    StudentDao.Properties.BirthDate.columnName + ", " +
                    StudentDao.Properties.GroupaId.columnName +
                    ") VALUES(4, 'Анна', 'Федотова', 'Сергеевна', '1996-04-22', 3)");
            db.execSQL("INSERT INTO " + StudentDao.TABLENAME + " (" +
                    StudentDao.Properties.Id.columnName + ", " +
                    StudentDao.Properties.Name.columnName + ", " +
                    StudentDao.Properties.Surname.columnName + ", " +
                    StudentDao.Properties.Patronymic.columnName + ", " +
                    StudentDao.Properties.BirthDate.columnName + ", " +
                    StudentDao.Properties.GroupaId.columnName +
                    ") VALUES(5, 'Анна', 'Федотова', 'Васильевна', '1996-04-22', 3)");
            db.execSQL("INSERT INTO " + StudentDao.TABLENAME + " (" +
                    StudentDao.Properties.Id.columnName + ", " +
                    StudentDao.Properties.Name.columnName + ", " +
                    StudentDao.Properties.Surname.columnName + ", " +
                    StudentDao.Properties.Patronymic.columnName + ", " +
                    StudentDao.Properties.BirthDate.columnName + ", " +
                    StudentDao.Properties.GroupaId.columnName +
                    ") VALUES(6, 'Анна', 'Федотова', 'Сергеевна', '1996-04-22', 3)");
            db.execSQL("INSERT INTO " + StudentDao.TABLENAME + " (" +
                    StudentDao.Properties.Id.columnName + ", " +
                    StudentDao.Properties.Name.columnName + ", " +
                    StudentDao.Properties.Surname.columnName + ", " +
                    StudentDao.Properties.Patronymic.columnName + ", " +
                    StudentDao.Properties.BirthDate.columnName + ", " +
                    StudentDao.Properties.GroupaId.columnName +
                    ") VALUES(7, 'Дарья', 'Федорова', 'Сергеевна', '1996-04-22', 3)");
            db.execSQL("INSERT INTO " + StudentDao.TABLENAME + " (" +
                    StudentDao.Properties.Id.columnName + ", " +
                    StudentDao.Properties.Name.columnName + ", " +
                    StudentDao.Properties.Surname.columnName + ", " +
                    StudentDao.Properties.Patronymic.columnName + ", " +
                    StudentDao.Properties.BirthDate.columnName + ", " +
                    StudentDao.Properties.GroupaId.columnName +
                    ") VALUES(8, 'Анна', 'Федотова', 'Васильевна', '1996-04-22', 1)");
            db.execSQL("INSERT INTO " + StudentDao.TABLENAME + " (" +
                    StudentDao.Properties.Id.columnName + ", " +
                    StudentDao.Properties.Name.columnName + ", " +
                    StudentDao.Properties.Surname.columnName + ", " +
                    StudentDao.Properties.Patronymic.columnName + ", " +
                    StudentDao.Properties.BirthDate.columnName + ", " +
                    StudentDao.Properties.GroupaId.columnName +
                    ") VALUES(9, 'Анна', 'Федорова', 'Сергеевна', '1996-04-22', 1)");
            db.execSQL("INSERT INTO " + StudentDao.TABLENAME + " (" +
                    StudentDao.Properties.Id.columnName + ", " +
                    StudentDao.Properties.Name.columnName + ", " +
                    StudentDao.Properties.Surname.columnName + ", " +
                    StudentDao.Properties.Patronymic.columnName + ", " +
                    StudentDao.Properties.BirthDate.columnName + ", " +
                    StudentDao.Properties.GroupaId.columnName +
                    ") VALUES(10, 'Дарья', 'Федотова', 'Васильевна', '1996-04-22', 1)");
            db.execSQL("INSERT INTO " + StudentDao.TABLENAME + " (" +
                    StudentDao.Properties.Id.columnName + ", " +
                    StudentDao.Properties.Name.columnName + ", " +
                    StudentDao.Properties.Surname.columnName + ", " +
                    StudentDao.Properties.Patronymic.columnName + ", " +
                    StudentDao.Properties.BirthDate.columnName + ", " +
                    StudentDao.Properties.GroupaId.columnName +
                    ") VALUES(11, 'Анна', 'Федорова', 'Сергеевна', '1996-04-22', 2)");
            db.execSQL("INSERT INTO " + StudentDao.TABLENAME + " (" +
                    StudentDao.Properties.Id.columnName + ", " +
                    StudentDao.Properties.Name.columnName + ", " +
                    StudentDao.Properties.Surname.columnName + ", " +
                    StudentDao.Properties.Patronymic.columnName + ", " +
                    StudentDao.Properties.BirthDate.columnName + ", " +
                    StudentDao.Properties.GroupaId.columnName +
                    ") VALUES(12, 'Анна', 'Федорова', 'Сергеевна', '1996-04-22', 2)");
            db.execSQL("INSERT INTO " + StudentDao.TABLENAME + " (" +
                    StudentDao.Properties.Id.columnName + ", " +
                    StudentDao.Properties.Name.columnName + ", " +
                    StudentDao.Properties.Surname.columnName + ", " +
                    StudentDao.Properties.Patronymic.columnName + ", " +
                    StudentDao.Properties.BirthDate.columnName + ", " +
                    StudentDao.Properties.GroupaId.columnName +
                    ") VALUES(13, 'Игорь', 'Федорова', 'Сергеевна', '1996-04-22', 2)");
            db.execSQL("INSERT INTO " + StudentDao.TABLENAME + " (" +
                    StudentDao.Properties.Id.columnName + ", " +
                    StudentDao.Properties.Name.columnName + ", " +
                    StudentDao.Properties.Surname.columnName + ", " +
                    StudentDao.Properties.Patronymic.columnName + ", " +
                    StudentDao.Properties.BirthDate.columnName + ", " +
                    StudentDao.Properties.GroupaId.columnName +
                    ") VALUES(14, 'Анна', 'Федорова', 'Сергеевна', '1996-04-22', 2)");
            db.execSQL("INSERT INTO " + StudentDao.TABLENAME + " (" +
                    StudentDao.Properties.Id.columnName + ", " +
                    StudentDao.Properties.Name.columnName + ", " +
                    StudentDao.Properties.Surname.columnName + ", " +
                    StudentDao.Properties.Patronymic.columnName + ", " +
                    StudentDao.Properties.BirthDate.columnName + ", " +
                    StudentDao.Properties.GroupaId.columnName +
                    ") VALUES(15, 'Игорь', 'Пикалов', 'Сергеевич', '1996-04-22', 2)");
            db.execSQL("INSERT INTO " + StudentDao.TABLENAME + " (" +
                    StudentDao.Properties.Id.columnName + ", " +
                    StudentDao.Properties.Name.columnName + ", " +
                    StudentDao.Properties.Surname.columnName + ", " +
                    StudentDao.Properties.Patronymic.columnName + ", " +
                    StudentDao.Properties.BirthDate.columnName + ", " +
                    StudentDao.Properties.GroupaId.columnName +
                    ") VALUES(16, 'Анна', 'Федорова', 'Сергеевна', '1996-04-22', 2)");
            db.execSQL("INSERT INTO " + StudentDao.TABLENAME + " (" +
                    StudentDao.Properties.Id.columnName + ", " +
                    StudentDao.Properties.Name.columnName + ", " +
                    StudentDao.Properties.Surname.columnName + ", " +
                    StudentDao.Properties.Patronymic.columnName + ", " +
                    StudentDao.Properties.BirthDate.columnName + ", " +
                    StudentDao.Properties.GroupaId.columnName +
                    ") VALUES(17, 'Игорь', 'Пикалов', 'Васильевич', '1996-04-22', 1)");
            db.execSQL("INSERT INTO " + StudentDao.TABLENAME + " (" +
                    StudentDao.Properties.Id.columnName + ", " +
                    StudentDao.Properties.Name.columnName + ", " +
                    StudentDao.Properties.Surname.columnName + ", " +
                    StudentDao.Properties.Patronymic.columnName + ", " +
                    StudentDao.Properties.BirthDate.columnName + ", " +
                    StudentDao.Properties.GroupaId.columnName +
                    ") VALUES(18, 'Василий', 'Фролов', 'Олегович', '1996-01-01', 1)");
            db.execSQL("INSERT INTO " + StudentDao.TABLENAME + " (" +
                    StudentDao.Properties.Id.columnName + ", " +
                    StudentDao.Properties.Name.columnName + ", " +
                    StudentDao.Properties.Surname.columnName + ", " +
                    StudentDao.Properties.Patronymic.columnName + ", " +
                    StudentDao.Properties.BirthDate.columnName + ", " +
                    StudentDao.Properties.GroupaId.columnName +
                    ") VALUES(19, 'Анатолий', 'Ужаков', 'Васильевич', '1986-06-12', 2)");
            db.execSQL("INSERT INTO " + StudentDao.TABLENAME + " (" +
                    StudentDao.Properties.Id.columnName + ", " +
                    StudentDao.Properties.Name.columnName + ", " +
                    StudentDao.Properties.Surname.columnName + ", " +
                    StudentDao.Properties.Patronymic.columnName + ", " +
                    StudentDao.Properties.BirthDate.columnName + ", " +
                    StudentDao.Properties.GroupaId.columnName +
                    ") VALUES(20, 'Анна', 'Федотова', 'Олеговна', '1996-04-22', 3)");
            db.execSQL("INSERT INTO " + StudentDao.TABLENAME + " (" +
                    StudentDao.Properties.Id.columnName + ", " +
                    StudentDao.Properties.Name.columnName + ", " +
                    StudentDao.Properties.Surname.columnName + ", " +
                    StudentDao.Properties.Patronymic.columnName + ", " +
                    StudentDao.Properties.BirthDate.columnName + ", " +
                    StudentDao.Properties.GroupaId.columnName +
                    ") VALUES(21, 'Анна', 'Федотова', 'Сергеевна', '1996-04-22', 3)");
            db.execSQL("INSERT INTO " + StudentDao.TABLENAME + " (" +
                    StudentDao.Properties.Id.columnName + ", " +
                    StudentDao.Properties.Name.columnName + ", " +
                    StudentDao.Properties.Surname.columnName + ", " +
                    StudentDao.Properties.Patronymic.columnName + ", " +
                    StudentDao.Properties.BirthDate.columnName + ", " +
                    StudentDao.Properties.GroupaId.columnName +
                    ") VALUES(22, 'Анна', 'Федотова', 'Васильевна', '1980-05-16', 3)");
            db.execSQL("INSERT INTO " + StudentDao.TABLENAME + " (" +
                    StudentDao.Properties.Id.columnName + ", " +
                    StudentDao.Properties.Name.columnName + ", " +
                    StudentDao.Properties.Surname.columnName + ", " +
                    StudentDao.Properties.Patronymic.columnName + ", " +
                    StudentDao.Properties.BirthDate.columnName + ", " +
                    StudentDao.Properties.GroupaId.columnName +
                    ") VALUES(23, 'Анна', 'Федотова', 'Сергеевна', '1980-05-16', 3)");
            db.execSQL("INSERT INTO " + StudentDao.TABLENAME + " (" +
                    StudentDao.Properties.Id.columnName + ", " +
                    StudentDao.Properties.Name.columnName + ", " +
                    StudentDao.Properties.Surname.columnName + ", " +
                    StudentDao.Properties.Patronymic.columnName + ", " +
                    StudentDao.Properties.BirthDate.columnName + ", " +
                    StudentDao.Properties.GroupaId.columnName +
                    ") VALUES(24, 'Дарья', 'Федорова', 'Сергеевна', '1996-04-22', 3)");
            db.execSQL("INSERT INTO " + StudentDao.TABLENAME + " (" +
                    StudentDao.Properties.Id.columnName + ", " +
                    StudentDao.Properties.Name.columnName + ", " +
                    StudentDao.Properties.Surname.columnName + ", " +
                    StudentDao.Properties.Patronymic.columnName + ", " +
                    StudentDao.Properties.BirthDate.columnName + ", " +
                    StudentDao.Properties.GroupaId.columnName +
                    ") VALUES(25, 'Анна', 'Федотова', 'Васильевна', '1980-05-16', 1)");
            db.execSQL("INSERT INTO " + StudentDao.TABLENAME + " (" +
                    StudentDao.Properties.Id.columnName + ", " +
                    StudentDao.Properties.Name.columnName + ", " +
                    StudentDao.Properties.Surname.columnName + ", " +
                    StudentDao.Properties.Patronymic.columnName + ", " +
                    StudentDao.Properties.BirthDate.columnName + ", " +
                    StudentDao.Properties.GroupaId.columnName +
                    ") VALUES(26, 'Анна', 'Федорова', 'Сергеевна', '1980-05-16', 1)");
            db.execSQL("INSERT INTO " + StudentDao.TABLENAME + " (" +
                    StudentDao.Properties.Id.columnName + ", " +
                    StudentDao.Properties.Name.columnName + ", " +
                    StudentDao.Properties.Surname.columnName + ", " +
                    StudentDao.Properties.Patronymic.columnName + ", " +
                    StudentDao.Properties.BirthDate.columnName + ", " +
                    StudentDao.Properties.GroupaId.columnName +
                    ") VALUES(27, 'Дарья', 'Федотова', 'Васильевна', '1980-05-16', 1)");
            db.execSQL("INSERT INTO " + StudentDao.TABLENAME + " (" +
                    StudentDao.Properties.Id.columnName + ", " +
                    StudentDao.Properties.Name.columnName + ", " +
                    StudentDao.Properties.Surname.columnName + ", " +
                    StudentDao.Properties.Patronymic.columnName + ", " +
                    StudentDao.Properties.BirthDate.columnName + ", " +
                    StudentDao.Properties.GroupaId.columnName +
                    ") VALUES(28, 'Анна', 'Федорова', 'Сергеевна', '1996-04-22', 2)");
            db.execSQL("INSERT INTO " + StudentDao.TABLENAME + " (" +
                    StudentDao.Properties.Id.columnName + ", " +
                    StudentDao.Properties.Name.columnName + ", " +
                    StudentDao.Properties.Surname.columnName + ", " +
                    StudentDao.Properties.Patronymic.columnName + ", " +
                    StudentDao.Properties.BirthDate.columnName + ", " +
                    StudentDao.Properties.GroupaId.columnName +
                    ") VALUES(29, 'Анна', 'Федорова', 'Сергеевна', '1980-05-16', 2)");
            db.execSQL("INSERT INTO " + StudentDao.TABLENAME + " (" +
                    StudentDao.Properties.Id.columnName + ", " +
                    StudentDao.Properties.Name.columnName + ", " +
                    StudentDao.Properties.Surname.columnName + ", " +
                    StudentDao.Properties.Patronymic.columnName + ", " +
                    StudentDao.Properties.BirthDate.columnName + ", " +
                    StudentDao.Properties.GroupaId.columnName +
                    ") VALUES(30, 'Игорь', 'Федорова', 'Сергеевна', '1996-04-22', 2)");
            db.execSQL("INSERT INTO " + StudentDao.TABLENAME + " (" +
                    StudentDao.Properties.Id.columnName + ", " +
                    StudentDao.Properties.Name.columnName + ", " +
                    StudentDao.Properties.Surname.columnName + ", " +
                    StudentDao.Properties.Patronymic.columnName + ", " +
                    StudentDao.Properties.BirthDate.columnName + ", " +
                    StudentDao.Properties.GroupaId.columnName +
                    ") VALUES(31, 'Анна', 'Федорова', 'Сергеевна', '1980-05-16', 2)");
            db.execSQL("INSERT INTO " + StudentDao.TABLENAME + " (" +
                    StudentDao.Properties.Id.columnName + ", " +
                    StudentDao.Properties.Name.columnName + ", " +
                    StudentDao.Properties.Surname.columnName + ", " +
                    StudentDao.Properties.Patronymic.columnName + ", " +
                    StudentDao.Properties.BirthDate.columnName + ", " +
                    StudentDao.Properties.GroupaId.columnName +
                    ") VALUES(32, 'Игорь', 'Пикалов', 'Сергеевич', '1996-04-22', 2)");
            db.execSQL("INSERT INTO " + StudentDao.TABLENAME + " (" +
                    StudentDao.Properties.Id.columnName + ", " +
                    StudentDao.Properties.Name.columnName + ", " +
                    StudentDao.Properties.Surname.columnName + ", " +
                    StudentDao.Properties.Patronymic.columnName + ", " +
                    StudentDao.Properties.BirthDate.columnName + ", " +
                    StudentDao.Properties.GroupaId.columnName +
                    ") VALUES(33, 'Анна', 'Федорова', 'Сергеевна', '1980-05-16', 2)");
            db.execSQL("INSERT INTO " + StudentDao.TABLENAME + " (" +
                    StudentDao.Properties.Id.columnName + ", " +
                    StudentDao.Properties.Name.columnName + ", " +
                    StudentDao.Properties.Surname.columnName + ", " +
                    StudentDao.Properties.Patronymic.columnName + ", " +
                    StudentDao.Properties.BirthDate.columnName + ", " +
                    StudentDao.Properties.GroupaId.columnName +
                    ") VALUES(34, 'Игорь', 'Пикалов', 'Васильевич', '1996-04-22', 1)");

        }
    }
}
