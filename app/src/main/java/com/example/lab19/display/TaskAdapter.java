package com.example.lab19.display;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lab19.R;
import com.example.lab19.persistence.db.TaskEntry;
import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.EntryHolder> {

    private List<TaskEntry> entries = new ArrayList<>();
    private OnInteractionListener interactionListener;

    public interface OnInteractionListener {
        void onShortPress(TaskEntry entry);
        void onLongPress(TaskEntry entry);
    }

    public void updateData(List<TaskEntry> newEntries) {
        this.entries = newEntries;
        notifyDataSetChanged();
    }

    public void setListener(OnInteractionListener listener) {
        this.interactionListener = listener;
    }

    @NonNull
    @Override
    public EntryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_task, parent, false);
        return new EntryHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EntryHolder holder, int position) {
        TaskEntry current = entries.get(position);
        holder.headerTxt.setText(current.getHeader());
        holder.bodyTxt.setText(current.getBody());
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    class EntryHolder extends RecyclerView.ViewHolder {
        private final TextView headerTxt;
        private final TextView bodyTxt;

        public EntryHolder(@NonNull View itemView) {
            super(itemView);
            headerTxt = itemView.findViewById(R.id.labelHeader);
            bodyTxt = itemView.findViewById(R.id.labelBody);

            itemView.setOnClickListener(v -> {
                int pos = getAdapterPosition();
                if (interactionListener != null && pos != RecyclerView.NO_POSITION) {
                    interactionListener.onShortPress(entries.get(pos));
                }
            });

            itemView.setOnLongClickListener(v -> {
                int pos = getAdapterPosition();
                if (interactionListener != null && pos != RecyclerView.NO_POSITION) {
                    interactionListener.onLongPress(entries.get(pos));
                    return true;
                }
                return false;
            });
        }
    }
}
