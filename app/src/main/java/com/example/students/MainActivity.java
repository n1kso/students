package com.example.students;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.students.Faculty.FacultyActivity;
import com.example.students.Groupa.GroupaActivity;
import com.example.students.Student.StudentActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button facultyBtn;
    private Button groupaBtn;
    private Button studentBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        facultyBtn = findViewById(R.id.facultyButton);
        groupaBtn = findViewById(R.id.groupaButton);
        studentBtn = findViewById(R.id.studentButton);

        facultyBtn.setOnClickListener(this);
        groupaBtn.setOnClickListener(this);
        studentBtn.setOnClickListener(this);


    }

//    private void onClickBtn(View v) {
//        Intent i;
//
//        if (v.getId() == R.id.facultyButton) {
//            i = new Intent(MainActivity.this, FacultyActivity.class);
//            startActivity(i);
//        } else if (v.getId() == R.id.groupaButton) {
//            i = new Intent(MainActivity.this, GroupaActivity.class);
//        } else if (v.getId() == R.id.studentButton) {
//            i = new Intent(MainActivity.this, MainActivity.class);
//        } else {
//            return;
//        }
//
//        startActivity(i);
//    }


    @Override
    public void onClick(View v) {
        Intent i;

        if (v.getId() == R.id.facultyButton) {
            i = new Intent(MainActivity.this, FacultyActivity.class);
        } else if (v.getId() == R.id.groupaButton) {
            i = new Intent(MainActivity.this, GroupaActivity.class);
        } else if (v.getId() == R.id.studentButton) {
            i = new Intent(MainActivity.this, StudentActivity.class);
        } else {
            return;
        }

        startActivity(i);
    }
}