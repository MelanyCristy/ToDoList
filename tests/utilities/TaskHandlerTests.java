package utilities;

import com.company.constants.DateFormats;
import com.company.entities.Task;
import com.company.utilities.TaskHandler;
import com.company.utilities.TaskSorter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskHandlerTests {
    private TaskHandler taskHandler;

    @BeforeAll
    public void setup(){
        taskHandler = new TaskHandler();
    }

    @Test
    public void saveAndQuit (){

    }

    @Test
    public void showTaskList_saved(){
    }

    @Test
    public void editTask(){
    }

    @Test
    public void validatedSelectedTask(){
    }

    @Test
    public void updateTask(){
    }

    @Test
    public void markTaskAsDone(){
    }

    @Test
    public void removeTask(){
    }

    @Test
    public void createTaskFromInput(){
    }

    @Test
    public void addTask(){

    }

    private Task addTask(String title, String date, String project){
        return new Task(title, date, project);
    }



    @Test
    public void numberOfCompletedTask(){
    }

    @Test
    public void numberOfTasks(){
    }
}
