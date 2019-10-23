import com.company.UserInterface;
import com.company.entities.Task;
import com.company.utilities.FileHelper;
import com.company.utilities.IOHandler;
import com.company.utilities.TaskSorter;
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
    private FileHelper fileHelper;
    private TaskSorter taskSorter;
    private IOHandler ioHandler;

    @BeforeEach
    public void setup(){
        fileHelper = Mockito.mock(FileHelper.class);
        taskSorter = Mockito.mock(TaskSorter.class);
        ioHandler = Mockito.mock(IOHandler.class);

        when(fileHelper.getTasksFromFile()).thenReturn(createFakeTasks());

        userInterface = new UserInterface(fileHelper, taskSorter, ioHandler);
    }

    @Test
    public void test(){
        when(ioHandler.getInput()).thenReturn("5");
        userInterface.showMainMenu();

        verify(ioHandler, times(7)).output(anyString());
    }

    private ArrayList<Task> createFakeTasks(){
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(new Task("title", "2018-08-08", "project"));

        return taskList;
    }
}
