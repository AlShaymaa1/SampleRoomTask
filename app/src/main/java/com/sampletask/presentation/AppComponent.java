package com.sampletask.presentation;


import com.sampletask.usecases.domain.repositories.TaskRepository;


import dagger.Component;

@Component(modules = {AppModule.class})
public interface AppComponent {

    void injectApp(TaskRepository repo);

}
