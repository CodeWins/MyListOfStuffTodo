package ca.ivar.MyListOfStuffToDo.services;

import ca.ivar.MyListOfStuffToDo.model.Task;
import ca.ivar.MyListOfStuffToDo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAll() {

        List<Task> all = taskRepository.findAll();
        return all;
    }
    public void save(Task task) {
        taskRepository.save(task);
    }
    // Additional methods for CRUD operations
    // e.g., addTask, deleteTask, updateTask, etc.
}