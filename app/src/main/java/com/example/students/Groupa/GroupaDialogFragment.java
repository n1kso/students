package com.example.students.Groupa;

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

import com.example.students.Entity.Groupa;
import com.example.students.Entity.GroupaDao;
import com.example.students.R;

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
                        if (groupa.getStudents().size() == 0) {
                            groupaDao.deleteByKey(groupa.getId());
                            deleteGroupaDialogListener.onFinishDeleteDialog(true);
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
            deleteGroupaDialogListener = (GroupaDialogFragment.DeleteGroupaDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement EditNameDialogListener");
        }
    }
}
