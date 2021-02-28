package com.example.students.Groupa;

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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.students.App;
import com.example.students.Entity.DaoSession;
import com.example.students.Entity.Faculty;
import com.example.students.Entity.FacultyDao;
import com.example.students.Entity.Groupa;
import com.example.students.Entity.GroupaDao;
import com.example.students.R;

import org.greenrobot.greendao.query.Query;

import java.util.List;

public class AddGroupaActivity extends AppCompatActivity {
    private EditText caption;
    private TextView id;
    private Spinner facultySpinner;
    private List<Faculty> faculties;
    private Button insertGroupa;
    private Handler handler;

    private GroupaDao groupaDao;
    private Query<Groupa> groupaQuery;

    private FacultyDao facultyDao;
    private Query<Faculty> facultyQuery;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);

        caption = findViewById(R.id.newGroupaCaption);
        facultySpinner = findViewById(R.id.facultySpinner);
        insertGroupa = findViewById(R.id.insertGroupa);
        id = findViewById(R.id.groupaId);

        DaoSession daoSession = ((App)getApplication()).getDaoSession();
        groupaDao = daoSession.getGroupaDao();
        groupaQuery = groupaDao.queryBuilder().build();

        facultyDao = daoSession.getFacultyDao();
        facultyQuery = facultyDao.queryBuilder().build();
        faculties = facultyQuery.list();

        AddGroupaActivity.MyAddGroupaAdapter myAddGroupaAdapter = new AddGroupaActivity.MyAddGroupaAdapter(this, R.layout.faculty_spinner_item, faculties);

        facultySpinner.setAdapter(myAddGroupaAdapter);
//        groupaSpinner.setSelection(0);

        handler = new Handler(Looper.getMainLooper());

        facultySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        insertGroupa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddGroupaActivity.InsertGroupa insertGroupa = new AddGroupaActivity.InsertGroupa(AddGroupaActivity.this);
                handler.post(insertGroupa);

            }
        });
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Groupa groupa = getIntent().getParcelableExtra("groupa");
        if (groupa != null) {
            id.setText(String.valueOf(groupa.getId()));
            caption.setText(groupa.getCaption());
            facultySpinner.setSelection(findSelectedPosition(groupa));

        }
    }

    private int findSelectedPosition(Groupa groupa) {
        int pos = -1;
        for (Faculty faculty: faculties) {
            pos++;
            if (groupa.getFacultyId() == faculty.getId())
                return pos;
        }
        return pos;
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private class InsertGroupa implements Runnable {
        private final Context context;

        InsertGroupa(Context context) {
            this.context = context;
        }

        @Override
        public void run() {
            if (isFilled()) {
                if (id.getText().equals("")) {
                    Groupa groupa = new Groupa();
                    groupa.setCaption(caption.getText().toString());
                    groupa.setFacultyId(faculties.get(facultySpinner.getSelectedItemPosition()).getId());
                    groupaDao.insert(groupa);
                    startActivity(new Intent(context, GroupaActivity.class));
                } else {
                    Groupa groupa = groupaDao.loadByRowId(Long.parseLong(id.getText().toString()));
                    groupa.setCaption(caption.getText().toString());
                    groupa.setFacultyId(faculties.get(facultySpinner.getSelectedItemPosition()).getId());
                    groupaDao.update(groupa);
                    startActivity(new Intent(context, GroupaActivity.class));

                }
            } else {
                Toast.makeText(context, R.string.fieldsMustBeFilled, Toast.LENGTH_LONG).show();
            }
        }
    }

    private class MyAddGroupaAdapter extends ArrayAdapter<Faculty> {

        public MyAddGroupaAdapter(@NonNull Context context, int resource, @NonNull List<Faculty> objects) {
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
            View item = inflater.inflate(R.layout.faculty_spinner_item, parent, false);
            TextView id = item.findViewById(R.id.facultyId);
            TextView caption = item.findViewById(R.id.facultyName);
            id.setText(String.valueOf(faculties.get(position).getId()));
            caption.setText(faculties.get(position).getName());

            return item;
        }
    }

    private boolean isFilled() {
        if (caption.getText() != null && !caption.getText().toString().equals("")) {
            return caption.getText().toString().matches("\\s*\\D+\\s*");
        } else return false;
    }
}
