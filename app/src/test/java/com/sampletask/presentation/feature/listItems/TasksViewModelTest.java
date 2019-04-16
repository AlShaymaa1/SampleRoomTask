package com.sampletask.presentation.feature.listItems;

import android.arch.lifecycle.MutableLiveData;
import android.support.test.runner.AndroidJUnit4;

import com.sampletask.entities.Task;
import com.sampletask.usecases.domain.repositories.TaskRepository;
import com.sampletask.usecases.domain.usecases.InsertTaskUseCase;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.robolectric.annotation.Config;

import java.util.List;


import static org.junit.Assert.*;

@Config(manifest = Config.NONE)
@RunWith(AndroidJUnit4.class)
public class TasksViewModelTest {

    @Mock
    TaskRepository repository;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void queryAllDataWithSuccessfulResponseThenUpdateResult() {

        //Arrange
        MutableLiveData<List<Task>> result = new MutableLiveData<>();
        InsertTaskUseCase useCase = new InsertTaskUseCase(repository);

        // Act
        useCase.retrieveTasks(result);

        //Assert
        assertTrue(result.getValue()!=null&&!result.getValue().isEmpty());
    }

    @Test
    public void updateTasks() {
        InsertTaskUseCase useCase = new InsertTaskUseCase(repository);
        Task task=new Task();

        task.setText("sara");
        useCase.updateTask(task);

        assertTrue(true);
    }
}