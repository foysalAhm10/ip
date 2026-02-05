package foybot.instructions;

import foybot.io.Ui;
import foybot.tools.TaskList;

public class ListInstruction extends Instruction {
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.showLine();
        if (tasks.size() < 1) {
            ui.showMessage("    No tasks in the list currently.");
        } else {
            System.out.println("    Here are the tasks in your list:");
            ui.showList(tasks);
        }
        ui.showLine();
    }
}
