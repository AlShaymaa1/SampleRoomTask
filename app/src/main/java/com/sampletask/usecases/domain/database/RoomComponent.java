package com.sampletask.usecases.domain.database;


import com.sampletask.presentation.feature.home.HomeViewModel;
import com.sampletask.presentation.feature.listItems.TasksViewModel;
import com.sampletask.usecases.domain.usecases.InsertTaskUseCase;

import dagger.Component;

@Component(modules = {RoomModule.class})
public interface RoomComponent {

    void injectRepo(InsertTaskUseCase useCase);
    void injectUseCase(TasksViewModel viewModel);
    void injectUseCase(HomeViewModel viewModel);


    class Initializer {

        private static RoomComponent component;

        public static RoomComponent buildComponent() {
            if (component == null) {
                component = DaggerRoomComponent.builder().build();
            }
            return component;
        }
    }
}
