import com.company.TaskHandler;
import org.junit.jupiter.api.Test;

public class TaskHandlerTests {
    private TaskHandler taskHandler = new TaskHandler();

    @Test
    public void test (){
        var task = taskHandler.getTaskById(1);
    }
}
