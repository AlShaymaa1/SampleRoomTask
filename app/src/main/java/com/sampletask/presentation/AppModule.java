package com.sampletask.presentation;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.sampletask.usecases.domain.database.TaskDao;
import com.sampletask.usecases.domain.database.TaskRoomDataBase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
class AppModule {

    private Application application;
    private TaskRoomDataBase taskDatabase;

    AppModule(Application mApplication) {
        taskDatabase = Room.databaseBuilder(mApplication, TaskRoomDataBase.class, "task_database").build();
        application = mApplication;
    }

    @Provides
    Application providesApplication() {
        return application;
    }


    @Provides
    TaskRoomDataBase providesRoomDatabase() {
        return taskDatabase;
    }

    @Provides
    TaskDao providesTaskDao() {
        return taskDatabase.getTaskDao();
    }

}
