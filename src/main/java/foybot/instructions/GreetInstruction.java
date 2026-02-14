package foybot.instructions;

import foybot.io.Ui;
import foybot.tools.TaskList;

/**
 * Represents an instruction that responds to user greetings.
 */
public class GreetInstruction extends Instruction {
    /**
     * {@inheritDoc} <p>
     * Displays a friendly greeting and asks how it can help.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.showMessage("Hey there, legend. FoyBot reporting for duty.");
        ui.showMessage("How may I help you today?");
    }
}
