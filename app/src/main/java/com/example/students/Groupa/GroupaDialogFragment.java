package com.example.students.Groupa;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.students.Entity.Groupa;
import com.example.students.Entity.GroupaDao;
import com.example.students.Entity.Student;
import com.example.students.Entity.StudentDao;
import com.example.students.R;
import com.example.students.Student.AddStudentActivity;
import com.example.students.Student.StudentDialogFragment;

public class GroupaDialogFragment extends DialogFragment {
    private Groupa groupa;
    private GroupaDao groupaDao;
    private GroupaDialogFragment.DeleteGroupaDialogListener deleteGroupaDialogListener;

    public interface DeleteGroupaDialogListener {
        void onFinishDeleteDialog(boolean delete);
    }

    public GroupaDialogFragment(Groupa groupa, GroupaDao groupaDao) {
        this.groupa = groupa;
        this.groupaDao = groupaDao;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.editordelete)
                .setPositiveButton(R.string.edit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getContext(), AddGroupaActivity.class);
                        intent.putExtra("groupa", groupa);
                        dismiss();
                        startActivity(intent);
                    }
                })
                .setNegativeButton(R.string.delete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        groupaDao.deleteByKey(groupa.getId());
//                        Intent intent = new Intent(getContext(), StudentActivity.class);
//                        startActivity(intent);
                        deleteGroupaDialogListener.onFinishDeleteDialog(true);
                        dismiss();
                    }
                });
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            deleteGroupaDialogListener = (GroupaDialogFragment.DeleteGroupaDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement EditNameDialogListener");
        }
    }
}
