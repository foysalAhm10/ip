package foybot.instructions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import foybot.exception.FoyBotException;
import foybot.io.Ui;
import foybot.tools.TaskList;

public class DeadlineInstructionTest {

    @Test
    public void constructor_missingBy_throwsException() {
        FoyBotException ex = assertThrows(
                FoyBotException.class, () -> new DeadlineInstruction("read book 2026-02-10")
        );

        assertEquals(
                "Invalid deadline task format! Use: deadline <description> /by <date>",
                ex.getMessage()
        );
    }

    @Test
    public void constructor_missingDescription_throwsException() {
        FoyBotException ex = assertThrows(
                FoyBotException.class, () -> new DeadlineInstruction(" /by 2026-02-10")
        );

        assertEquals("Description or deadline missing!", ex.getMessage());
    }

    @Test
    public void execute_addsTaskAndDisplaysConfirmation() throws Exception {
        DeadlineInstruction instruction = new DeadlineInstruction("submit report /by 2026-02-10");
        Ui ui = new Ui();
        TaskList list = new TaskList();

        instruction.execute(list, ui);

        assertEquals(1, list.size());
        assertTrue(instruction.isMutating());

        String output = ui.consumeOutput();
        assertTrue(output.contains("Got it. I've added this task:"));
        assertTrue(output.contains(Ui.INDENT + "[D][ ] submit report (by: Feb 10 2026)"));
        assertTrue(output.contains("Now you have 1 tasks in the list."));
    }
}
