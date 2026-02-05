package foybot.instructions;

import foybot.io.Ui;

import foybot.tools.TaskList;

/**
 * Represents an instruction that displays all tasks in the task list.
 */
public class ListInstruction extends Instruction {
    /**
     * {@inheritDoc} <p>
     * Displays the tasks currently in the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.showLine();
        if (tasks.size() < 1) {
            ui.showMessage("    No tasks in the list currently.");
        } else {
            ui.showList(tasks);
        }
        ui.showLine();
    }
}
