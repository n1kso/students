package com.example.students.Student;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.students.App;
import com.example.students.Entity.DaoSession;
import com.example.students.Entity.Groupa;
import com.example.students.Entity.GroupaDao;
import com.example.students.Entity.Student;
import com.example.students.Entity.StudentDao;
import com.example.students.MainActivity;
import com.example.students.R;

import org.greenrobot.greendao.query.Query;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class AddStudentActivity extends AppCompatActivity {

    private EditText name;
    private EditText surname;
    private EditText patronymic;
    private EditText birthDate;
    private TextView id;
    private Spinner groupaSpinner;
    private List<Groupa> groupas;
    private Button insertStudent;
    private Handler handler;

    private StudentDao studentDao;
    private Query<Student> studentQuery;

    private GroupaDao groupaDao;
    private Query<Groupa> groupaQuery;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        name = findViewById(R.id.NewStudentName);
        surname = findViewById(R.id.NewStudentSurname);
        patronymic = findViewById(R.id.NewStudentPatronymic);
        birthDate = findViewById(R.id.NewStudentBirthDate);
        groupaSpinner = findViewById(R.id.groupaSpinner);
        insertStudent = findViewById(R.id.InsertStudent);
        id = findViewById(R.id.studentId);

        DaoSession daoSession = ((App)getApplication()).getDaoSession();
        studentDao = daoSession.getStudentDao();
        studentQuery = studentDao.queryBuilder().build();

        groupaDao = daoSession.getGroupaDao();
        groupaQuery = groupaDao.queryBuilder().build();
        groupas = groupaQuery.list();

        MyAddStudentAdapter addStudentAdapter = new MyAddStudentAdapter(this, R.layout.student_spinner_item, groupas);

        groupaSpinner.setAdapter(addStudentAdapter);
//        groupaSpinner.setSelection(0);

        handler = new Handler(Looper.getMainLooper());

        groupaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        insertStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertStudent insertStudent = new AddStudentActivity.InsertStudent(AddStudentActivity.this);
                handler.post(insertStudent);

            }
        });
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Student student = getIntent().getParcelableExtra("student");
        if (student != null) {
            id.setText(String.valueOf(student.getId()));
            name.setText(student.getName());
            surname.setText(student.getSurname());
            patronymic.setText(student.getPatronymic());
            DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
            birthDate.setText(format.format(student.getBirthDate()));
            groupaSpinner.setSelection(findSelectedPosition(student));

        }
    }

    private int findSelectedPosition(Student student) {
        int pos = -1;
        for (Groupa groupa: groupas) {
            pos++;
            if (student.getGroupaId() == groupa.getId())
                return pos;
        }
        return pos;
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private class InsertStudent implements Runnable {
        private final Context context;

        InsertStudent(Context context) {
            this.context = context;
        }

        @Override
        public void run() {
            if (id.getText().equals("")) {
                Student student = new Student();
                student.setName(name.getText().toString());
                student.setSurname(surname.getText().toString());
                student.setPatronymic(patronymic.getText().toString());
                DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
                Date birth;
                try {
                    birth = format.parse(birthDate.getText().toString());
                    student.setBirthDate(birth);
                    student.setGroupaId(groupas.get(groupaSpinner.getSelectedItemPosition()).getId());
                    studentDao.insert(student);
                    startActivity(new Intent(context, StudentActivity.class));
                } catch (ParseException e) {
                    e.printStackTrace();
                    Toast.makeText(context, R.string.wrongDateFormat, Toast.LENGTH_LONG).show();
                    startActivity(new Intent(context, AddStudentActivity.class));
                }
            } else {
                Student student = studentDao.loadByRowId(Long.parseLong(id.getText().toString()));
                student.setName(name.getText().toString());
                student.setSurname(surname.getText().toString());
                student.setPatronymic(patronymic.getText().toString());
                DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
                Date birth;
                try {
                    birth = format.parse(birthDate.getText().toString());
                    student.setBirthDate(birth);
                    student.setGroupaId(groupas.get(groupaSpinner.getSelectedItemPosition()).getId());
                    studentDao.update(student);
                    startActivity(new Intent(context, StudentActivity.class));
                } catch (ParseException e) {
                    e.printStackTrace();
                    Toast.makeText(context, R.string.wrongDateFormat, Toast.LENGTH_LONG).show();
                    startActivity(new Intent(context, AddStudentActivity.class));
                }

            }
        }
    }

    private class MyAddStudentAdapter extends ArrayAdapter<Groupa> {

        public MyAddStudentAdapter(@NonNull Context context, int resource, @NonNull List<Groupa> objects) {
            super(context, resource, objects);
        }

        @Override
        public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView,
                                  ViewGroup parent) {

            LayoutInflater inflater = getLayoutInflater();
            View item = inflater.inflate(R.layout.student_spinner_item, parent, false);
            TextView id = item.findViewById(R.id.groupaId);
            TextView caption = item.findViewById(R.id.groupaName);
            id.setText(String.valueOf(groupas.get(position).getId()));
            caption.setText(groupas.get(position).getCaption());

            return item;
        }
    }



}
