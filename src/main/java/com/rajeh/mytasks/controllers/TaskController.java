package com.rajeh.mytasks.controllers;

import com.rajeh.mytasks.entities.Task;
import com.rajeh.mytasks.services.TaskService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController()
@RequestMapping("tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @GetMapping
    public ResponseEntity findAllTask() {
        return ResponseEntity.ok(taskService.findAllTask());
    }


    @PostMapping
    public ResponseEntity createTask(@RequestBody Task task) throws URISyntaxException {
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.created(new URI("/tasks/" + createdTask.getId())).body(createdTask);
    }

    @PutMapping()
    public ResponseEntity updateTaskById(@RequestBody Task task) {

        Optional reqTask = taskService.findAllTask()
                .stream()
                .filter(taskTemp -> taskTemp.getId() == task.getId())
                .findFirst();

        if (reqTask.isPresent()) {
            Task updatedTask = (Task) reqTask.get();

            updatedTask.setTitle(task.getTitle());
            updatedTask.setDescription(task.getDescription());
            updatedTask.setStatus(task.getStatus());

            taskService.updateTask(updatedTask);
            return ResponseEntity.ok(updatedTask);

        } else {
            return ResponseEntity.notFound().build();
        }



    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable int id) {
        Optional reqTask = taskService.findAllTask()
                .stream()
                .filter(task -> task.getId() == id)
                .findAny();

        if (reqTask.isPresent()) {
            taskService.deleteTask((Task) reqTask.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping(value = "how-many-tasks")
    public ResponseEntity countTasks() {
        long numberOfTasks = taskService.countTask();
        return ResponseEntity.ok(numberOfTasks);
    }
}
