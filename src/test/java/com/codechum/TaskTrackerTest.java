package com.codechum;

import com.codechum.awt.list.TaskTracker;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;

import static org.testng.Assert.*;

import java.awt.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class TaskTrackerTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    
    List toDoList, finishedList;
    TextField nameTextField;
    Checkbox priorityCheckButton;
    Button addButton, removeButton, clearButton, doneButton;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(TaskTracker.class).start();
    }
    
    // Description: Should have all components `toDoList`, `finishedList`, `nameTextField`, `priorityCheckButton`, `addButton`, `removeButton`, `clearButton`, and `doneButton`.
    @Test
    public void shouldHaveAllComponents() {
        robot().waitForIdle(); 

        toDoList = (List) TestUtils.findComponent("toDoList", true);
        finishedList = (List) TestUtils.findComponent("finishedList", true);
        nameTextField = (TextField) TestUtils.findComponent("nameTextField", true);
        priorityCheckButton = (Checkbox) TestUtils.findComponent("priorityCheckButton", true);
        addButton = (Button) TestUtils.findComponent("addButton", true);
        removeButton = (Button) TestUtils.findComponent("removeButton", true);
        clearButton = (Button) TestUtils.findComponent("clearButton", true);
        doneButton = (Button) TestUtils.findComponent("doneButton", true);

        assertNotNull(toDoList, "No toDoList found.");
        assertNotNull(finishedList, "No finishedList found.");
        assertNotNull(nameTextField, "No nameTextField found.");
        assertNotNull(priorityCheckButton, "No priorityCheckButton found.");
        assertNotNull(addButton, "No addButton found.");
        assertNotNull(removeButton, "No removeButton found.");
        assertNotNull(clearButton, "No clearButton found.");
        assertNotNull(doneButton, "No doneButton found.");
    }

    // Description: Should add task entered in `nameTextField` to `toDoList` when `addButton` is clicked.
    @Test
    public void shouldAddTaskToTodoList(){
        robot().waitForIdle(); 

        toDoList = (List) TestUtils.findComponent("toDoList", true);
        nameTextField = (TextField) TestUtils.findComponent("nameTextField", true);
        priorityCheckButton = (Checkbox) TestUtils.findComponent("priorityCheckButton", true);
        addButton = (Button) TestUtils.findComponent("addButton", true);

        robot().click(nameTextField);
        robot().enterText("Task 1");
        robot().click(addButton);

        assertEquals(toDoList.getItemCount(), 1);
    }

    // Description: Should transfer selected task from `toDoList` to `finishedList` when `doneButton` is clicked.
    @Test
    public void shouldTransferTaskToFinishedList(){
        robot().waitForIdle(); 

        toDoList = (List) TestUtils.findComponent("toDoList", true);
        finishedList = (List) TestUtils.findComponent("finishedList", true);
        nameTextField = (TextField) TestUtils.findComponent("nameTextField", true);
        priorityCheckButton = (Checkbox) TestUtils.findComponent("priorityCheckButton", true);
        addButton = (Button) TestUtils.findComponent("addButton", true);
        doneButton = (Button) TestUtils.findComponent("doneButton", true);

        robot().click(nameTextField);
        robot().enterText("Task 1");
        robot().click(addButton);
        robot().click(toDoList);
        toDoList.select(0);
        robot().click(doneButton);

        assertEquals(toDoList.getItemCount(), 0);
        assertEquals(finishedList.getItemCount(), 1);
    }

    // Description: Should remove selected task from `toDoList` when `removeButton` is clicked.
    @Test
    public void shouldRemoveTaskFromTodoList(){
        robot().waitForIdle(); 

        toDoList = (List) TestUtils.findComponent("toDoList", true);
        finishedList = (List) TestUtils.findComponent("finishedList", true);
        nameTextField = (TextField) TestUtils.findComponent("nameTextField", true);
        priorityCheckButton = (Checkbox) TestUtils.findComponent("priorityCheckButton", true);
        addButton = (Button) TestUtils.findComponent("addButton", true);
        removeButton = (Button) TestUtils.findComponent("removeButton", true);

        robot().click(nameTextField);
        robot().enterText("Task 1");
        robot().click(addButton);
        robot().click(toDoList);
        toDoList.select(0);
        robot().click(removeButton);

        assertEquals(toDoList.getItemCount(), 0);
    }

    // Description: Should remove selected task from `finishedList` when `removeButton` is clicked.
    @Test
    public void shouldRemoveTaskFromFinishedList(){
        robot().waitForIdle(); 

        toDoList = (List) TestUtils.findComponent("toDoList", true);
        finishedList = (List) TestUtils.findComponent("finishedList", true);
        nameTextField = (TextField) TestUtils.findComponent("nameTextField", true);
        priorityCheckButton = (Checkbox) TestUtils.findComponent("priorityCheckButton", true);
        addButton = (Button) TestUtils.findComponent("addButton", true);
        doneButton = (Button) TestUtils.findComponent("doneButton", true);
        removeButton = (Button) TestUtils.findComponent("removeButton", true);

        robot().click(nameTextField);
        robot().enterText("Task 1");
        robot().click(addButton);
        robot().click(toDoList);
        toDoList.select(0);
        robot().click(doneButton);
        robot().click(finishedList);
        finishedList.select(0);
        robot().click(removeButton);

        assertEquals(finishedList.getItemCount(), 0);
    }

    // Description: Should clear `toDoList` and ` when `clearButton` is clicked.
    @Test
    public void shouldClearTodoList(){
        robot().waitForIdle(); 

        toDoList = (List) TestUtils.findComponent("toDoList", true);
        finishedList = (List) TestUtils.findComponent("finishedList", true);
        nameTextField = (TextField) TestUtils.findComponent("nameTextField", true);
        priorityCheckButton = (Checkbox) TestUtils.findComponent("priorityCheckButton", true);
        addButton = (Button) TestUtils.findComponent("addButton", true);
        clearButton = (Button) TestUtils.findComponent("clearButton", true);

        robot().click(nameTextField);
        robot().enterText("Task 1");
        robot().click(addButton);
        robot().click(nameTextField);
        robot().enterText("Task 2");
        robot().click(addButton);
        robot().click(nameTextField);
        robot().enterText("Task 3");
        robot().click(addButton);
        robot().click(toDoList);
        toDoList.select(0);
        robot().click(clearButton);

        assertEquals(toDoList.getItemCount(), 0);
    }

    // Description: Should clear `finishedList` when `clearButton` is clicked.
    @Test
    public void shouldClearFinishedList(){
        robot().waitForIdle(); 

        toDoList = (List) TestUtils.findComponent("toDoList", true);
        finishedList = (List) TestUtils.findComponent("finishedList", true);
        nameTextField = (TextField) TestUtils.findComponent("nameTextField", true);
        priorityCheckButton = (Checkbox) TestUtils.findComponent("priorityCheckButton", true);
        addButton = (Button) TestUtils.findComponent("addButton", true);
        doneButton = (Button) TestUtils.findComponent("doneButton", true);
        clearButton = (Button) TestUtils.findComponent("clearButton", true);

        robot().click(nameTextField);
        robot().enterText("Task 1");
        robot().click(addButton);
        robot().click(toDoList);
        toDoList.select(0);
        robot().click(doneButton);
        robot().click(nameTextField);
        robot().enterText("Task 2");
        robot().click(addButton);
        toDoList.select(0);
        robot().click(doneButton);
        robot().click(finishedList);
        finishedList.select(0);
        robot().click(clearButton);

        assertEquals(finishedList.getItemCount(), 0);
    }
    
}

