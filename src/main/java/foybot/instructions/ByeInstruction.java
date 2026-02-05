package foybot.instructions;

import foybot.io.Ui;
import foybot.tools.TaskList;

public class ByeInstruction extends Instruction {
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
