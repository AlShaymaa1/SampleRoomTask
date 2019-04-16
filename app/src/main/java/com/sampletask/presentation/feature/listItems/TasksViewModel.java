package com.sampletask.presentation.feature.listItems;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.sampletask.entities.Task;
import com.sampletask.usecases.domain.usecases.InsertTaskUseCase;

import java.util.List;


import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TasksViewModel extends ViewModel {

    private InsertTaskUseCase useCase;
    private CompositeDisposable disposable;
    MutableLiveData<List<Task>> result;

     public TasksViewModel() {
        useCase = new InsertTaskUseCase();
        result = new MutableLiveData<>();
        disposable = new CompositeDisposable();
    }



    void queryAllData() {
        Disposable d = Single.fromCallable(() -> {useCase.retrieveTasks(result);return result;}).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(value -> Log.e("successShowList",String.valueOf(value.getValue().size())), error -> Log.e("error", error.getMessage()));
        disposable.add(d);
    }

    void updateTasks(Task text) {
        Disposable d = Single.fromCallable(() -> {
            useCase.updateTask(text);
            return true;
        }).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(value -> Log.e("success", value.toString()), error -> Log.e("success", error.getMessage()));
        disposable.add(d);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }

}
