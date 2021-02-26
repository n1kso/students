package com.example.students.Student;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.students.Entity.Student;
import com.example.students.Entity.StudentDao;
import com.example.students.R;

public class StudentDialogFragment extends DialogFragment {
    private Student student;
    private StudentDao studentDao;

    public StudentDialogFragment(Student student, StudentDao studentDao) {
        this.student = student;
        this.studentDao = studentDao;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.editordelete)
                .setPositiveButton(R.string.edit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getContext(), AddStudentActivity.class);
                        intent.putExtra("student", student);
                        startActivity(intent);
                    }
                })
                .setNegativeButton(R.string.delete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        studentDao.deleteByKey(student.getId());
                    }
                });
        return builder.create();
    }
}