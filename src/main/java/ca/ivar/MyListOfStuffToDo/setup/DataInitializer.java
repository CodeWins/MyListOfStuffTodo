package ca.ivar.MyListOfStuffToDo.setup;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ca.ivar.MyListOfStuffToDo.model.Task;
import ca.ivar.MyListOfStuffToDo.repository.TaskRepository;

import java.time.LocalDate;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(TaskRepository repository) {
        return args -> {
            repository.save(new Task("Task 1", "Description for Task 1", LocalDate.now(), false));
            repository.save(new Task("Task 2", "Description for Task 2", LocalDate.now().plusDays(1), true));
            // Add more tasks as needed
        };
    }
}
