package foybot.instructions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import foybot.io.Ui;
import foybot.tools.TaskList;

public class TodoInstructionTest {

    @Test
    public void execute_addsTaskAndDisplaysConfirmation() {
        TodoInstruction instruction = new TodoInstruction("read book");
        Ui ui = new Ui();
        TaskList list = new TaskList();

        instruction.execute(list, ui);

        assertEquals(1, list.size());
        assertTrue(instruction.isMutating());

        String output = ui.consumeOutput();
        assertTrue(output.contains("Got it. I've added this task:"));
        assertTrue(output.contains(Ui.INDENT + "[T][ ] read book"));
        assertTrue(output.contains("Now you have 1 tasks in the list."));
    }
}
