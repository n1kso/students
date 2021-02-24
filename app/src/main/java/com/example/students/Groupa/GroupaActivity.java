package com.example.students.Groupa;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.students.App;
import com.example.students.Entity.DaoSession;
import com.example.students.Entity.Groupa;
import com.example.students.Entity.GroupaDao;
import com.example.students.R;

import org.greenrobot.greendao.query.Query;

import java.util.List;

public class GroupaActivity extends AppCompatActivity {

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

        // get the note DAO
        DaoSession daoSession = ((App) getApplication()).getDaoSession();
        groupaDao = daoSession.getGroupaDao();

        // query all notes, sorted a-z by their text
        groupaQuery = groupaDao.queryBuilder().build();
        updateGroups();
    }

    private void updateGroups() {
        List<Groupa> groupas = groupaQuery.list();
        groupaAdapter.setGroupas(groupas);
    }

    GroupaAdapter.GroupaClickListener groupaClickListener = new GroupaAdapter.GroupaClickListener() {
        @Override
        public void onGroupaClick(int position) {
            Groupa groupa = groupaAdapter.getGroupa(position);
            Long noteId = groupa.getId();

            groupaDao.deleteByKey(noteId);
            Log.d("DaoExample", "Deleted note, ID: " + noteId);

            updateGroups();
        }
    };

    protected void setUpViews() {
        RecyclerView recyclerView = findViewById(R.id.recyclerViewGroups);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        groupaAdapter = new GroupaAdapter(groupaClickListener);
        recyclerView.setAdapter(groupaAdapter);

        addGroupaButton = findViewById(R.id.buttonAdd);
        addGroupaButton.setEnabled(true);

//        editText = findViewById(R.id.textViewGroupFaculty);
//        editText.setOnEditorActionListener((v, actionId, event) -> {
//            if (actionId == EditorInfo.IME_ACTION_DONE) {
//                addNote();
//                return true;
//            }
//            return false;
//        });
//        editText.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                boolean enable = s.length() != 0;
//                addNoteButton.setEnabled(enable);
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//            }
//        });
    }

    public void onAddButtonClick(View view) {

    }
}
