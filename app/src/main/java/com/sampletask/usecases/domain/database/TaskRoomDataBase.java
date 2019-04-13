package com.sampletask.usecases.domain.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import com.sampletask.entities.Task;
import com.sampletask.presentation.MyApplication;

@Database(entities = {Task.class}, version = 1,exportSchema = false)
public abstract class TaskRoomDataBase extends RoomDatabase {
    public abstract TaskDao dataDao();

    private static volatile TaskRoomDataBase INSTANCE;

    public static TaskRoomDataBase getDatabase() {
        if (INSTANCE == null) {
            synchronized (TaskRoomDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(MyApplication.getAppContext(),
                            TaskRoomDataBase.class, "task_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
