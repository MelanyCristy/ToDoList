package com.company.utilities;

import com.company.constants.RegularExpressions;
import com.company.entities.Task;

import java.util.ArrayList;

public class TaskValidator {
    public static boolean indexIsIntegerAndWithinBoundsOfArray(String selectedIndex, int taskListSize) {
        if (!selectedIndex.matches(RegularExpressions.RegexStringIsOfTypeInteger)) return false;

        int selectedIndexAsInt = Integer.parseInt(selectedIndex);

        return selectedIndexAsInt >= 0 && selectedIndexAsInt <= taskListSize;
    }

    public static boolean taskListIsNotEmpty(ArrayList<Task> taskList){
        return taskList != null && !taskList.isEmpty();
    }
}
