package foybot.instructions;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import foybot.io.Ui;
import foybot.tasks.TodoTask;
import foybot.tools.TaskList;

public class FindInstructionTest {

    @Test
    public void execute_noMatches_displaysEmptyMessage() throws Exception {
        FindInstruction instruction = new FindInstruction("read");
        Ui ui = new Ui();
        TaskList list = new TaskList();
        list.addTask(new TodoTask("write tests"));

        instruction.execute(list, ui);

        String output = ui.consumeOutput();
        assertTrue(output.contains("No matching tasks found."));
    }

    @Test
    public void execute_matches_displaysList() throws Exception {
        FindInstruction instruction = new FindInstruction("read");
        Ui ui = new Ui();
        TaskList list = new TaskList();
        list.addTask(new TodoTask("read book"));
        list.addTask(new TodoTask("write tests"));

        instruction.execute(list, ui);

        String output = ui.consumeOutput();
        assertTrue(output.contains("Here are the matching tasks in your list:"));
        assertTrue(output.contains(Ui.INDENT + "1. [T][ ] read book"));
    }
}
