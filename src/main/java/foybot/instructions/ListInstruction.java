package foybot.instructions;

import foybot.io.FoyBotOutput;

import foybot.tools.TaskList;

public class ListInstruction extends Instruction {
    @Override
    public void execute(TaskList tasks, FoyBotOutput output) {
        output.showLine();
        if (tasks.size() < 1) {
            output.showMessage("    No tasks in the list currently.");
        } else {
            output.showList(tasks);
        }
        output.showLine();
    }
}
