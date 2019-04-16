package com.sampletask.usecases.domain.usecases;

import android.arch.lifecycle.MutableLiveData;
import android.text.TextUtils;

import com.sampletask.entities.Task;
import com.sampletask.usecases.domain.repositories.TaskRepository;

import java.util.ArrayList;
import java.util.List;


public class InsertTaskUseCase {


    private TaskRepository repository;

    public InsertTaskUseCase() {
        repository = new TaskRepository();
        new InsertTaskUseCase(repository);
    }

    public InsertTaskUseCase(TaskRepository repository) {
        this.repository = repository;
    }

    public void insert(Task text, MutableLiveData<Boolean> clearText) {
        if (TextUtils.isEmpty(text.getText())) {
            clearText.postValue(false);
        } else {
            repository.addItem(text);
            clearText.postValue(true);
        }
    }

    public void retrieveTasks(MutableLiveData<List<Task>> result) {
        List<Task> list=repository.retrieveAllData();
        result.postValue(list);
    }

    public void updateTask(Task task) {
        repository.updateTask(task);
    }
}
