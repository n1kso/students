package com.example.students;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.students.Entity.DaoSession;
import com.example.students.Entity.Faculty;
import com.example.students.Entity.FacultyDao;

import org.greenrobot.greendao.query.Query;

import java.util.List;

public class FacultyActivity extends AppCompatActivity {

    private View addFacultyButton;
    private FacultyDao facultyDao;
    private Query<Faculty> facultyQuery;
    private FacultyAdapter facultyAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);

        setUpViews();

        DaoSession daoSession = ((App)getApplication()).getDaoSession();
        facultyDao = daoSession.getFacultyDao();

        facultyQuery = facultyDao.queryBuilder().build();
        updateFaculties();
    }

    private void updateFaculties() {
        List<Faculty> faculties = facultyQuery.list();
        facultyAdapter.setFaculties(faculties);
    }

    protected void setUpViews() {
        RecyclerView recyclerView = findViewById(R.id.recyclerViewFaculties);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        facultyAdapter = new FacultyAdapter(facultyClickListener);
        recyclerView.setAdapter(facultyAdapter);

        addFacultyButton = findViewById(R.id.buttonAddFaculty);
        addFacultyButton.setEnabled(false);


    }

    FacultyAdapter.FacultyClickListener facultyClickListener = new FacultyAdapter.FacultyClickListener() {
        @Override
        public void onFacultyClick(int position) {
            Faculty faculty = facultyAdapter.getFaculty(position);
            Long facultyId = faculty.getId();

            facultyDao.deleteByKey(facultyId);
            Log.d("DaoExample", "Deleted note, ID: " + facultyId);

            updateFaculties();
        }
    };

    public void onAddButtonClick(View view) {
        
    }
}
