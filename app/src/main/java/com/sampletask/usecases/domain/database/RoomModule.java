package com.sampletask.usecases.domain.database;


import com.sampletask.usecases.domain.repositories.TaskRepository;
import com.sampletask.usecases.domain.usecases.InsertTaskUseCase;


import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {

    @Provides
    TaskRepository taskRepository() {
        return new TaskRepository();
    }

    @Provides
    InsertTaskUseCase insertUseCase() {
        return new InsertTaskUseCase();
    }

}