package foybot.instructions;

import foybot.io.FoyBotOutput;

import foybot.tools.TaskList;

public class ByeInstruction extends Instruction {
    @Override
    public void execute(TaskList tasks, FoyBotOutput output) {
        output.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
