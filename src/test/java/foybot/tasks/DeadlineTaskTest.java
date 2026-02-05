package foybot.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import foybot.exception.FoyBotException;

public class DeadlineTaskTest {

    @Test
    public void constructor_validDate_success() throws Exception {
        DeadlineTask task = new DeadlineTask("submit report", "2026-02-10");

        assertEquals("2026-02-10", task.getDeadline());
        assertTrue(task.toString().startsWith("[D]"));
        assertTrue(task.toString().contains("Feb 10 2026"));
    }

    @Test
    public void constructor_invalidDate_throwsException() {
        FoyBotException ex = assertThrows(
                FoyBotException.class,
                () -> new DeadlineTask("submit report", "10-02-2026")
        );

        assertEquals(
                "Invalid date format for deadline! Use: yyyy-mm-dd",
                ex.getMessage()
        );
    }

    @Test
    public void toString_correctFormatting() throws Exception {
        DeadlineTask task = new DeadlineTask("exam", "2025-12-01");

        String expected = "[D][ ] exam (by: Dec 01 2025)";
        assertEquals(expected, task.toString());
    }
}
