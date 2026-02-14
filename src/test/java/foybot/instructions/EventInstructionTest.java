package foybot.instructions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import foybot.exception.FoyBotException;
import foybot.io.Ui;
import foybot.tools.TaskList;

public class EventInstructionTest {

    @Test
    public void constructor_missingFrom_throwsException() {
        FoyBotException ex = assertThrows(
                FoyBotException.class, () -> new EventInstruction("meeting /to 2026-02-10")
        );

        assertEquals(
                "Invalid deadline task format! Use: event <description> /from <start> /to <end>",
                ex.getMessage()
        );
    }

    @Test
    public void constructor_missingTo_throwsException() {
        FoyBotException ex = assertThrows(
                FoyBotException.class, () -> new EventInstruction("meeting /from 2026-02-10")
        );

        assertEquals(
                "Invalid deadline task format! Use: event <description> /from <start> /to <end>",
                ex.getMessage()
        );
    }

    @Test
    public void constructor_missingDescription_throwsException() {
        FoyBotException ex = assertThrows(
                FoyBotException.class, () -> new EventInstruction(" /from 2026-02-10 /to 2026-02-12")
        );

        assertEquals("Event description cannot be empty.", ex.getMessage());
    }

    @Test
    public void execute_addsTaskAndDisplaysConfirmation() throws Exception {
        EventInstruction instruction = new EventInstruction("camp /from 2026-02-10 /to 2026-02-12");
        Ui ui = new Ui();
        TaskList list = new TaskList();

        instruction.execute(list, ui);

        assertEquals(1, list.size());
        assertTrue(instruction.isMutating());

        String output = ui.consumeOutput();
        assertTrue(output.contains("Got it. I've added this task:"));
        assertTrue(output.contains(Ui.INDENT + "[E][ ] camp (from: Feb 10 2026 to: Feb 12 2026)"));
        assertTrue(output.contains("Now you have 1 tasks in the list."));
    }
}
