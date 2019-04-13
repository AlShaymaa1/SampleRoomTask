package com.sampletask.presentation.feature.home;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sampletask.R;
import com.sampletask.entities.Task;
import com.sampletask.presentation.feature.listItems.TasksScreen;

public class HomeScreen extends AppCompatActivity {

    private Button showDataSavedButton, addDataButton;
    private EditText inputDataEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViews();
        HomeViewModel viewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        viewModel.clearText.observe(this, aBoolean-> {
                if (aBoolean) {
                    addItemSuccessfully();
                }
        });


        addDataButton.setOnClickListener((View v) -> {
            String data = inputDataEditText.getText().toString();
            if(!TextUtils.isEmpty(data)){
                Task task1 = new Task();
                task1.setText(data);
                viewModel.insertData(task1);
            }else {
                Toast.makeText(HomeScreen.this, getResources().getString(R.string.empty_toast_message), Toast.LENGTH_SHORT).show();
            }
        });

        showDataSavedButton.setOnClickListener((View v) -> {
            Intent intent = new Intent(HomeScreen.this, TasksScreen.class);
            startActivity(intent);

        });
    }

    private void initViews() {
        showDataSavedButton = findViewById(R.id.show_data_button);
        addDataButton = findViewById(R.id.add_button);
        inputDataEditText = findViewById(R.id.item_input_edit_text);
    }

    private void addItemSuccessfully() {
        inputDataEditText.setText("");
        Toast.makeText(HomeScreen.this, getResources().getString(R.string.success_toast_message), Toast.LENGTH_SHORT).show();
    }
}
