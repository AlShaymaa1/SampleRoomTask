package com.sampletask.presentation.feature.home;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.sampletask.entities.Task;
import com.sampletask.usecases.domain.usecases.InsertTaskUseCase;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends ViewModel {


    private InsertTaskUseCase useCase;
    private CompositeDisposable disposable;
    MutableLiveData<Boolean> clearText;

    public HomeViewModel() {
        useCase = new InsertTaskUseCase();
        disposable = new CompositeDisposable();
        clearText = new MutableLiveData<>();
    }

    void insertNewTask(Task text) {
        Disposable d = Single.fromCallable(() -> {
            useCase.insert(text,clearText);
            return true;
        }).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(value -> Log.e("success",value.toString()), error ->Log.e("fail",error.getMessage()) );
        disposable.add(d);
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }

}
