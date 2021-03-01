package com.example.students.Faculty;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.students.App;
import com.example.students.Entity.DaoSession;
import com.example.students.Entity.Faculty;
import com.example.students.Entity.FacultyDao;
import com.example.students.R;

import org.greenrobot.greendao.query.Query;

public class AddFacultyActivity extends AppCompatActivity {
    private EditText name;
    private TextView id;
    private Button insertFaculty;
    private Handler handler;

    private FacultyDao facultyDao;
    private Query<Faculty> facultyQuery;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_faculty);

        name = findViewById(R.id.newFacultyName);
        insertFaculty = findViewById(R.id.insertFaculty);
        id = findViewById(R.id.facultyId);

        DaoSession daoSession = ((App)getApplication()).getDaoSession();
        facultyDao = daoSession.getFacultyDao();
        facultyQuery = facultyDao.queryBuilder().build();

        handler = new Handler(Looper.getMainLooper());

        insertFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddFacultyActivity.InsertFaculty insertFaculty  = new AddFacultyActivity.InsertFaculty(AddFacultyActivity.this);
                handler.post(insertFaculty);

            }
        });
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Faculty faculty = getIntent().getParcelableExtra("faculty");
        if (faculty != null) {
            id.setText(String.valueOf(faculty.getId()));
            name.setText(faculty.getName());
        }
    }



    @Override
    protected void onResume() {
        super.onResume();

    }

    private class InsertFaculty implements Runnable {
        private final Context context;

        InsertFaculty(Context context) {
            this.context = context;
        }

        @Override
        public void run() {
            if (isFilled()) {
                if (id.getText().equals("")) {
                    Faculty faculty = new Faculty();
                    faculty.setName(name.getText().toString());
                    facultyDao.insert(faculty);
                    startActivity(new Intent(context, FacultyActivity.class));
                } else {
                    Faculty faculty = facultyDao.loadByRowId(Long.parseLong(id.getText().toString()));
                    faculty.setName(name.getText().toString());
                    facultyDao.update(faculty);
                    startActivity(new Intent(context, FacultyActivity.class));

                }
            } else {
                Toast.makeText(context, R.string.fieldsMustBeFilled, Toast.LENGTH_LONG).show();
            }
        }
    }

    private boolean isFilled() {
        if (name.getText() != null && !name.getText().toString().equals("")) {
            return name.getText().toString().matches("^[^\\s*]\\D+\\s*");
        } else return false;
    }

}
