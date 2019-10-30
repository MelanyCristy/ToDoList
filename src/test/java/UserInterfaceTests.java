import com.company.UserInterface;
import com.company.entities.Task;
import com.company.utilities.IOHandler;
import com.company.utilities.TaskHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserInterfaceTests {
    private UserInterface userInterface;
    private IOHandler ioHandler;
    private TaskHandler taskHandler;

    @BeforeEach
    public void setup() {
        ioHandler = Mockito.mock(IOHandler.class);
        taskHandler = Mockito.mock(TaskHandler.class);
        when(taskHandler.getTasks()).thenReturn(createFakeTasks());

        userInterface = new UserInterface(ioHandler, taskHandler);
    }

    @Test
    public void showMainMenu_prints_out_main_menu() {
        when(ioHandler.getInput()).thenReturn("4");
        userInterface.showMainMenu();

        verify(ioHandler, times(7)).output(anyString());
    }

    @Test
    public void showMainMenu_closes_application_if_4_is_pressed_and_saves_tasks_to_file() {
        when(ioHandler.getInput()).thenReturn("4");
        userInterface.showMainMenu();

        verify(taskHandler, times(1)).saveTasks();
        verify(ioHandler, times(7)).output(anyString());
    }

    @Test
    public void showMainMenu_and_edit_task_edits_task() {
        when(ioHandler.getInput())
                .thenReturn("3")
                .thenReturn("0")
                .thenReturn("U")
                .thenReturn("title")
                .thenReturn("2018-09-09")
                .thenReturn("Project")
                .thenReturn("4");

        when(taskHandler.taskListIsNotEmpty()).thenReturn(true);
        userInterface.showMainMenu();

        verify(taskHandler, times(1)).updateTask(anyInt(), any());
    }

    @Test
    public void showMainMenu_and_add_task_calls_add_task() {
        when(ioHandler.getInput())
                .thenReturn("2")
                .thenReturn("title")
                .thenReturn("2018-09-09")
                .thenReturn("Project")
                .thenReturn("N")
                .thenReturn("4");

        when(taskHandler.taskListIsNotEmpty()).thenReturn(true);
        userInterface.showMainMenu();

        verify(taskHandler, times(1)).addTask(any());
    }

    @Test
    public void showMainMenu_and_show_task_list_shows_task_list() {
        when(ioHandler.getInput())
                .thenReturn("1")
                .thenReturn("C")
                .thenReturn("4");

        when(taskHandler.taskListIsNotEmpty()).thenReturn(true);
        userInterface.showMainMenu();

        verify(taskHandler, times(1)).getTasks();
    }

   @Test
   public void showMainMenus_and_remove_task(){
        when(ioHandler.getInput())
                .thenReturn("3")
                .thenReturn("0")
                .thenReturn("R")
                .thenReturn("4");

       when(taskHandler.taskListIsNotEmpty()).thenReturn(true);
       userInterface.showMainMenu();

       verify(taskHandler, times(1)).removeTask(0);

   }


    private ArrayList<Task> createFakeTasks() {
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(new Task("title", "2018-08-08", "project"));

        return taskList;
    }
}
