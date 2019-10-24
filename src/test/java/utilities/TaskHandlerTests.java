package utilities;

import com.company.entities.Task;
import com.company.enums.Status;
import com.company.utilities.FileHelper;
import com.company.utilities.TaskHandler;
import com.company.utilities.TaskSorter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class TaskHandlerTests {
    private TaskHandler taskHandler;
    private FileHelper fileHelper;
    private TaskSorter taskSorter;
    private ArrayList<Task> tasks;

    @BeforeEach
    public void setup(){
        fileHelper =  Mockito.mock(FileHelper.class);
        taskSorter = Mockito.mock(TaskSorter.class);
        tasks = new ArrayList<>();
        Task task1 = new Task("hola1","1999-09-09","Holix1");
        Task task2 = new Task("hola2","1998-09-09","Holix2");
        Task task3 = new Task("hola3","1997-09-09","Holix3");
        task2.setStatus(Status.Finished);

        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        taskHandler = new TaskHandler(fileHelper,taskSorter, tasks);
    }

    @Test
    void numberOfCompletedTask(){
       int result =  taskHandler.numberOfCompletedTasks();
       assertEquals(1, result);
    }

}
