package foybot.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import foybot.exception.FoyBotException;

public class EventTaskTest {

    @Test
    public void constructor_validDates_success() throws Exception {
        EventTask task = new EventTask("camp", "2026-02-10", "2026-02-12");

        assertEquals("2026-02-10 - 2026-02-12", task.getFromTo());
        assertTrue(task.toString().startsWith("[E][ ]"));
        assertTrue(task.toString().contains("Feb 10 2026"));
        assertTrue(task.toString().contains("Feb 12 2026"));
    }

    @Test
    public void constructor_invalidDate_throwsException() {
        FoyBotException ex = assertThrows(
                FoyBotException.class, () -> new EventTask("camp", "10-02-2026", "2026-02-12")
        );

        assertEquals(
                "Invalid date format for event time! Use: yyyy-mm-dd",
                ex.getMessage()
        );
    }

    @Test
    public void constructor_startAfterEnd_throwsException() {
        FoyBotException ex = assertThrows(
                FoyBotException.class, () -> new EventTask("camp", "2026-02-13", "2026-02-12")
        );

        assertEquals(
                "Event start date must be before or equal to end date.",
                ex.getMessage()
        );
    }
}
