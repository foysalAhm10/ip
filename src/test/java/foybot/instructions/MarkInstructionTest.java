package foybot.instructions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import foybot.exception.FoyBotException;
import foybot.io.Ui;
import foybot.tasks.TodoTask;
import foybot.tools.TaskList;

public class MarkInstructionTest {

    @Test
    public void execute_emptyList_throwsException() {
        MarkInstruction instruction = new MarkInstruction("1");

        FoyBotException ex = assertThrows(
                FoyBotException.class, () -> instruction.execute(new TaskList(), new Ui())
        );

        assertEquals("There are no tasks to mark.", ex.getMessage());
    }

    @Test
    public void execute_outOfBounds_throwsException() {
        MarkInstruction instruction = new MarkInstruction("2");
        TaskList list = new TaskList();
        list.addTask(new TodoTask("read book"));

        FoyBotException ex = assertThrows(
                FoyBotException.class, () -> instruction.execute(list, new Ui())
        );

        assertEquals("Index out of bounds.", ex.getMessage());
    }

    @Test
    public void execute_marksTaskAndDisplaysConfirmation() throws Exception {
        MarkInstruction instruction = new MarkInstruction("1");
        TaskList list = new TaskList();
        list.addTask(new TodoTask("read book"));
        Ui ui = new Ui();

        instruction.execute(list, ui);

        assertTrue(list.get(0).isDone());
        assertTrue(instruction.isMutating());

        String output = ui.consumeOutput();
        assertTrue(output.contains("Nice! I've marked this task as done:"));
        assertTrue(output.contains(Ui.INDENT + "[T][X] read book"));
    }
}
