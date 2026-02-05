package foybot.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTaskTest {

    @Test
    public void toString_correctFormatting() {
        TodoTask todo = new TodoTask("read book");

        String expected = "[T][ ] read book";
        assertEquals(expected, todo.toString());
    }
}
