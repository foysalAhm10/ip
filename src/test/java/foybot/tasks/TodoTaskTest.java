package foybot.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTaskTest {

    @Test
    public void toString_correctFormatting() {
        TodoTask todo = new TodoTask("read book");

        String expected = "[T][ ] read book";
        assertEquals(expected, todo.toString());
    }
}
