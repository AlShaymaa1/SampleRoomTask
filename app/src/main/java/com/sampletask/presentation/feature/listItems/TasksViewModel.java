package com.sampletask.presentation.feature.listItems;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.sampletask.entities.Task;
import com.sampletask.presentation.DaggerAppComponent;
import com.sampletask.usecases.domain.usecases.InsertTaskUseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TasksViewModel extends ViewModel {


    @Inject
    InsertTaskUseCase useCase;
    private CompositeDisposable disposable;
     MutableLiveData<List<Task>> result;


    public TasksViewModel() {
        DaggerAppComponent.Initializer.buildComponent().injectUseCase(this);
        result=new MutableLiveData<>();
        disposable=new CompositeDisposable();
    }


    void queryAllData() {
        Disposable d = Single.fromCallable(() -> useCase.retrieveData()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(value -> result.postValue(value), error -> Log.e("error", error.getMessage()));
        disposable.add(d);
    }

    void updateTasks(Task text) {
            Disposable d = Single.fromCallable(()->{useCase.updateTask(text);
                return true;}).
                    subscribeOn(Schedulers.io()).
                    observeOn(AndroidSchedulers.mainThread()).
                    subscribe(value ->Log.e("success",value.toString()), error -> Log.e("success",error.getMessage()));
            disposable.add(d);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }

}
