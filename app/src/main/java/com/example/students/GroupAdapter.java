package com.example.students;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.students.Entity.Group;
import com.example.students.Entity.Student;

import java.util.ArrayList;
import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.GroupViewHolder> {
    private GroupClickListener clickListener;
    private List<Group> dataset;

    public interface GroupClickListener {
        void onStudentClick(int position);
    }

    static class GroupViewHolder extends RecyclerView.ViewHolder {

        public TextView caption;
        public TextView faculty;

        public GroupViewHolder(View itemView, final GroupAdapter.GroupClickListener clickListener) {
            super(itemView);
            caption = itemView.findViewById(R.id.textViewGroupCaption);
            faculty = itemView.findViewById(R.id.textViewGroupFaculty);

            itemView.setOnClickListener(view -> {
                if (clickListener != null) {
                    clickListener.onStudentClick(getAdapterPosition());
                }
            });
        }
    }

    public GroupAdapter(GroupAdapter.GroupClickListener clickListener) {
        this.clickListener = clickListener;
        this.dataset = new ArrayList<>();
    }

    public void setStudents(@NonNull List<Group> groups) {
        dataset = groups;
        notifyDataSetChanged();
    }

    public Group getGroup(int position) {
        return dataset.get(position);
    }

    @NonNull
    @Override
    public GroupAdapter.GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_group, parent, false);
        return new GroupAdapter.GroupViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupAdapter.GroupViewHolder holder, int position) {
        Group group = dataset.get(position);
        holder.caption.setText(group.getCaption());
        holder.faculty.setText(group.getFaculty());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
