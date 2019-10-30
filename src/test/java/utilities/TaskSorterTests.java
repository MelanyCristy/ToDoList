package utilities;

import com.company.constants.DateFormats;
import com.company.entities.Task;
import com.company.utilities.TaskSorter;
import org.junit.jupiter.api.*;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskSorterTests {
    private TaskSorter taskSorter;

    @BeforeEach
    void setup(){
        taskSorter = new TaskSorter();
    }

    @Test
    void sortByProject_sorts_list_alphabetically_on_the_project_property(){
        // Arrange
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(createTask("Title1", "2018-08-07", "AAA"));
        taskList.add(createTask("Title3", "2018-08-07", "CCC"));
        taskList.add(createTask("Title2", "2018-08-07", "BBB"));
        taskList.add(createTask("Title4", "2018-08-07", "DDD"));

        // Act
        ArrayList<Task> actualResult = taskSorter.sortByProject(taskList);

        // Assert
        Assertions.assertEquals("AAA", actualResult.get(0).getProject());
        Assertions.assertEquals("BBB", actualResult.get(1).getProject());
        Assertions.assertEquals("CCC", actualResult.get(2).getProject());
        Assertions.assertEquals("DDD", actualResult.get(3).getProject());
    }

    @Test
    void sortByDueDate_sort_list_by_due_date(){
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(createTask("Title1", "2018-08-07", "AAA"));
        taskList.add(createTask("Title3", "2018-09-07", "CCC"));
        taskList.add(createTask("Title2", "2018-02-09", "BBB"));
        taskList.add(createTask("Title4", "2018-08-08", "DDD"));

        // Act
        ArrayList<Task> actualResult = taskSorter.sortByDueDate(taskList);

        // Assert
        Assertions.assertEquals("2018-02-09", actualResult.get(0).getDueDate().format(DateTimeFormatter.ofPattern(DateFormats.DateShortFormat)));
        Assertions.assertEquals("2018-08-07", actualResult.get(1).getDueDate().format(DateTimeFormatter.ofPattern(DateFormats.DateShortFormat)));
        Assertions.assertEquals("2018-08-08", actualResult.get(2).getDueDate().format(DateTimeFormatter.ofPattern(DateFormats.DateShortFormat)));
        Assertions.assertEquals("2018-09-07", actualResult.get(3).getDueDate().format(DateTimeFormatter.ofPattern(DateFormats.DateShortFormat)));

    }

    private Task createTask(String title, String date, String project){
        return new Task(title, date, project);
    }

}
