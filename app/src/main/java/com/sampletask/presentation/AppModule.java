package com.sampletask.presentation;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Application application;

     AppModule(Application mApplication) {
        application = mApplication;
    }

    @Provides
    Application providesApplication() {
        return application;
    }

}
