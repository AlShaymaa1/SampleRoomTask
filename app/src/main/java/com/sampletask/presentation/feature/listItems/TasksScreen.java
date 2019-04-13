package com.sampletask.presentation.feature.listItems;

import android.arch.lifecycle.ViewModelProviders;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sampletask.R;
import com.sampletask.entities.Task;

import static com.sampletask.presentation.feature.listItems.TasksAdapter.TasksViewHolder.ACTION_BUTTON_CHECKED;
import static com.sampletask.presentation.feature.listItems.TasksAdapter.TasksViewHolder.ACTION_BUTTON_UNCHECKED;
import static com.sampletask.presentation.feature.listItems.TasksAdapter.TasksViewHolder.EXTRA_DATA;

public class TasksScreen extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BroadcastReceiver broadcastReceiver;
    private TasksAdapter adapter;
    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_screen);
        initRecycler();
        TasksViewModel viewModel = ViewModelProviders.of(this).get(TasksViewModel.class);
        viewModel.queryAllData();
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction() != null)
                    switch (intent.getAction()) {
                        case ACTION_BUTTON_CHECKED:
                            task = (Task) intent.getSerializableExtra(EXTRA_DATA);

                            task.setDone(true);
                            viewModel.updateTasks(task);
                            break;
                        case ACTION_BUTTON_UNCHECKED:
                            task = (Task) intent.getSerializableExtra(EXTRA_DATA);
                            task.setDone(false);
                            viewModel.updateTasks(task);
                            break;
                        default:
                            break;
                    }
            }
        };

        viewModel.result.observe(this, data -> {
            adapter = new TasksAdapter(data);
            recyclerView.setAdapter(adapter);
        });

    }

    private void initRecycler() {
        recyclerView = findViewById(R.id.items_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(broadcastReceiver, new IntentFilter(ACTION_BUTTON_CHECKED));
        registerReceiver(broadcastReceiver, new IntentFilter(ACTION_BUTTON_UNCHECKED));
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadcastReceiver);
    }


//    private void calculateCountOfSelectedItems() {
//       int numberCheckedItems = 0;
//        for (Task task : task) {
//            if (task.isDone()) {
//                numberCheckedItems++;
//            }
//        }
//    }

}
