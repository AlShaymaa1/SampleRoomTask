package com.sampletask.presentation;


import com.sampletask.presentation.feature.listItems.TasksViewModel;
import com.sampletask.usecases.domain.database.RoomModule;

import com.sampletask.usecases.domain.usecases.InsertTaskUseCase;


import dagger.Component;

@Component(modules = { RoomModule.class})
public interface AppComponent {

    void injectRepo(InsertTaskUseCase useCase);
    void injectUseCase(TasksViewModel viewModel);

    class Initializer {

        private static AppComponent component;

        public static AppComponent buildComponent() {
            if (component == null) {
                component = DaggerAppComponent.builder().build();
            }
            return component;
        }
    }
}
