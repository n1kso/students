package com.example.students.Student;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.students.App;
import com.example.students.Entity.DaoSession;
import com.example.students.Entity.Student;
import com.example.students.Entity.StudentDao;
import com.example.students.R;

import org.greenrobot.greendao.query.Query;

import java.util.List;

public class StudentActivity extends AppCompatActivity {

    private Button addFacultyButton;
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
        updateStudents();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void updateStudents() {
        List<Student> students = studentQuery.list();
        studentsAdapter.setStudents(students);
    }

    protected void setUpViews() {
        RecyclerView recyclerView = findViewById(R.id.recyclerViewStudents);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        studentsAdapter = new StudentsAdapter(studentClickListener);
        recyclerView.setAdapter(studentsAdapter);

        addFacultyButton = findViewById(R.id.buttonAddStudent);
        addFacultyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentActivity.this, AddStudentActivity.class));
            }
        });


    }

    StudentsAdapter.StudentClickListener studentClickListener = new StudentsAdapter.StudentClickListener() {
        @Override
        public void onStudentClick(int position) {
            Student student = studentsAdapter.getStudent(position);
            StudentDialogFragment studentDialogFragment = new StudentDialogFragment(student, studentDao);
            studentDialogFragment.show(getSupportFragmentManager(), "edit");
//            Long studentId = student.getId();


//            studentDao.deleteByKey(studentId);
//            Log.d("DaoExample", "Deleted note, ID: " + studentId);

            updateStudents();
        }
    };
}


