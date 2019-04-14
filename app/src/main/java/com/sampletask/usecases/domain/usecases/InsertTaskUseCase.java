package com.sampletask.usecases.domain.usecases;

import com.sampletask.entities.Task;
import com.sampletask.presentation.DaggerAppComponent;
import com.sampletask.usecases.domain.repositories.TaskRepository;

import java.util.List;

import javax.inject.Inject;

public class InsertTaskUseCase {


    @Inject
    TaskRepository repository;

    public InsertTaskUseCase() {
        DaggerAppComponent.Initializer.buildComponent().injectRepo(this);
    }

    public void insert(Task text) {
        repository.addItem(text);
    }

    public List<Task> retrieveData(){
        return repository.retrieveAllData();
    }

    public void updateTask(Task task){
        repository.updateTask(task);
    }
}
