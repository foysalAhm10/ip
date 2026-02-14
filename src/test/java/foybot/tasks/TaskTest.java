package foybot.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskTest {

    private static class StubTask extends Task {
        StubTask(String description) {
            super(description);
        }
    }

    @Test
    public void markDoneAndUndone_updatesStatus() {
        StubTask task = new StubTask("read book");

        assertFalse(task.isDone());

        task.markDone();
        assertTrue(task.isDone());
        assertEquals("[X] read book", task.toString());

        task.markUndone();
        assertFalse(task.isDone());
        assertEquals("[ ] read book", task.toString());
    }
}
