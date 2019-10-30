package utilities;

import com.company.entities.Task;
import com.company.utilities.TaskValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class TaskValidatorTests {

    @Test
    void taskListIsNotEmpty_returns_false_if_taskList_provided_is_empty(){
        ArrayList<Task> taskList = new ArrayList<>();

        boolean isNotEmpty = TaskValidator.taskListIsNotEmpty(taskList);

        Assertions.assertFalse(isNotEmpty);
    }

    @Test
    void taskListIsNotEmpty_returns_false_if_taskList_provided_is_null(){
        ArrayList<Task> taskList = null;

        boolean isNotEmpty = TaskValidator.taskListIsNotEmpty(taskList);

        Assertions.assertFalse(isNotEmpty);
    }

    @Test
    void taskListIsNotEmpty_returns_true_if_taskList_provided_is_not_empty(){
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(new Task("title", "2018-09-08", "project"));

        boolean isNotEmpty = TaskValidator.taskListIsNotEmpty(taskList);

        Assertions.assertTrue(isNotEmpty);
    }

    @Test
    void indexIsIntegerAndWithinBoundsOfArray_returns_true_if_index_is_integer_and_within_bounds_of_list(){
        String selectedIndex = "3";
        int sizeOfList = 5;

        boolean result = TaskValidator.indexIsIntegerAndWithinBoundsOfArray(selectedIndex, sizeOfList);

        Assertions.assertTrue(result);
    }

    @Test
    void indexIsIntegerAndWithinBoundsOfArray_returns_false_if_index_is_integer_out_of_bounds_of_list(){
        String selectedIndex = "6";
        int sizeOfList = 5;

        boolean result = TaskValidator.indexIsIntegerAndWithinBoundsOfArray(selectedIndex, sizeOfList);

        Assertions.assertFalse(result);
    }

    @Test
    void indexIsIntegerAndWithinBoundsOfArray_returns_false_if_index_is_not_an_integer(){
        String selectedIndex = "K";
        int sizeOfList = 5;

        boolean result = TaskValidator.indexIsIntegerAndWithinBoundsOfArray(selectedIndex, sizeOfList);

        Assertions.assertFalse(result);
    }
}