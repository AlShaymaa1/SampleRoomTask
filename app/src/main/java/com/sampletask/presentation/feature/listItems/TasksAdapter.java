package com.sampletask.presentation.feature.listItems;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.sampletask.R;
import com.sampletask.entities.Task;

import java.io.Serializable;
import java.util.List;

import static com.sampletask.presentation.feature.listItems.TasksAdapter.TasksViewHolder.EXTRA_DATA;


public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TasksViewHolder> {

    private List<Task> list;

    TasksAdapter(List<Task> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public TasksViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_tasks, viewGroup, false);
        return new TasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TasksViewHolder tasksViewHolder, int i) {
        Task task = list.get(i);
        tasksViewHolder.bind(task);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    class TasksViewHolder extends RecyclerView.ViewHolder {
        static final String ACTION_BUTTON_CHECKED = "ACTION_BUTTON_CHECKED";
        static final String ACTION_BUTTON_UNCHECKED = "ACTION_BUTTON_UNCHECKED";
        static final String EXTRA_DATA = "EXTRA_DATA";
        private TextView itemNameTextView;
        private CheckBox updateDataCheckBox;

        TasksViewHolder(@NonNull View itemView) {
            super(itemView);
            itemNameTextView = itemView.findViewById(R.id.item_input_text_view);
            updateDataCheckBox = itemView.findViewById(R.id.update_database_check_box);
        }

        void bind(Task task) {
            itemNameTextView.setText(task.getText());
            if (task.isDone()) {
                updateDataCheckBox.setChecked(true);
            }
            updateDataCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    sendUpdateTaskBroadcast(itemView, ACTION_BUTTON_CHECKED, task);
                } else {
                    sendUpdateTaskBroadcast(itemView, ACTION_BUTTON_UNCHECKED, task);
                }
            });
        }
    }

    private void sendUpdateTaskBroadcast(View view, String action, Task task) {
        Intent intent = new Intent(action);
        intent.putExtra(EXTRA_DATA, (Serializable) task);
        view.getContext().sendBroadcast(intent);
    }

}
