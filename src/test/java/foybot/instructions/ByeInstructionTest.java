package foybot.instructions;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import foybot.io.Ui;
import foybot.tools.TaskList;

public class ByeInstructionTest {

    @Test
    public void execute_displaysByeAndIsExit() {
        ByeInstruction instruction = new ByeInstruction();
        Ui ui = new Ui();

        instruction.execute(new TaskList(), ui);

        String output = ui.consumeOutput();
        assertTrue(output.contains("Bye. Hope to see you again soon!"));
        assertTrue(instruction.isExit());
    }
}
