package com.sampletask.usecases.domain.repositories;

import com.sampletask.entities.Task;
import com.sampletask.usecases.domain.database.TaskDao;
import com.sampletask.usecases.domain.database.TaskRoomDataBase;

import java.util.List;

public class TaskRepository {

    private TaskDao taskDao;

    public TaskRepository() {
        TaskRoomDataBase db = TaskRoomDataBase.getDatabase();
        taskDao = db.dataDao();
    }

    public List<Task> retrieveAllData() {
        return taskDao.queryAll();
    }

    public void addItem(Task text) {
        taskDao.insert(text);
    }

    public void updateTask(Task task){
        taskDao.update(task);
    }

}
