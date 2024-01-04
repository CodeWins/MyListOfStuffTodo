package ca.ivar.MyListOfStuffToDo.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import ca.ivar.MyListOfStuffToDo.model.Task;
import ca.ivar.MyListOfStuffToDo.services.TaskService;
import jakarta.annotation.security.PermitAll;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import java.util.List;

@Route(value = "")
@PermitAll
public class MainView extends VerticalLayout {

    private final TaskService taskService; // Service to interact with tasks

    // Constructor
    public MainView(TaskService taskService) {
        this.taskService = taskService;

        // Setting up the UI components
        setupUI();
    }

    private void setupUI() {
        // Title
        H1 title = new H1("Task Tracker");
        // Grid to display tasks
        Grid<Task> taskGrid = new Grid<>(Task.class);
        // Button to add a new task
        Button addTaskButton = new Button("Add New Task", event -> {
            showAddTaskDialog(taskGrid);
        });


        taskGrid.setColumns("id", "title", "description", "dueDate"); // Adjust columns as per your Task class
        taskGrid.addComponentColumn(task -> {
            Checkbox completedCheckbox = new Checkbox();
            completedCheckbox.setValue(task.isCompleted());
            completedCheckbox.addValueChangeListener(event -> {
                task.setCompleted(event.getValue());
                taskService.save(task); // Update the task status
                updateTaskGrid(taskGrid); // Optionally refresh the grid
            });
            return completedCheckbox;
        }).setHeader("Completed");
        updateTaskGrid(taskGrid);

        // Adding components to the layout
        add(title, addTaskButton, taskGrid);
       // add(title, addTaskButton);
    }

    private void updateTaskGrid(Grid<Task> taskGrid) {
        // Fetch tasks and update the grid
        List<Task> tasks = taskService.findAll();
        taskGrid.setItems(tasks);
    }

    private void showAddTaskDialog(Grid<Task> taskGrid) {
        Dialog dialog = new Dialog();
        dialog.setModal(true);

        TextField titleField = new TextField("Title");
        TextField descriptionField = new TextField("Description");
        DatePicker dueDateField = new DatePicker("Due Date");
        Checkbox completedCheckbox = new Checkbox("Completed");

        Button saveButton = new Button("Save", event -> {
            Task newTask = new Task();
            newTask.setTitle(titleField.getValue());
            newTask.setDescription(descriptionField.getValue());
            newTask.setDueDate(dueDateField.getValue());
            newTask.setCompleted(completedCheckbox.getValue());

            taskService.save(newTask); // Assuming you have a save method in your TaskService
            updateTaskGrid(taskGrid);
            dialog.close();
        });

        Button cancelButton = new Button("Cancel", event -> dialog.close());
        HorizontalLayout buttonsLayout = new HorizontalLayout(saveButton, cancelButton);

        dialog.add(titleField, descriptionField, dueDateField, completedCheckbox, buttonsLayout);
        dialog.open();
    }

}