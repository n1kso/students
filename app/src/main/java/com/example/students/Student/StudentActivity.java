package com.example.students.Student;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.students.App;
import com.example.students.Entity.DaoSession;
import com.example.students.Entity.Student;
import com.example.students.Entity.StudentDao;
import com.example.students.R;

import org.greenrobot.greendao.query.Query;

import java.util.List;

public class StudentActivity extends AppCompatActivity implements StudentDialogFragment.DeleteStudentDialogListener {

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

    @Override
    public void onFinishDeleteDialog(boolean delete) {
        if (delete)
            updateStudents();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                studentsAdapter.filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                studentsAdapter.filter(newText);
                return true;
            }
        });

        return true;
    }
}


