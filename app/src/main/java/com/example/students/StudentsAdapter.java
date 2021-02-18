package com.example.students;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.students.Entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.StudentsViewHolder> {

    private StudentClickListener clickListener;
    private List<Student> dataset;

    public interface StudentClickListener {
        void onStudentClick(int position);
    }

    static class StudentsViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView familyName;
        public TextView patronymic;
        public TextView birthDate;
        public TextView group;

        public StudentsViewHolder(View itemView, final StudentClickListener clickListener) {
            super(itemView);
            name = itemView.findViewById(R.id.textViewStudentName);
            familyName = itemView.findViewById(R.id.textViewStudentFamilyName);
            patronymic = itemView.findViewById(R.id.textViewStudentPatronymic);
            birthDate = itemView.findViewById(R.id.textViewStudentBirthDate);
            group = itemView.findViewById(R.id.textViewStudentGroup);

            itemView.setOnClickListener(view -> {
                if (clickListener != null) {
                    clickListener.onStudentClick(getAdapterPosition());
                }
            });
        }
    }

    public StudentsAdapter(StudentClickListener clickListener) {
        this.clickListener = clickListener;
        this.dataset = new ArrayList<>();
    }

    public void setStudents(@NonNull List<Student> students) {
        dataset = students;
        notifyDataSetChanged();
    }

    public Student getStudent(int position) {
        return dataset.get(position);
    }

    @NonNull
    @Override
    public StudentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_stundent, parent, false);
        return new StudentsViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentsViewHolder holder, int position) {
        Student student = dataset.get(position);
        holder.name.setText(student.getName());
        holder.familyName.setText(student.getName());
        holder.patronymic.setText(student.getPatronymic());
        holder.birthDate.setText(student.getBirthDate().toString());
        holder.group.setText(String.valueOf(student.getGroupaId()));
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
