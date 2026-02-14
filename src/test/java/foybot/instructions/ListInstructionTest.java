package foybot.instructions;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import foybot.io.Ui;
import foybot.tasks.TodoTask;
import foybot.tools.TaskList;

public class ListInstructionTest {

    @Test
    public void execute_emptyList_displaysMessage() {
        ListInstruction instruction = new ListInstruction();
        Ui ui = new Ui();

        instruction.execute(new TaskList(), ui);

        String output = ui.consumeOutput();
        assertTrue(output.contains("No tasks in the list currently."));
    }

    @Test
    public void execute_nonEmptyList_displaysTasks() {
        ListInstruction instruction = new ListInstruction();
        Ui ui = new Ui();
        TaskList list = new TaskList();
        list.addTask(new TodoTask("read book"));

        instruction.execute(list, ui);

        String output = ui.consumeOutput();
        assertTrue(output.contains("Here are the tasks in your list:"));
        assertTrue(output.contains(Ui.INDENT + "1. [T][ ] read book"));
    }
}
