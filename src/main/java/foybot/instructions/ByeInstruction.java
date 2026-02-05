package foybot.instructions;

import foybot.io.Ui;
import foybot.tools.TaskList;

/**
 * Represents the instruction that ends the program.
 */
public class ByeInstruction extends Instruction {
    /**
     * {@inheritDoc} <p>
     * Displays a farewell message to the user.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.showBye();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
