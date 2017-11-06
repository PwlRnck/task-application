package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.stubbing.BaseStubbing;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTestSuite {
    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository taskRepository;

    @Test
    public void testGetAllTasks() {
        //Given
        Task task = new Task(1L, "New task", "My new task");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);
        when(taskRepository.findAll()).thenReturn(taskList);
        //When
        List<Task> resultList = dbService.getAllTasks();
        //Then
        assertEquals(1,resultList.size());
    }

    @Test
    public void testGetTask() {
        //Given
        Task task = new Task(1L, "New task", "My new task");
        when(taskRepository.findById(anyLong())).thenReturn(Optional.ofNullable(task));
        //When
        Task resultTask = dbService.getTask(1L).orElse(null);
        //Then
        assertEquals((long)1L, (long) resultTask.getId());
        assertEquals("New task", resultTask.getTitle());
        assertEquals("My new task", resultTask.getContent());
    }

    @Test
    public void testSaveTask() {
        //Given
        Task task = new Task(1L, "New task", "My new task");
        when(taskRepository.save(task)).thenReturn(task);
        //When
        Task resultTask = dbService.saveTask(task);
        //Then
        assertEquals((long) 1L, (long) resultTask.getId());
        assertEquals("New task", resultTask.getTitle());
        assertEquals("My new task", resultTask.getContent());
    }

    @Test
    public void testDeleteTask() {
        //Given
        Task task = new Task(1L, "New task", "My new task");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);
        doNothing().when(taskRepository).delete(task);
        //When
        dbService.deleteTask(task);
        //Then
        verify(taskRepository,times(1)).delete(task);
    }
}
