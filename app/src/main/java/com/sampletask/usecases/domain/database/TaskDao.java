package com.sampletask.usecases.domain.database;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


import com.sampletask.entities.Task;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("select * from task_table")
    List<Task> queryAll();


    @Insert
    void insert(Task text);

    @Update
    void update(Task text);

}
