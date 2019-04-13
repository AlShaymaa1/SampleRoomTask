package com.sampletask.presentation.feature.listItems;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.sampletask.entities.Task;
import com.sampletask.usecases.domain.usecases.RetrieveTasksUseCase;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TasksViewModel extends ViewModel {


    private RetrieveTasksUseCase useCase;
    private CompositeDisposable disposable;
     MutableLiveData<Boolean> notifyAdapter;
     MutableLiveData<List<Task>> result;


    public TasksViewModel() {
        useCase = new RetrieveTasksUseCase();
        disposable = new CompositeDisposable();
        notifyAdapter=new MutableLiveData<>();
        result=new MutableLiveData<>();

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
                    subscribe(value -> notifyAdapter.postValue(true), error -> notifyAdapter.postValue(false));
            disposable.add(d);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }

}
