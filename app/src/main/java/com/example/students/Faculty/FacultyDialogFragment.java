package com.example.students.Faculty;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.students.Entity.Faculty;
import com.example.students.Entity.FacultyDao;
import com.example.students.R;

public class FacultyDialogFragment extends DialogFragment {
    private Faculty faculty;
    private FacultyDao facultyDao;
    private FacultyDialogFragment.DeleteFacultyDialogListener deleteFacultyDialogListener;

    public interface DeleteFacultyDialogListener {
        void onFinishDeleteDialog(boolean delete);
    }

    public FacultyDialogFragment(Faculty faculty, FacultyDao facultyDao) {
        this.faculty = faculty;
        this.facultyDao = facultyDao;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.editordelete)
                .setPositiveButton(R.string.edit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getContext(), AddFacultyActivity.class);
                        intent.putExtra("faculty", faculty);
                        dismiss();
                        startActivity(intent);
                    }
                })
                .setNegativeButton(R.string.delete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (faculty.getGroupas().size() == 0) {
                            facultyDao.deleteByKey(faculty.getId());
                            deleteFacultyDialogListener.onFinishDeleteDialog(true);
                        } else {
                            Toast.makeText(getContext(), R.string.cantDelete, Toast.LENGTH_LONG).show();
                        }
                        dismiss();
                    }
                });
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            deleteFacultyDialogListener = (FacultyDialogFragment.DeleteFacultyDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement EditNameDialogListener");
        }
    }
}
