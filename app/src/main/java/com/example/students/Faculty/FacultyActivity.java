package com.example.students.Faculty;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.students.App;
import com.example.students.Entity.DaoSession;
import com.example.students.Entity.Faculty;
import com.example.students.Entity.FacultyDao;
import com.example.students.R;

import org.greenrobot.greendao.query.Query;

import java.util.List;

public class FacultyActivity extends AppCompatActivity implements FacultyDialogFragment.DeleteFacultyDialogListener {

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

    @Override
    protected void onResume() {
        super.onResume();

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
        addFacultyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FacultyActivity.this, AddFacultyActivity.class));
            }
        });


    }

    FacultyAdapter.FacultyClickListener facultyClickListener = new FacultyAdapter.FacultyClickListener() {
        @Override
        public void onFacultyClick(int position) {
            Faculty faculty = facultyAdapter.getFaculty(position);
            FacultyDialogFragment facultyDialogFragment = new FacultyDialogFragment(faculty, facultyDao);
            facultyDialogFragment.show(getSupportFragmentManager(), "edit");

            updateFaculties();
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                facultyAdapter.filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                facultyAdapter.filter(newText);
                return true;
            }
        });

        return true;
    }

    @Override
    public void onFinishDeleteDialog(boolean delete) {
        if (delete)
            updateFaculties();
    }
}
