package foybot.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import foybot.tasks.TodoTask;
import foybot.tools.TaskList;

public class UiTest {

    @Test
    public void showWelcomeAndConsume_clearsBuffer() {
        Ui ui = new Ui();
        ui.showWelcome();

        String output = ui.consumeOutput();
        assertTrue(output.contains("Hello! I'm FoyBot!"));
        assertTrue(output.contains("What can I do for you?"));

        assertEquals("", ui.consumeOutput());
    }

    @Test
    public void showBye_displaysFarewell() {
        Ui ui = new Ui();
        ui.showBye();

        String output = ui.consumeOutput();
        assertTrue(output.contains("Bye. Hope to see you again soon!"));
    }

    @Test
    public void showList_displaysIndexedTasks() {
        Ui ui = new Ui();
        TaskList list = new TaskList();
        list.addTask(new TodoTask("read book"));
        list.addTask(new TodoTask("write tests"));

        ui.showList(list);
        String output = ui.consumeOutput();

        assertTrue(output.contains(Ui.INDENT + "1. [T][ ] read book"));
        assertTrue(output.contains(Ui.INDENT + "2. [T][ ] write tests"));
    }

    @Test
    public void showErrorAndMessage_appendsLines() {
        Ui ui = new Ui();
        ui.showError("Error!");
        ui.showMessage("Hello");

        String output = ui.consumeOutput();
        assertTrue(output.contains("Error!"));
        assertTrue(output.contains("Hello"));
    }
}
