package com.example.students.Groupa;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.students.App;
import com.example.students.Entity.DaoSession;
import com.example.students.Entity.Groupa;
import com.example.students.Entity.GroupaDao;
import com.example.students.Entity.Student;
import com.example.students.R;
import com.example.students.Student.AddStudentActivity;
import com.example.students.Student.StudentActivity;
import com.example.students.Student.StudentDialogFragment;

import org.greenrobot.greendao.query.Query;

import java.util.List;

public class GroupaActivity extends AppCompatActivity implements GroupaDialogFragment.DeleteGroupaDialogListener{

    private EditText editText;
    private View addGroupaButton;

    private GroupaDao groupaDao;
    private Query<Groupa> groupaQuery;
    private GroupaAdapter groupaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        setUpViews();
        DaoSession daoSession = ((App) getApplication()).getDaoSession();
        groupaDao = daoSession.getGroupaDao();
        groupaQuery = groupaDao.queryBuilder().build();
        updateGroups();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void updateGroups() {
        List<Groupa> groupas = groupaQuery.list();
        groupaAdapter.setGroupas(groupas);
    }

    GroupaAdapter.GroupaClickListener groupaClickListener = new GroupaAdapter.GroupaClickListener() {
        @Override
        public void onGroupaClick(int position) {
            Groupa groupa = groupaAdapter.getGroupa(position);
            GroupaDialogFragment groupaDialogFragment = new GroupaDialogFragment(groupa, groupaDao);
            groupaDialogFragment.show(getSupportFragmentManager(), "edit");
            updateGroups();
        }
    };

    @Override
    public void onFinishDeleteDialog(boolean delete) {
        if (delete)
            updateGroups();
    }

    protected void setUpViews() {
        RecyclerView recyclerView = findViewById(R.id.recyclerViewGroups);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        groupaAdapter = new GroupaAdapter(groupaClickListener);
        recyclerView.setAdapter(groupaAdapter);

        addGroupaButton = findViewById(R.id.buttonAddGroupa);
        addGroupaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GroupaActivity.this, AddGroupaActivity.class));
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                groupaAdapter.filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                groupaAdapter.filter(newText);
                return true;
            }
        });

        return true;
    }

}
