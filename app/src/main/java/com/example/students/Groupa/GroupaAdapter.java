package com.example.students.Groupa;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.students.Entity.Groupa;
import com.example.students.R;

import java.util.ArrayList;
import java.util.List;

public class GroupaAdapter extends RecyclerView.Adapter<GroupaAdapter.GroupaViewHolder> {
    private GroupaClickListener clickListener;
    private List<Groupa> dataset;

    public interface GroupaClickListener {
        void onGroupaClick(int position);
    }

    static class GroupaViewHolder extends RecyclerView.ViewHolder {

        public TextView caption;
        public TextView faculty;

        public GroupaViewHolder(View itemView, final GroupaAdapter.GroupaClickListener clickListener) {
            super(itemView);
            caption = itemView.findViewById(R.id.textViewGroupCaption);
            faculty = itemView.findViewById(R.id.textViewGroupFaculty);

            itemView.setOnClickListener(view -> {
                if (clickListener != null) {
                    clickListener.onGroupaClick(getAdapterPosition());
                }
            });
        }
    }

    public GroupaAdapter(GroupaAdapter.GroupaClickListener clickListener) {
        this.clickListener = clickListener;
        this.dataset = new ArrayList<>();
    }

    public void setGroupas(@NonNull List<Groupa> groupas) {
        dataset = groupas;
        notifyDataSetChanged();
    }

    public Groupa getGroupa(int position) {
        return dataset.get(position);
    }

    @NonNull
    @Override
    public GroupaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_group, parent, false);
        return new GroupaViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupaViewHolder holder, int position) {
        Groupa groupa = dataset.get(position);
        holder.caption.setText(groupa.getCaption());
        holder.faculty.setText(String.valueOf(groupa.getFacultyId()));
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
