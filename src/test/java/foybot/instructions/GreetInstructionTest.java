package foybot.instructions;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import foybot.io.Ui;
import foybot.tools.TaskList;

public class GreetInstructionTest {

    @Test
    public void execute_displaysGreeting() {
        GreetInstruction instruction = new GreetInstruction();
        Ui ui = new Ui();

        instruction.execute(new TaskList(), ui);

        String output = ui.consumeOutput();
        assertTrue(output.contains("FoyBot"));
        assertTrue(output.contains("How may I help you today?"));
    }
}
