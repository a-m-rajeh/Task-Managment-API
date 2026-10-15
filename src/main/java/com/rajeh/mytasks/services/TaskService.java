package com.rajeh.mytasks.services;

import com.rajeh.mytasks.entities.Task;
import com.rajeh.mytasks.repos.TaskRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService{

    private final TaskRepo taskRepo;

    public TaskService(TaskRepo taskRepo)
    {
        this.taskRepo = taskRepo;
    }

    public List<Task> findAllTask()
    {
        return taskRepo.findAll();
    }

    public Task createTask(Task task)
    {
        taskRepo.save(task);
        return task;
    }


    public Task updateTask(Task task)
    {
        taskRepo.save(task);
        return task;
    }


    public void deleteTask(Task task)
    {
        taskRepo.delete(task);
    }


    public long countTask()
    {
        return taskRepo.count();
    }


}
