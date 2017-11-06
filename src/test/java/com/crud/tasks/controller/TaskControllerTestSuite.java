package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TaskControllerTestSuite {
    @InjectMocks
    private TaskController taskController;

    @Mock
    private DbService dbService;

    @Mock
    private TaskMapper taskMapper;

    @Test
    public void testGetTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "New task", "My new task");
        List<TaskDto> taskList = new ArrayList<>();
        taskList.add(taskDto);
        when(taskMapper.mapToTaskDtoList(any())).thenReturn(taskList);
        //When
        List<TaskDto> resultList = taskController.getTasks();
        //Then
        assertEquals(1, resultList.size());
    }

    @Test
    public void testDeleteTask() {
        //Given
        Task task = new Task(1L, "New task", "My new task");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);
        when(dbService.getTask(task.getId())).thenReturn(java.util.Optional.ofNullable(task));
        doNothing().when(dbService).deleteTask(task);
        //When
        try {
            taskController.deleteTask(task.getId());
        } catch (TaskNotFoundException e) {
            e.printStackTrace();
        }
        //Then
        verify(dbService, times(1)).deleteTask(task);
    }

    @Test
    public void testUpdateTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "New task", "My new task");
        TaskDto taskDtoUpdated = new TaskDto(1L, "New task updated", "My new task updated");
        when(taskMapper.mapToTaskDto(any())).thenReturn(taskDtoUpdated);
        //When
        TaskDto result = taskController.updateTask(taskDto);
        //Then
        assertEquals((long) 1L, (long) result.getId());
        assertEquals("New task updated", result.getTitle());
        assertEquals("My new task updated", result.getContent());
    }
}
