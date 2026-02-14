package foybot.instructions;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import foybot.io.Ui;
import foybot.tools.TaskList;

public class HelpInstructionTest {

    @Test
    public void execute_displaysHelpSummary() {
        HelpInstruction instruction = new HelpInstruction();
        Ui ui = new Ui();

        instruction.execute(new TaskList(), ui);

        String output = ui.consumeOutput();
        assertTrue(output.contains("Supported instructions:"));
        assertTrue(output.contains("1. list"));
        assertTrue(output.contains("10. bye"));
    }
}
