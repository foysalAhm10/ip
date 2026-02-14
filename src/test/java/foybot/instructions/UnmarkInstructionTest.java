package foybot.instructions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import foybot.exception.FoyBotException;
import foybot.io.Ui;
import foybot.tasks.TodoTask;
import foybot.tools.TaskList;

public class UnmarkInstructionTest {

    @Test
    public void execute_emptyList_throwsException() {
        UnmarkInstruction instruction = new UnmarkInstruction("1");

        FoyBotException ex = assertThrows(
                FoyBotException.class, () -> instruction.execute(new TaskList(), new Ui())
        );

        assertEquals("There are no tasks to unmark.", ex.getMessage());
    }

    @Test
    public void execute_outOfBounds_throwsException() {
        UnmarkInstruction instruction = new UnmarkInstruction("2");
        TaskList list = new TaskList();
        list.addTask(new TodoTask("read book"));

        FoyBotException ex = assertThrows(
                FoyBotException.class, () -> instruction.execute(list, new Ui())
        );

        assertEquals("Index out of bounds.", ex.getMessage());
    }

    @Test
    public void execute_unmarksTaskAndDisplaysConfirmation() throws Exception {
        UnmarkInstruction instruction = new UnmarkInstruction("1");
        TaskList list = new TaskList();
        TodoTask task = new TodoTask("read book");
        task.markDone();
        list.addTask(task);
        Ui ui = new Ui();

        instruction.execute(list, ui);

        assertTrue(!list.get(0).isDone());
        assertTrue(instruction.isMutating());

        String output = ui.consumeOutput();
        assertTrue(output.contains("OK, I've marked this task as not done yet:"));
        assertTrue(output.contains(Ui.INDENT + "[T][ ] read book"));
    }
}
