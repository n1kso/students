package com.example.students;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.students.Entity.DaoSession;
import com.example.students.Entity.Student;
import com.example.students.Entity.StudentDao;

import org.greenrobot.greendao.query.Query;

import java.util.List;

public class StudentActivity extends AppCompatActivity {

    private View addFacultyButton;
    private StudentDao studentDao;
    private Query<Student> studentQuery;
    private StudentsAdapter studentsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        setUpViews();

        DaoSession daoSession = ((App)getApplication()).getDaoSession();
        studentDao = daoSession.getStudentDao();
        studentQuery = studentDao.queryBuilder().build();
        updateFaculties();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void updateFaculties() {
        List<Student> students = studentQuery.list();
        studentsAdapter.setStudents(students);
    }

    protected void setUpViews() {
        RecyclerView recyclerView = findViewById(R.id.recyclerViewStudents);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        studentsAdapter = new StudentsAdapter(studentClickListener);
        recyclerView.setAdapter(studentsAdapter);

        addFacultyButton = findViewById(R.id.buttonAddSudent);
        addFacultyButton.setEnabled(false);


    }

    StudentsAdapter.StudentClickListener studentClickListener = new StudentsAdapter.StudentClickListener() {
        @Override
        public void onStudentClick(int position) {
            Student student = studentsAdapter.getStudent(position);
            Long studentId = student.getId();

//            studentDao.deleteByKey(studentId);
//            Log.d("DaoExample", "Deleted note, ID: " + studentId);

            updateFaculties();
        }
    };

    public void onAddButtonClick(View view) {

    }
}
