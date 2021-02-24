package com.example.students.Faculty;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.students.Entity.Faculty;
import com.example.students.R;

import java.util.ArrayList;
import java.util.List;

public class FacultyAdapter extends RecyclerView.Adapter<FacultyAdapter.FacultyViewHolder> {

    private FacultyClickListener clickListener;
    private List<Faculty> dataset;

    public interface FacultyClickListener {
        void onFacultyClick(int position);
    }

    static class FacultyViewHolder extends  RecyclerView.ViewHolder {

        TextView name;

        public FacultyViewHolder(View itemView, final FacultyClickListener clickListener) {
            super(itemView);
            name = itemView.findViewById(R.id.textViewFacultyName);
            itemView.setOnClickListener(view -> {
                    if (clickListener != null) {
                        clickListener.onFacultyClick(getAdapterPosition());
                    };
            });
        }
    }

    public FacultyAdapter(FacultyClickListener clickListener){
        this.clickListener = clickListener;
        this.dataset = new ArrayList<>();
    }

    public void setFaculties(@NonNull List<Faculty> faculties) {
        dataset = faculties;
        notifyDataSetChanged();
    }

    public Faculty getFaculty(int position) {
        return dataset.get(position);
    }

    @NonNull
    @Override
    public FacultyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_faculty, parent, false);
        return new FacultyViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(FacultyViewHolder holder, int position) {
        Faculty faculty = dataset.get(position);
        holder.name.setText(faculty.getName());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
