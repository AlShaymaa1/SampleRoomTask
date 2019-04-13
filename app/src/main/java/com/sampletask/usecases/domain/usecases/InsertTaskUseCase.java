package com.sampletask.usecases.domain.usecases;

import com.sampletask.entities.Task;
import com.sampletask.usecases.domain.repositories.TaskRepository;

public class InsertTaskUseCase {


    private TaskRepository repository;

    public InsertTaskUseCase() {
        repository = new TaskRepository();
    }

    public void insert(Task text) {
        repository.addItem(text);
    }


}
