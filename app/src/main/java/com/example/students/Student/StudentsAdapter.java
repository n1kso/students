package com.example.students.Student;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.students.Entity.Student;
import com.example.students.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.StudentsViewHolder> {

    private StudentClickListener clickListener;
    private List<Student> dataset;
    private List<Student> datasetCopy;

    public interface StudentClickListener {
        void onStudentClick(int position);
    }

    static class StudentsViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView familyName;
        public TextView patronymic;
        public TextView birthDate;
        public TextView group;
//        public ImageButton editButton;
//        public ImageButton deleteButton;

        public StudentsViewHolder(View itemView, final StudentClickListener clickListener) {
            super(itemView);
            name = itemView.findViewById(R.id.textViewStudentName);
            familyName = itemView.findViewById(R.id.textViewStudentFamilyName);
            patronymic = itemView.findViewById(R.id.textViewStudentPatronymic);
            birthDate = itemView.findViewById(R.id.textViewStudentBirthDate);
            group = itemView.findViewById(R.id.textViewStudentGroup);
//            editButton = itemView.findViewById(R.id.editStudent);
//            deleteButton = itemView.findViewById(R.id.deleteStudent);

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
        this.datasetCopy = new ArrayList<>();
    }

    public void setStudents(@NonNull List<Student> students) {
        dataset = students;
        datasetCopy.addAll(dataset);
        notifyDataSetChanged();
    }

    public Student getStudent(int position) {
        return dataset.get(position);
    }

    @NonNull
    @Override
    public StudentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_student, parent, false);
        return new StudentsViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentsViewHolder holder, int position) {
        Student student = dataset.get(position);
        holder.name.setText(student.getName());
        holder.familyName.setText(student.getSurname());
        holder.patronymic.setText(student.getPatronymic());
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
        holder.birthDate.setText(format.format(student.getBirthDate()));
        holder.group.setText(student.getGroupa().getCaption());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void filter(String text) {
        dataset.clear();
        if(text.isEmpty()){
            dataset.addAll(datasetCopy);
        } else{
            text = text.toLowerCase();
            for(Student item: datasetCopy){
                if(item.getName().toLowerCase().contains(text) ||
                        item.getSurname().toLowerCase().contains(text) ||
                        item.getPatronymic().toLowerCase().contains(text) ||
                        item.getGroupa().getCaption().toLowerCase().contains(text) ||
                        item.getBirthDate().toString().contains(text)) {
                    dataset.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }
}
