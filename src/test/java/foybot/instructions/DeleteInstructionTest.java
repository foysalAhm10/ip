package foybot.instructions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import foybot.exception.FoyBotException;
import foybot.io.Ui;
import foybot.tasks.TodoTask;
import foybot.tools.TaskList;

public class DeleteInstructionTest {

    @Test
    public void execute_emptyList_throwsException() {
        DeleteInstruction instruction = new DeleteInstruction("1");

        FoyBotException ex = assertThrows(
                FoyBotException.class, () -> instruction.execute(new TaskList(), new Ui())
        );

        assertEquals("There are no tasks to delete.", ex.getMessage());
    }

    @Test
    public void execute_outOfBounds_throwsException() {
        DeleteInstruction instruction = new DeleteInstruction("2");
        TaskList list = new TaskList();
        list.addTask(new TodoTask("read book"));

        FoyBotException ex = assertThrows(
                FoyBotException.class, () -> instruction.execute(list, new Ui())
        );

        assertEquals("Index out of bounds.", ex.getMessage());
    }

    @Test
    public void execute_deletesTaskAndDisplaysConfirmation() throws Exception {
        DeleteInstruction instruction = new DeleteInstruction("1");
        TaskList list = new TaskList();
        list.addTask(new TodoTask("read book"));
        Ui ui = new Ui();

        instruction.execute(list, ui);

        assertEquals(0, list.size());
        assertTrue(instruction.isMutating());

        String output = ui.consumeOutput();
        assertTrue(output.contains("Noted. I've removed this task:"));
        assertTrue(output.contains(Ui.INDENT + "[T][ ] read book"));
        assertTrue(output.contains("Now you have 0 tasks in the list."));
    }
}
