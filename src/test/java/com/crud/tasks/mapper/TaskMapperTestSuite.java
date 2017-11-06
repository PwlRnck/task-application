package com.crud.tasks.mapper;

import com.crud.tasks.config.Profiles;
import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(Profiles.LOCAL)

public class TaskMapperTestSuite {
    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "New task", "My new task");
        //When
        Task task = taskMapper.mapToTask(taskDto);
        //Then
        assertEquals((long)1L, (long)task.getId());
        assertEquals("New task", task.getTitle());
        assertEquals("My new task", task.getContent());
    }

    @Test
    public void testMapToTaskDto() {
        //Given
        Task task = new Task(1L, "New task", "My new task");
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        //Then
        assertEquals((long)1L, (long)taskDto.getId());
        assertEquals("New task", taskDto.getTitle());
        assertEquals("My new task", taskDto.getContent());
    }

    @Test
    public void testMapToTaskDtoList() {
        //Given
        Task task = new Task(1L, "New task", "My new task");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);
        //When
        List<TaskDto> taskListDto = taskMapper.mapToTaskDtoList(taskList);
        //Then
        assertEquals(1,taskListDto.size());
    }
}
