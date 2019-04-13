package com.sampletask.usecases.domain.usecases;

import android.arch.lifecycle.MutableLiveData;

import com.sampletask.entities.Task;
import com.sampletask.usecases.domain.repositories.TaskRepository;

import java.util.List;

public class RetrieveTasksUseCase {

    private MutableLiveData<Boolean> retrieving;
    private MutableLiveData<List<Task>> result;
    private TaskRepository repository;

    public RetrieveTasksUseCase() {
        repository=new TaskRepository();
    }

    public List<Task> retrieveData(){
        return repository.retrieveAllData();
    }

    public void addDoneTasks(Task task){
         repository.addItem(task);
    }

    public void updateTask(Task task){
        repository.updateTask(task);
    }
}
