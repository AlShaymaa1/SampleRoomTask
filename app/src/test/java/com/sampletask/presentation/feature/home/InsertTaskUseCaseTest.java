package com.sampletask.presentation.feature.home;

import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.runner.AndroidJUnit4;

import com.sampletask.entities.Task;
import com.sampletask.usecases.domain.database.TaskDao;
import com.sampletask.usecases.domain.database.TaskRoomDataBase;
import com.sampletask.usecases.domain.repositories.TaskRepository;
import com.sampletask.usecases.domain.usecases.InsertTaskUseCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.robolectric.annotation.Config;

import java.io.IOException;

import io.reactivex.disposables.CompositeDisposable;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@Config(manifest = Config.NONE)
@RunWith(AndroidJUnit4.class)
public class InsertTaskUseCaseTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    TaskRepository repository;


    @Test
    public void insertNewTaskWhenTextIsNotEmptyThenClearTextLiveDataValueIsTrue() {

        InsertTaskUseCase useCase=new InsertTaskUseCase(repository);
        MutableLiveData<Boolean> clearText=new MutableLiveData<>();
        Task task=new Task();

        task.setText("hello");
        useCase.insert(task,clearText);

        assertTrue(clearText.getValue());
    }

    @Test
    public void insertNewTaskWhenTextIsEmptyThenClearTextLiveDataValueIsFalse() {


        MutableLiveData<Boolean> clearText=new MutableLiveData<>();
        InsertTaskUseCase useCase=new InsertTaskUseCase(repository);
        Task task=new Task();


        task.setText("");
        useCase.insert(task,clearText);

        assertTrue(!clearText.getValue());
    }



}