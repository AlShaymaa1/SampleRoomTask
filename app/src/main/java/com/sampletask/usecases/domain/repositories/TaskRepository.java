package com.sampletask.usecases.domain.repositories;

import android.app.Application;

import com.sampletask.entities.Task;

import com.sampletask.presentation.MyApplication;
import com.sampletask.usecases.domain.database.TaskDao;
import com.sampletask.usecases.domain.database.TaskRoomDataBase;

import java.util.List;

import javax.inject.Inject;

public class TaskRepository {

    //@Inject Application application;
    private TaskDao taskDao;
    //@Inject TaskRoomDataBase taskRoomDataBase;

    public TaskRepository() {
        //MyApplication.buildComponent().injectApp(this);
        TaskRoomDataBase dataBase = TaskRoomDataBase.getDatabase();
        taskDao = dataBase.getTaskDao();
    }

    public List<Task> retrieveAllData() {
        return taskDao.queryAll();
    }

    public void addItem(Task text) {
        taskDao.insert(text);
    }

    public void updateTask(Task task) {
        taskDao.update(task);
    }

}
